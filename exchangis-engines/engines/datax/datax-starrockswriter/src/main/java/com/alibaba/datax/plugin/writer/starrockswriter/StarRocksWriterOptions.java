package com.alibaba.datax.plugin.writer.starrockswriter;

import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.common.util.Configuration;
import com.alibaba.datax.plugin.rdbms.util.DBUtilErrorCode;
import com.webank.wedatasphere.exchangis.datax.common.CryptoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StarRocksWriterOptions implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(StarRocksWriterOptions.class);

    private static final long serialVersionUID = 1l;
    private static final long KILO_BYTES_SCALE = 1024l;
    private static final long MEGA_BYTES_SCALE = KILO_BYTES_SCALE * KILO_BYTES_SCALE;
    private static final int MAX_RETRIES = 1;
    private static final int BATCH_ROWS = 500000;
    private static final long BATCH_BYTES = 5 * MEGA_BYTES_SCALE;
    private static final long FLUSH_INTERVAL = 300000;

    private static final String KEY_LOAD_PROPS_FORMAT = "format";
    public enum StreamLoadFormat {
        CSV, JSON;
    }

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_DATABASE = "database";
    public static final String KEY_SELECTED_DATABASE = "selectedDatabase";
    public static final String KEY_TABLE = "table";
    public static final String KEY_COLUMN = "column";
    public static final String KEY_PRE_SQL = "preSql";
    public static final String KEY_POST_SQL = "postSql";
    public static final String KEY_JDBC_URL = "jdbcUrl";
    public static final String KEY_HOST = "host";
    public static final String KEY_PORT = "port";
    public static final String KEY_HTTP_PORT = "httpPort";
    public static final String KEY_LABEL_PREFIX = "labelPrefix";
    public static final String KEY_MAX_BATCH_ROWS = "maxBatchRows";
    public static final String KEY_MAX_BATCH_SIZE = "maxBatchSize";
    public static final String KEY_FLUSH_INTERVAL = "flushInterval";
    public static final String KEY_LOAD_URL = "loadUrl";
    public static final String KEY_FLUSH_QUEUE_LENGTH = "flushQueueLength";
    public static final String KEY_LOAD_PROPS = "loadProps";
    public static final String CONNECTION_JDBC_URL = "connection[0].jdbcUrl";
    public static final String CONNECTION_HOST = "connection[0].host";
    public static final String CONNECTION_PORT = "connection[0].port";
    public static final String CONNECTION_HTTP_PORT = "connection[0].httpPort";
    public static final String CONNECTION_TABLE_NAME = "connection[0].table[0]";
    public static final String CONNECTION_SELECTED_DATABASE = "connection[0].selectedDatabase";

    private final Configuration options;
    private List<String> infoCchemaColumns;
    private List<String> userSetColumns;
    private boolean isWildcardColumn;

    public StarRocksWriterOptions(Configuration options) {
        this.options = options;
        // database
        String database = this.options.getString(KEY_DATABASE);
        if (StringUtils.isNotBlank(database)) {
            this.options.set(KEY_DATABASE, database);
        }
        // jdbcUrl
        String jdbcUrl = null;
        String host = this.options.getString(CONNECTION_HOST);
        String port = this.options.getString(CONNECTION_PORT);
        if (StringUtils.isNotBlank(host) && StringUtils.isNotBlank(port)) {
            jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/";
            this.options.set(KEY_JDBC_URL, jdbcUrl);
        }
        // table
        String table = this.options.getString(CONNECTION_TABLE_NAME);
        if (StringUtils.isNotBlank(table)) {
            this.options.set(KEY_TABLE, table);
        }
        // column
        List<Map> keyColumns = this.options.getList(KEY_COLUMN, Map.class);
        if (Objects.nonNull(keyColumns) && keyColumns.size() > 0) {
            this.userSetColumns = keyColumns.stream()
                    .map(map -> String.valueOf(map.getOrDefault("name", "")))
                    .map(name -> name.replace("`", ""))
                    .collect(Collectors.toList());
        }
    }

    public void doPretreatment() {
        validateRequired();
        validateStreamLoadUrl();
    }

    public String getJdbcUrl() {
        return options.getString(KEY_JDBC_URL);
    }

    public String getDatabase() {
        return options.getString(KEY_DATABASE);
    }

    public String getTable() {
        return options.getString(KEY_TABLE);
    }

    public String getUsername() {
        return options.getString(KEY_USERNAME);
    }

    public String getPassword() {
        if(StringUtils.isNotBlank(options.getString(KEY_PASSWORD))) {
            try {
                return (String) CryptoUtils.string2Object(options.getString(KEY_PASSWORD));
            } catch (Exception e) {
                throw DataXException.asDataXException(DBUtilErrorCode.CONF_ERROR,
                        "Decrypt password failed.");
            }
        }
        return "";
    }

    public String getLabelPrefix() {
        return options.getString(KEY_LABEL_PREFIX);
    }

    public List<String> getLoadUrlList() {
        return options.getList(KEY_LOAD_URL, String.class);
    }

    public List<String> getColumns() {
        if (isWildcardColumn) {
            return this.infoCchemaColumns;
        }
        return this.userSetColumns;
    }

    public boolean isWildcardColumn() {
        return this.isWildcardColumn;
    }

    public void setInfoCchemaColumns(List<String> cols) {
        this.infoCchemaColumns = cols;
    }

    public List<String> getPreSqlList() {
        return options.getList(KEY_PRE_SQL, String.class);
    }

    public List<String> getPostSqlList() {
        return options.getList(KEY_POST_SQL, String.class);
    }

    public Map<String, Object> getLoadProps() {
        return options.getMap(KEY_LOAD_PROPS);
    }

    public int getMaxRetries() {
        return MAX_RETRIES;
    }

    public int getBatchRows() {
        Integer rows = options.getInt(KEY_MAX_BATCH_ROWS);
        return null == rows ? BATCH_ROWS : rows;
    }

    public long getBatchSize() {
        Long size = options.getLong(KEY_MAX_BATCH_SIZE);
        return null == size ? BATCH_BYTES : size;
    }

    public long getFlushInterval() {
        Long interval = options.getLong(KEY_FLUSH_INTERVAL);
        return null == interval ? FLUSH_INTERVAL : interval;
    }

    public int getFlushQueueLength() {
        Integer len = options.getInt(KEY_FLUSH_QUEUE_LENGTH);
        return null == len ? 1 : len;
    }

    public StreamLoadFormat getStreamLoadFormat() {
        Map<String, Object> loadProps = getLoadProps();
        if (null == loadProps) {
            return StreamLoadFormat.CSV;
        }
        if (loadProps.containsKey(KEY_LOAD_PROPS_FORMAT)
                && StreamLoadFormat.JSON.name().equalsIgnoreCase(String.valueOf(loadProps.get(KEY_LOAD_PROPS_FORMAT)))) {
            return StreamLoadFormat.JSON;
        }
        return StreamLoadFormat.CSV;
    }

    private void validateStreamLoadUrl() {
        List<String> urlList = getLoadUrlList();
        for (String host : urlList) {
            if (host.split(":").length < 2) {
                throw DataXException.asDataXException(DBUtilErrorCode.CONF_ERROR,
                        "The format of loadUrl is illegal, please input `fe_ip:fe_http_ip;fe_ip:fe_http_ip`.");
            }
        }
    }

    private void validateRequired() {
        final String[] requiredOptionKeys = new String[]{
                KEY_USERNAME,
                KEY_DATABASE,
                KEY_TABLE,
                KEY_COLUMN,
                KEY_LOAD_URL
        };
        for (String optionKey : requiredOptionKeys) {
            options.getNecessaryValue(optionKey, DBUtilErrorCode.REQUIRED_VALUE);
        }
    }

    @Override
    public String toString() {
        return "StarRocksWriterOptions{" +
                "options=" + options +
                ", infoCchemaColumns=" + infoCchemaColumns +
                ", userSetColumns=" + userSetColumns +
                ", isWildcardColumn=" + isWildcardColumn +
                '}';
    }
}

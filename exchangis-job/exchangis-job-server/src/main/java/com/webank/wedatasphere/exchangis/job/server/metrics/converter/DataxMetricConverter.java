package com.webank.wedatasphere.exchangis.job.server.metrics.converter;

import com.webank.wedatasphere.exchangis.job.server.metrics.ExchangisMetricsVo;
import com.webank.wedatasphere.exchangis.common.util.json.JsonEntity;
import com.webank.wedatasphere.exchangis.job.utils.MemUtils;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;


/**
 * Convert the metrics in datax engine
 */
public class DataxMetricConverter extends AbstractMetricConverter implements AbstractMetricConverter.MetricsParser{

    @Override
    public String resourceUsedKey() {
        return "";
    }

    @Override
    public String trafficKey() {
        return "";
    }

    @Override
    public String indicatorKey() {
        return "";
    }

    @Override
    protected AbstractMetricConverter.MetricsParser getParser() {
        return this;
    }

    @Override
    public ExchangisMetricsVo.ResourceUsed parseResourceUsed(String key, JsonEntity rawValue) {
        ExchangisMetricsVo.ResourceUsed resourceUsed = new ExchangisMetricsVo.ResourceUsed();
        String nodeResource = rawValue.getString("NodeResourceJson");
        if (StringUtils.isNotBlank(nodeResource)){
            JsonEntity nodeResourceJson = JsonEntity.from(nodeResource);
            String memoryUnit = nodeResourceJson.getString("memory");
            if (StringUtils.isNotBlank(memoryUnit)){
                String[] memory = memoryUnit.split(" ");
                resourceUsed.setMemory(memory.length >= 2 ?
                        MemUtils.convertToMB((long) Double.parseDouble(memory[0]), memory[1]) : (long) Double.parseDouble(memory[0]));
            }
            String cpuVCores = nodeResourceJson.getString("cpu");
            if (StringUtils.isNotBlank(cpuVCores)){
                resourceUsed.setCpu(Integer.parseInt(cpuVCores));
            }
        }
        return resourceUsed;
    }

    @Override
    public ExchangisMetricsVo.Traffic parseTraffic(String key, JsonEntity rawValue) {
        ExchangisMetricsVo.Traffic traffic = new ExchangisMetricsVo.Traffic();
        Double speed = Optional.ofNullable(rawValue.getDouble("recordSpeedPerSecond")).orElse(
                rawValue.getDouble("recordSpeed")
        );
        if (Objects.nonNull(speed)){
            traffic.setFlow(new BigDecimal(speed).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        return traffic;
    }

    @Override
    public ExchangisMetricsVo.Indicator parseIndicator(String key, JsonEntity rawValue) {
        ExchangisMetricsVo.Indicator indicator = new ExchangisMetricsVo.Indicator();
        long readSuccess = Optional.ofNullable(rawValue.getLong("readSucceedRecords")).orElse(0L);
        long readFail = Optional.ofNullable(rawValue.getLong("readFailedRecords")).orElse(0L);
        indicator.setExchangedRecords(readSuccess + readFail);
        long writeFail = Optional.ofNullable(rawValue.getLong("writeFailedRecords")).orElse(0L);
        indicator.setErrorRecords(readFail + writeFail);
        return indicator;
    }

}

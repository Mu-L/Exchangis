-- 插入 job_param_config 记录
INSERT INTO `exchangis_job_param_config` (`id`, `config_key`, `config_name`, `config_direction`, `type`, `ui_type`, `ui_field`, `ui_label`, `unit`, `required`, `value_type`, `value_range`, `default_value`, `validate_type`, `validate_range`, `validate_msg`, `is_hidden`, `is_advanced`, `source`, `level`, `treename`, `sort`, `description`, `status`)
VALUES
(1,'exchangis.datax.setting.speed.bytes','作业速率限制','','DATAX','INPUT','exchangis.datax.setting.speed.bytes','作业速率限制','Mb/s',b'1','NUMBER','','','REGEX','^[1-9]\\d*$','作业速率限制输入错误',b'0',b'0','', 1,'',1,'',1),
(2,'exchangis.datax.setting.speed.records','作业记录数限制','','DATAX','INPUT','exchangis.datax.setting.speed.records','作业记录数限制','条/s',b'1','NUMBER','','','REGEX','^[1-9]\\d*$','作业记录数限制输入错误',b'0',b'0','', 1,'',2,'',1),
(3,'exchangis.datax.setting.max.parallelism','作业最大并行度','','DATAX','INPUT','exchangis.datax.setting.max.parallelism','作业最大并行度','个',b'1','NUMBER','','1','REGEX','^[1-9]\\d*$','作业最大并行度输入错误',b'0',b'0','', 1,'',3,'',1),
(4,'exchangis.datax.setting.max.memory','作业最大使用内存','','DATAX','INPUT','exchangis.datax.setting.max.memory','作业最大使用内存','Mb',b'1','NUMBER','','1000','REGEX','^[1-9]\\d*$','作业最大使用内存输入错误',b'0',b'0','', 1,'',4,'',1),
(5,'exchangis.datax.setting.errorlimit.record','最多错误记录数','','DATAX','INPUT','exchangis.datax.setting.errorlimit.record','最多错误记录数','条',b'1','NUMBER','','','REGEX','^[1-9]\\d*$','最多错误记录数输入错误',b'0',b'0','', 1,'',5,'',1),
(6,'exchangis.sqoop.setting.max.parallelism','作业最大并行数','','SQOOP','INPUT','exchangis.sqoop.setting.max.parallelism','作业最大并行数','个',b'1','NUMBER','','1','REGEX','^[1-9]\\d*$','作业最大并行数输入错误',b'0',b'0','', 1,'',1,'',1),
(7,'exchangis.sqoop.setting.max.memory','作业最大内存','','SQOOP','INPUT','exchangis.sqoop.setting.max.memory','作业最大内存','Mb',b'1','NUMBER','','1000','REGEX','^[1-9]\\d*$','作业最大内存输入错误',b'0',b'0','', 1,'',2,'',1),
(8,'exchangis.job.ds.params.datax.mysql.r.join_condition','连接条件','DATAX-SOURCE','MYSQL','INPUT','exchangis.job.ds.params.datax.mysql.r.join_condition','连接条件','',b'0','VARCHAR','','','','','连接条件输入错误',b'0',b'0','', 1,'',1,'',1),
(9,'exchangis.job.ds.params.datax.mysql.r.where_condition','WHERE条件','DATAX-SOURCE','MYSQL','INPUT','exchangis.job.ds.params.datax.mysql.r.where_condition','WHERE条件','',b'0','VARCHAR','','','','','WHERE条件输入错误',b'0',b'0','', 1,'',2,'',1),
(10,'exchangis.job.ds.params.datax.hive.r.trans_proto','传输方式','DATAX-SOURCE','HIVE','OPTION','exchangis.job.ds.params.datax.hive.r.trans_proto','传输方式','',b'1','OPTION','[\"记录\",\"二进制\"]','记录','','','传输方式输入错误',b'0',b'0','', 1,'',1,'',1),
(11,'exchangis.job.ds.params.datax.hive.r.partition','分区信息','DATAX-SOURCE','HIVE','INPUT','exchangis.job.ds.params.datax.hive.r.partition','分区信息','',b'0','VARCHAR','','','','','分区信息输入错误',b'0',b'0','', 1,'',2,'',1),
(12,'exchangis.job.ds.params.datax.hive.r.row_format','字段格式','DATAX-SOURCE','HIVE','INPUT','exchangis.job.ds.params.datax.hive.r.row_format','字段格式','',b'0','VARCHAR','','','','','字段格式输入错误',b'0',b'0','', 1,'',3,'',1),
(13,'exchangis.job.ds.params.datax.mysql.w.write_type','写入方式','DATAX-SINK','MYSQL','OPTION','exchangis.job.ds.params.datax.mysql.w.write_type','写入方式','',b'1','OPTION','[\"INSERT\",\"REPLACE\",\"UPDATE\"]','INSERT','','','写入方式输入错误',b'0',b'0','', 1,'',1,'',1),
(14,'exchangis.job.ds.params.datax.mysql.w.batch_size','批量大小','DATAX-SINK','MYSQL','INPUT','exchangis.job.ds.params.datax.mysql.w.batch_size','批量大小','',b'0','NUMBER','','1000','REGEX','^[1-9]\\d*$','批量大小输入错误',b'0',b'0','', 1,'',2,'',1),
(16,'exchangis.job.ds.params.datax.hive.w.partition','分区信息','DATAX-SINK','HIVE','INPUT','exchangis.job.ds.params.datax.hive.w.partition','分区信息','',b'0','VARCHAR','','','','','分股信息输入错误',b'0',b'0','', 1,'',2,'',1),
(17,'exchangis.job.ds.params.datax.hive.w.row_format','字段格式','DATAX-SINK','HIVE','INPUT','exchangis.job.ds.params.datax.hive.w.row_format','字段格式','',b'0','VARCHAR','','','','','字段格式输入错误',b'0',b'0','', 1,'',3,'',1),
(18,'exchangis.job.ds.params.datax.hive.w.write_type','写入策略','DATAX-SINK','HIVE','OPTION','exchangis.job.ds.params.datax.hive.w.write_type','写入策略','',b'1','OPTION','[\"清空目录\",\"追加数据\"]','清空目录','','','写入策略输入错误',b'0',b'0','', 1,'',4,'',1),
(19,'exchangis.job.ds.params.datax.hive.w.sync_meta','同步元数据','DATAX-SINK','HIVE','OPTION','exchangis.job.ds.params.datax.hive.w.sync_meta','同步元数据','',b'1','OPTION','[\"是\",\"否\"]','是','','','同步元数据输入错误',b'0',b'0','', 1,'',5,'',1),
(20,'exchangis.job.ds.params.sqoop.mysql.r.join_condition','连接条件','SQOOP-SOURCE','MYSQL','INPUT','exchangis.job.ds.params.sqoop.mysql.r.join_condition','连接条件','',b'0','VARCHAR','','','','','连接条件输入错误',b'0',b'0','', 1,'',1,'',1),
(21,'exchangis.job.ds.params.sqoop.mysql.r.where_condition','WHERE条件','SQOOP-SOURCE','MYSQL','INPUT','exchangis.job.ds.params.sqoop.mysql.r.where_condition','WHERE条件','',b'0','VARCHAR','','','','','WHERE条件输入错误',b'0',b'0','', 1,'',2,'',1),
(22,'exchangis.job.ds.params.sqoop.mysql.w.write_type','写入方式','SQOOP-SINK','MYSQL','OPTION','exchangis.job.ds.params.sqoop.mysql.w.write_type','写入方式','',b'1','OPTION','[\"UPDATEONLY\",\"ALLOWINSERT\"]','UPDATEONLY','','','写入方式输入错误',b'0',b'0','', 1,'',1,'',1),
(23,'exchangis.job.ds.params.sqoop.mysql.w.batch_size','批量大小','SQOOP-SINK','MYSQL','INPUT','exchangis.job.ds.params.sqoop.mysql.w.batch_size','批量大小','',b'0','NUMBER','','1000','REGEX','^[1-9]\\d*$','批量大小输入错误',b'0',b'0','', 1,'',2,'',1),
(25,'exchangis.job.ds.params.sqoop.hive.r.partition','分区信息','SQOOP-SOURCE','HIVE','INPUT','exchangis.job.ds.params.sqoop.hive.r.partition','分区信息','',b'0','VARCHAR','','','','','分区信息输入错误',b'0',b'0','/api/rest_j/v1/exchangis/job/partitionInfo?dataSourceType=&dbname&table=', 1,'',2,'',1),
(26,'exchangis.job.ds.params.sqoop.hive.r.row_format','字段格式','SQOOP-SOURCE','HIVE','INPUT','exchangis.job.ds.params.sqoop.hive.r.row_format','字段格式','',b'0','VARCHAR','','','','','字段格式输入错误',b'0',b'0','', 1,'',3,'',1),
(28,'exchangis.job.ds.params.sqoop.hive.w.partition','分区信息','SQOOP-SINK','HIVE','INPUT','exchangis.job.ds.params.sqoop.hive.w.partition','分区信息','',b'0','VARCHAR','','','','','分区信息输入错误',b'0',b'0','', 1,'',2,'',1),
(29,'exchangis.job.ds.params.sqoop.hive.w.row_format','字段格式','SQOOP-SINK','HIVE','INPUT','exchangis.job.ds.params.sqoop.hive.w.row_format','字段格式','',b'0','VARCHAR','','','','','字段格式输入错误',b'0',b'0','', 1,'',3,'',1);
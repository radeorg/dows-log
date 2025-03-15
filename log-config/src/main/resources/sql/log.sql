
-- 若库不存在创建一个
CREATE DATABASE IF NOT EXISTS `dows_log`;
USE `dows_log`;

drop table if exists `log_setting`;
CREATE TABLE IF NOT EXISTS `log_setting`(
    `log_setting_id` bigint(19) NOT NULL COMMENT '日志配置ID',
    `app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
    `database_name` varchar(64) DEFAULT NULL COMMENT '数据库',
    `database_json` varchar(64) DEFAULT NULL COMMENT '数据库配置',
    `ver` integer(2) DEFAULT NULL COMMENT '数据版本号',
    `deleted` tinyint(4) DEFAULT NULL COMMENT '是否逻辑删',
    `dt` datetime DEFAULT NULL COMMENT '',
    PRIMARY KEY (`log_setting_id`) 
) ENGINE=InnoDB COMMENT='日志配置';

drop table if exists `log_table`;
CREATE TABLE IF NOT EXISTS `log_table`(
    `log_table_id` bigint(19) NOT NULL COMMENT '日志表ID',
    `log_setting_id` bigint(19) DEFAULT NULL COMMENT '日志配置ID',
    `app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
    `table_name` varchar(64) DEFAULT NULL COMMENT '表名',
    `ver` integer(2) DEFAULT NULL COMMENT '数据版本号',
    `deleted` tinyint(4) DEFAULT NULL COMMENT '是否逻辑删',
    `dt` datetime DEFAULT NULL COMMENT '',
    PRIMARY KEY (`log_table_id`) 
) ENGINE=InnoDB COMMENT='日志表';

drop table if exists `log_column`;
CREATE TABLE IF NOT EXISTS `log_column`(
    `log_column_id` bigint(19) NOT NULL COMMENT '日志列ID',
    `log_table_id` bigint(19) DEFAULT NULL COMMENT '日志表ID',
    `app_id` varchar(64) DEFAULT NULL COMMENT '应用ID',
    `column_json` varchar(64) DEFAULT NULL COMMENT '列JSON',
    `ver` integer(2) DEFAULT NULL COMMENT '数据版本号',
    `deleted` tinyint(4) DEFAULT NULL COMMENT '是否逻辑删',
    `dt` datetime DEFAULT NULL COMMENT '',
    PRIMARY KEY (`log_column_id`) 
) ENGINE=InnoDB COMMENT='日志列';



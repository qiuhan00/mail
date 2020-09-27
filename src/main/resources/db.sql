
CREATE TABLE `oa_email` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `receive_email` varchar(500) NOT NULL COMMENT '接收人邮箱(多个逗号分开)',
  `subject` varchar(100) NOT NULL COMMENT '主题',
  `content` text NOT NULL COMMENT '发送内容',
  `template` varchar(100) DEFAULT NULL COMMENT '模板',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

CREATE TABLE `oa_sms` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `mobile` varchar(500) NOT NULL,
  `code` varchar(100) NOT NULL,
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

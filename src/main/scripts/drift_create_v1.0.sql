SET FOREIGN_KEY_CHECKS=0;


DROP DATABASE `drift`;

-- ----------------------------
-- Database structure for `drift`
-- ----------------------------
CREATE DATABASE `drift` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE drift;

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(20) DEFAULT NULL COMMENT '发送方',
  `to_user_id` bigint(20) DEFAULT NULL COMMENT '到达方',
  `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送时间',
  `last_bottle` bigint(20) NULL COMMENT '回复上次的瓶子的ID',
  `type` int(4) NULL COMMENT '类型',
  `content` varchar(255) NOT NULL,
  `result` varchar(255) NOT NULL DEFAULT '返回结果',
  `is_received` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为无人接收;1为有人接收',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;




-- -----------------------------------------------
-- Table structure for `wechat_message` 信息
-- -----------------------------------------------
DROP TABLE if exists `wechat_message`;
CREATE TABLE `wechat_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg_id` varchar(50) DEFAULT NULL COMMENT '微信msgid',
  `to_user_name` varchar(50) DEFAULT NULL COMMENT '微信接受方',
  `from_user_name` varchar(50) DEFAULT NULL COMMENT '微信发送方',
  `request_time` timestamp NOT NULL DEFAULT '2000-01-01 00:00:00' COMMENT '创建时间',
  `msg_type` int(2) DEFAULT 1 COMMENT '微信类型',
  `event_type` int(2) DEFAULT 0 COMMENT '事件类型',
  `content` varchar(200) DEFAULT NULL COMMENT '截取后内容',
  `raw_content` varchar(500) DEFAULT NULL COMMENT '原内容',
  `result` text DEFAULT NULL COMMENT '返回结果',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP  COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '用户昵称',
  `wx_user_name` varchar(200) NOT NULL,
  `sex` varchar(4) DEFAULT '美女' COMMENT '性别',
  `age` int(3) NOT NULL DEFAULT '0' COMMENT '年龄',
  `city` varchar(10) DEFAULT NULL COMMENT '所在城市',
  `is_valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0为失效;1为有效',
  `is_robot` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为用户;1为机器人',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
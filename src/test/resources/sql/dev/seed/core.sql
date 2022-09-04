SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `is_admin` int DEFAULT 1 COMMENT '是否为admin账号',
  `enabled` int DEFAULT 1 COMMENT '状态：1启用、0禁用',
  `pwd_reset_time` datetime DEFAULT NULL COMMENT '修改密码的时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user`(`user_id`, `username`,`nick_name`, `gender`, `phone`, `email`, `password`, `is_admin`, `enabled`, `pwd_reset_time`) VALUES (1, 'admin', '管理员', '男', '18888888888', '11111@qq.com', '123456', 1, 1, '2020-05-03 16:38:31');
INSERT INTO `sys_user`(`user_id`, `username`,`nick_name`, `gender`, `phone`, `email`, `password`, `is_admin`, `enabled`, `pwd_reset_time`) VALUES (2, 'test01', '测试1', '男', '18888888888', '2222@qq.com', '123456', 1, 1, '2020-05-03 16:38:31');
INSERT INTO `sys_user`(`user_id`, `username`,`nick_name`, `gender`, `phone`, `email`, `password`, `is_admin`, `enabled`, `pwd_reset_time`) VALUES (3, 'test02', '测试2', '男', '18888888888', '3333@qq.com', '123456', 1, 1, NULL);
INSERT INTO `sys_user`(`user_id`, `username`,`nick_name`, `gender`, `phone`, `email`, `password`, `is_admin`, `enabled`, `pwd_reset_time`) VALUES (4, 'test03', '测试3', '男', '18888888888', '4444@qq.com', '123456', 1, 1, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order` (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `name` varchar(255) NOT NULL COMMENT '订单名称',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '价格',
  `owner_id` bigint NOT NULL COMMENT '归属人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_order
-- ----------------------------
BEGIN;
INSERT INTO `sys_order` VALUES (1, '羽绒服', 4.60, 1, '2022-09-04 13:32:08', '2022-09-04 13:34:35');
INSERT INTO `sys_order` VALUES (2, '裤子', 6.44, 2, '2022-09-04 13:32:35', '2022-09-04 13:34:25');
INSERT INTO `sys_order` VALUES (3, '耐克鞋子', 3.50, 2, '2022-09-04 13:33:06', '2022-09-04 13:34:31');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
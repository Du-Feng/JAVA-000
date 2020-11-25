CREATE DATABASE IF NOT EXISTS `ecommerce` DEFAULT CHARACTER  SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `ecommerce`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名字',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `phone` char(20) DEFAULT NULL COMMENT '用户手机',
  `address` varchar(200) DEFAULT NULL COMMENT '收货地址',
  `role` int(4) NOT NULL COMMENT '角色0-买家,1-卖家',  
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone_index` (`phone`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '商品ID',
  `name` varchar(32) DEFAULT NULL COMMENT '商品名字',
  `stock` varchar(16) DEFAULT NULL COMMENT '库存数量',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `category_id` varchar(16) DEFAULT NULL COMMENT '商品分类',
  `description` varchar(64) DEFAULT NULL COMMENT '商品描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `create_time_index` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '订单ID',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `product_id` varchar(16) DEFAULT NULL COMMENT '商品id',
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付,2-微信支付，3-支付宝支付，4-货到付款',
  `freight` int(10) DEFAULT NULL COMMENT '运费',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-初始状态，1-未付款，2-已付款，4-已发货，5-交易成功，6-交易关闭，7-已取消',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `create_time_index` (`create_time`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
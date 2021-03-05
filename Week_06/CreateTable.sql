CREATE TABLE order_detail ( 
		id int(32) primary key not null auto_increment,
		optimistic bigint(64) not null,
		product_no varchar(64) not null,
		customer_no varchar(64) not null, 
		order_status varchar(32) default null,
		order_type varchar(32) default null, 
		order_time datetime default null,
		order_pay_time datetime default null, 
		order_complete_time datetime default null, 
		order_count int(64) default null,
		order_amount double(32,2) default null,
		create_time TIMESTAMP not null,
		update_time TIMESTAMP
		)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE customer_info (
		id int(32) primary key not null auto_increment,
		optimistic bigint(64) not null,
		customer_no varchar(64) not null, 
		customer_type varchar(32) not null,  
		customer_status varchar(32) not null, 
		customer_nick_name varchar(128) not null, 
		customer_create_time TIMESTAMP not null,
		create_time TIMESTAMP not null,
		update_time TIMESTAMP
		)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE product_info( 
		id int(32) primary key not null auto_increment,
		optimistic bigint(64) not null,
		product_no varchar(64) not null,
		product_name varchar(128) not null, 
		product_price double(32,2) default null,
		product_status varchar(32) not null, 
		create_time TIMESTAMP not null,
		update_time TIMESTAMP
		)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table if exists coffee CASCADE;

create table coffee (
    id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255),
	price DECIMAL(19,2),
	create_time timestamp,
	update_time timestamp
);

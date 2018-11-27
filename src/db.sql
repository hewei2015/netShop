create database day23;
		use day23;
		create table category(
			id varchar(100) primary key,
			name varchar(100) not null unique,
			description varchar(255)
		);
		create table book(
			id varchar(100) primary key,
			name varchar(100) not null,
			author varchar(100),
			price float(8,2),
			image varchar(100),
			description varchar(255),
			category_id varchar(100),
			constraint category_id_fk foreign key(category_id) references category(id)
		);
		create table user(
			id varchar(100) primary key,
			username varchar(100) not null unique,
			password varchar(100),
			cellphone varchar(20),
			mobilephone varchar(20),
			address varchar(255),
			email varchar(100) not null unique
		);
		
		create table orders(
			id varchar(100) primary key,
			ordernum varchar(100) not null unique,
			num int,
			price float(8,2),
			user_id varchar(100),
			constraint user_id_fk foreign key(user_id) references user(id)
		);
		create table ordersitem(
			id varchar(100) primary key,
			num int,
			price float(8,2),
			orders_id varchar(100),
			book_id varchar(100),
			constraint orders_id_fk foreign key(orders_id) references orders(id),
			constraint book_id_fk foreign key(book_id) references book(id)
		);
		alter table orders add state int default 0;
## 查看博客
- [最简单的小型商城实现：Serlvet+Jsp经典案例](https://blog.csdn.net/heweirun_2014/article/details/45293913)

## 数据库
```sql
create database netshop;
use netshop;
create table category(
    id varchar(100) primary key,
    name varchar(100) not null unique,
    description varchar(255)
);
CREATE TABLE book(
    id VARCHAR(100) PRIMARY KEY,
    NAME VARCHAR(100) NOT NULL,
    author VARCHAR(100),
    price FLOAT(8,2),
    image VARCHAR(100),
    description VARCHAR(255),
    category_id VARCHAR(100),
    CONSTRAINT category_id_fk FOREIGN KEY(category_id) REFERENCES category(id)
);
 CREATE TABLE `user`(
    id VARCHAR(100) PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    PASSWORD VARCHAR(100),
    cellphone VARCHAR(100),
    mobilepbone VARCHAR(100),
    address VARCHAR(100),
    email VARCHAR(100)
 );
```
-----
- 打赏一下呗
<img src="http://www.52vedio.com:8080/img/wx.jpg" width="256px" height="256px" />
<img src="http://www.52vedio.com:8080/img/jpg.jpg" width="256px" height="256px" />


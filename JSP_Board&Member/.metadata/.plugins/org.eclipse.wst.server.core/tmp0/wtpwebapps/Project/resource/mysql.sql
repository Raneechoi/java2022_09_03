-- 2022-11-30(수)
create table member2(
email varchar(100) not null,
pwd varchar(100) not null,
nick_name varchar(100) not null,
reg_at datetime default current_timestamp,
last_login datetime,
primary key(email));

-- 2022-12-02(금)
create table comment(
cno int not null auto_increment,
bno int default -1,
writer varchar(100) default "unknown",
content varchar(1000) not null,
reg_at datetime default current_timestamp,
primary key(cno));



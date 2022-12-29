mysql -ujavauser -pmysql

create table product(
pno int auto_increment,
pname varchar(100) not null,
price int not null,
regdate datetime default now(),
madeby varchar(50) not null,
primary key(pno));
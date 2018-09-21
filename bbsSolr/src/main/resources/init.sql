
create database if not exists bbs_solr;
use bbs_solr;
create table if not exists product_info(
product_id int primary key auto_increment,
product_name varchar(100) not null,
product_description varchar(2000));

insert into product_info (product_name,product_description)values('长虹电视','长虹电视');
insert into product_info (product_name,product_description)values('TCL电视','TCL电视');
insert into product_info (product_name,product_description)values('格力空调','格力空调');
insert into product_info (product_name,product_description)values('海尔空调','海尔空调');
 
set search_path to diary;  
  
create sequence seq_m_user start with 1 INCREMENT by 1;  
CREATE TABLE m_user (   
id bigint NOT NULL DEFAULT nextval('seq_m_user'),
email character varying(40)  NOT NULL unique,
user_type char(2) Not Null DEFAULT 'UR',
password character varying(255)  NOT NULL,
first_name character varying(40)  NOT NULL,
middle_name character varying(40) ,
last_name character varying(40)  NOT NULL,
mobile character varying(40)  NOT NULL,
locked boolean  NULL,  
status boolean NOT NULL,   
enabled boolean NOT NULL DEFAULT FALSE,
modified_by character varying(40)  NOT NULL, 
last_logged timestamp   NULL, 
primary key (id));

create sequence seq_m_ver_token start with 1 INCREMENT by 1;  

create table m_verification_token (id bigint  NOT NULL DEFAULT nextval('seq_m_ver_token'),token character varying(255)  NOT NULL,
user_id bigint  NOT NULL references m_user(id),    expiry_date timestamp ,primary key(id));
--apppassword
 insert into m_user (email,user_type,password,first_name,middle_name,last_name,mobile,locked,status,modified_by)   values
('sysadmin@diary.com','AU','$2a$10$Ph85EuZXxSsDtYOcmEPqKegqtfhAZDB7phubIPYJJnsrfsJgJSd2m','SYSTEM','SUPER','USER','XXXXXX',false,true,'sysadmin@diary.com');

create sequence seq_m_role start with 1 INCREMENT by 1;  

CREATE TABLE m_role (
id integer  DEFAULT nextval('seq_m_role') ,  
name character varying(255) NOT  NULL unique,  
super_role boolean default false,
status character varying(1),
primary key (id));


create sequence seq_m_privilege start with 1 INCREMENT by 1;  
CREATE TABLE m_privilege (
id integer  DEFAULT nextval('seq_m_privilege'), 
name character varying(255)  NOT  NULL unique,  
status character varying(1),
primary key (id));

CREATE TABLE map_user_roles (
user_id bigint  NOT NULL references m_user(id),    
role_id integer  NOT NULL references m_role(id),  
primary key (user_id,role_id));

CREATE TABLE map_roles_privileges (
role_id integer  NOT NULL references m_role(id), 
priv_id integer  NOT NULL references m_privilege(id), 
primary key (priv_id,role_id));

insert into m_role (id,name,status,super_role) values (1,'ROLE_SUPER_SYS_USER','A',True);
insert into m_role (id,name,status,super_role) values (2,'ROLE_APPADMIN','A',False);
insert into m_role (id,name,status,super_role) values (3,'ROLE_CUSTOMER','A',False);



insert into m_privilege values (1,'ROLE_EMPLOYEE_MANAGE');
insert into m_privilege values (2,'ROLE_USER_MANAGE');
insert into m_privilege values (3,'ROLE_TASK_MANAGE');
insert into m_privilege values (4,'ROLE_TASKASSIGNMENT_MANAGE');
insert into m_privilege values (5,'ROLE_TIMESHEET_MANAGE');
insert into m_privilege values (6,'ROLE_CUSTOMER_MANAGE');


insert into map_user_roles values(1,1);




insert into map_roles_privileges values(1,1);
insert into map_roles_privileges values(1,2);
insert into map_roles_privileges values(1,3);
insert into map_roles_privileges values(1,4);
insert into map_roles_privileges values(1,5);
insert into map_roles_privileges values(1,6);

create table persistent_logins (username varchar(64) not null, 
series varchar(64) primary key, 
token varchar(64) not null, last_used timestamp not null);


create sequence seq_t_diary start with 1 INCREMENT by 1;  

CREATE TABLE t_diary (
id bigint  DEFAULT nextval('seq_t_diary'), 
user_id bigint references m_user(id), 
subject character varying(255)  NOT  NULL  ,  
doc_date date NOT NULL,
content character varying(255)  NOT  NULL  ,  
 primary key (id));
 
  
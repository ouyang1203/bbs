create database if not exists bbs_oauth2;
use bbs_oauth2;
-- used in tests that use HSQL
create table if not exists oauth_client_details (
client_id VARCHAR(128) PRIMARY KEY,
resource_ids VARCHAR(128),
client_secret VARCHAR(128),
scope VARCHAR(128),
authorized_grant_types VARCHAR(128),
web_server_redirect_uri VARCHAR(128),
authorities VARCHAR(128),
access_token_validity INTEGER,
refresh_token_validity INTEGER,
additional_information VARCHAR(4096),
autoapprove VARCHAR(128)
);
insert into oauth_client_details (client_id,scope,client_secret,authorized_grant_types,web_server_redirect_uri)values ('client','app','secret','authorization_code','https://www.baidu.com');

insert into oauth_client_details (client_id,scope,client_secret,authorized_grant_types,web_server_redirect_uri)values ('client2','app','secret','authorization_code','http://localhost:7777/api-user/user/index');
insert into oauth_client_details (client_id,scope,resource_ids,client_secret,authorized_grant_types,web_server_redirect_uri)values ('news','app','news','new_secret','authorization_code','http://localhost:3000/');
insert into oauth_client_details (client_id,scope,resource_ids,client_secret,authorized_grant_types,web_server_redirect_uri)values ('module','app','module','module_secret','authorization_code','http://localhost:3001/');
insert into oauth_client_details (client_id,scope,resource_ids,client_secret,authorized_grant_types,web_server_redirect_uri)values ('module2','app','module','module_secret','authorization_code','http://192.168.1.56:3001/');
insert into oauth_client_details (client_id,scope,resource_ids,client_secret,authorized_grant_types,web_server_redirect_uri)values ('scf','app','module','scf','authorization_code','http://192.168.1.73:9003/hello?name=33');
insert into oauth_client_details (client_id,scope,resource_ids,client_secret,authorized_grant_types,web_server_redirect_uri)values ('zuul','app','zuul','zuul','authorization_code','http://localhost:7777/');

create table if not exists oauth_client_token (
token_id VARCHAR(128),
token BLOB,
authentication_id VARCHAR(128) PRIMARY KEY,
user_name VARCHAR(128),
client_id VARCHAR(128)
);
create table if not exists oauth_access_token (
token_id VARCHAR(128),
token BLOB,
authentication_id VARCHAR(128) PRIMARY KEY,
user_name VARCHAR(128),
client_id VARCHAR(128),
authentication BLOB,
refresh_token VARCHAR(128)
);
create table if not exists oauth_refresh_token (
token_id VARCHAR(128),
token BLOB,
authentication BLOB
);
create table if not exists oauth_code (
code VARCHAR(128), authentication BLOB
);
create table if not exists oauth_approvals (
userId VARCHAR(128),
clientId VARCHAR(128),
scope VARCHAR(128),
status VARCHAR(10),
expiresAt TIMESTAMP,
lastModifiedAt TIMESTAMP
);
-- customized oauth_client_details table
create table if not exists ClientDetails (
appId VARCHAR(128) PRIMARY KEY,
resourceIds VARCHAR(128),
appSecret VARCHAR(128),
scope VARCHAR(128),
grantTypes VARCHAR(128),
redirectUrl VARCHAR(128),
authorities VARCHAR(128),
access_token_validity INTEGER,
refresh_token_validity INTEGER,
additionalInformation VARCHAR(4096),
autoApproveScopes VARCHAR(128)
);
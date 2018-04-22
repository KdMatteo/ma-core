drop table if exists t_dbsql;

drop table if exists t_device_attr;

drop table if exists t_device_attr_type;

drop table if exists t_device_type;

drop table if exists t_group;

drop table if exists t_host;

drop table if exists t_object;

drop table if exists t_object_device;

drop table if exists t_terminal;

drop table if exists t_terminal_attr;

drop table if exists t_user;

create table t_dbsql
(
   c_id                 int not null auto_increment,
   c_sql_string         text,
   primary key (c_id)
);

create table t_device_attr
(
   c_id                 int not null auto_increment,
   c_device_id          int,
   c_attrtype_id        int,
   primary key (c_id)
);

create table t_device_attr_type
(
   c_id                 int not null auto_increment,
   c_devicetype_id      int,
   c_name               varchar(24),
   c_field_name         varchar(24),
   c_label              varchar(24),
   c_data_type          varchar(8) comment 'int    float      ',
   primary key (c_id)
);

create table t_device_type
(
   c_id                 int not null auto_increment,
   c_name               varchar(24),
   c_table_name         varchar(24),
   c_multi              int,
   primary key (c_id)
);

create table t_group
(
   c_id                 int not null auto_increment,
   c_object_id          int,
   c_name               varchar(24),
   primary key (c_id)
);

create table t_host
(
   c_id                 int not null auto_increment,
   c_ip                 varchar(24),
   c_port               int,
   c_account            varchar(24),
   c_password           varchar(24),
   primary key (c_id)
);

create table t_object
(
   c_id                 int not null auto_increment,
   c_host_id            int,
   c_type_id            int,
   c_database_name      varchar(24),
   c_name               varchar(24),
   c_address            varchar(24),
   c_linkman            varchar(24),
   c_mobile             varchar(11),
   primary key (c_id)
);

create table t_object_device
(
   c_id                 int not null auto_increment,
   c_group_id           int,
   c_object_id          int,
   c_devicetype_id      int,
   c_code               varchar(24),
   c_index              int,
   primary key (c_id)
);

create table t_terminal
(
   c_id                 int not null auto_increment,
   c_object_id          int,
   c_code               varchar(24),
   c_name               varchar(24),
   c_ip                 varchar(24),
   c_port               int,
   c_version            varchar(24),
   c_last_time          datetime,
   primary key (c_id)
);

create table t_terminal_attr
(
   c_id                 int not null auto_increment,
   c_terminal_id        int,
   c_deviceattr_id      int,
   c_plc_address        varchar(24),
   primary key (c_id)
);

create table t_user
(
   c_id                 int not null auto_increment,
   c_account            varchar(24),
   c_password           varchar(24),
   primary key (c_id)
);


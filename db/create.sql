drop table if exists dbsql;

drop table if exists device_attr;

drop table if exists device_attr_type;

drop table if exists device_type;

drop table if exists group;

drop table if exists host;

drop table if exists object;

drop table if exists object_device;

create table dbsql
(
   id                   int not null auto_increment,
   "sql"                text,
   primary key (id)
);

create table device_attr
(
   id                   int not null auto_increment,
   device_id            int,
   attrtype_id          int,
   plc_address          varchar(24),
   primary key (id)
);

create table device_attr_type
(
   id                   int not null auto_increment,
   devicetype_id        int,
   name                 varchar(24),
   field_name           varchar(24),
   "label"              varchar(24),
   data_type            varchar(8) comment 'int    float      ',
   primary key (id)
);

create table device_type
(
   id                   int not null auto_increment,
   name                 varchar(24),
   table_name           varchar(24),
   muliti               int,
   primary key (id)
);

create table group
(
   id                   int not null auto_increment,
   object_id            int,
   name                 varchar(24),
   primary key (id)
);

create table host
(
   id                   int not null auto_increment,
   ip                   varchar(24),
   port                 int,
   account              varchar(24),
   password             varchar(24),
   primary key (id)
);

create table object
(
   id                   int not null auto_increment,
   host_id              int,
   type_id              int,
   database_name        varchar(24),
   name                 varchar(24),
   address              varchar(24),
   linkman              varchar(24),
   mobile               varchar(11),
   primary key (id)
);

create table object_device
(
   id                   int not null auto_increment,
   group_id             int,
   object_id            int,
   devicetype_id        int,
   code                 varchar(24),
   index                int,
   primary key (id)
);


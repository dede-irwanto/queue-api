create database queue_db;

create user queue_user identified by 'queue_password';

grant all privileges on queue_db.* to 'queue_user'@'%';

use queue_db;
drop table roles;
create table roles
(
    id   varchar(100) not null,
    name varchar(10)  not null,
    primary key (id),
    index (name)
) engine = innodb;

desc roles;

insert into roles value ('46ceef87-02d5-4029-ac3f-afd019c74a2a', 'ROLE_ADMIN');
insert into roles value ('0004fbc5-8b7c-4883-b125-725a1b50a823', 'ROLE_USER');

select *
from roles;

drop table users;

create table users
(
    id         varchar(100) not null,
    username   varchar(50)  not null,
    password   text         not null,
    full_name  varchar(100) not null,
    role_id    varchar(100) not null,
    token      varchar(250),
    expired_at bigint,
    primary key (id),
    unique (username, token),
    index (username),
    foreign key (role_id) references roles (id)
) engine = innodb;

desc users;

# pass: 123

insert into users(id, username, password, full_name, role_id)
    value ('346c9193-8e1b-4818-979a-cd32588dd618',
           'admin',
           '$2a$10$sIu8K4gHwfSC6.iEYBSdvuz1ChGePmCwB4NvM7Al3jSfrlmlLgt6O',
           'Administrator',
           '46ceef87-02d5-4029-ac3f-afd019c74a2a');

insert into users(id, username, password, full_name, role_id)
    value ('fa5c6ae6-7a6d-4fcf-b2a1-47ddcf68851a',
           'dee',
           '$2a$10$sIu8K4gHwfSC6.iEYBSdvuz1ChGePmCwB4NvM7Al3jSfrlmlLgt6O',
           'Dede Irwanto',
           '0004fbc5-8b7c-4883-b125-725a1b50a823');

select *
from users;

drop table application_settings;

create table application_settings
(
    id             varchar(100) not null,
    institute_name varchar(200) not null,
    footer         text         not null,
    primary key (id)
) engine = innodb;

desc application_settings;

select *
from application_settings;

drop table logos;

create table logos
(
    id                     varchar(100) not null,
    name                   varchar(200) not null,
    data                   longblob     not null,
    type                   varchar(100) not null,
    application_setting_id varchar(100) not null,
    primary key (id),
    foreign key (application_setting_id) references application_settings (id)
) engine = innodb;

desc logos;

select *
from logos;

drop table backgrounds;

create table backgrounds
(
    id                     varchar(100) not null,
    name                   varchar(200) not null,
    data                   longblob     not null,
    type                   varchar(100) not null,
    application_setting_id varchar(100) not null,
    primary key (id),
    foreign key (application_setting_id) references application_settings (id)
) engine = innodb;

desc backgrounds;

select *
from backgrounds;

drop table service_types;

create table service_types
(
    id          varchar(100) not null,
    name        varchar(100) not null,
    description varchar(500) not null,
    code        varchar(1)   not null,
    primary key (id),
    index (name, code),
    unique (code)
) engine = innodb;

desc service_types;

select *
from service_types;

drop table counters;

create table counters
(
    id              varchar(100) not null,
    name            varchar(50)  not null,
    service_type_id varchar(100) not null,
    primary key (id),
    index (name),
    foreign key (service_type_id) references service_types (id)
) engine = innodb;

desc counters;

select *
from counters;

drop table queues;

create table queues
(
    id                       varchar(100)          not null,
    number                   varchar(4)            not null,
    service_type             varchar(100)          not null,
    service_type_description varchar(500)          not null,
    created_at               date                  not null,
    printed_at               bigint                not null,
    is_called                boolean default false not null,
    counter                  varchar(50),
    primary key (id),
    index (created_at, is_called, service_type, service_type_description, printed_at, number)
) engine = innodb;

desc queues;

select *
from queues;

create table chart
(
    id           varchar(100) not null,
    date         date         not null,
    service_type varchar(100) not null,
    count        bigint default 0,
    primary key (id),
    index (date, service_type, count)
) engine = innodb;
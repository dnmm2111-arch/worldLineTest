create table if not exists task
(
    task_id     bigint auto_increment
    primary key,
    name        varchar(255) null,
    description varchar(255) not null
    );

create table if not exists task_list
(
    fk_user_id        int         not null,
    fk_task_id_parent int         null,
    name              varchar(32) not null
    );

create table if not exists task_seq
(
    next_val bigint null
);

create table if not exists task_tree
(
    fk_task_id_parent int null,
    fk_task_id_child  int null
);

create table if not exists user
(
    user_id int auto_increment
    primary key,
    name    varchar(32) not null
    );


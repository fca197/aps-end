

create table if not exists flow_group
(
    id                bigint auto_increment comment 'ID 自增'     primary key,
   flow_group_code         varchar(64)                          null comment '工作流组编码',
   flow_group_name         varchar(64)                          null comment '工作流组名称',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'

) comment '工作流组表';


create table if not exists flow_definition
(
    id                bigint auto_increment comment 'ID 自增'     primary key,
    flow_name          varchar(64)                          null comment '工作流名称',
    flow_group_id      bigint                               null comment '工作流组ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'

) comment '工作定义表';

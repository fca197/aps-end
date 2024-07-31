
alter  table     base_role_resource
add column  tenant_id bigint                       null comment '租户ID';


create table if not exists base_role
(
    id                bigint auto_increment comment 'ID 自增'     primary key,
    role_code         varchar(64)                          null comment '角色编码',
    role_name         varchar(64)                          null comment '角色名称',
    role_group_id   bigint                       null comment '角色组',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'

) comment '角色表';

create table if not exists base_role_group
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    role_group_code   varchar(64)                          null comment '角色组编码',
    role_group_name   varchar(64)                          null comment '角色组名称',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
)
    comment '角色组表';


create table if not exists base_user_role
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    user_id           bigint                               null comment '用户ID',
    role_id           bigint                               null comment '角色ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号',
      key idx_user_id (user_id)
) comment '用户角色表';

create table if not exists base_user_role_group
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    user_id           bigint                               null comment '用户ID',
    role_group_id     bigint                               null comment '角色组ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号',
    key idx_user_id (user_id)
) comment '用户角色组表';


-- 部门
create table if not exists base_dept
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    dept_code         varchar(64)                          null comment '部门编码',
    dept_name         varchar(64)                          null comment '部门名称',
    parent_id         bigint                               null comment '父部门ID',
    path              varchar(512)                          null comment '部门路径',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
)
comment '部门表';


create table if not exists base_user_dept
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    user_id           bigint                               null comment '用户ID',
    dept_id           bigint                               null comment '部门ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号',
    key idx_user_id_dept_id (user_id, dept_id)
) comment '用户部门表';



-- 菜单

create table if not exists base_resource
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    resource_code         varchar(64)                          null comment '菜单编码',
    resource_name         varchar(64)                          null comment '菜单名称',
    resource_url          varchar(512)                          null comment '菜单URL',
    resource_icon         varchar(64)                          null comment '菜单图标',
    resource_type         varchar(64)                          null comment '菜单类型',
    is_button              tinyint(1) default 0                 null comment '是否按钮 0 否,1 是',
    parent_id         bigint                               null comment '父菜单ID',
    path              varchar(512)                          null comment '菜单路径',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
) comment '资源';

create table if not exists base_role_resource
(
    id                bigint auto_increment comment 'ID 自增'        primary key,
    role_id           bigint                               null comment '角色ID',
    resource_id       bigint                               null comment '菜单ID',
      is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
) comment '角色资源表';

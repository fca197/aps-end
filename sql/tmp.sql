

create table if not exists  base_app
(
    id  bigint auto_increment comment 'ID 自增' primary key,
    app_code  varchar(64)  null comment 'app编码',
    app_name  varchar(64)  null comment 'app名称',
    tenant_id  bigint null comment '租户ID',
    is_delete tinyint(1) default 0 null comment '是否删除 0 否,1 是',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    create_by bigint null comment '创建人',
    update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by bigint null comment '修改人',
    trace_id  varchar(64)  null comment '调用链路',
    version_num int  default 0 null comment '版本号',
    key idx_app_code(app_code)

) comment '应用表';





create table if not exists base_app_resource
(
    id            bigint auto_increment comment 'ID 自增'        primary key,
    app_id        bigint                               null comment '应用ID',
    app_code      varchar(64)                          null comment '应用编码',
    resource_code varchar(64)                          null comment '菜单编码',
    resource_name varchar(64)                          null comment '菜单名称',
    resource_url  varchar(512)                         null comment '菜单URL',
    resource_icon varchar(64)                          null comment '菜单图标',
    resource_type varchar(64)                          null comment '菜单类型',
    is_button     tinyint(1) default 0                 null comment '是否按钮 0 否,1 是',
    parent_id     bigint                               null comment '父菜单ID',
    path          varchar(512)                         null comment '菜单路径',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    tenant_id     bigint                               null comment '租户ID',
    key idx_app_code(app_code),
    key idx_app_id(app_id)
)
    comment '资源';





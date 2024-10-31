create table if not exists base_user_resource
(
    id          bigint auto_increment comment 'ID 自增' primary key,
    user_id     bigint                               null comment '角色ID',
    resource_id bigint                               null comment '资源ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    key idx_user_id (user_id)

) comment '用户角色资源表';




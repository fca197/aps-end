create table if not exists aps_scheduling_day_config_version_detail_machine_use_time
(
    id                 bigint auto_increment comment 'ID 自增' primary key,

    scheduling_day_id  bigint comment '排程ID',
    machine_id         bigint comment '机器ID',
    use_time           bigint comment '耗时',
    use_usage_rate     decimal(15, 6) comment '使用率',
    make_produce_count int comment '商品数',

    tenant_id          bigint                               null comment '租户ID',
    is_delete          tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time        datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by          bigint                               null comment '创建人',
    update_time        datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by          bigint                               null comment '修改人',
    trace_id           varchar(64)                          null comment '调用链路',
    version_num        int        default 0                 null comment '版本号',
    key idx_scheduling_day_id (scheduling_day_id)

) comment '用户角色资源表';




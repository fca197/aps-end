create table if not exists base_h3_code
(
    id      bigint comment 'ID 自增' primary key,
    lat     decimal(13, 6) comment '纬度',
    lng     decimal(13, 6) comment '经度',
    value0  bigint comment '第0级对应的h3值',
    tenant_id          bigint                               null comment '租户ID',
    is_delete          tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time        datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by          bigint                               null comment '创建人',
    update_time        datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by          bigint                               null comment '修改人',
    trace_id           varchar(64)                          null comment '调用链路',
    version_num        int        default 0                 null comment '版本号',
    key idx_lat (lat),
    key idx_lng (lng)

) comment 'H3对应的值';




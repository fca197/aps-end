create table if not exists task_def
(
    id            bigint comment 'ID 自增' primary key,
    task_name        varchar(100) comment '任务名称',
    tas_code         varchar(100) comment '任务编号',
    task_remark      text comment '任务备注',
    task_def_content longtext comment '任务名称',
    tenant_id     bigint                               null comment '租户ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    key idx_tenant_id (tenant_id)

) comment '任务定义';


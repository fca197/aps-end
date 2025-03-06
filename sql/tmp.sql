drop table if exists task_instance_history;
create table if not exists task_instance_history
(
    id               bigint comment 'ID 自增' primary key,
    instance_id      bigint comment '实例ID',
    task_id          bigint comment '任务ID',
    task_def_id      varchar(64) comment '任务节点ID',
    task_input       longtext comment '入参',
    task_output      longtext comment '返回值',
    task_exec_status varchar(32) comment '执行状态 ',
    exception_msg    longtext comment '异常描述',
    use_time         int comment '耗时',
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号',
    key idx_task_id (task_id),
    key idx_task_def_id (task_def_id)

) comment '任务实例历史';


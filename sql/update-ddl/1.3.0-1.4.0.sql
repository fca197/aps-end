
create table if not exists aps_machine
(
    id           bigint auto_increment comment 'ID 自增'
    primary key,
    machine_no   varchar(32)                          not null comment '机器编号',
    machine_name varchar(32)                          not null comment '机器名称',
    tenant_id    bigint                               null comment '租户ID',
    is_delete    tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by    bigint                               null comment '创建人',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by    bigint                               null comment '修改人',
    trace_id     varchar(64)                          null comment '调用链路',
    version_num  int        default 0                 null comment '版本号'
    )
    comment 'aps 生产机器';

create index idx_tenant_id
    on aps_machine (tenant_id);


alter  table  aps_order
add column     order_no_parent       varchar(50)     null comment '父订单号';



create table if not exists aps_produce_process
(
    id                   bigint auto_increment comment 'ID 自增'
        primary key,
    produce_process_no   varchar(32)                          not null comment '生产路径编码',
    produce_process_name varchar(32)                          not null comment '生产路径名称',
    is_default           tinyint    default 0                 null comment '是否默认',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(64)                          null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
)
    comment 'aps 生产路径';

create index idx_tenant_id
    on aps_produce_process (tenant_id);

create table if not exists aps_produce_process_item
(
    id                      bigint auto_increment comment 'ID 自增'
        primary key,
    produce_process_id      bigint                               null comment '生产路径 Id aps_produce_process',
    machine_id              bigint                               null comment '机器ID',
    status_id               bigint                               null comment '状态ID',
    machine_use_time_second bigint                               null comment '耗时（秒）',
    tenant_id               bigint                               null comment '租户ID',
    is_delete               tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time             datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by               bigint                               null comment '创建人',
    update_time             datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by               bigint                               null comment '修改人',
    trace_id                varchar(64)                          null comment '调用链路',
    version_num             int        default 0                 null comment '版本号'
)
    comment 'aps 生产机器';

create index idx_produce_process_id
    on aps_produce_process_item (produce_process_id);

create index idx_tenant_id
    on aps_produce_process_item (tenant_id);


alter table  aps_scheduling_day_config
add column
    room_config         varchar(1024)                        null comment '车间配置';



create table if not exists aps_scheduling_day_config_version_detail_machine
(
    id                bigint auto_increment comment 'ID 自增'
        primary key,
    scheduling_day_id bigint                               null comment '版本ID',
    order_id          bigint                               null comment '订单ID',
    machine_id        bigint                               null comment '机器ID',
    status_id         bigint                               null comment '状态ID',
    begin_date_time   datetime                             null comment '开始时间',
    end_date_time     datetime                             null comment '结束时间',
    start_second      bigint                               null comment '开始秒',
    end_second        bigint                               null comment '结束秒',
    use_time          bigint                               null comment '耗时（秒）',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
)
    comment '排程版本详情_机器';

create index idx_scheduling_day_id
    on aps_scheduling_day_config_version_detail_machine (scheduling_day_id);



create table if not exists base_role_group_resource
(
    id            bigint auto_increment comment 'ID 自增'
        primary key,
    role_group_id bigint                               null comment '角色ID',
    resource_id   bigint                               null comment '资源ID',
    tenant_id     bigint                               null comment '租户ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号'
)
    comment '角色组资源表';

create index idx_role_group_id
    on base_role_group_resource (role_group_id);



create table if not exists base_user_resource
(
    id          bigint auto_increment comment 'ID 自增'
        primary key,
    user_id     bigint                               null comment '角色ID',
    resource_id bigint                               null comment '资源ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
)
    comment '用户角色资源表';

create index idx_user_id
    on base_user_resource (user_id);





INSERT INTO base_resource (id, resource_code, resource_name, resource_url, resource_icon, resource_type, is_button, is_hidden, parent_id, path, is_delete, create_time, create_by, update_time, update_by, trace_id, version_num, tenant_id) VALUES (1820757199220150403, '1820757199220150403', '机器管理', '/aps/machine/index', null, null, 0, null, 1820757199220150274, null, 0, '2024-10-25 23:29:19', null, '2024-10-25 23:29:59', null, null, 0, 1001);
INSERT INTO base_resource (id, resource_code, resource_name, resource_url, resource_icon, resource_type, is_button, is_hidden, parent_id, path, is_delete, create_time, create_by, update_time, update_by, trace_id, version_num, tenant_id) VALUES (1820757199220150404, '1820757199220150404', '制造路径', '/aps/produceProcess/path/index', null, null, 0, null, 1820757199220150274, null, 0, '2024-10-25 23:29:19', null, '2024-10-25 23:29:59', null, null, 0, 1001);

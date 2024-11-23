create table if not exists aps_bom
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    bom_code            varchar(255)                         null comment 'bom 编码',
    bom_name            varchar(255)                         null comment 'bom 名称',
    bom_cost_price      decimal(15, 6)                       null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_inventory       decimal(15, 6)                       null comment '库存',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    group_id            bigint                               null comment '组ID'
    )
    comment 'BOM 清单';

create index idx_aps_bom_tenant_id
    on aps_bom (tenant_id);

create index idx_g_id
    on aps_bom (group_id)
    comment '组';

create table if not exists aps_bom_group
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    group_code  varchar(255)                         null comment '组编码',
    group_name  varchar(255)                         null comment '组名称',
    parent_id   bigint                               null comment '父级ID',
    path_id     varchar(512)                         null comment '路径配置',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '零件组配置';

create index id_tenant_id
    on aps_bom_group (tenant_id);

create table if not exists aps_goods
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    goods_name      varchar(255)                         null comment '商品名称',
    goods_remark    varchar(255)                         null comment '商品备注',
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    supplier_status varchar(255)                         null,
    version_num     int        default 0                 null comment '版本号',
    factory_id      bigint                               null comment '工厂ID',
    process_path_id bigint                               null comment '工艺路线'
    )
    comment 'aps 商品表';

create index idx_aps_goods_factory_id
    on aps_goods (factory_id);

create index idx_aps_goods_tenant_id
    on aps_goods (tenant_id);

create table if not exists aps_goods_bom
(
    id                   bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id             bigint                                   null comment '商品ID',
    group_id             bigint                                   null comment '零件组ID',
    bom_id               bigint                                   null comment '商品ID',
    bom_code             varchar(255)                             null comment 'bom 编码',
    bom_name             varchar(255)                             null comment 'bom 名称',
    bom_usage            decimal(15, 6)                           null comment '使用量',
    bom_unit            varchar(255) null comment '规格',
    bom_cost_price       decimal(15, 6)                           null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_use_work_station bigint                                   null comment '使用工位',
    bom_use_expression   varchar(255)                             null comment '使用表达式',
    bom_inventory        decimal(15, 6) default 0.000000          null comment '库存',
    is_follow            tinyint(1)     default 0                 null comment '是否关注',
    factory_id           bigint                                   null comment '工厂ID',
    tenant_id            bigint                                   null comment '租户ID',
    is_delete            tinyint(1)     default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime       default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                                   null comment '创建人',
    update_time          datetime       default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                                   null comment '修改人',
    trace_id             varchar(64)                              null comment '调用链路',
    version_num          int            default 0                 null comment '版本号'
    )
    comment 'BOM 清单';

create index idx_aps_goods_bom_factory_id
    on aps_goods_bom (factory_id);

create index idx_aps_goods_bom_goods_id
    on aps_goods_bom (goods_id);

create index idx_aps_goods_bom_tenant_id
    on aps_goods_bom (tenant_id);

create index idx_g_id
    on aps_goods_bom (group_id)
    comment '组Id ';

create table if not exists aps_goods_bom_buy_plan
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    plan_name         varchar(255)                         null comment '计划名称',
    plan_total_amount decimal(15, 6)                       null comment '总价',
    plan_source       varchar(255)                         null comment '计划来源',
    plan_remark       varchar(255)                         null comment '计划备注',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment 'BOM 购买计划';

create index idx_aps_goods_bom_plan_tenant_id
    on aps_goods_bom_buy_plan (tenant_id);

create table if not exists aps_goods_bom_buy_plan_item
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    buy_plan_id         bigint                               null comment '计划ID',
    bom_id              bigint                               null comment 'ID',
    bom_code            varchar(255)                         null comment 'bom 编码',
    bom_name            varchar(255)                         null comment 'bom 名称',
    is_follow           tinyint(1) default 0                 null comment '是否关注',
    bom_cost_price      decimal(15, 6)                       null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_inventory       decimal(15, 6)                       null comment '库存',
    bom_buy_count       decimal(15, 6)                       null comment '购买数量',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    goods_bom_id        bigint                               not null comment '商品零件ID'
    )
    comment 'BOM 购买清单';

create index idx_aps_goods_bom_buy_plan_item_buy_plan_id_index
    on aps_goods_bom_buy_plan_item (buy_plan_id);

create index idx_aps_goods_bom_plan_item_factory_id
    on aps_goods_bom_buy_plan_item (bom_id);

create index idx_aps_goods_bom_plan_item_tenant_id
    on aps_goods_bom_buy_plan_item (tenant_id);

create table if not exists aps_goods_forecast
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id            bigint                               null comment '商品ID',
    forecast_no         varchar(255)                         null comment '预测编码',
    forecast_name       varchar(255)                         null comment '预测名称',
    forecast_begin_date varchar(255)                         null comment '开始时间',
    forecast_end_date   varchar(255)                         null comment '结束时间',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    month               varchar(255)                         null,
    months              text                                 null,
    forecast_status     tinyint                              null
    )
    comment '预测表';

create index idx_aps_goods_forecast_goods_id
    on aps_goods_forecast (goods_id);

create index idx_aps_goods_forecast_tenant_id
    on aps_goods_forecast (tenant_id);

create table if not exists aps_goods_forecast_compute_sale_data
(
    id               bigint auto_increment comment 'ID 自增'
    primary key,
    forecast_id      bigint                               null comment '预测ID',
    sale_config_id   bigint                               null comment '销售配置',
    year             smallint                             null comment '年份',
    month_01         decimal(15, 6)                       null,
    month_02         decimal(15, 6)                       null,
    month_03         decimal(15, 6)                       null,
    month_04         decimal(15, 6)                       null,
    month_05         decimal(15, 6)                       null,
    month_06         decimal(15, 6)                       null,
    month_07         decimal(15, 6)                       null,
    month_08         decimal(15, 6)                       null,
    month_09         decimal(15, 6)                       null,
    month_10         decimal(15, 6)                       null,
    month_11         decimal(15, 6)                       null,
    month_12         decimal(15, 6)                       null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号',
    sale_config_code varchar(255)                         null
    )
    comment '预测计算销售数据';

create index idx_aps_goods_forecast_compute_sale_data_forecast_id
    on aps_goods_forecast_compute_sale_data (forecast_id);

create index idx_aps_goods_forecast_compute_sale_data_tenant_id
    on aps_goods_forecast_compute_sale_data (tenant_id);

create table if not exists aps_goods_forecast_main
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id            bigint                               null comment '商品ID',
    forecast_no         varchar(255)                         null comment '预测编码',
    forecast_name       varchar(255)                         null comment '预测名称',
    forecast_begin_date varchar(255)                         null comment '开始时间',
    forecast_end_date   varchar(255)                         null comment '结束时间',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    month               varchar(255)                         null,
    months              varchar(255)                         null,
    factory_id          bigint                               null comment '工厂ID'
    )
    comment '预测主表';

create index idx_aps_goods_forecast_main_factory_id
    on aps_goods_forecast_main (factory_id);

create index idx_aps_goods_forecast_main_goods_id
    on aps_goods_forecast_main (goods_id);

create index idx_aps_goods_forecast_main_tenant_id
    on aps_goods_forecast_main (tenant_id);

create table if not exists aps_goods_forecast_main_goods_data
(
    id               bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id         bigint                               null comment '商品ID',
    year             varchar(255)                         null,
    month_01         decimal(15, 6)                       null,
    month_02         decimal(15, 6)                       null,
    month_03         decimal(15, 6)                       null,
    month_04         decimal(15, 6)                       null,
    month_05         decimal(15, 6)                       null,
    month_06         decimal(15, 6)                       null,
    month_07         decimal(15, 6)                       null,
    month_08         decimal(15, 6)                       null,
    month_09         decimal(15, 6)                       null,
    month_10         decimal(15, 6)                       null,
    month_11         decimal(15, 6)                       null,
    month_12         decimal(15, 6)                       null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        varchar(255)                         null,
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号',
    forecast_main_id bigint                               null
    )
    comment '预测商品数据';

create table if not exists aps_goods_forecast_main_make
(
    id                            bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id                      bigint                               null comment '商品ID',
    forecast_make_main_no         varchar(255)                         null comment '编码',
    forecast_make_main_name       varchar(255)                         null comment '名称',
    forecast_make_main_begin_date varchar(255)                         null comment '开始时间',
    forecast_make_main_end_date   varchar(255)                         null comment '结束时间',
    factory_id                    bigint                               null comment '工厂ID',
    tenant_id                     bigint                               null comment '租户ID',
    is_delete                     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time                   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                     bigint                               null comment '创建人',
    update_time                   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                     bigint                               null comment '修改人',
    trace_id                      varchar(64)                          null comment '调用链路',
    version_num                   int        default 0                 null comment '版本号'
    )
    comment '预测生产主表';

create index idx_aps_goods_forecast_main_make_factory_id
    on aps_goods_forecast_main_make (factory_id);

create index idx_aps_goods_forecast_main_make_goods_id
    on aps_goods_forecast_main_make (goods_id);

create index idx_aps_goods_forecast_main_make_tenant_id
    on aps_goods_forecast_main_make (tenant_id);

create table if not exists aps_goods_forecast_main_make_sale_data
(
    id               bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id         bigint                               null comment '商品ID',
    main_make_id     bigint                               null,
    sale_config_code varchar(255)                         null,
    year             smallint                             null,
    day_num1         decimal(15, 6)                       null comment '制造数',
    day_num2         decimal(15, 6)                       null comment '制造数',
    day_num3         decimal(15, 6)                       null comment '制造数',
    day_num4         decimal(15, 6)                       null comment '制造数',
    day_num5         decimal(15, 6)                       null comment '制造数',
    day_num6         decimal(15, 6)                       null comment '制造数',
    day_num7         decimal(15, 6)                       null comment '制造数',
    day_num8         decimal(15, 6)                       null comment '制造数',
    day_num9         decimal(15, 6)                       null comment '制造数',
    day_num10        decimal(15, 6)                       null comment '制造数',
    day_num11        decimal(15, 6)                       null comment '制造数',
    day_num12        decimal(15, 6)                       null comment '制造数',
    day_num13        decimal(15, 6)                       null comment '制造数',
    day_num14        decimal(15, 6)                       null comment '制造数',
    day_num15        decimal(15, 6)                       null comment '制造数',
    day_num16        decimal(15, 6)                       null comment '制造数',
    day_num17        decimal(15, 6)                       null comment '制造数',
    day_num18        decimal(15, 6)                       null comment '制造数',
    day_num19        decimal(15, 6)                       null comment '制造数',
    day_num20        decimal(15, 6)                       null comment '制造数',
    day_num21        decimal(15, 6)                       null comment '制造数',
    day_num22        decimal(15, 6)                       null comment '制造数',
    day_num23        decimal(15, 6)                       null comment '制造数',
    day_num24        decimal(15, 6)                       null comment '制造数',
    day_num25        decimal(15, 6)                       null comment '制造数',
    day_num26        decimal(15, 6)                       null comment '制造数',
    day_num27        decimal(15, 6)                       null comment '制造数',
    day_num28        decimal(15, 6)                       null comment '制造数',
    day_num29        decimal(15, 6)                       null comment '制造数',
    day_num30        decimal(15, 6)                       null comment '制造数',
    day_num31        decimal(15, 6)                       null comment '制造数',
    day_num32        decimal(15, 6)                       null comment '制造数',
    day_num33        decimal(15, 6)                       null comment '制造数',
    day_num34        decimal(15, 6)                       null comment '制造数',
    day_num35        decimal(15, 6)                       null comment '制造数',
    day_num36        decimal(15, 6)                       null comment '制造数',
    day_num37        decimal(15, 6)                       null comment '制造数',
    day_num38        decimal(15, 6)                       null comment '制造数',
    day_num39        decimal(15, 6)                       null comment '制造数',
    day_num40        decimal(15, 6)                       null comment '制造数',
    day_num41        decimal(15, 6)                       null comment '制造数',
    day_num42        decimal(15, 6)                       null comment '制造数',
    day_num43        decimal(15, 6)                       null comment '制造数',
    day_num44        decimal(15, 6)                       null comment '制造数',
    day_num45        decimal(15, 6)                       null comment '制造数',
    day_num46        decimal(15, 6)                       null comment '制造数',
    day_num47        decimal(15, 6)                       null comment '制造数',
    day_num48        decimal(15, 6)                       null comment '制造数',
    day_num49        decimal(15, 6)                       null comment '制造数',
    day_num50        decimal(15, 6)                       null comment '制造数',
    day_num51        decimal(15, 6)                       null comment '制造数',
    day_num52        decimal(15, 6)                       null comment '制造数',
    day_num53        decimal(15, 6)                       null comment '制造数',
    day_num54        decimal(15, 6)                       null comment '制造数',
    day_num55        decimal(15, 6)                       null comment '制造数',
    day_num56        decimal(15, 6)                       null comment '制造数',
    day_num57        decimal(15, 6)                       null comment '制造数',
    day_num58        decimal(15, 6)                       null comment '制造数',
    day_num59        decimal(15, 6)                       null comment '制造数',
    day_num60        decimal(15, 6)                       null comment '制造数',
    day_num61        decimal(15, 6)                       null comment '制造数',
    day_num62        decimal(15, 6)                       null comment '制造数',
    day_num63        decimal(15, 6)                       null comment '制造数',
    day_num64        decimal(15, 6)                       null comment '制造数',
    day_num65        decimal(15, 6)                       null comment '制造数',
    day_num66        decimal(15, 6)                       null comment '制造数',
    day_num67        decimal(15, 6)                       null comment '制造数',
    day_num68        decimal(15, 6)                       null comment '制造数',
    day_num69        decimal(15, 6)                       null comment '制造数',
    day_num70        decimal(15, 6)                       null comment '制造数',
    day_num71        decimal(15, 6)                       null comment '制造数',
    day_num72        decimal(15, 6)                       null comment '制造数',
    day_num73        decimal(15, 6)                       null comment '制造数',
    day_num74        decimal(15, 6)                       null comment '制造数',
    day_num75        decimal(15, 6)                       null comment '制造数',
    day_num76        decimal(15, 6)                       null comment '制造数',
    day_num77        decimal(15, 6)                       null comment '制造数',
    day_num78        decimal(15, 6)                       null comment '制造数',
    day_num79        decimal(15, 6)                       null comment '制造数',
    day_num80        decimal(15, 6)                       null comment '制造数',
    day_num81        decimal(15, 6)                       null comment '制造数',
    day_num82        decimal(15, 6)                       null comment '制造数',
    day_num83        decimal(15, 6)                       null comment '制造数',
    day_num84        decimal(15, 6)                       null comment '制造数',
    day_num85        decimal(15, 6)                       null comment '制造数',
    day_num86        decimal(15, 6)                       null comment '制造数',
    day_num87        decimal(15, 6)                       null comment '制造数',
    day_num88        decimal(15, 6)                       null comment '制造数',
    day_num89        decimal(15, 6)                       null comment '制造数',
    day_num90        decimal(15, 6)                       null comment '制造数',
    day_num91        decimal(15, 6)                       null comment '制造数',
    day_num92        decimal(15, 6)                       null comment '制造数',
    day_num93        decimal(15, 6)                       null comment '制造数',
    day_num94        decimal(15, 6)                       null comment '制造数',
    day_num95        decimal(15, 6)                       null comment '制造数',
    day_num96        decimal(15, 6)                       null comment '制造数',
    day_num97        decimal(15, 6)                       null comment '制造数',
    day_num98        decimal(15, 6)                       null comment '制造数',
    day_num99        decimal(15, 6)                       null comment '制造数',
    day_num100       decimal(15, 6)                       null comment '制造数',
    day_num101       decimal(15, 6)                       null comment '制造数',
    day_num102       decimal(15, 6)                       null comment '制造数',
    day_num103       decimal(15, 6)                       null comment '制造数',
    day_num104       decimal(15, 6)                       null comment '制造数',
    day_num105       decimal(15, 6)                       null comment '制造数',
    day_num106       decimal(15, 6)                       null comment '制造数',
    day_num107       decimal(15, 6)                       null comment '制造数',
    day_num108       decimal(15, 6)                       null comment '制造数',
    day_num109       decimal(15, 6)                       null comment '制造数',
    day_num110       decimal(15, 6)                       null comment '制造数',
    day_num111       decimal(15, 6)                       null comment '制造数',
    day_num112       decimal(15, 6)                       null comment '制造数',
    day_num113       decimal(15, 6)                       null comment '制造数',
    day_num114       decimal(15, 6)                       null comment '制造数',
    day_num115       decimal(15, 6)                       null comment '制造数',
    day_num116       decimal(15, 6)                       null comment '制造数',
    day_num117       decimal(15, 6)                       null comment '制造数',
    day_num118       decimal(15, 6)                       null comment '制造数',
    day_num119       decimal(15, 6)                       null comment '制造数',
    day_num120       decimal(15, 6)                       null comment '制造数',
    day_num121       decimal(15, 6)                       null comment '制造数',
    day_num122       decimal(15, 6)                       null comment '制造数',
    day_num123       decimal(15, 6)                       null comment '制造数',
    day_num124       decimal(15, 6)                       null comment '制造数',
    day_num125       decimal(15, 6)                       null comment '制造数',
    day_num126       decimal(15, 6)                       null comment '制造数',
    day_num127       decimal(15, 6)                       null comment '制造数',
    day_num128       decimal(15, 6)                       null comment '制造数',
    day_num129       decimal(15, 6)                       null comment '制造数',
    day_num130       decimal(15, 6)                       null comment '制造数',
    day_num131       decimal(15, 6)                       null comment '制造数',
    day_num132       decimal(15, 6)                       null comment '制造数',
    day_num133       decimal(15, 6)                       null comment '制造数',
    day_num134       decimal(15, 6)                       null comment '制造数',
    day_num135       decimal(15, 6)                       null comment '制造数',
    day_num136       decimal(15, 6)                       null comment '制造数',
    day_num137       decimal(15, 6)                       null comment '制造数',
    day_num138       decimal(15, 6)                       null comment '制造数',
    day_num139       decimal(15, 6)                       null comment '制造数',
    day_num140       decimal(15, 6)                       null comment '制造数',
    day_num141       decimal(15, 6)                       null comment '制造数',
    day_num142       decimal(15, 6)                       null comment '制造数',
    day_num143       decimal(15, 6)                       null comment '制造数',
    day_num144       decimal(15, 6)                       null comment '制造数',
    day_num145       decimal(15, 6)                       null comment '制造数',
    day_num146       decimal(15, 6)                       null comment '制造数',
    day_num147       decimal(15, 6)                       null comment '制造数',
    day_num148       decimal(15, 6)                       null comment '制造数',
    day_num149       decimal(15, 6)                       null comment '制造数',
    day_num150       decimal(15, 6)                       null comment '制造数',
    day_num151       decimal(15, 6)                       null comment '制造数',
    day_num152       decimal(15, 6)                       null comment '制造数',
    day_num153       decimal(15, 6)                       null comment '制造数',
    day_num154       decimal(15, 6)                       null comment '制造数',
    day_num155       decimal(15, 6)                       null comment '制造数',
    day_num156       decimal(15, 6)                       null comment '制造数',
    day_num157       decimal(15, 6)                       null comment '制造数',
    day_num158       decimal(15, 6)                       null comment '制造数',
    day_num159       decimal(15, 6)                       null comment '制造数',
    day_num160       decimal(15, 6)                       null comment '制造数',
    day_num161       decimal(15, 6)                       null comment '制造数',
    day_num162       decimal(15, 6)                       null comment '制造数',
    day_num163       decimal(15, 6)                       null comment '制造数',
    day_num164       decimal(15, 6)                       null comment '制造数',
    day_num165       decimal(15, 6)                       null comment '制造数',
    day_num166       decimal(15, 6)                       null comment '制造数',
    day_num167       decimal(15, 6)                       null comment '制造数',
    day_num168       decimal(15, 6)                       null comment '制造数',
    day_num169       decimal(15, 6)                       null comment '制造数',
    day_num170       decimal(15, 6)                       null comment '制造数',
    day_num171       decimal(15, 6)                       null comment '制造数',
    day_num172       decimal(15, 6)                       null comment '制造数',
    day_num173       decimal(15, 6)                       null comment '制造数',
    day_num174       decimal(15, 6)                       null comment '制造数',
    day_num175       decimal(15, 6)                       null comment '制造数',
    day_num176       decimal(15, 6)                       null comment '制造数',
    day_num177       decimal(15, 6)                       null comment '制造数',
    day_num178       decimal(15, 6)                       null comment '制造数',
    day_num179       decimal(15, 6)                       null comment '制造数',
    day_num180       decimal(15, 6)                       null comment '制造数',
    day_num181       decimal(15, 6)                       null comment '制造数',
    day_num182       decimal(15, 6)                       null comment '制造数',
    day_num183       decimal(15, 6)                       null comment '制造数',
    day_num184       decimal(15, 6)                       null comment '制造数',
    day_num185       decimal(15, 6)                       null comment '制造数',
    day_num186       decimal(15, 6)                       null comment '制造数',
    day_num187       decimal(15, 6)                       null comment '制造数',
    day_num188       decimal(15, 6)                       null comment '制造数',
    day_num189       decimal(15, 6)                       null comment '制造数',
    day_num190       decimal(15, 6)                       null comment '制造数',
    day_num191       decimal(15, 6)                       null comment '制造数',
    day_num192       decimal(15, 6)                       null comment '制造数',
    day_num193       decimal(15, 6)                       null comment '制造数',
    day_num194       decimal(15, 6)                       null comment '制造数',
    day_num195       decimal(15, 6)                       null comment '制造数',
    day_num196       decimal(15, 6)                       null comment '制造数',
    day_num197       decimal(15, 6)                       null comment '制造数',
    day_num198       decimal(15, 6)                       null comment '制造数',
    day_num199       decimal(15, 6)                       null comment '制造数',
    day_num200       decimal(15, 6)                       null comment '制造数',
    day_num201       decimal(15, 6)                       null comment '制造数',
    day_num202       decimal(15, 6)                       null comment '制造数',
    day_num203       decimal(15, 6)                       null comment '制造数',
    day_num204       decimal(15, 6)                       null comment '制造数',
    day_num205       decimal(15, 6)                       null comment '制造数',
    day_num206       decimal(15, 6)                       null comment '制造数',
    day_num207       decimal(15, 6)                       null comment '制造数',
    day_num208       decimal(15, 6)                       null comment '制造数',
    day_num209       decimal(15, 6)                       null comment '制造数',
    day_num210       decimal(15, 6)                       null comment '制造数',
    day_num211       decimal(15, 6)                       null comment '制造数',
    day_num212       decimal(15, 6)                       null comment '制造数',
    day_num213       decimal(15, 6)                       null comment '制造数',
    day_num214       decimal(15, 6)                       null comment '制造数',
    day_num215       decimal(15, 6)                       null comment '制造数',
    day_num216       decimal(15, 6)                       null comment '制造数',
    day_num217       decimal(15, 6)                       null comment '制造数',
    day_num218       decimal(15, 6)                       null comment '制造数',
    day_num219       decimal(15, 6)                       null comment '制造数',
    day_num220       decimal(15, 6)                       null comment '制造数',
    day_num221       decimal(15, 6)                       null comment '制造数',
    day_num222       decimal(15, 6)                       null comment '制造数',
    day_num223       decimal(15, 6)                       null comment '制造数',
    day_num224       decimal(15, 6)                       null comment '制造数',
    day_num225       decimal(15, 6)                       null comment '制造数',
    day_num226       decimal(15, 6)                       null comment '制造数',
    day_num227       decimal(15, 6)                       null comment '制造数',
    day_num228       decimal(15, 6)                       null comment '制造数',
    day_num229       decimal(15, 6)                       null comment '制造数',
    day_num230       decimal(15, 6)                       null comment '制造数',
    day_num231       decimal(15, 6)                       null comment '制造数',
    day_num232       decimal(15, 6)                       null comment '制造数',
    day_num233       decimal(15, 6)                       null comment '制造数',
    day_num234       decimal(15, 6)                       null comment '制造数',
    day_num235       decimal(15, 6)                       null comment '制造数',
    day_num236       decimal(15, 6)                       null comment '制造数',
    day_num237       decimal(15, 6)                       null comment '制造数',
    day_num238       decimal(15, 6)                       null comment '制造数',
    day_num239       decimal(15, 6)                       null comment '制造数',
    day_num240       decimal(15, 6)                       null comment '制造数',
    day_num241       decimal(15, 6)                       null comment '制造数',
    day_num242       decimal(15, 6)                       null comment '制造数',
    day_num243       decimal(15, 6)                       null comment '制造数',
    day_num244       decimal(15, 6)                       null comment '制造数',
    day_num245       decimal(15, 6)                       null comment '制造数',
    day_num246       decimal(15, 6)                       null comment '制造数',
    day_num247       decimal(15, 6)                       null comment '制造数',
    day_num248       decimal(15, 6)                       null comment '制造数',
    day_num249       decimal(15, 6)                       null comment '制造数',
    day_num250       decimal(15, 6)                       null comment '制造数',
    day_num251       decimal(15, 6)                       null comment '制造数',
    day_num252       decimal(15, 6)                       null comment '制造数',
    day_num253       decimal(15, 6)                       null comment '制造数',
    day_num254       decimal(15, 6)                       null comment '制造数',
    day_num255       decimal(15, 6)                       null comment '制造数',
    day_num256       decimal(15, 6)                       null comment '制造数',
    day_num257       decimal(15, 6)                       null comment '制造数',
    day_num258       decimal(15, 6)                       null comment '制造数',
    day_num259       decimal(15, 6)                       null comment '制造数',
    day_num260       decimal(15, 6)                       null comment '制造数',
    day_num261       decimal(15, 6)                       null comment '制造数',
    day_num262       decimal(15, 6)                       null comment '制造数',
    day_num263       decimal(15, 6)                       null comment '制造数',
    day_num264       decimal(15, 6)                       null comment '制造数',
    day_num265       decimal(15, 6)                       null comment '制造数',
    day_num266       decimal(15, 6)                       null comment '制造数',
    day_num267       decimal(15, 6)                       null comment '制造数',
    day_num268       decimal(15, 6)                       null comment '制造数',
    day_num269       decimal(15, 6)                       null comment '制造数',
    day_num270       decimal(15, 6)                       null comment '制造数',
    day_num271       decimal(15, 6)                       null comment '制造数',
    day_num272       decimal(15, 6)                       null comment '制造数',
    day_num273       decimal(15, 6)                       null comment '制造数',
    day_num274       decimal(15, 6)                       null comment '制造数',
    day_num275       decimal(15, 6)                       null comment '制造数',
    day_num276       decimal(15, 6)                       null comment '制造数',
    day_num277       decimal(15, 6)                       null comment '制造数',
    day_num278       decimal(15, 6)                       null comment '制造数',
    day_num279       decimal(15, 6)                       null comment '制造数',
    day_num280       decimal(15, 6)                       null comment '制造数',
    day_num281       decimal(15, 6)                       null comment '制造数',
    day_num282       decimal(15, 6)                       null comment '制造数',
    day_num283       decimal(15, 6)                       null comment '制造数',
    day_num284       decimal(15, 6)                       null comment '制造数',
    day_num285       decimal(15, 6)                       null comment '制造数',
    day_num286       decimal(15, 6)                       null comment '制造数',
    day_num287       decimal(15, 6)                       null comment '制造数',
    day_num288       decimal(15, 6)                       null comment '制造数',
    day_num289       decimal(15, 6)                       null comment '制造数',
    day_num290       decimal(15, 6)                       null comment '制造数',
    day_num291       decimal(15, 6)                       null comment '制造数',
    day_num292       decimal(15, 6)                       null comment '制造数',
    day_num293       decimal(15, 6)                       null comment '制造数',
    day_num294       decimal(15, 6)                       null comment '制造数',
    day_num295       decimal(15, 6)                       null comment '制造数',
    day_num296       decimal(15, 6)                       null comment '制造数',
    day_num297       decimal(15, 6)                       null comment '制造数',
    day_num298       decimal(15, 6)                       null comment '制造数',
    day_num299       decimal(15, 6)                       null comment '制造数',
    day_num300       decimal(15, 6)                       null comment '制造数',
    day_num301       decimal(15, 6)                       null comment '制造数',
    day_num302       decimal(15, 6)                       null comment '制造数',
    day_num303       decimal(15, 6)                       null comment '制造数',
    day_num304       decimal(15, 6)                       null comment '制造数',
    day_num305       decimal(15, 6)                       null comment '制造数',
    day_num306       decimal(15, 6)                       null comment '制造数',
    day_num307       decimal(15, 6)                       null comment '制造数',
    day_num308       decimal(15, 6)                       null comment '制造数',
    day_num309       decimal(15, 6)                       null comment '制造数',
    day_num310       decimal(15, 6)                       null comment '制造数',
    day_num311       decimal(15, 6)                       null comment '制造数',
    day_num312       decimal(15, 6)                       null comment '制造数',
    day_num313       decimal(15, 6)                       null comment '制造数',
    day_num314       decimal(15, 6)                       null comment '制造数',
    day_num315       decimal(15, 6)                       null comment '制造数',
    day_num316       decimal(15, 6)                       null comment '制造数',
    day_num317       decimal(15, 6)                       null comment '制造数',
    day_num318       decimal(15, 6)                       null comment '制造数',
    day_num319       decimal(15, 6)                       null comment '制造数',
    day_num320       decimal(15, 6)                       null comment '制造数',
    day_num321       decimal(15, 6)                       null comment '制造数',
    day_num322       decimal(15, 6)                       null comment '制造数',
    day_num323       decimal(15, 6)                       null comment '制造数',
    day_num324       decimal(15, 6)                       null comment '制造数',
    day_num325       decimal(15, 6)                       null comment '制造数',
    day_num326       decimal(15, 6)                       null comment '制造数',
    day_num327       decimal(15, 6)                       null comment '制造数',
    day_num328       decimal(15, 6)                       null comment '制造数',
    day_num329       decimal(15, 6)                       null comment '制造数',
    day_num330       decimal(15, 6)                       null comment '制造数',
    day_num331       decimal(15, 6)                       null comment '制造数',
    day_num332       decimal(15, 6)                       null comment '制造数',
    day_num333       decimal(15, 6)                       null comment '制造数',
    day_num334       decimal(15, 6)                       null comment '制造数',
    day_num335       decimal(15, 6)                       null comment '制造数',
    day_num336       decimal(15, 6)                       null comment '制造数',
    day_num337       decimal(15, 6)                       null comment '制造数',
    day_num338       decimal(15, 6)                       null comment '制造数',
    day_num339       decimal(15, 6)                       null comment '制造数',
    day_num340       decimal(15, 6)                       null comment '制造数',
    day_num341       decimal(15, 6)                       null comment '制造数',
    day_num342       decimal(15, 6)                       null comment '制造数',
    day_num343       decimal(15, 6)                       null comment '制造数',
    day_num344       decimal(15, 6)                       null comment '制造数',
    day_num345       decimal(15, 6)                       null comment '制造数',
    day_num346       decimal(15, 6)                       null comment '制造数',
    day_num347       decimal(15, 6)                       null comment '制造数',
    day_num348       decimal(15, 6)                       null comment '制造数',
    day_num349       decimal(15, 6)                       null comment '制造数',
    day_num350       decimal(15, 6)                       null comment '制造数',
    day_num351       decimal(15, 6)                       null comment '制造数',
    day_num352       decimal(15, 6)                       null comment '制造数',
    day_num353       decimal(15, 6)                       null comment '制造数',
    day_num354       decimal(15, 6)                       null comment '制造数',
    day_num355       decimal(15, 6)                       null comment '制造数',
    day_num356       decimal(15, 6)                       null comment '制造数',
    day_num357       decimal(15, 6)                       null comment '制造数',
    day_num358       decimal(15, 6)                       null comment '制造数',
    day_num359       decimal(15, 6)                       null comment '制造数',
    day_num360       decimal(15, 6)                       null comment '制造数',
    day_num361       decimal(15, 6)                       null comment '制造数',
    day_num362       decimal(15, 6)                       null comment '制造数',
    day_num363       decimal(15, 6)                       null comment '制造数',
    day_num364       decimal(15, 6)                       null comment '制造数',
    day_num365       decimal(15, 6)                       null comment '制造数',
    day_num366       decimal(15, 6)                       null comment '制造数',
    factory_id       bigint                               null comment '工厂ID',
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号'
    )
    comment '商品预测主表-销售数据';

create index idx_aps_goods_forecast_main_make_sale_data_factory_id
    on aps_goods_forecast_main_make_sale_data (factory_id);

create index idx_aps_goods_forecast_main_make_sale_data_goods_id
    on aps_goods_forecast_main_make_sale_data (goods_id);

create index idx_aps_goods_forecast_main_make_sale_data_main_make_id
    on aps_goods_forecast_main_make_sale_data (main_make_id);

create index idx_aps_goods_forecast_main_make_sale_data_tenant_id
    on aps_goods_forecast_main_make_sale_data (tenant_id);

create table if not exists aps_goods_forecast_main_sale_data
(
    id               bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id         bigint                               null comment '商品ID',
    sale_config_code varchar(255)                         null,
    year             smallint                             null,
    month_01         decimal(15, 6)                       null,
    month_02         decimal(15, 6)                       null,
    month_03         decimal(15, 6)                       null,
    month_04         decimal(15, 6)                       null,
    month_05         decimal(15, 6)                       null,
    month_06         decimal(15, 6)                       null,
    month_07         decimal(15, 6)                       null,
    month_08         decimal(15, 6)                       null,
    month_09         decimal(15, 6)                       null,
    month_10         decimal(15, 6)                       null,
    month_11         decimal(15, 6)                       null,
    month_12         decimal(15, 6)                       null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号',
    forecast_main_id bigint                               null
    )
    comment '商品预测主表-销售数据';

create index idx_aps_goods_forecast_main_sale_data_forecast_main_id
    on aps_goods_forecast_main_sale_data (forecast_main_id);

create index idx_aps_goods_forecast_main_sale_data_goods_id
    on aps_goods_forecast_main_sale_data (goods_id);

create index idx_aps_goods_forecast_main_sale_data_tenant_id
    on aps_goods_forecast_main_sale_data (tenant_id);

create table if not exists aps_goods_forecast_make
(
    id                             bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id                       bigint                               null comment '商品ID',
    forecast_make_month_no         varchar(255)                         null,
    forecast_make_month_name       varchar(255)                         null,
    forecast_make_month_begin_date varchar(255)                         null,
    forecast_make_month_end_date   varchar(255)                         null,
    factory_id                     varchar(255)                         null,
    tenant_id                      bigint                               null comment '租户ID',
    is_delete                      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time                    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                      bigint                               null comment '创建人',
    update_time                    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                      bigint                               null comment '修改人',
    trace_id                       varchar(64)                          null comment '调用链路',
    version_num                    int        default 0                 null comment '版本号',
    month                          varchar(255)                         null,
    weeks                          text                                 null,
    forecast_main_id               varchar(255)                         null,
    is_deploy                      tinyint                              null,
    bom_use_begin_date             varchar(255)                         null,
    bom_use_end_date               varchar(255)                         null
    )
    comment '商品预测-制造';

create index idx_aps_goods_forecast_make_factory_id
    on aps_goods_forecast_make (factory_id);

create index idx_aps_goods_forecast_make_forecast_main_id
    on aps_goods_forecast_make (forecast_main_id);

create index idx_aps_goods_forecast_make_goods_id
    on aps_goods_forecast_make (goods_id);

create index idx_aps_goods_forecast_make_tenant_id
    on aps_goods_forecast_make (tenant_id);

create table if not exists aps_goods_forecast_make_bom_use
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id            bigint                                 null comment '商品ID',
    make_month_id       bigint                                 null,
    bom_id              bigint                                 null,
    year                smallint                               null,
    day_num1            decimal(15, 6)                         null comment '制造数',
    day_num2            decimal(15, 6)                         null comment '制造数',
    day_num3            decimal(15, 6)                         null comment '制造数',
    day_num4            decimal(15, 6)                         null comment '制造数',
    day_num5            decimal(15, 6)                         null comment '制造数',
    day_num6            decimal(15, 6)                         null comment '制造数',
    day_num7            decimal(15, 6)                         null comment '制造数',
    day_num8            decimal(15, 6)                         null comment '制造数',
    day_num9            decimal(15, 6)                         null comment '制造数',
    day_num10           decimal(15, 6)                         null comment '制造数',
    day_num11           decimal(15, 6)                         null comment '制造数',
    day_num12           decimal(15, 6)                         null comment '制造数',
    day_num13           decimal(15, 6)                         null comment '制造数',
    day_num14           decimal(15, 6)                         null comment '制造数',
    day_num15           decimal(15, 6)                         null comment '制造数',
    day_num16           decimal(15, 6)                         null comment '制造数',
    day_num17           decimal(15, 6)                         null comment '制造数',
    day_num18           decimal(15, 6)                         null comment '制造数',
    day_num19           decimal(15, 6)                         null comment '制造数',
    day_num20           decimal(15, 6)                         null comment '制造数',
    day_num21           decimal(15, 6)                         null comment '制造数',
    day_num22           decimal(15, 6)                         null comment '制造数',
    day_num23           decimal(15, 6)                         null comment '制造数',
    day_num24           decimal(15, 6)                         null comment '制造数',
    day_num25           decimal(15, 6)                         null comment '制造数',
    day_num26           decimal(15, 6)                         null comment '制造数',
    day_num27           decimal(15, 6)                         null comment '制造数',
    day_num28           decimal(15, 6)                         null comment '制造数',
    day_num29           decimal(15, 6)                         null comment '制造数',
    day_num30           decimal(15, 6)                         null comment '制造数',
    day_num31           decimal(15, 6)                         null comment '制造数',
    day_num32           decimal(15, 6)                         null comment '制造数',
    day_num33           decimal(15, 6)                         null comment '制造数',
    day_num34           decimal(15, 6)                         null comment '制造数',
    day_num35           decimal(15, 6)                         null comment '制造数',
    day_num36           decimal(15, 6)                         null comment '制造数',
    day_num37           decimal(15, 6)                         null comment '制造数',
    day_num38           decimal(15, 6)                         null comment '制造数',
    day_num39           decimal(15, 6)                         null comment '制造数',
    day_num40           decimal(15, 6)                         null comment '制造数',
    day_num41           decimal(15, 6)                         null comment '制造数',
    day_num42           decimal(15, 6)                         null comment '制造数',
    day_num43           decimal(15, 6)                         null comment '制造数',
    day_num44           decimal(15, 6)                         null comment '制造数',
    day_num45           decimal(15, 6)                         null comment '制造数',
    day_num46           decimal(15, 6)                         null comment '制造数',
    day_num47           decimal(15, 6)                         null comment '制造数',
    day_num48           decimal(15, 6)                         null comment '制造数',
    day_num49           decimal(15, 6)                         null comment '制造数',
    day_num50           decimal(15, 6)                         null comment '制造数',
    day_num51           decimal(15, 6)                         null comment '制造数',
    day_num52           decimal(15, 6)                         null comment '制造数',
    day_num53           decimal(15, 6)                         null comment '制造数',
    day_num54           decimal(15, 6)                         null comment '制造数',
    day_num55           decimal(15, 6)                         null comment '制造数',
    day_num56           decimal(15, 6)                         null comment '制造数',
    day_num57           decimal(15, 6)                         null comment '制造数',
    day_num58           decimal(15, 6)                         null comment '制造数',
    day_num59           decimal(15, 6)                         null comment '制造数',
    day_num60           decimal(15, 6)                         null comment '制造数',
    day_num61           decimal(15, 6)                         null comment '制造数',
    day_num62           decimal(15, 6)                         null comment '制造数',
    day_num63           decimal(15, 6)                         null comment '制造数',
    day_num64           decimal(15, 6)                         null comment '制造数',
    day_num65           decimal(15, 6)                         null comment '制造数',
    day_num66           decimal(15, 6)                         null comment '制造数',
    day_num67           decimal(15, 6)                         null comment '制造数',
    day_num68           decimal(15, 6)                         null comment '制造数',
    day_num69           decimal(15, 6)                         null comment '制造数',
    day_num70           decimal(15, 6)                         null comment '制造数',
    day_num71           decimal(15, 6)                         null comment '制造数',
    day_num72           decimal(15, 6)                         null comment '制造数',
    day_num73           decimal(15, 6)                         null comment '制造数',
    day_num74           decimal(15, 6)                         null comment '制造数',
    day_num75           decimal(15, 6)                         null comment '制造数',
    day_num76           decimal(15, 6)                         null comment '制造数',
    day_num77           decimal(15, 6)                         null comment '制造数',
    day_num78           decimal(15, 6)                         null comment '制造数',
    day_num79           decimal(15, 6)                         null comment '制造数',
    day_num80           decimal(15, 6)                         null comment '制造数',
    day_num81           decimal(15, 6)                         null comment '制造数',
    day_num82           decimal(15, 6)                         null comment '制造数',
    day_num83           decimal(15, 6)                         null comment '制造数',
    day_num84           decimal(15, 6)                         null comment '制造数',
    day_num85           decimal(15, 6)                         null comment '制造数',
    day_num86           decimal(15, 6)                         null comment '制造数',
    day_num87           decimal(15, 6)                         null comment '制造数',
    day_num88           decimal(15, 6)                         null comment '制造数',
    day_num89           decimal(15, 6)                         null comment '制造数',
    day_num90           decimal(15, 6)                         null comment '制造数',
    day_num91           decimal(15, 6)                         null comment '制造数',
    day_num92           decimal(15, 6)                         null comment '制造数',
    day_num93           decimal(15, 6)                         null comment '制造数',
    day_num94           decimal(15, 6)                         null comment '制造数',
    day_num95           decimal(15, 6)                         null comment '制造数',
    day_num96           decimal(15, 6)                         null comment '制造数',
    day_num97           decimal(15, 6)                         null comment '制造数',
    day_num98           decimal(15, 6)                         null comment '制造数',
    day_num99           decimal(15, 6)                         null comment '制造数',
    day_num100          decimal(15, 6)                         null comment '制造数',
    day_num101          decimal(15, 6)                         null comment '制造数',
    day_num102          decimal(15, 6)                         null comment '制造数',
    day_num103          decimal(15, 6)                         null comment '制造数',
    day_num104          decimal(15, 6)                         null comment '制造数',
    day_num105          decimal(15, 6)                         null comment '制造数',
    day_num106          decimal(15, 6)                         null comment '制造数',
    day_num107          decimal(15, 6)                         null comment '制造数',
    day_num108          decimal(15, 6)                         null comment '制造数',
    day_num109          decimal(15, 6)                         null comment '制造数',
    day_num110          decimal(15, 6)                         null comment '制造数',
    day_num111          decimal(15, 6)                         null comment '制造数',
    day_num112          decimal(15, 6)                         null comment '制造数',
    day_num113          decimal(15, 6)                         null comment '制造数',
    day_num114          decimal(15, 6)                         null comment '制造数',
    day_num115          decimal(15, 6)                         null comment '制造数',
    day_num116          decimal(15, 6)                         null comment '制造数',
    day_num117          decimal(15, 6)                         null comment '制造数',
    day_num118          decimal(15, 6)                         null comment '制造数',
    day_num119          decimal(15, 6)                         null comment '制造数',
    day_num120          decimal(15, 6)                         null comment '制造数',
    day_num121          decimal(15, 6)                         null comment '制造数',
    day_num122          decimal(15, 6)                         null comment '制造数',
    day_num123          decimal(15, 6)                         null comment '制造数',
    day_num124          decimal(15, 6)                         null comment '制造数',
    day_num125          decimal(15, 6)                         null comment '制造数',
    day_num126          decimal(15, 6)                         null comment '制造数',
    day_num127          decimal(15, 6)                         null comment '制造数',
    day_num128          decimal(15, 6)                         null comment '制造数',
    day_num129          decimal(15, 6)                         null comment '制造数',
    day_num130          decimal(15, 6)                         null comment '制造数',
    day_num131          decimal(15, 6)                         null comment '制造数',
    day_num132          decimal(15, 6)                         null comment '制造数',
    day_num133          decimal(15, 6)                         null comment '制造数',
    day_num134          decimal(15, 6)                         null comment '制造数',
    day_num135          decimal(15, 6)                         null comment '制造数',
    day_num136          decimal(15, 6)                         null comment '制造数',
    day_num137          decimal(15, 6)                         null comment '制造数',
    day_num138          decimal(15, 6)                         null comment '制造数',
    day_num139          decimal(15, 6)                         null comment '制造数',
    day_num140          decimal(15, 6)                         null comment '制造数',
    day_num141          decimal(15, 6)                         null comment '制造数',
    day_num142          decimal(15, 6)                         null comment '制造数',
    day_num143          decimal(15, 6)                         null comment '制造数',
    day_num144          decimal(15, 6)                         null comment '制造数',
    day_num145          decimal(15, 6)                         null comment '制造数',
    day_num146          decimal(15, 6)                         null comment '制造数',
    day_num147          decimal(15, 6)                         null comment '制造数',
    day_num148          decimal(15, 6)                         null comment '制造数',
    day_num149          decimal(15, 6)                         null comment '制造数',
    day_num150          decimal(15, 6)                         null comment '制造数',
    day_num151          decimal(15, 6)                         null comment '制造数',
    day_num152          decimal(15, 6)                         null comment '制造数',
    day_num153          decimal(15, 6)                         null comment '制造数',
    day_num154          decimal(15, 6)                         null comment '制造数',
    day_num155          decimal(15, 6)                         null comment '制造数',
    day_num156          decimal(15, 6)                         null comment '制造数',
    day_num157          decimal(15, 6)                         null comment '制造数',
    day_num158          decimal(15, 6)                         null comment '制造数',
    day_num159          decimal(15, 6)                         null comment '制造数',
    day_num160          decimal(15, 6)                         null comment '制造数',
    day_num161          decimal(15, 6)                         null comment '制造数',
    day_num162          decimal(15, 6)                         null comment '制造数',
    day_num163          decimal(15, 6)                         null comment '制造数',
    day_num164          decimal(15, 6)                         null comment '制造数',
    day_num165          decimal(15, 6)                         null comment '制造数',
    day_num166          decimal(15, 6)                         null comment '制造数',
    day_num167          decimal(15, 6)                         null comment '制造数',
    day_num168          decimal(15, 6)                         null comment '制造数',
    day_num169          decimal(15, 6)                         null comment '制造数',
    day_num170          decimal(15, 6)                         null comment '制造数',
    day_num171          decimal(15, 6)                         null comment '制造数',
    day_num172          decimal(15, 6)                         null comment '制造数',
    day_num173          decimal(15, 6)                         null comment '制造数',
    day_num174          decimal(15, 6)                         null comment '制造数',
    day_num175          decimal(15, 6)                         null comment '制造数',
    day_num176          decimal(15, 6)                         null comment '制造数',
    day_num177          decimal(15, 6)                         null comment '制造数',
    day_num178          decimal(15, 6)                         null comment '制造数',
    day_num179          decimal(15, 6)                         null comment '制造数',
    day_num180          decimal(15, 6)                         null comment '制造数',
    day_num181          decimal(15, 6)                         null comment '制造数',
    day_num182          decimal(15, 6)                         null comment '制造数',
    day_num183          decimal(15, 6)                         null comment '制造数',
    day_num184          decimal(15, 6)                         null comment '制造数',
    day_num185          decimal(15, 6)                         null comment '制造数',
    day_num186          decimal(15, 6)                         null comment '制造数',
    day_num187          decimal(15, 6)                         null comment '制造数',
    day_num188          decimal(15, 6)                         null comment '制造数',
    day_num189          decimal(15, 6)                         null comment '制造数',
    day_num190          decimal(15, 6)                         null comment '制造数',
    day_num191          decimal(15, 6)                         null comment '制造数',
    day_num192          decimal(15, 6)                         null comment '制造数',
    day_num193          decimal(15, 6)                         null comment '制造数',
    day_num194          decimal(15, 6)                         null comment '制造数',
    day_num195          decimal(15, 6)                         null comment '制造数',
    day_num196          decimal(15, 6)                         null comment '制造数',
    day_num197          decimal(15, 6)                         null comment '制造数',
    day_num198          decimal(15, 6)                         null comment '制造数',
    day_num199          decimal(15, 6)                         null comment '制造数',
    day_num200          decimal(15, 6)                         null comment '制造数',
    day_num201          decimal(15, 6)                         null comment '制造数',
    day_num202          decimal(15, 6)                         null comment '制造数',
    day_num203          decimal(15, 6)                         null comment '制造数',
    day_num204          decimal(15, 6)                         null comment '制造数',
    day_num205          decimal(15, 6)                         null comment '制造数',
    day_num206          decimal(15, 6)                         null comment '制造数',
    day_num207          decimal(15, 6)                         null comment '制造数',
    day_num208          decimal(15, 6)                         null comment '制造数',
    day_num209          decimal(15, 6)                         null comment '制造数',
    day_num210          decimal(15, 6)                         null comment '制造数',
    day_num211          decimal(15, 6)                         null comment '制造数',
    day_num212          decimal(15, 6)                         null comment '制造数',
    day_num213          decimal(15, 6)                         null comment '制造数',
    day_num214          decimal(15, 6)                         null comment '制造数',
    day_num215          decimal(15, 6)                         null comment '制造数',
    day_num216          decimal(15, 6)                         null comment '制造数',
    day_num217          decimal(15, 6)                         null comment '制造数',
    day_num218          decimal(15, 6)                         null comment '制造数',
    day_num219          decimal(15, 6)                         null comment '制造数',
    day_num220          decimal(15, 6)                         null comment '制造数',
    day_num221          decimal(15, 6)                         null comment '制造数',
    day_num222          decimal(15, 6)                         null comment '制造数',
    day_num223          decimal(15, 6)                         null comment '制造数',
    day_num224          decimal(15, 6)                         null comment '制造数',
    day_num225          decimal(15, 6)                         null comment '制造数',
    day_num226          decimal(15, 6)                         null comment '制造数',
    day_num227          decimal(15, 6)                         null comment '制造数',
    day_num228          decimal(15, 6)                         null comment '制造数',
    day_num229          decimal(15, 6)                         null comment '制造数',
    day_num230          decimal(15, 6)                         null comment '制造数',
    day_num231          decimal(15, 6)                         null comment '制造数',
    day_num232          decimal(15, 6)                         null comment '制造数',
    day_num233          decimal(15, 6)                         null comment '制造数',
    day_num234          decimal(15, 6)                         null comment '制造数',
    day_num235          decimal(15, 6)                         null comment '制造数',
    day_num236          decimal(15, 6)                         null comment '制造数',
    day_num237          decimal(15, 6)                         null comment '制造数',
    day_num238          decimal(15, 6)                         null comment '制造数',
    day_num239          decimal(15, 6)                         null comment '制造数',
    day_num240          decimal(15, 6)                         null comment '制造数',
    day_num241          decimal(15, 6)                         null comment '制造数',
    day_num242          decimal(15, 6)                         null comment '制造数',
    day_num243          decimal(15, 6)                         null comment '制造数',
    day_num244          decimal(15, 6)                         null comment '制造数',
    day_num245          decimal(15, 6)                         null comment '制造数',
    day_num246          decimal(15, 6)                         null comment '制造数',
    day_num247          decimal(15, 6)                         null comment '制造数',
    day_num248          decimal(15, 6)                         null comment '制造数',
    day_num249          decimal(15, 6)                         null comment '制造数',
    day_num250          decimal(15, 6)                         null comment '制造数',
    day_num251          decimal(15, 6)                         null comment '制造数',
    day_num252          decimal(15, 6)                         null comment '制造数',
    day_num253          decimal(15, 6)                         null comment '制造数',
    day_num254          decimal(15, 6)                         null comment '制造数',
    day_num255          decimal(15, 6)                         null comment '制造数',
    day_num256          decimal(15, 6)                         null comment '制造数',
    day_num257          decimal(15, 6)                         null comment '制造数',
    day_num258          decimal(15, 6)                         null comment '制造数',
    day_num259          decimal(15, 6)                         null comment '制造数',
    day_num260          decimal(15, 6)                         null comment '制造数',
    day_num261          decimal(15, 6)                         null comment '制造数',
    day_num262          decimal(15, 6)                         null comment '制造数',
    day_num263          decimal(15, 6)                         null comment '制造数',
    day_num264          decimal(15, 6)                         null comment '制造数',
    day_num265          decimal(15, 6)                         null comment '制造数',
    day_num266          decimal(15, 6)                         null comment '制造数',
    day_num267          decimal(15, 6)                         null comment '制造数',
    day_num268          decimal(15, 6)                         null comment '制造数',
    day_num269          decimal(15, 6)                         null comment '制造数',
    day_num270          decimal(15, 6)                         null comment '制造数',
    day_num271          decimal(15, 6)                         null comment '制造数',
    day_num272          decimal(15, 6)                         null comment '制造数',
    day_num273          decimal(15, 6)                         null comment '制造数',
    day_num274          decimal(15, 6)                         null comment '制造数',
    day_num275          decimal(15, 6)                         null comment '制造数',
    day_num276          decimal(15, 6)                         null comment '制造数',
    day_num277          decimal(15, 6)                         null comment '制造数',
    day_num278          decimal(15, 6)                         null comment '制造数',
    day_num279          decimal(15, 6)                         null comment '制造数',
    day_num280          decimal(15, 6)                         null comment '制造数',
    day_num281          decimal(15, 6)                         null comment '制造数',
    day_num282          decimal(15, 6)                         null comment '制造数',
    day_num283          decimal(15, 6)                         null comment '制造数',
    day_num284          decimal(15, 6)                         null comment '制造数',
    day_num285          decimal(15, 6)                         null comment '制造数',
    day_num286          decimal(15, 6)                         null comment '制造数',
    day_num287          decimal(15, 6)                         null comment '制造数',
    day_num288          decimal(15, 6)                         null comment '制造数',
    day_num289          decimal(15, 6)                         null comment '制造数',
    day_num290          decimal(15, 6)                         null comment '制造数',
    day_num291          decimal(15, 6)                         null comment '制造数',
    day_num292          decimal(15, 6)                         null comment '制造数',
    day_num293          decimal(15, 6)                         null comment '制造数',
    day_num294          decimal(15, 6)                         null comment '制造数',
    day_num295          decimal(15, 6)                         null comment '制造数',
    day_num296          decimal(15, 6)                         null comment '制造数',
    day_num297          decimal(15, 6)                         null comment '制造数',
    day_num298          decimal(15, 6)                         null comment '制造数',
    day_num299          decimal(15, 6)                         null comment '制造数',
    day_num300          decimal(15, 6)                         null comment '制造数',
    day_num301          decimal(15, 6)                         null comment '制造数',
    day_num302          decimal(15, 6)                         null comment '制造数',
    day_num303          decimal(15, 6)                         null comment '制造数',
    day_num304          decimal(15, 6)                         null comment '制造数',
    day_num305          decimal(15, 6)                         null comment '制造数',
    day_num306          decimal(15, 6)                         null comment '制造数',
    day_num307          decimal(15, 6)                         null comment '制造数',
    day_num308          decimal(15, 6)                         null comment '制造数',
    day_num309          decimal(15, 6)                         null comment '制造数',
    day_num310          decimal(15, 6)                         null comment '制造数',
    day_num311          decimal(15, 6)                         null comment '制造数',
    day_num312          decimal(15, 6)                         null comment '制造数',
    day_num313          decimal(15, 6)                         null comment '制造数',
    day_num314          decimal(15, 6)                         null comment '制造数',
    day_num315          decimal(15, 6)                         null comment '制造数',
    day_num316          decimal(15, 6)                         null comment '制造数',
    day_num317          decimal(15, 6)                         null comment '制造数',
    day_num318          decimal(15, 6)                         null comment '制造数',
    day_num319          decimal(15, 6)                         null comment '制造数',
    day_num320          decimal(15, 6)                         null comment '制造数',
    day_num321          decimal(15, 6)                         null comment '制造数',
    day_num322          decimal(15, 6)                         null comment '制造数',
    day_num323          decimal(15, 6)                         null comment '制造数',
    day_num324          decimal(15, 6)                         null comment '制造数',
    day_num325          decimal(15, 6)                         null comment '制造数',
    day_num326          decimal(15, 6)                         null comment '制造数',
    day_num327          decimal(15, 6)                         null comment '制造数',
    day_num328          decimal(15, 6)                         null comment '制造数',
    day_num329          decimal(15, 6)                         null comment '制造数',
    day_num330          decimal(15, 6)                         null comment '制造数',
    day_num331          decimal(15, 6)                         null comment '制造数',
    day_num332          decimal(15, 6)                         null comment '制造数',
    day_num333          decimal(15, 6)                         null comment '制造数',
    day_num334          decimal(15, 6)                         null comment '制造数',
    day_num335          decimal(15, 6)                         null comment '制造数',
    day_num336          decimal(15, 6)                         null comment '制造数',
    day_num337          decimal(15, 6)                         null comment '制造数',
    day_num338          decimal(15, 6)                         null comment '制造数',
    day_num339          decimal(15, 6)                         null comment '制造数',
    day_num340          decimal(15, 6)                         null comment '制造数',
    day_num341          decimal(15, 6)                         null comment '制造数',
    day_num342          decimal(15, 6)                         null comment '制造数',
    day_num343          decimal(15, 6)                         null comment '制造数',
    day_num344          decimal(15, 6)                         null comment '制造数',
    day_num345          decimal(15, 6)                         null comment '制造数',
    day_num346          decimal(15, 6)                         null comment '制造数',
    day_num347          decimal(15, 6)                         null comment '制造数',
    day_num348          decimal(15, 6)                         null comment '制造数',
    day_num349          decimal(15, 6)                         null comment '制造数',
    day_num350          decimal(15, 6)                         null comment '制造数',
    day_num351          decimal(15, 6)                         null comment '制造数',
    day_num352          decimal(15, 6)                         null comment '制造数',
    day_num353          decimal(15, 6)                         null comment '制造数',
    day_num354          decimal(15, 6)                         null comment '制造数',
    day_num355          decimal(15, 6)                         null comment '制造数',
    day_num356          decimal(15, 6)                         null comment '制造数',
    day_num357          decimal(15, 6)                         null comment '制造数',
    day_num358          decimal(15, 6)                         null comment '制造数',
    day_num359          decimal(15, 6)                         null comment '制造数',
    day_num360          decimal(15, 6)                         null comment '制造数',
    day_num361          decimal(15, 6)                         null comment '制造数',
    day_num362          decimal(15, 6)                         null comment '制造数',
    day_num363          decimal(15, 6)                         null comment '制造数',
    day_num364          decimal(15, 6)                         null comment '制造数',
    day_num365          decimal(15, 6)                         null comment '制造数',
    day_num366          decimal(15, 6)                         null comment '制造数',
    factory_id          bigint                                 null comment '工厂ID',
    tenant_id           bigint                                 null comment '租户ID',
    is_delete           tinyint(1)   default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                                 null comment '创建人',
    update_time         datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                                 null comment '修改人',
    trace_id            varchar(64)                            null comment '调用链路',
    version_num         varchar(255) default '0'               null comment '版本号',
    make_sale_config_id bigint                                 null
    )
    comment '商品预测-生产BOM使用';

create index idx_aps_goods_forecast_make_bom_use_bom_id
    on aps_goods_forecast_make_bom_use (bom_id);

create index idx_aps_goods_forecast_make_bom_use_factory_id
    on aps_goods_forecast_make_bom_use (factory_id);

create index idx_aps_goods_forecast_make_bom_use_make_month_id
    on aps_goods_forecast_make_bom_use (make_month_id);

create index idx_aps_goods_forecast_make_bom_use_tenant_id
    on aps_goods_forecast_make_bom_use (tenant_id);

create table if not exists aps_goods_forecast_make_project_data
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id            bigint                               null comment '商品ID',
    make_month_id       bigint                               null,
    project_config_code varchar(255)                         null,
    year                smallint                             null,
    day_num1            decimal(15, 6)                       null comment '制造数',
    day_num2            decimal(15, 6)                       null comment '制造数',
    day_num3            decimal(15, 6)                       null comment '制造数',
    day_num4            decimal(15, 6)                       null comment '制造数',
    day_num5            decimal(15, 6)                       null comment '制造数',
    day_num6            decimal(15, 6)                       null comment '制造数',
    day_num7            decimal(15, 6)                       null comment '制造数',
    day_num8            decimal(15, 6)                       null comment '制造数',
    day_num9            decimal(15, 6)                       null comment '制造数',
    day_num10           decimal(15, 6)                       null comment '制造数',
    day_num11           decimal(15, 6)                       null comment '制造数',
    day_num12           decimal(15, 6)                       null comment '制造数',
    day_num13           decimal(15, 6)                       null comment '制造数',
    day_num14           decimal(15, 6)                       null comment '制造数',
    day_num15           decimal(15, 6)                       null comment '制造数',
    day_num16           decimal(15, 6)                       null comment '制造数',
    day_num17           decimal(15, 6)                       null comment '制造数',
    day_num18           decimal(15, 6)                       null comment '制造数',
    day_num19           decimal(15, 6)                       null comment '制造数',
    day_num20           decimal(15, 6)                       null comment '制造数',
    day_num21           decimal(15, 6)                       null comment '制造数',
    day_num22           decimal(15, 6)                       null comment '制造数',
    day_num23           decimal(15, 6)                       null comment '制造数',
    day_num24           decimal(15, 6)                       null comment '制造数',
    day_num25           decimal(15, 6)                       null comment '制造数',
    day_num26           decimal(15, 6)                       null comment '制造数',
    day_num27           decimal(15, 6)                       null comment '制造数',
    day_num28           decimal(15, 6)                       null comment '制造数',
    day_num29           decimal(15, 6)                       null comment '制造数',
    day_num30           decimal(15, 6)                       null comment '制造数',
    day_num31           decimal(15, 6)                       null comment '制造数',
    day_num32           decimal(15, 6)                       null comment '制造数',
    day_num33           decimal(15, 6)                       null comment '制造数',
    day_num34           decimal(15, 6)                       null comment '制造数',
    day_num35           decimal(15, 6)                       null comment '制造数',
    day_num36           decimal(15, 6)                       null comment '制造数',
    day_num37           decimal(15, 6)                       null comment '制造数',
    day_num38           decimal(15, 6)                       null comment '制造数',
    day_num39           decimal(15, 6)                       null comment '制造数',
    day_num40           decimal(15, 6)                       null comment '制造数',
    day_num41           decimal(15, 6)                       null comment '制造数',
    day_num42           decimal(15, 6)                       null comment '制造数',
    day_num43           decimal(15, 6)                       null comment '制造数',
    day_num44           decimal(15, 6)                       null comment '制造数',
    day_num45           decimal(15, 6)                       null comment '制造数',
    day_num46           decimal(15, 6)                       null comment '制造数',
    day_num47           decimal(15, 6)                       null comment '制造数',
    day_num48           decimal(15, 6)                       null comment '制造数',
    day_num49           decimal(15, 6)                       null comment '制造数',
    day_num50           decimal(15, 6)                       null comment '制造数',
    day_num51           decimal(15, 6)                       null comment '制造数',
    day_num52           decimal(15, 6)                       null comment '制造数',
    day_num53           decimal(15, 6)                       null comment '制造数',
    day_num54           decimal(15, 6)                       null comment '制造数',
    day_num55           decimal(15, 6)                       null comment '制造数',
    day_num56           decimal(15, 6)                       null comment '制造数',
    day_num57           decimal(15, 6)                       null comment '制造数',
    day_num58           decimal(15, 6)                       null comment '制造数',
    day_num59           decimal(15, 6)                       null comment '制造数',
    day_num60           decimal(15, 6)                       null comment '制造数',
    day_num61           decimal(15, 6)                       null comment '制造数',
    day_num62           decimal(15, 6)                       null comment '制造数',
    day_num63           decimal(15, 6)                       null comment '制造数',
    day_num64           decimal(15, 6)                       null comment '制造数',
    day_num65           decimal(15, 6)                       null comment '制造数',
    day_num66           decimal(15, 6)                       null comment '制造数',
    day_num67           decimal(15, 6)                       null comment '制造数',
    day_num68           decimal(15, 6)                       null comment '制造数',
    day_num69           decimal(15, 6)                       null comment '制造数',
    day_num70           decimal(15, 6)                       null comment '制造数',
    day_num71           decimal(15, 6)                       null comment '制造数',
    day_num72           decimal(15, 6)                       null comment '制造数',
    day_num73           decimal(15, 6)                       null comment '制造数',
    day_num74           decimal(15, 6)                       null comment '制造数',
    day_num75           decimal(15, 6)                       null comment '制造数',
    day_num76           decimal(15, 6)                       null comment '制造数',
    day_num77           decimal(15, 6)                       null comment '制造数',
    day_num78           decimal(15, 6)                       null comment '制造数',
    day_num79           decimal(15, 6)                       null comment '制造数',
    day_num80           decimal(15, 6)                       null comment '制造数',
    day_num81           decimal(15, 6)                       null comment '制造数',
    day_num82           decimal(15, 6)                       null comment '制造数',
    day_num83           decimal(15, 6)                       null comment '制造数',
    day_num84           decimal(15, 6)                       null comment '制造数',
    day_num85           decimal(15, 6)                       null comment '制造数',
    day_num86           decimal(15, 6)                       null comment '制造数',
    day_num87           decimal(15, 6)                       null comment '制造数',
    day_num88           decimal(15, 6)                       null comment '制造数',
    day_num89           decimal(15, 6)                       null comment '制造数',
    day_num90           decimal(15, 6)                       null comment '制造数',
    day_num91           decimal(15, 6)                       null comment '制造数',
    day_num92           decimal(15, 6)                       null comment '制造数',
    day_num93           decimal(15, 6)                       null comment '制造数',
    day_num94           decimal(15, 6)                       null comment '制造数',
    day_num95           decimal(15, 6)                       null comment '制造数',
    day_num96           decimal(15, 6)                       null comment '制造数',
    day_num97           decimal(15, 6)                       null comment '制造数',
    day_num98           decimal(15, 6)                       null comment '制造数',
    day_num99           decimal(15, 6)                       null comment '制造数',
    day_num100          decimal(15, 6)                       null comment '制造数',
    day_num101          decimal(15, 6)                       null comment '制造数',
    day_num102          decimal(15, 6)                       null comment '制造数',
    day_num103          decimal(15, 6)                       null comment '制造数',
    day_num104          decimal(15, 6)                       null comment '制造数',
    day_num105          decimal(15, 6)                       null comment '制造数',
    day_num106          decimal(15, 6)                       null comment '制造数',
    day_num107          decimal(15, 6)                       null comment '制造数',
    day_num108          decimal(15, 6)                       null comment '制造数',
    day_num109          decimal(15, 6)                       null comment '制造数',
    day_num110          decimal(15, 6)                       null comment '制造数',
    day_num111          decimal(15, 6)                       null comment '制造数',
    day_num112          decimal(15, 6)                       null comment '制造数',
    day_num113          decimal(15, 6)                       null comment '制造数',
    day_num114          decimal(15, 6)                       null comment '制造数',
    day_num115          decimal(15, 6)                       null comment '制造数',
    day_num116          decimal(15, 6)                       null comment '制造数',
    day_num117          decimal(15, 6)                       null comment '制造数',
    day_num118          decimal(15, 6)                       null comment '制造数',
    day_num119          decimal(15, 6)                       null comment '制造数',
    day_num120          decimal(15, 6)                       null comment '制造数',
    day_num121          decimal(15, 6)                       null comment '制造数',
    day_num122          decimal(15, 6)                       null comment '制造数',
    day_num123          decimal(15, 6)                       null comment '制造数',
    day_num124          decimal(15, 6)                       null comment '制造数',
    day_num125          decimal(15, 6)                       null comment '制造数',
    day_num126          decimal(15, 6)                       null comment '制造数',
    day_num127          decimal(15, 6)                       null comment '制造数',
    day_num128          decimal(15, 6)                       null comment '制造数',
    day_num129          decimal(15, 6)                       null comment '制造数',
    day_num130          decimal(15, 6)                       null comment '制造数',
    day_num131          decimal(15, 6)                       null comment '制造数',
    day_num132          decimal(15, 6)                       null comment '制造数',
    day_num133          decimal(15, 6)                       null comment '制造数',
    day_num134          decimal(15, 6)                       null comment '制造数',
    day_num135          decimal(15, 6)                       null comment '制造数',
    day_num136          decimal(15, 6)                       null comment '制造数',
    day_num137          decimal(15, 6)                       null comment '制造数',
    day_num138          decimal(15, 6)                       null comment '制造数',
    day_num139          decimal(15, 6)                       null comment '制造数',
    day_num140          decimal(15, 6)                       null comment '制造数',
    day_num141          decimal(15, 6)                       null comment '制造数',
    day_num142          decimal(15, 6)                       null comment '制造数',
    day_num143          decimal(15, 6)                       null comment '制造数',
    day_num144          decimal(15, 6)                       null comment '制造数',
    day_num145          decimal(15, 6)                       null comment '制造数',
    day_num146          decimal(15, 6)                       null comment '制造数',
    day_num147          decimal(15, 6)                       null comment '制造数',
    day_num148          decimal(15, 6)                       null comment '制造数',
    day_num149          decimal(15, 6)                       null comment '制造数',
    day_num150          decimal(15, 6)                       null comment '制造数',
    day_num151          decimal(15, 6)                       null comment '制造数',
    day_num152          decimal(15, 6)                       null comment '制造数',
    day_num153          decimal(15, 6)                       null comment '制造数',
    day_num154          decimal(15, 6)                       null comment '制造数',
    day_num155          decimal(15, 6)                       null comment '制造数',
    day_num156          decimal(15, 6)                       null comment '制造数',
    day_num157          decimal(15, 6)                       null comment '制造数',
    day_num158          decimal(15, 6)                       null comment '制造数',
    day_num159          decimal(15, 6)                       null comment '制造数',
    day_num160          decimal(15, 6)                       null comment '制造数',
    day_num161          decimal(15, 6)                       null comment '制造数',
    day_num162          decimal(15, 6)                       null comment '制造数',
    day_num163          decimal(15, 6)                       null comment '制造数',
    day_num164          decimal(15, 6)                       null comment '制造数',
    day_num165          decimal(15, 6)                       null comment '制造数',
    day_num166          decimal(15, 6)                       null comment '制造数',
    day_num167          decimal(15, 6)                       null comment '制造数',
    day_num168          decimal(15, 6)                       null comment '制造数',
    day_num169          decimal(15, 6)                       null comment '制造数',
    day_num170          decimal(15, 6)                       null comment '制造数',
    day_num171          decimal(15, 6)                       null comment '制造数',
    day_num172          decimal(15, 6)                       null comment '制造数',
    day_num173          decimal(15, 6)                       null comment '制造数',
    day_num174          decimal(15, 6)                       null comment '制造数',
    day_num175          decimal(15, 6)                       null comment '制造数',
    day_num176          decimal(15, 6)                       null comment '制造数',
    day_num177          decimal(15, 6)                       null comment '制造数',
    day_num178          decimal(15, 6)                       null comment '制造数',
    day_num179          decimal(15, 6)                       null comment '制造数',
    day_num180          decimal(15, 6)                       null comment '制造数',
    day_num181          decimal(15, 6)                       null comment '制造数',
    day_num182          decimal(15, 6)                       null comment '制造数',
    day_num183          decimal(15, 6)                       null comment '制造数',
    day_num184          decimal(15, 6)                       null comment '制造数',
    day_num185          decimal(15, 6)                       null comment '制造数',
    day_num186          decimal(15, 6)                       null comment '制造数',
    day_num187          decimal(15, 6)                       null comment '制造数',
    day_num188          decimal(15, 6)                       null comment '制造数',
    day_num189          decimal(15, 6)                       null comment '制造数',
    day_num190          decimal(15, 6)                       null comment '制造数',
    day_num191          decimal(15, 6)                       null comment '制造数',
    day_num192          decimal(15, 6)                       null comment '制造数',
    day_num193          decimal(15, 6)                       null comment '制造数',
    day_num194          decimal(15, 6)                       null comment '制造数',
    day_num195          decimal(15, 6)                       null comment '制造数',
    day_num196          decimal(15, 6)                       null comment '制造数',
    day_num197          decimal(15, 6)                       null comment '制造数',
    day_num198          decimal(15, 6)                       null comment '制造数',
    day_num199          decimal(15, 6)                       null comment '制造数',
    day_num200          decimal(15, 6)                       null comment '制造数',
    day_num201          decimal(15, 6)                       null comment '制造数',
    day_num202          decimal(15, 6)                       null comment '制造数',
    day_num203          decimal(15, 6)                       null comment '制造数',
    day_num204          decimal(15, 6)                       null comment '制造数',
    day_num205          decimal(15, 6)                       null comment '制造数',
    day_num206          decimal(15, 6)                       null comment '制造数',
    day_num207          decimal(15, 6)                       null comment '制造数',
    day_num208          decimal(15, 6)                       null comment '制造数',
    day_num209          decimal(15, 6)                       null comment '制造数',
    day_num210          decimal(15, 6)                       null comment '制造数',
    day_num211          decimal(15, 6)                       null comment '制造数',
    day_num212          decimal(15, 6)                       null comment '制造数',
    day_num213          decimal(15, 6)                       null comment '制造数',
    day_num214          decimal(15, 6)                       null comment '制造数',
    day_num215          decimal(15, 6)                       null comment '制造数',
    day_num216          decimal(15, 6)                       null comment '制造数',
    day_num217          decimal(15, 6)                       null comment '制造数',
    day_num218          decimal(15, 6)                       null comment '制造数',
    day_num219          decimal(15, 6)                       null comment '制造数',
    day_num220          decimal(15, 6)                       null comment '制造数',
    day_num221          decimal(15, 6)                       null comment '制造数',
    day_num222          decimal(15, 6)                       null comment '制造数',
    day_num223          decimal(15, 6)                       null comment '制造数',
    day_num224          decimal(15, 6)                       null comment '制造数',
    day_num225          decimal(15, 6)                       null comment '制造数',
    day_num226          decimal(15, 6)                       null comment '制造数',
    day_num227          decimal(15, 6)                       null comment '制造数',
    day_num228          decimal(15, 6)                       null comment '制造数',
    day_num229          decimal(15, 6)                       null comment '制造数',
    day_num230          decimal(15, 6)                       null comment '制造数',
    day_num231          decimal(15, 6)                       null comment '制造数',
    day_num232          decimal(15, 6)                       null comment '制造数',
    day_num233          decimal(15, 6)                       null comment '制造数',
    day_num234          decimal(15, 6)                       null comment '制造数',
    day_num235          decimal(15, 6)                       null comment '制造数',
    day_num236          decimal(15, 6)                       null comment '制造数',
    day_num237          decimal(15, 6)                       null comment '制造数',
    day_num238          decimal(15, 6)                       null comment '制造数',
    day_num239          decimal(15, 6)                       null comment '制造数',
    day_num240          decimal(15, 6)                       null comment '制造数',
    day_num241          decimal(15, 6)                       null comment '制造数',
    day_num242          decimal(15, 6)                       null comment '制造数',
    day_num243          decimal(15, 6)                       null comment '制造数',
    day_num244          decimal(15, 6)                       null comment '制造数',
    day_num245          decimal(15, 6)                       null comment '制造数',
    day_num246          decimal(15, 6)                       null comment '制造数',
    day_num247          decimal(15, 6)                       null comment '制造数',
    day_num248          decimal(15, 6)                       null comment '制造数',
    day_num249          decimal(15, 6)                       null comment '制造数',
    day_num250          decimal(15, 6)                       null comment '制造数',
    day_num251          decimal(15, 6)                       null comment '制造数',
    day_num252          decimal(15, 6)                       null comment '制造数',
    day_num253          decimal(15, 6)                       null comment '制造数',
    day_num254          decimal(15, 6)                       null comment '制造数',
    day_num255          decimal(15, 6)                       null comment '制造数',
    day_num256          decimal(15, 6)                       null comment '制造数',
    day_num257          decimal(15, 6)                       null comment '制造数',
    day_num258          decimal(15, 6)                       null comment '制造数',
    day_num259          decimal(15, 6)                       null comment '制造数',
    day_num260          decimal(15, 6)                       null comment '制造数',
    day_num261          decimal(15, 6)                       null comment '制造数',
    day_num262          decimal(15, 6)                       null comment '制造数',
    day_num263          decimal(15, 6)                       null comment '制造数',
    day_num264          decimal(15, 6)                       null comment '制造数',
    day_num265          decimal(15, 6)                       null comment '制造数',
    day_num266          decimal(15, 6)                       null comment '制造数',
    day_num267          decimal(15, 6)                       null comment '制造数',
    day_num268          decimal(15, 6)                       null comment '制造数',
    day_num269          decimal(15, 6)                       null comment '制造数',
    day_num270          decimal(15, 6)                       null comment '制造数',
    day_num271          decimal(15, 6)                       null comment '制造数',
    day_num272          decimal(15, 6)                       null comment '制造数',
    day_num273          decimal(15, 6)                       null comment '制造数',
    day_num274          decimal(15, 6)                       null comment '制造数',
    day_num275          decimal(15, 6)                       null comment '制造数',
    day_num276          decimal(15, 6)                       null comment '制造数',
    day_num277          decimal(15, 6)                       null comment '制造数',
    day_num278          decimal(15, 6)                       null comment '制造数',
    day_num279          decimal(15, 6)                       null comment '制造数',
    day_num280          decimal(15, 6)                       null comment '制造数',
    day_num281          decimal(15, 6)                       null comment '制造数',
    day_num282          decimal(15, 6)                       null comment '制造数',
    day_num283          decimal(15, 6)                       null comment '制造数',
    day_num284          decimal(15, 6)                       null comment '制造数',
    day_num285          decimal(15, 6)                       null comment '制造数',
    day_num286          decimal(15, 6)                       null comment '制造数',
    day_num287          decimal(15, 6)                       null comment '制造数',
    day_num288          decimal(15, 6)                       null comment '制造数',
    day_num289          decimal(15, 6)                       null comment '制造数',
    day_num290          decimal(15, 6)                       null comment '制造数',
    day_num291          decimal(15, 6)                       null comment '制造数',
    day_num292          decimal(15, 6)                       null comment '制造数',
    day_num293          decimal(15, 6)                       null comment '制造数',
    day_num294          decimal(15, 6)                       null comment '制造数',
    day_num295          decimal(15, 6)                       null comment '制造数',
    day_num296          decimal(15, 6)                       null comment '制造数',
    day_num297          decimal(15, 6)                       null comment '制造数',
    day_num298          decimal(15, 6)                       null comment '制造数',
    day_num299          decimal(15, 6)                       null comment '制造数',
    day_num300          decimal(15, 6)                       null comment '制造数',
    day_num301          decimal(15, 6)                       null comment '制造数',
    day_num302          decimal(15, 6)                       null comment '制造数',
    day_num303          decimal(15, 6)                       null comment '制造数',
    day_num304          decimal(15, 6)                       null comment '制造数',
    day_num305          decimal(15, 6)                       null comment '制造数',
    day_num306          decimal(15, 6)                       null comment '制造数',
    day_num307          decimal(15, 6)                       null comment '制造数',
    day_num308          decimal(15, 6)                       null comment '制造数',
    day_num309          decimal(15, 6)                       null comment '制造数',
    day_num310          decimal(15, 6)                       null comment '制造数',
    day_num311          decimal(15, 6)                       null comment '制造数',
    day_num312          decimal(15, 6)                       null comment '制造数',
    day_num313          decimal(15, 6)                       null comment '制造数',
    day_num314          decimal(15, 6)                       null comment '制造数',
    day_num315          decimal(15, 6)                       null comment '制造数',
    day_num316          decimal(15, 6)                       null comment '制造数',
    day_num317          decimal(15, 6)                       null comment '制造数',
    day_num318          decimal(15, 6)                       null comment '制造数',
    day_num319          decimal(15, 6)                       null comment '制造数',
    day_num320          decimal(15, 6)                       null comment '制造数',
    day_num321          decimal(15, 6)                       null comment '制造数',
    day_num322          decimal(15, 6)                       null comment '制造数',
    day_num323          decimal(15, 6)                       null comment '制造数',
    day_num324          decimal(15, 6)                       null comment '制造数',
    day_num325          decimal(15, 6)                       null comment '制造数',
    day_num326          decimal(15, 6)                       null comment '制造数',
    day_num327          decimal(15, 6)                       null comment '制造数',
    day_num328          decimal(15, 6)                       null comment '制造数',
    day_num329          decimal(15, 6)                       null comment '制造数',
    day_num330          decimal(15, 6)                       null comment '制造数',
    day_num331          decimal(15, 6)                       null comment '制造数',
    day_num332          decimal(15, 6)                       null comment '制造数',
    day_num333          decimal(15, 6)                       null comment '制造数',
    day_num334          decimal(15, 6)                       null comment '制造数',
    day_num335          decimal(15, 6)                       null comment '制造数',
    day_num336          decimal(15, 6)                       null comment '制造数',
    day_num337          decimal(15, 6)                       null comment '制造数',
    day_num338          decimal(15, 6)                       null comment '制造数',
    day_num339          decimal(15, 6)                       null comment '制造数',
    day_num340          decimal(15, 6)                       null comment '制造数',
    day_num341          decimal(15, 6)                       null comment '制造数',
    day_num342          decimal(15, 6)                       null comment '制造数',
    day_num343          decimal(15, 6)                       null comment '制造数',
    day_num344          decimal(15, 6)                       null comment '制造数',
    day_num345          decimal(15, 6)                       null comment '制造数',
    day_num346          decimal(15, 6)                       null comment '制造数',
    day_num347          decimal(15, 6)                       null comment '制造数',
    day_num348          decimal(15, 6)                       null comment '制造数',
    day_num349          decimal(15, 6)                       null comment '制造数',
    day_num350          decimal(15, 6)                       null comment '制造数',
    day_num351          decimal(15, 6)                       null comment '制造数',
    day_num352          decimal(15, 6)                       null comment '制造数',
    day_num353          decimal(15, 6)                       null comment '制造数',
    day_num354          decimal(15, 6)                       null comment '制造数',
    day_num355          decimal(15, 6)                       null comment '制造数',
    day_num356          decimal(15, 6)                       null comment '制造数',
    day_num357          decimal(15, 6)                       null comment '制造数',
    day_num358          decimal(15, 6)                       null comment '制造数',
    day_num359          decimal(15, 6)                       null comment '制造数',
    day_num360          decimal(15, 6)                       null comment '制造数',
    day_num361          decimal(15, 6)                       null comment '制造数',
    day_num362          decimal(15, 6)                       null comment '制造数',
    day_num363          decimal(15, 6)                       null comment '制造数',
    day_num364          decimal(15, 6)                       null comment '制造数',
    day_num365          decimal(15, 6)                       null comment '制造数',
    day_num366          decimal(15, 6)                       null comment '制造数',
    factory_id          bigint                               null comment '工厂ID',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    make_sale_config_id bigint                               null
    )
    comment '预测数据';

create index idx_aps_goods_forecast_make_project_data_make_month_id
    on aps_goods_forecast_make_project_data (make_month_id);

create index idx_aps_goods_forecast_make_project_data_make_sale_config_id
    on aps_goods_forecast_make_project_data (make_sale_config_id);

create index idx_aps_goods_forecast_make_project_data_tenant_id
    on aps_goods_forecast_make_project_data (tenant_id);

create table if not exists aps_goods_forecast_make_sale_data
(
    id               bigint                               null,
    goods_id         bigint                               null comment '商品ID',
    make_month_id    bigint                               null,
    sale_config_code varchar(255)                         null,
    year             int                                  null,
    day_num1         decimal(15, 6)                       null comment '制造数',
    day_num2         decimal(15, 6)                       null comment '制造数',
    day_num3         decimal(15, 6)                       null comment '制造数',
    day_num4         decimal(15, 6)                       null comment '制造数',
    day_num5         decimal(15, 6)                       null comment '制造数',
    day_num6         decimal(15, 6)                       null comment '制造数',
    day_num7         decimal(15, 6)                       null comment '制造数',
    day_num8         decimal(15, 6)                       null comment '制造数',
    day_num9         decimal(15, 6)                       null comment '制造数',
    day_num10        decimal(15, 6)                       null comment '制造数',
    day_num11        decimal(15, 6)                       null comment '制造数',
    day_num12        decimal(15, 6)                       null comment '制造数',
    day_num13        decimal(15, 6)                       null comment '制造数',
    day_num14        decimal(15, 6)                       null comment '制造数',
    day_num15        decimal(15, 6)                       null comment '制造数',
    day_num16        decimal(15, 6)                       null comment '制造数',
    day_num17        decimal(15, 6)                       null comment '制造数',
    day_num18        decimal(15, 6)                       null comment '制造数',
    day_num19        decimal(15, 6)                       null comment '制造数',
    day_num20        decimal(15, 6)                       null comment '制造数',
    day_num21        decimal(15, 6)                       null comment '制造数',
    day_num22        decimal(15, 6)                       null comment '制造数',
    day_num23        decimal(15, 6)                       null comment '制造数',
    day_num24        decimal(15, 6)                       null comment '制造数',
    day_num25        decimal(15, 6)                       null comment '制造数',
    day_num26        decimal(15, 6)                       null comment '制造数',
    day_num27        decimal(15, 6)                       null comment '制造数',
    day_num28        decimal(15, 6)                       null comment '制造数',
    day_num29        decimal(15, 6)                       null comment '制造数',
    day_num30        decimal(15, 6)                       null comment '制造数',
    day_num31        decimal(15, 6)                       null comment '制造数',
    day_num32        decimal(15, 6)                       null comment '制造数',
    day_num33        decimal(15, 6)                       null comment '制造数',
    day_num34        decimal(15, 6)                       null comment '制造数',
    day_num35        decimal(15, 6)                       null comment '制造数',
    day_num36        decimal(15, 6)                       null comment '制造数',
    day_num37        decimal(15, 6)                       null comment '制造数',
    day_num38        decimal(15, 6)                       null comment '制造数',
    day_num39        decimal(15, 6)                       null comment '制造数',
    day_num40        decimal(15, 6)                       null comment '制造数',
    day_num41        decimal(15, 6)                       null comment '制造数',
    day_num42        decimal(15, 6)                       null comment '制造数',
    day_num43        decimal(15, 6)                       null comment '制造数',
    day_num44        decimal(15, 6)                       null comment '制造数',
    day_num45        decimal(15, 6)                       null comment '制造数',
    day_num46        decimal(15, 6)                       null comment '制造数',
    day_num47        decimal(15, 6)                       null comment '制造数',
    day_num48        decimal(15, 6)                       null comment '制造数',
    day_num49        decimal(15, 6)                       null comment '制造数',
    day_num50        decimal(15, 6)                       null comment '制造数',
    day_num51        decimal(15, 6)                       null comment '制造数',
    day_num52        decimal(15, 6)                       null comment '制造数',
    day_num53        decimal(15, 6)                       null comment '制造数',
    day_num54        decimal(15, 6)                       null comment '制造数',
    day_num55        decimal(15, 6)                       null comment '制造数',
    day_num56        decimal(15, 6)                       null comment '制造数',
    day_num57        decimal(15, 6)                       null comment '制造数',
    day_num58        decimal(15, 6)                       null comment '制造数',
    day_num59        decimal(15, 6)                       null comment '制造数',
    day_num60        decimal(15, 6)                       null comment '制造数',
    day_num61        decimal(15, 6)                       null comment '制造数',
    day_num62        decimal(15, 6)                       null comment '制造数',
    day_num63        decimal(15, 6)                       null comment '制造数',
    day_num64        decimal(15, 6)                       null comment '制造数',
    day_num65        decimal(15, 6)                       null comment '制造数',
    day_num66        decimal(15, 6)                       null comment '制造数',
    day_num67        decimal(15, 6)                       null comment '制造数',
    day_num68        decimal(15, 6)                       null comment '制造数',
    day_num69        decimal(15, 6)                       null comment '制造数',
    day_num70        decimal(15, 6)                       null comment '制造数',
    day_num71        decimal(15, 6)                       null comment '制造数',
    day_num72        decimal(15, 6)                       null comment '制造数',
    day_num73        decimal(15, 6)                       null comment '制造数',
    day_num74        decimal(15, 6)                       null comment '制造数',
    day_num75        decimal(15, 6)                       null comment '制造数',
    day_num76        decimal(15, 6)                       null comment '制造数',
    day_num77        decimal(15, 6)                       null comment '制造数',
    day_num78        decimal(15, 6)                       null comment '制造数',
    day_num79        decimal(15, 6)                       null comment '制造数',
    day_num80        decimal(15, 6)                       null comment '制造数',
    day_num81        decimal(15, 6)                       null comment '制造数',
    day_num82        decimal(15, 6)                       null comment '制造数',
    day_num83        decimal(15, 6)                       null comment '制造数',
    day_num84        decimal(15, 6)                       null comment '制造数',
    day_num85        decimal(15, 6)                       null comment '制造数',
    day_num86        decimal(15, 6)                       null comment '制造数',
    day_num87        decimal(15, 6)                       null comment '制造数',
    day_num88        decimal(15, 6)                       null comment '制造数',
    day_num89        decimal(15, 6)                       null comment '制造数',
    day_num90        decimal(15, 6)                       null comment '制造数',
    day_num91        decimal(15, 6)                       null comment '制造数',
    day_num92        decimal(15, 6)                       null comment '制造数',
    day_num93        decimal(15, 6)                       null comment '制造数',
    day_num94        decimal(15, 6)                       null comment '制造数',
    day_num95        decimal(15, 6)                       null comment '制造数',
    day_num96        decimal(15, 6)                       null comment '制造数',
    day_num97        decimal(15, 6)                       null comment '制造数',
    day_num98        decimal(15, 6)                       null comment '制造数',
    day_num99        decimal(15, 6)                       null comment '制造数',
    day_num100       decimal(15, 6)                       null comment '制造数',
    day_num101       decimal(15, 6)                       null comment '制造数',
    day_num102       decimal(15, 6)                       null comment '制造数',
    day_num103       decimal(15, 6)                       null comment '制造数',
    day_num104       decimal(15, 6)                       null comment '制造数',
    day_num105       decimal(15, 6)                       null comment '制造数',
    day_num106       decimal(15, 6)                       null comment '制造数',
    day_num107       decimal(15, 6)                       null comment '制造数',
    day_num108       decimal(15, 6)                       null comment '制造数',
    day_num109       decimal(15, 6)                       null comment '制造数',
    day_num110       decimal(15, 6)                       null comment '制造数',
    day_num111       decimal(15, 6)                       null comment '制造数',
    day_num112       decimal(15, 6)                       null comment '制造数',
    day_num113       decimal(15, 6)                       null comment '制造数',
    day_num114       decimal(15, 6)                       null comment '制造数',
    day_num115       decimal(15, 6)                       null comment '制造数',
    day_num116       decimal(15, 6)                       null comment '制造数',
    day_num117       decimal(15, 6)                       null comment '制造数',
    day_num118       decimal(15, 6)                       null comment '制造数',
    day_num119       decimal(15, 6)                       null comment '制造数',
    day_num120       decimal(15, 6)                       null comment '制造数',
    day_num121       decimal(15, 6)                       null comment '制造数',
    day_num122       decimal(15, 6)                       null comment '制造数',
    day_num123       decimal(15, 6)                       null comment '制造数',
    day_num124       decimal(15, 6)                       null comment '制造数',
    day_num125       decimal(15, 6)                       null comment '制造数',
    day_num126       decimal(15, 6)                       null comment '制造数',
    day_num127       decimal(15, 6)                       null comment '制造数',
    day_num128       decimal(15, 6)                       null comment '制造数',
    day_num129       decimal(15, 6)                       null comment '制造数',
    day_num130       decimal(15, 6)                       null comment '制造数',
    day_num131       decimal(15, 6)                       null comment '制造数',
    day_num132       decimal(15, 6)                       null comment '制造数',
    day_num133       decimal(15, 6)                       null comment '制造数',
    day_num134       decimal(15, 6)                       null comment '制造数',
    day_num135       decimal(15, 6)                       null comment '制造数',
    day_num136       decimal(15, 6)                       null comment '制造数',
    day_num137       decimal(15, 6)                       null comment '制造数',
    day_num138       decimal(15, 6)                       null comment '制造数',
    day_num139       decimal(15, 6)                       null comment '制造数',
    day_num140       decimal(15, 6)                       null comment '制造数',
    day_num141       decimal(15, 6)                       null comment '制造数',
    day_num142       decimal(15, 6)                       null comment '制造数',
    day_num143       decimal(15, 6)                       null comment '制造数',
    day_num144       decimal(15, 6)                       null comment '制造数',
    day_num145       decimal(15, 6)                       null comment '制造数',
    day_num146       decimal(15, 6)                       null comment '制造数',
    day_num147       decimal(15, 6)                       null comment '制造数',
    day_num148       decimal(15, 6)                       null comment '制造数',
    day_num149       decimal(15, 6)                       null comment '制造数',
    day_num150       decimal(15, 6)                       null comment '制造数',
    day_num151       decimal(15, 6)                       null comment '制造数',
    day_num152       decimal(15, 6)                       null comment '制造数',
    day_num153       decimal(15, 6)                       null comment '制造数',
    day_num154       decimal(15, 6)                       null comment '制造数',
    day_num155       decimal(15, 6)                       null comment '制造数',
    day_num156       decimal(15, 6)                       null comment '制造数',
    day_num157       decimal(15, 6)                       null comment '制造数',
    day_num158       decimal(15, 6)                       null comment '制造数',
    day_num159       decimal(15, 6)                       null comment '制造数',
    day_num160       decimal(15, 6)                       null comment '制造数',
    day_num161       decimal(15, 6)                       null comment '制造数',
    day_num162       decimal(15, 6)                       null comment '制造数',
    day_num163       decimal(15, 6)                       null comment '制造数',
    day_num164       decimal(15, 6)                       null comment '制造数',
    day_num165       decimal(15, 6)                       null comment '制造数',
    day_num166       decimal(15, 6)                       null comment '制造数',
    day_num167       decimal(15, 6)                       null comment '制造数',
    day_num168       decimal(15, 6)                       null comment '制造数',
    day_num169       decimal(15, 6)                       null comment '制造数',
    day_num170       decimal(15, 6)                       null comment '制造数',
    day_num171       decimal(15, 6)                       null comment '制造数',
    day_num172       decimal(15, 6)                       null comment '制造数',
    day_num173       decimal(15, 6)                       null comment '制造数',
    day_num174       decimal(15, 6)                       null comment '制造数',
    day_num175       decimal(15, 6)                       null comment '制造数',
    day_num176       decimal(15, 6)                       null comment '制造数',
    day_num177       decimal(15, 6)                       null comment '制造数',
    day_num178       decimal(15, 6)                       null comment '制造数',
    day_num179       decimal(15, 6)                       null comment '制造数',
    day_num180       decimal(15, 6)                       null comment '制造数',
    day_num181       decimal(15, 6)                       null comment '制造数',
    day_num182       decimal(15, 6)                       null comment '制造数',
    day_num183       decimal(15, 6)                       null comment '制造数',
    day_num184       decimal(15, 6)                       null comment '制造数',
    day_num185       decimal(15, 6)                       null comment '制造数',
    day_num186       decimal(15, 6)                       null comment '制造数',
    day_num187       decimal(15, 6)                       null comment '制造数',
    day_num188       decimal(15, 6)                       null comment '制造数',
    day_num189       decimal(15, 6)                       null comment '制造数',
    day_num190       decimal(15, 6)                       null comment '制造数',
    day_num191       decimal(15, 6)                       null comment '制造数',
    day_num192       decimal(15, 6)                       null comment '制造数',
    day_num193       decimal(15, 6)                       null comment '制造数',
    day_num194       decimal(15, 6)                       null comment '制造数',
    day_num195       decimal(15, 6)                       null comment '制造数',
    day_num196       decimal(15, 6)                       null comment '制造数',
    day_num197       decimal(15, 6)                       null comment '制造数',
    day_num198       decimal(15, 6)                       null comment '制造数',
    day_num199       decimal(15, 6)                       null comment '制造数',
    day_num200       decimal(15, 6)                       null comment '制造数',
    day_num201       decimal(15, 6)                       null comment '制造数',
    day_num202       decimal(15, 6)                       null comment '制造数',
    day_num203       decimal(15, 6)                       null comment '制造数',
    day_num204       decimal(15, 6)                       null comment '制造数',
    day_num205       decimal(15, 6)                       null comment '制造数',
    day_num206       decimal(15, 6)                       null comment '制造数',
    day_num207       decimal(15, 6)                       null comment '制造数',
    day_num208       decimal(15, 6)                       null comment '制造数',
    day_num209       decimal(15, 6)                       null comment '制造数',
    day_num210       decimal(15, 6)                       null comment '制造数',
    day_num211       decimal(15, 6)                       null comment '制造数',
    day_num212       decimal(15, 6)                       null comment '制造数',
    day_num213       decimal(15, 6)                       null comment '制造数',
    day_num214       decimal(15, 6)                       null comment '制造数',
    day_num215       decimal(15, 6)                       null comment '制造数',
    day_num216       decimal(15, 6)                       null comment '制造数',
    day_num217       decimal(15, 6)                       null comment '制造数',
    day_num218       decimal(15, 6)                       null comment '制造数',
    day_num219       decimal(15, 6)                       null comment '制造数',
    day_num220       decimal(15, 6)                       null comment '制造数',
    day_num221       decimal(15, 6)                       null comment '制造数',
    day_num222       decimal(15, 6)                       null comment '制造数',
    day_num223       decimal(15, 6)                       null comment '制造数',
    day_num224       decimal(15, 6)                       null comment '制造数',
    day_num225       decimal(15, 6)                       null comment '制造数',
    day_num226       decimal(15, 6)                       null comment '制造数',
    day_num227       decimal(15, 6)                       null comment '制造数',
    day_num228       decimal(15, 6)                       null comment '制造数',
    day_num229       decimal(15, 6)                       null comment '制造数',
    day_num230       decimal(15, 6)                       null comment '制造数',
    day_num231       decimal(15, 6)                       null comment '制造数',
    day_num232       decimal(15, 6)                       null comment '制造数',
    day_num233       decimal(15, 6)                       null comment '制造数',
    day_num234       decimal(15, 6)                       null comment '制造数',
    day_num235       decimal(15, 6)                       null comment '制造数',
    day_num236       decimal(15, 6)                       null comment '制造数',
    day_num237       decimal(15, 6)                       null comment '制造数',
    day_num238       decimal(15, 6)                       null comment '制造数',
    day_num239       decimal(15, 6)                       null comment '制造数',
    day_num240       decimal(15, 6)                       null comment '制造数',
    day_num241       decimal(15, 6)                       null comment '制造数',
    day_num242       decimal(15, 6)                       null comment '制造数',
    day_num243       decimal(15, 6)                       null comment '制造数',
    day_num244       decimal(15, 6)                       null comment '制造数',
    day_num245       decimal(15, 6)                       null comment '制造数',
    day_num246       decimal(15, 6)                       null comment '制造数',
    day_num247       decimal(15, 6)                       null comment '制造数',
    day_num248       decimal(15, 6)                       null comment '制造数',
    day_num249       decimal(15, 6)                       null comment '制造数',
    day_num250       decimal(15, 6)                       null comment '制造数',
    day_num251       decimal(15, 6)                       null comment '制造数',
    day_num252       decimal(15, 6)                       null comment '制造数',
    day_num253       decimal(15, 6)                       null comment '制造数',
    day_num254       decimal(15, 6)                       null comment '制造数',
    day_num255       decimal(15, 6)                       null comment '制造数',
    day_num256       decimal(15, 6)                       null comment '制造数',
    day_num257       decimal(15, 6)                       null comment '制造数',
    day_num258       decimal(15, 6)                       null comment '制造数',
    day_num259       decimal(15, 6)                       null comment '制造数',
    day_num260       decimal(15, 6)                       null comment '制造数',
    day_num261       decimal(15, 6)                       null comment '制造数',
    day_num262       decimal(15, 6)                       null comment '制造数',
    day_num263       decimal(15, 6)                       null comment '制造数',
    day_num264       decimal(15, 6)                       null comment '制造数',
    day_num265       decimal(15, 6)                       null comment '制造数',
    day_num266       decimal(15, 6)                       null comment '制造数',
    day_num267       decimal(15, 6)                       null comment '制造数',
    day_num268       decimal(15, 6)                       null comment '制造数',
    day_num269       decimal(15, 6)                       null comment '制造数',
    day_num270       decimal(15, 6)                       null comment '制造数',
    day_num271       decimal(15, 6)                       null comment '制造数',
    day_num272       decimal(15, 6)                       null comment '制造数',
    day_num273       decimal(15, 6)                       null comment '制造数',
    day_num274       decimal(15, 6)                       null comment '制造数',
    day_num275       decimal(15, 6)                       null comment '制造数',
    day_num276       decimal(15, 6)                       null comment '制造数',
    day_num277       decimal(15, 6)                       null comment '制造数',
    day_num278       decimal(15, 6)                       null comment '制造数',
    day_num279       decimal(15, 6)                       null comment '制造数',
    day_num280       decimal(15, 6)                       null comment '制造数',
    day_num281       decimal(15, 6)                       null comment '制造数',
    day_num282       decimal(15, 6)                       null comment '制造数',
    day_num283       decimal(15, 6)                       null comment '制造数',
    day_num284       decimal(15, 6)                       null comment '制造数',
    day_num285       decimal(15, 6)                       null comment '制造数',
    day_num286       decimal(15, 6)                       null comment '制造数',
    day_num287       decimal(15, 6)                       null comment '制造数',
    day_num288       decimal(15, 6)                       null comment '制造数',
    day_num289       decimal(15, 6)                       null comment '制造数',
    day_num290       decimal(15, 6)                       null comment '制造数',
    day_num291       decimal(15, 6)                       null comment '制造数',
    day_num292       decimal(15, 6)                       null comment '制造数',
    day_num293       decimal(15, 6)                       null comment '制造数',
    day_num294       decimal(15, 6)                       null comment '制造数',
    day_num295       decimal(15, 6)                       null comment '制造数',
    day_num296       decimal(15, 6)                       null comment '制造数',
    day_num297       decimal(15, 6)                       null comment '制造数',
    day_num298       decimal(15, 6)                       null comment '制造数',
    day_num299       decimal(15, 6)                       null comment '制造数',
    day_num300       decimal(15, 6)                       null comment '制造数',
    day_num301       decimal(15, 6)                       null comment '制造数',
    day_num302       decimal(15, 6)                       null comment '制造数',
    day_num303       decimal(15, 6)                       null comment '制造数',
    day_num304       decimal(15, 6)                       null comment '制造数',
    day_num305       decimal(15, 6)                       null comment '制造数',
    day_num306       decimal(15, 6)                       null comment '制造数',
    day_num307       decimal(15, 6)                       null comment '制造数',
    day_num308       decimal(15, 6)                       null comment '制造数',
    day_num309       decimal(15, 6)                       null comment '制造数',
    day_num310       decimal(15, 6)                       null comment '制造数',
    day_num311       decimal(15, 6)                       null comment '制造数',
    day_num312       decimal(15, 6)                       null comment '制造数',
    day_num313       decimal(15, 6)                       null comment '制造数',
    day_num314       decimal(15, 6)                       null comment '制造数',
    day_num315       decimal(15, 6)                       null comment '制造数',
    day_num316       decimal(15, 6)                       null comment '制造数',
    day_num317       decimal(15, 6)                       null comment '制造数',
    day_num318       decimal(15, 6)                       null comment '制造数',
    day_num319       decimal(15, 6)                       null comment '制造数',
    day_num320       decimal(15, 6)                       null comment '制造数',
    day_num321       decimal(15, 6)                       null comment '制造数',
    day_num322       decimal(15, 6)                       null comment '制造数',
    day_num323       decimal(15, 6)                       null comment '制造数',
    day_num324       decimal(15, 6)                       null comment '制造数',
    day_num325       decimal(15, 6)                       null comment '制造数',
    day_num326       decimal(15, 6)                       null comment '制造数',
    day_num327       decimal(15, 6)                       null comment '制造数',
    day_num328       decimal(15, 6)                       null comment '制造数',
    day_num329       decimal(15, 6)                       null comment '制造数',
    day_num330       decimal(15, 6)                       null comment '制造数',
    day_num331       decimal(15, 6)                       null comment '制造数',
    day_num332       decimal(15, 6)                       null comment '制造数',
    day_num333       decimal(15, 6)                       null comment '制造数',
    day_num334       decimal(15, 6)                       null comment '制造数',
    day_num335       decimal(15, 6)                       null comment '制造数',
    day_num336       decimal(15, 6)                       null comment '制造数',
    day_num337       decimal(15, 6)                       null comment '制造数',
    day_num338       decimal(15, 6)                       null comment '制造数',
    day_num339       decimal(15, 6)                       null comment '制造数',
    day_num340       decimal(15, 6)                       null comment '制造数',
    day_num341       decimal(15, 6)                       null comment '制造数',
    day_num342       decimal(15, 6)                       null comment '制造数',
    day_num343       decimal(15, 6)                       null comment '制造数',
    day_num344       decimal(15, 6)                       null comment '制造数',
    day_num345       decimal(15, 6)                       null comment '制造数',
    day_num346       decimal(15, 6)                       null comment '制造数',
    day_num347       decimal(15, 6)                       null comment '制造数',
    day_num348       decimal(15, 6)                       null comment '制造数',
    day_num349       decimal(15, 6)                       null comment '制造数',
    day_num350       decimal(15, 6)                       null comment '制造数',
    day_num351       decimal(15, 6)                       null comment '制造数',
    day_num352       decimal(15, 6)                       null comment '制造数',
    day_num353       decimal(15, 6)                       null comment '制造数',
    day_num354       decimal(15, 6)                       null comment '制造数',
    day_num355       decimal(15, 6)                       null comment '制造数',
    day_num356       decimal(15, 6)                       null comment '制造数',
    day_num357       decimal(15, 6)                       null comment '制造数',
    day_num358       decimal(15, 6)                       null comment '制造数',
    day_num359       decimal(15, 6)                       null comment '制造数',
    day_num360       decimal(15, 6)                       null comment '制造数',
    day_num361       decimal(15, 6)                       null comment '制造数',
    day_num362       decimal(15, 6)                       null comment '制造数',
    day_num363       decimal(15, 6)                       null comment '制造数',
    day_num364       decimal(15, 6)                       null comment '制造数',
    day_num365       decimal(15, 6)                       null comment '制造数',
    day_num366       decimal(15, 6)                       null comment '制造数',
    factory_id       bigint                               null comment '工厂ID',
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号',
    month            varchar(255)                         null,
    months           varchar(255)                         null,
    forecast_status  int                                  null,
    week_num01_0     int                                  null
    )
    comment '预测数据';

create index idx_aps_goods_forecast_make_sale_data_factory_id
    on aps_goods_forecast_make_sale_data (factory_id);

create index idx_aps_goods_forecast_make_sale_data_goods_id
    on aps_goods_forecast_make_sale_data (goods_id);

create index idx_aps_goods_forecast_make_sale_data_make_month_id
    on aps_goods_forecast_make_sale_data (make_month_id);

create index idx_aps_goods_forecast_make_sale_data_tenant_id
    on aps_goods_forecast_make_sale_data (tenant_id);

create table if not exists aps_goods_forecast_user_goods_data
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    forecast_id bigint                               null,
    year        varchar(255)                         null,
    month_01    decimal(15, 6)                       null,
    month_02    decimal(15, 6)                       null,
    month_03    decimal(15, 6)                       null,
    month_04    decimal(15, 6)                       null,
    month_05    decimal(15, 6)                       null,
    month_06    decimal(15, 6)                       null,
    month_07    decimal(15, 6)                       null,
    month_08    decimal(15, 6)                       null,
    month_09    decimal(15, 6)                       null,
    month_10    decimal(15, 6)                       null,
    month_11    decimal(15, 6)                       null,
    month_12    decimal(15, 6)                       null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    );

create index idx_aps_goods_forecast_user_goods_data_forecast_id
    on aps_goods_forecast_user_goods_data (forecast_id);

create index idx_aps_goods_forecast_user_goods_data_tenant_id
    on aps_goods_forecast_user_goods_data (tenant_id);

create table if not exists aps_goods_forecast_user_sale_data
(
    id             bigint                               null,
    forecast_id    bigint                               null,
    sale_config_id varchar(255)                         null,
    year           varchar(255)                         null,
    month_01       decimal(15, 6)                       null,
    month_02       decimal(15, 6)                       null,
    month_03       decimal(15, 6)                       null,
    month_04       decimal(15, 6)                       null,
    month_05       decimal(15, 6)                       null,
    month_06       decimal(15, 6)                       null,
    month_07       decimal(15, 6)                       null,
    month_08       decimal(15, 6)                       null,
    month_09       decimal(15, 6)                       null,
    month_10       decimal(15, 6)                       null,
    month_11       decimal(15, 6)                       null,
    month_12       decimal(15, 6)                       null,
    tenant_id      bigint                               null comment '租户ID',
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      bigint                               null comment '修改人',
    trace_id       varchar(64)                          null comment '调用链路',
    version_num    int        default 0                 null comment '版本号'
    )
    comment '预测数据';

create index idx_aps_goods_forecast_user_sale_data_forecast_id
    on aps_goods_forecast_user_sale_data (forecast_id);

create index idx_aps_goods_forecast_user_sale_data_sale_config_id
    on aps_goods_forecast_user_sale_data (sale_config_id);

create index idx_aps_goods_forecast_user_sale_data_tenant_id
    on aps_goods_forecast_user_sale_data (tenant_id);

create table if not exists aps_goods_sale_item
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id        bigint                               null comment '商品ID',
    sale_goods_id   bigint                               null,
    sale_config_id  bigint                               null,
    use_forecast    decimal(15, 6)                       null,
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    supplier_status varchar(255)                         null,
    version_num     int        default 0                 null comment '版本号'
    )
    comment '商品销售配置';

create index idx_aps_goods_sale_item_goods_id
    on aps_goods_sale_item (goods_id);

create index idx_aps_goods_sale_item_sale_config_id
    on aps_goods_sale_item (sale_config_id);

create index idx_aps_goods_sale_item_tenant_id
    on aps_goods_sale_item (tenant_id);

create table if not exists aps_goods_sale_project_config
(
    id                       bigint auto_increment comment 'ID 自增'
    primary key,
    goods_id                 bigint                               null comment '商品ID',
    factory_id               varchar(255)                         null,
    project_config_id        bigint                               null,
    project_config_name      varchar(255)                         null,
    project_config_parent_id bigint                               null,
    quantity                 smallint                             null,
    sale_config_id           bigint                               null,
    sale_config_name         varchar(255)                         null,
    sale_config_parent_id    bigint                               null,
    tenant_id                bigint                               null comment '租户ID',
    is_delete                tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time              datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                bigint                               null comment '创建人',
    update_time              datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                bigint                               null comment '修改人',
    trace_id                 varchar(64)                          null comment '调用链路',
    version_num              int        default 0                 null comment '版本号',
    current_index            tinyint                              null
    )
    comment '项目配置';

create index idx_aps_goods_sale_project_config_factory_id
    on aps_goods_sale_project_config (factory_id);

create index idx_aps_goods_sale_project_config_goods_id
    on aps_goods_sale_project_config (goods_id);

create index idx_aps_goods_sale_project_config_project_config_id
    on aps_goods_sale_project_config (project_config_id);

create index idx_aps_goods_sale_project_config_project_config_parent_id
    on aps_goods_sale_project_config (project_config_parent_id);

create index idx_aps_goods_sale_project_config_sale_config_id
    on aps_goods_sale_project_config (sale_config_id);

create index idx_aps_goods_sale_project_config_sale_config_parent_id
    on aps_goods_sale_project_config (sale_config_parent_id);

create index idx_aps_goods_sale_project_config_tenant_id
    on aps_goods_sale_project_config (tenant_id);

create table if not exists aps_logistics_path
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    logistics_path_code   varchar(255)                         null comment '物流路径编码',
    logistics_path_name   varchar(255)                         null comment '物流路径名称',
    logistics_path_remark varchar(255)                         null comment '备注',
    is_default            tinyint    default 0                 null comment '是否默认 0 否,1 是',
    factory_id            bigint                               null comment '工厂ID',
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号'
    )
    comment '物流路径表';

create index idx_aps_logistics_path_factory_id
    on aps_logistics_path (factory_id);

create index idx_aps_logistics_path_tenant_id
    on aps_logistics_path (tenant_id);

create table if not exists aps_logistics_path_item
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    logistics_path_id bigint                               null comment '物流路径id',
    province_code     varchar(255)                         null comment '省编码',
    province_name     varchar(255)                         null comment '省名称',
    city_code         varchar(255)                         null comment '市编码',
    city_name         varchar(255)                         null comment '市名称',
    transport_day     int                                  null comment '运输天数',
    is_default        tinyint    default 0                 null comment '是否默认 0 否,1 是',
    factory_id        bigint                               null comment '工厂ID',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment '物流路详情径表';

create index idx_logistics_path_id
    on aps_logistics_path_item (logistics_path_id);

create table if not exists aps_make_capacity_factory
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    factory_id             bigint                               null comment '工厂ID',
    make_capacity_quantity varchar(255)                         null,
    year                   smallint                             null,
    month                  tinyint                              null,
    day_min1               decimal(15, 6)                       null comment '制造数',
    day_max1               decimal(15, 6)                       null comment '制造数',
    day_min2               decimal(15, 6)                       null comment '制造数',
    day_max2               decimal(15, 6)                       null comment '制造数',
    day_min3               decimal(15, 6)                       null comment '制造数',
    day_max3               decimal(15, 6)                       null comment '制造数',
    day_min4               decimal(15, 6)                       null comment '制造数',
    day_max4               decimal(15, 6)                       null comment '制造数',
    day_min5               decimal(15, 6)                       null comment '制造数',
    day_max5               decimal(15, 6)                       null comment '制造数',
    day_min6               decimal(15, 6)                       null comment '制造数',
    day_max6               decimal(15, 6)                       null comment '制造数',
    day_min7               decimal(15, 6)                       null comment '制造数',
    day_max7               decimal(15, 6)                       null comment '制造数',
    day_min8               decimal(15, 6)                       null comment '制造数',
    day_max8               decimal(15, 6)                       null comment '制造数',
    day_min9               decimal(15, 6)                       null comment '制造数',
    day_max9               decimal(15, 6)                       null comment '制造数',
    day_min10              decimal(15, 6)                       null comment '制造数',
    day_max10              decimal(15, 6)                       null comment '制造数',
    day_min11              decimal(15, 6)                       null comment '制造数',
    day_max11              decimal(15, 6)                       null comment '制造数',
    day_min12              decimal(15, 6)                       null comment '制造数',
    day_max12              decimal(15, 6)                       null comment '制造数',
    day_min13              decimal(15, 6)                       null comment '制造数',
    day_max13              decimal(15, 6)                       null comment '制造数',
    day_min14              decimal(15, 6)                       null comment '制造数',
    day_max14              decimal(15, 6)                       null comment '制造数',
    day_min15              decimal(15, 6)                       null comment '制造数',
    day_max15              decimal(15, 6)                       null comment '制造数',
    day_min16              decimal(15, 6)                       null comment '制造数',
    day_max16              decimal(15, 6)                       null comment '制造数',
    day_min17              decimal(15, 6)                       null comment '制造数',
    day_max17              decimal(15, 6)                       null comment '制造数',
    day_min18              decimal(15, 6)                       null comment '制造数',
    day_max18              decimal(15, 6)                       null comment '制造数',
    day_min19              decimal(15, 6)                       null comment '制造数',
    day_max19              decimal(15, 6)                       null comment '制造数',
    day_min20              decimal(15, 6)                       null comment '制造数',
    day_max20              decimal(15, 6)                       null comment '制造数',
    day_min21              decimal(15, 6)                       null comment '制造数',
    day_max21              decimal(15, 6)                       null comment '制造数',
    day_min22              decimal(15, 6)                       null comment '制造数',
    day_max22              decimal(15, 6)                       null comment '制造数',
    day_min23              decimal(15, 6)                       null comment '制造数',
    day_max23              decimal(15, 6)                       null comment '制造数',
    day_min24              decimal(15, 6)                       null comment '制造数',
    day_max24              decimal(15, 6)                       null comment '制造数',
    day_min25              decimal(15, 6)                       null comment '制造数',
    day_max25              decimal(15, 6)                       null comment '制造数',
    day_min26              decimal(15, 6)                       null comment '制造数',
    day_max26              decimal(15, 6)                       null comment '制造数',
    day_min27              decimal(15, 6)                       null comment '制造数',
    day_max27              decimal(15, 6)                       null comment '制造数',
    day_min28              decimal(15, 6)                       null comment '制造数',
    day_max28              decimal(15, 6)                       null comment '制造数',
    day_min29              decimal(15, 6)                       null comment '制造数',
    day_max29              decimal(15, 6)                       null comment '制造数',
    day_min30              decimal(15, 6)                       null comment '制造数',
    day_max30              decimal(15, 6)                       null comment '制造数',
    day_min31              decimal(15, 6)                       null comment '制造数',
    day_max31              decimal(15, 6)                       null comment '制造数',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号'
    )
    comment '工厂产能';

create index idx_aps_make_capacity_factory_factory_id
    on aps_make_capacity_factory (factory_id);

create index idx_aps_make_capacity_factory_tenant_id
    on aps_make_capacity_factory (tenant_id);

create table if not exists aps_make_capacity_goods
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    factory_id             bigint                               null comment '工厂ID',
    goods_id               bigint                               null comment '商品ID',
    make_capacity_quantity varchar(255)                         null,
    year                   smallint                             null,
    month                  tinyint                              null,
    day_min1               decimal(15, 6)                       null comment '制造数',
    day_max1               decimal(15, 6)                       null comment '制造数',
    day_min2               decimal(15, 6)                       null comment '制造数',
    day_max2               decimal(15, 6)                       null comment '制造数',
    day_min3               decimal(15, 6)                       null comment '制造数',
    day_max3               decimal(15, 6)                       null comment '制造数',
    day_min4               decimal(15, 6)                       null comment '制造数',
    day_max4               decimal(15, 6)                       null comment '制造数',
    day_min5               decimal(15, 6)                       null comment '制造数',
    day_max5               decimal(15, 6)                       null comment '制造数',
    day_min6               decimal(15, 6)                       null comment '制造数',
    day_max6               decimal(15, 6)                       null comment '制造数',
    day_min7               decimal(15, 6)                       null comment '制造数',
    day_max7               decimal(15, 6)                       null comment '制造数',
    day_min8               decimal(15, 6)                       null comment '制造数',
    day_max8               decimal(15, 6)                       null comment '制造数',
    day_min9               decimal(15, 6)                       null comment '制造数',
    day_max9               decimal(15, 6)                       null comment '制造数',
    day_min10              decimal(15, 6)                       null comment '制造数',
    day_max10              decimal(15, 6)                       null comment '制造数',
    day_min11              decimal(15, 6)                       null comment '制造数',
    day_max11              decimal(15, 6)                       null comment '制造数',
    day_min12              decimal(15, 6)                       null comment '制造数',
    day_max12              decimal(15, 6)                       null comment '制造数',
    day_min13              decimal(15, 6)                       null comment '制造数',
    day_max13              decimal(15, 6)                       null comment '制造数',
    day_min14              decimal(15, 6)                       null comment '制造数',
    day_max14              decimal(15, 6)                       null comment '制造数',
    day_min15              decimal(15, 6)                       null comment '制造数',
    day_max15              decimal(15, 6)                       null comment '制造数',
    day_min16              decimal(15, 6)                       null comment '制造数',
    day_max16              decimal(15, 6)                       null comment '制造数',
    day_min17              decimal(15, 6)                       null comment '制造数',
    day_max17              decimal(15, 6)                       null comment '制造数',
    day_min18              decimal(15, 6)                       null comment '制造数',
    day_max18              decimal(15, 6)                       null comment '制造数',
    day_min19              decimal(15, 6)                       null comment '制造数',
    day_max19              decimal(15, 6)                       null comment '制造数',
    day_min20              decimal(15, 6)                       null comment '制造数',
    day_max20              decimal(15, 6)                       null comment '制造数',
    day_min21              decimal(15, 6)                       null comment '制造数',
    day_max21              decimal(15, 6)                       null comment '制造数',
    day_min22              decimal(15, 6)                       null comment '制造数',
    day_max22              decimal(15, 6)                       null comment '制造数',
    day_min23              decimal(15, 6)                       null comment '制造数',
    day_max23              decimal(15, 6)                       null comment '制造数',
    day_min24              decimal(15, 6)                       null comment '制造数',
    day_max24              decimal(15, 6)                       null comment '制造数',
    day_min25              decimal(15, 6)                       null comment '制造数',
    day_max25              decimal(15, 6)                       null comment '制造数',
    day_min26              decimal(15, 6)                       null comment '制造数',
    day_max26              decimal(15, 6)                       null comment '制造数',
    day_min27              decimal(15, 6)                       null comment '制造数',
    day_max27              decimal(15, 6)                       null comment '制造数',
    day_min28              decimal(15, 6)                       null comment '制造数',
    day_max28              decimal(15, 6)                       null comment '制造数',
    day_min29              decimal(15, 6)                       null comment '制造数',
    day_max29              decimal(15, 6)                       null comment '制造数',
    day_min30              decimal(15, 6)                       null comment '制造数',
    day_max30              decimal(15, 6)                       null comment '制造数',
    day_min31              decimal(15, 6)                       null comment '制造数',
    day_max31              decimal(15, 6)                       null comment '制造数',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号'
    )
    comment '工厂产能商品';

create index idx_aps_make_capacity_goods_goods_id
    on aps_make_capacity_goods (goods_id);

create index idx_aps_make_capacity_goods_tenant_id
    on aps_make_capacity_goods (tenant_id);

create table if not exists aps_make_capacity_sale_config
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    sale_config_id         bigint                               null,
    make_capacity_quantity varchar(255)                         null,
    year                   smallint                             null,
    month                  tinyint                              null,
    day_min1               decimal(15, 6)                       null comment '制造数',
    day_max1               decimal(15, 6)                       null comment '制造数',
    day_min2               decimal(15, 6)                       null comment '制造数',
    day_max2               decimal(15, 6)                       null comment '制造数',
    day_min3               decimal(15, 6)                       null comment '制造数',
    day_max3               decimal(15, 6)                       null comment '制造数',
    day_min4               decimal(15, 6)                       null comment '制造数',
    day_max4               decimal(15, 6)                       null comment '制造数',
    day_min5               decimal(15, 6)                       null comment '制造数',
    day_max5               decimal(15, 6)                       null comment '制造数',
    day_min6               decimal(15, 6)                       null comment '制造数',
    day_max6               decimal(15, 6)                       null comment '制造数',
    day_min7               decimal(15, 6)                       null comment '制造数',
    day_max7               decimal(15, 6)                       null comment '制造数',
    day_min8               decimal(15, 6)                       null comment '制造数',
    day_max8               decimal(15, 6)                       null comment '制造数',
    day_min9               decimal(15, 6)                       null comment '制造数',
    day_max9               decimal(15, 6)                       null comment '制造数',
    day_min10              decimal(15, 6)                       null comment '制造数',
    day_max10              decimal(15, 6)                       null comment '制造数',
    day_min11              decimal(15, 6)                       null comment '制造数',
    day_max11              decimal(15, 6)                       null comment '制造数',
    day_min12              decimal(15, 6)                       null comment '制造数',
    day_max12              decimal(15, 6)                       null comment '制造数',
    day_min13              decimal(15, 6)                       null comment '制造数',
    day_max13              decimal(15, 6)                       null comment '制造数',
    day_min14              decimal(15, 6)                       null comment '制造数',
    day_max14              decimal(15, 6)                       null comment '制造数',
    day_min15              decimal(15, 6)                       null comment '制造数',
    day_max15              decimal(15, 6)                       null comment '制造数',
    day_min16              decimal(15, 6)                       null comment '制造数',
    day_max16              decimal(15, 6)                       null comment '制造数',
    day_min17              decimal(15, 6)                       null comment '制造数',
    day_max17              decimal(15, 6)                       null comment '制造数',
    day_min18              decimal(15, 6)                       null comment '制造数',
    day_max18              decimal(15, 6)                       null comment '制造数',
    day_min19              decimal(15, 6)                       null comment '制造数',
    day_max19              decimal(15, 6)                       null comment '制造数',
    day_min20              decimal(15, 6)                       null comment '制造数',
    day_max20              decimal(15, 6)                       null comment '制造数',
    day_min21              decimal(15, 6)                       null comment '制造数',
    day_max21              decimal(15, 6)                       null comment '制造数',
    day_min22              decimal(15, 6)                       null comment '制造数',
    day_max22              decimal(15, 6)                       null comment '制造数',
    day_min23              decimal(15, 6)                       null comment '制造数',
    day_max23              decimal(15, 6)                       null comment '制造数',
    day_min24              decimal(15, 6)                       null comment '制造数',
    day_max24              decimal(15, 6)                       null comment '制造数',
    day_min25              decimal(15, 6)                       null comment '制造数',
    day_max25              decimal(15, 6)                       null comment '制造数',
    day_min26              decimal(15, 6)                       null comment '制造数',
    day_max26              decimal(15, 6)                       null comment '制造数',
    day_min27              decimal(15, 6)                       null comment '制造数',
    day_max27              decimal(15, 6)                       null comment '制造数',
    day_min28              decimal(15, 6)                       null comment '制造数',
    day_max28              decimal(15, 6)                       null comment '制造数',
    day_min29              decimal(15, 6)                       null comment '制造数',
    day_max29              decimal(15, 6)                       null comment '制造数',
    day_min30              decimal(15, 6)                       null comment '制造数',
    day_max30              decimal(15, 6)                       null comment '制造数',
    day_min31              decimal(15, 6)                       null comment '制造数',
    day_max31              decimal(15, 6)                       null comment '制造数',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号'
    )
    comment '工厂产能销售配置';

create index idx_aps_make_capacity_sale_config_sale_config_id
    on aps_make_capacity_sale_config (sale_config_id);

create index idx_aps_make_capacity_sale_config_tenant_id
    on aps_make_capacity_sale_config (tenant_id);

create table if not exists aps_order
(
    id                    bigint                               null,
    order_no              varchar(255)                         null,
    order_remark          varchar(255)                         null,
    order_status          bigint                               null comment '订单状态',
    order_total_price     decimal(15, 6)                       null comment '成本价',
    goods_id              bigint                               null comment '商品ID',
    reserve_amount        decimal(15, 6)                       null comment '总价',
    reserve_datetime      varchar(255)                         null,
    finish_payed_amount   decimal(15, 6)                       null comment '总价',
    finish_payed_datetime varchar(255)                         null,
    make_finish_date      varchar(255)                         null,
    delivery_date         varchar(255)                         null,
    factory_id            bigint                               null comment '工厂ID',
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    urgency_level         int        default 0                 null comment '紧急度0最小,越大越紧急',
    scheduling_date       date                                 null comment '排产时间'
    )
    comment '订单表';

create index idx_aps_order_tenant_id
    on aps_order (tenant_id);

create table if not exists aps_order_goods
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    order_id               bigint                               null,
    goods_id               bigint                               null comment '商品ID',
    goods_name             varchar(255)                         null,
    goods_remark           varchar(255)                         null,
    goods_amount           decimal(20, 6)                       null comment '总价',
    goods_price            decimal(15, 6)                       null comment '成本价',
    goods_total_price      decimal(15, 6)                       null comment '成本价',
    goods_unit             varchar(255)                         null,
    goods_unit_price       decimal(15, 6)                       null comment '成本价',
    goods_unit_total_price decimal(15, 6)                       null comment '成本价',
    goods_status_id        bigint                               null comment '订单状态',
    factory_id             bigint                               null comment '工厂ID',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号'
    )
    comment '订单商品表';

create index idx_aps_order_goods_factory_id
    on aps_order_goods (factory_id);

create index idx_aps_order_goods_goods_id
    on aps_order_goods (goods_id);

create index idx_aps_order_goods_order_id
    on aps_order_goods (order_id);

create index idx_aps_order_goods_tenant_id
    on aps_order_goods (tenant_id);

create table if not exists aps_order_goods_bom
(
    id                   bigint auto_increment comment 'ID 自增'
    primary key,
    order_id             bigint                               null comment '订单ID',
    goods_id             bigint                               null comment '商品ID',
    goods_status_id      bigint                               null comment '订单状态',
    bom_id               bigint                               null comment '零件ID',
    bom_code             varchar(255)                         null comment 'bom 编码',
    bom_name             varchar(255)                         null comment 'bom 名称',
    bom_usage            decimal(15, 6)                       null comment '使用量',
    bom_unit            varchar(255) null comment '规格',
    bom_cost_price       decimal(15, 6)                       null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(64)                          null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
    )
    comment '订单商品零件表';

create index idx_aps_order_goods_bom_factory_id
    on aps_order_goods_bom (factory_id);

create index idx_aps_order_goods_bom_goods_id
    on aps_order_goods_bom (goods_id);

create index idx_aps_order_goods_bom_order_id
    on aps_order_goods_bom (order_id);

create index idx_aps_order_goods_bom_tenant_id
    on aps_order_goods_bom (tenant_id);

create table if not exists aps_order_goods_forecast_make
(
    id                 bigint auto_increment comment 'ID 自增'
    primary key,
    order_id           bigint                               null,
    goods_id           bigint                               null comment '商品ID',
    goods_status_id    bigint                               null comment '订单状态',
    goods_status_name  varchar(255)                         null comment '节点名称',
    forecast_make_date date                                 null comment '预计制造日期',
    factory_id         bigint                               null comment '工厂ID',
    tenant_id          bigint                               null comment '租户ID',
    is_delete          tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time        datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by          bigint                               null comment '创建人',
    update_time        datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by          bigint                               null comment '修改人',
    trace_id           varchar(64)                          null comment '调用链路',
    version_num        int        default 0                 null comment '版本号'
    )
    comment '订单商品节点预测表';

create index idx_aps_order_goods_factory_id
    on aps_order_goods_forecast_make (factory_id);

create index idx_aps_order_goods_goods_id
    on aps_order_goods_forecast_make (goods_id);

create index idx_aps_order_goods_order_id
    on aps_order_goods_forecast_make (order_id);

create index idx_aps_order_goods_tenant_id
    on aps_order_goods_forecast_make (tenant_id);

create table if not exists aps_order_goods_project_config
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    order_id    bigint                               null,
    goods_id    bigint                               null comment '商品ID',
    config_id   bigint                               null,
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '订单商品项目配置表';

create table if not exists aps_order_goods_sale_config
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    order_id    bigint                               null,
    goods_id    bigint                               null comment '商品ID',
    config_id   bigint                               null,
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '订单商品销售配置表';

create index idx_aps_order_goods_sale_config_config_id
    on aps_order_goods_sale_config (config_id);

create index idx_aps_order_goods_sale_config_factory_id
    on aps_order_goods_sale_config (factory_id);

create index idx_aps_order_goods_sale_config_goods_id
    on aps_order_goods_sale_config (goods_id);

create index idx_aps_order_goods_sale_config_order_id
    on aps_order_goods_sale_config (order_id);

create index idx_aps_order_goods_sale_config_tenant_id
    on aps_order_goods_sale_config (tenant_id);

create table if not exists aps_order_goods_status_date
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    order_id               bigint                               null,
    goods_id               bigint                               null comment '商品ID',
    goods_status_id        bigint                               null comment '订单状态',
    factory_id             bigint                               null comment '工厂ID',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    status_index           int        default 0                 null comment '状态索引',
    expect_make_begin_time date                                 null comment '预计开始时间',
    expect_make_end_time   date                                 null comment '预计结束时间',
    actual_make_begin_time datetime                             null comment '实际开始时间',
    actual_make_end_time   datetime                             null comment '实际结束时间'
    )
    comment '订单商品状态表';

create index idx_order_goods_id
    on aps_order_goods_status_date (goods_id);

create index idx_order_id
    on aps_order_goods_status_date (order_id);

create table if not exists aps_order_user
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    order_id      bigint                               null,
    user_name     varchar(255)                         null,
    user_phone    bigint                               null,
    user_sex      tinyint                              null,
    country_code  varchar(255)                         null,
    province_code varchar(255)                         null,
    city_code     varchar(255)                         null,
    area_code     varchar(255)                         null,
    user_address  varchar(255)                         null,
    user_remark   varchar(255)                         null,
    factory_id    bigint                               null comment '工厂ID',
    tenant_id     bigint                               null comment '租户ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号'
    )
    comment '订单用户表';

create index idx_aps_order_user_order_id
    on aps_order_user (order_id);

create index idx_aps_order_user_tenant_id
    on aps_order_user (tenant_id);

create table if not exists aps_process_path
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    process_path_code   varchar(255)                         null,
    process_path_name   varchar(255)                         null,
    process_path_remark varchar(255)                         null,
    is_default          tinyint                              null,
    factory_id          bigint                               null comment '工厂ID',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号'
    )
    comment '流程路径表';

create index idx_aps_process_path_factory_id
    on aps_process_path (factory_id);

create index idx_aps_process_path_tenant_id
    on aps_process_path (tenant_id);

create table if not exists aps_process_path_room
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    process_path_id varchar(255)                         null,
    room_id         bigint                               null,
    factory_id      bigint                               null comment '工厂ID',
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    version_num     int        default 0                 null comment '版本号'
    )
    comment '流程路径房间表';

create index idx_aps_process_path_room_process_path_id
    on aps_process_path_room (process_path_id);

create index idx_aps_process_path_room_room_id
    on aps_process_path_room (room_id);

create index idx_aps_process_path_room_tenant_id
    on aps_process_path_room (tenant_id);

create table if not exists aps_project_config
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    sale_code       varchar(255)                         null,
    sale_name       varchar(255)                         null,
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    supplier_status varchar(255)                         null,
    version_num     int        default 0                 null comment '版本号',
    is_value        tinyint                              null,
    parent_id       bigint                               null
    )
    comment '项目配置表';

create index idx_aps_project_config_parent_id
    on aps_project_config (parent_id);

create index idx_aps_project_config_tenant_id
    on aps_project_config (tenant_id);

create table if not exists aps_rolling_forecast_factory_capacity
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    factory_id  bigint                               null comment '工厂ID',
    year        int                                  null comment '年份',
    month       int                                  null comment '月份',
    day01       int                                  null comment '01日期产量',
    day02       int                                  null comment '02日期产量',
    day03       int                                  null comment '03日期产量',
    day04       int                                  null comment '04日期产量',
    day05       int                                  null comment '05日期产量',
    day06       int                                  null comment '06日期产量',
    day07       int                                  null comment '07日期产量',
    day08       int                                  null comment '08日期产量',
    day09       int                                  null comment '09日期产量',
    day10       int                                  null comment '10日期产量',
    day11       int                                  null comment '11日期产量',
    day12       int                                  null comment '12日期产量',
    day13       int                                  null comment '13日期产量',
    day14       int                                  null comment '14日期产量',
    day15       int                                  null comment '15日期产量',
    day16       int                                  null comment '16日期产量',
    day17       int                                  null comment '17日期产量',
    day18       int                                  null comment '18日期产量',
    day19       int                                  null comment '19日期产量',
    day20       int                                  null comment '20日期产量',
    day21       int                                  null comment '21日期产量',
    day22       int                                  null comment '22日期产量',
    day23       int                                  null comment '23日期产量',
    day24       int                                  null comment '24日期产量',
    day25       int                                  null comment '25日期产量',
    day26       int                                  null comment '26日期产量',
    day27       int                                  null comment '27日期产量',
    day28       int                                  null comment '28日期产量',
    day29       int                                  null comment '29日期产量',
    day30       int                                  null comment '30日期产量',
    day31       int                                  null comment '31日期产量',
    capacity    int        default 0                 null comment '容量',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '滚动预测';

create table if not exists aps_rolling_forecast_order
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    roll_code       varchar(64)                          not null comment '唯一编码',
    roll_name       varchar(64)                          not null comment '名称',
    factory_id      bigint                               null comment '工厂Id',
    begin_status_id bigint                               null comment '开始节点',
    end_status_id   bigint                               null comment '结束节点',
    begin_time      date                                 not null comment '开始时间',
    end_time        date                                 not null comment '结束时间',
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    version_num     int        default 0                 null comment '版本号'
    )
    comment '滚动预测';

create table if not exists aps_rolling_forecast_order_item
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    forecast_id       bigint                               not null comment '预测ID',
    factory_id        bigint                               not null comment '工厂ID',
    order_id          bigint                               not null comment '订单ID',
    goods_status_id   bigint                               not null comment '状态ID',
    status_begin_date date                                 not null comment '状态开始时间',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment '滚动预测订单节点表';

create index idx_forecast_id
    on aps_rolling_forecast_order_item (forecast_id);

create index idx_order_id
    on aps_rolling_forecast_order_item (order_id);

create table if not exists aps_room
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    room_code   varchar(255)                         null,
    room_name   varchar(255)                         null,
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '房间配置表';

create index idx_aps_room_factory_id
    on aps_room (factory_id);

create index idx_aps_room_tenant_id
    on aps_room (tenant_id);

create table if not exists aps_room_config
(
    id           bigint auto_increment comment 'ID 自增'
    primary key,
    room_id      varchar(255)                         null,
    section_id   bigint                               null,
    station_id   bigint                               null,
    execute_time varchar(255)                         null,
    factory_id   bigint                               null comment '工厂ID',
    tenant_id    bigint                               null comment '租户ID',
    is_delete    tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by    bigint                               null comment '创建人',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by    bigint                               null comment '修改人',
    trace_id     varchar(64)                          null comment '调用链路',
    version_num  int        default 0                 null comment '版本号',
    status_id    bigint                               null
    )
    comment '房间配置表';

create index idx_aps_room_config_room_id
    on aps_room_config (room_id);

create index idx_aps_room_config_section_id
    on aps_room_config (section_id);

create index idx_aps_room_config_station_id
    on aps_room_config (station_id);

create index idx_aps_room_config_status_id
    on aps_room_config (status_id);

create index idx_aps_room_config_tenant_id
    on aps_room_config (tenant_id);

create table if not exists aps_sale_config
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    sale_code       varchar(255)                         null,
    sale_name       varchar(255)                         null,
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       varchar(255)                         null,
    trace_id        varchar(64)                          null comment '调用链路',
    supplier_status varchar(255)                         null,
    version_num     int        default 0                 null comment '版本号',
    is_value        tinyint                              null,
    parent_id       bigint                               null
    )
    comment '销售配置表';

create index idx_aps_sale_config_parent_id
    on aps_sale_config (parent_id);

create index idx_aps_sale_config_tenant_id
    on aps_sale_config (tenant_id);

create table if not exists aps_scheduling_constraints
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    constraints_no      varchar(255)                         null,
    constraints_name    varchar(255)                         null,
    constraints_context text                                 null,
    constraints_remark  varchar(255)                         null,
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号'
    )
    comment '排产约束表';

create index idx_aps_scheduling_constraints_tenant_id
    on aps_scheduling_constraints (tenant_id);

create table if not exists aps_scheduling_day_config
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    factory_id          bigint                               null comment '工厂ID',
    process_id          bigint                               null comment '工艺路径ID',
    scheduling_day_no   varchar(255)                         null comment '排程版本号',
    scheduling_day_name varchar(255)                         null comment '排程版本名称',
    is_default          tinyint(1) default 0                 null comment '是否默认 0 否,1 是',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号'
    )
    comment '排程版本表';

create table if not exists aps_scheduling_day_config_item
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_day_id bigint                               null comment '排程版本ID',
    process_id        bigint                               null comment '工艺路径ID',
    room_id           bigint                               null comment '车间ID',
    status_id         bigint                               null comment '状态ID',
    config_biz_type   varchar(255)                         null comment '配置类型 sale,part,bom ,sleep',
    config_biz_id     bigint                               null comment '配置业务ID',
    config_biz_name   varchar(255)                         null comment '配置业务名称',
    config_biz_num    bigint                               null comment '配置业务数量',
    config_biz_time   bigint                               null comment '配置业务耗时(秒)',
    is_default        tinyint(1) default 0                 null comment '是否默认 0 否,1 是',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment '排程版本配置表';

create index scheduling_day_id
    on aps_scheduling_day_config_item (scheduling_day_id);

create table if not exists aps_scheduling_day_config_version
(
    id                        bigint auto_increment comment 'ID 自增'
    primary key,
    factory_id                bigint                               null comment '工厂ID',
    scheduling_day_version_no varchar(255)                         null comment '排程版本号',
    scheduling_day            date                                 null comment '排程日期',
    is_issued_third           tinyint(1) default 0                 null comment '是否下发 0 否,1 是',
    tenant_id                 bigint                               null comment '租户ID',
    is_delete                 tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time               datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                 bigint                               null comment '创建人',
    update_time               datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                 bigint                               null comment '修改人',
    trace_id                  varchar(64)                          null comment '调用链路',
    version_num               int        default 0                 null comment '版本号',
    process_id                bigint                               not null comment '工艺路径id',
    header_list               varchar(1024)                        not null comment '订单号'
    )
    comment '排程版本';

create table if not exists aps_scheduling_day_config_version_detail
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_day_id bigint                               null,
    config_biz_type   varchar(255)                         null comment '配置类型 sale,part,bom',
    config_biz_id     bigint                               null comment '配置业务ID',
    config_biz_name   varchar(255)                         null comment '配置业务名称',
    config_biz_num    bigint                               null comment '配置业务数量',
    order_id          bigint                               null comment '订单ID',
    order_no          varchar(255)                         null comment '订单编号',
    room_id           bigint                               null comment '房间id',
    status_id         bigint                               null comment '状态 Id',
    sort_index        int                                  null comment '排序',
    is_match          tinyint(1) default 0                 null comment '是否匹配 0 否,1 是',
    loop_index        int                                  null comment '循环次数',
    loop_enough       tinyint(1) default 0                 null comment '是否满足 0 否,1 是',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment '排程版本配置明细表';

create index idx_order_id
    on aps_scheduling_day_config_version_detail (order_id);

create index idx_scheduling_day_id
    on aps_scheduling_day_config_version_detail (scheduling_day_id);

create table if not exists aps_scheduling_goods_bom
(
    id                   bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_id        bigint                               null comment '排产ID',
    order_id             bigint                               null comment '订单ID',
    goods_id             bigint                               null comment '商品ID',
    goods_status_id      bigint                               null comment '订单状态',
    bom_id               bigint                               null comment '零件ID',
    bom_code             varchar(255)                         null comment 'bom 编码',
    bom_name             varchar(255)                         null comment 'bom 名称',
    bom_usage            decimal(15, 6)                       null comment '使用量',
    bom_unit            varchar(255) null comment '规格',
    bom_cost_price       decimal(15, 6)                       null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(64)                          null comment '调用链路',
    version_num          int        default 0                 null comment '版本号',
    goods_bom_id         bigint                               null comment '商品bom表id'
    )
    comment '订单商品零件表';

create index idx_aps_scheduling_goods_bom_factory_id
    on aps_scheduling_goods_bom (factory_id);

create index idx_aps_scheduling_goods_bom_goods_id
    on aps_scheduling_goods_bom (goods_id);

create index idx_aps_scheduling_goods_bom_scheduling_id
    on aps_scheduling_goods_bom (scheduling_id);

create index idx_aps_scheduling_goods_bom_tenant_id
    on aps_scheduling_goods_bom (tenant_id);

create table if not exists aps_scheduling_goods_bom_total
(
    id                   bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_id        bigint                               null comment '订单ID',
    bom_id               bigint                               null comment '零件ID',
    goods_bom_id         bigint                               null comment '商品零件ID',
    bom_code             varchar(255)                         null comment 'bom 编码',
    bom_name             varchar(255)                         null comment 'bom 名称',
    bom_usage            decimal(15, 6)                       null comment '使用量',
    bom_unit            varchar(255) null comment '规格',
    bom_cost_price       decimal(15, 6)                       null comment '成本价',
    bom_cost_price_unit varchar(255) null comment '规格',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(64)                          null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
    )
    comment '订单商品零件汇总表';

create index idx_aps_scheduling_goods_bom_total_scheduling_id
    on aps_scheduling_goods_bom_total (scheduling_id);

create index idx_aps_scheduling_goods_bom_total_tenant_id
    on aps_scheduling_goods_bom_total (tenant_id);

create table if not exists aps_scheduling_goods_status_date
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_id          bigint                               null comment '排产ID',
    order_id               bigint                               null comment '订单ID',
    goods_id               bigint                               null comment '商品ID',
    goods_status_id        bigint                               null comment '订单状态',
    expect_make_time       date                                 null comment '期望制造时间',
    actual_make_time       date                                 null comment '实际制造时间',
    factory_id             bigint                               null comment '工厂ID',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    status_index           int        default 0                 null comment '状态索引',
    expect_make_begin_time date                                 null comment '预计开始时间',
    expect_make_end_time   date                                 null comment '预计结束时间',
    actual_make_begin_time date                                 null comment '实际开始时间',
    actual_make_end_time   date                                 null comment '实际结束时间'
    )
    comment '订单商品状态表';

create index idx_scheduling_id
    on aps_scheduling_goods_status_date (scheduling_id);

create table if not exists aps_scheduling_issue_item
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_version_id bigint                               null comment '排产版本ID',
    current_day           varchar(255)                         null comment '当前日期',
    order_id              bigint                               null comment '订单ID',
    goods_id              bigint                               null comment '商品ID',
    number_index          int                                  null comment '生产序号',
    factory_id            bigint                               null comment '工厂id',
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             varchar(255)                         null,
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    order_no              varchar(64)                          not null comment '订单号'
    )
    comment '排产下发详情';

create index idx_current_day
    on aps_scheduling_issue_item (current_day);

create index idx_factory_id
    on aps_scheduling_issue_item (factory_id);

create table if not exists aps_scheduling_version
(
    id                        bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_version_no     varchar(255)                         null,
    tenant_id                 bigint                               null comment '租户ID',
    is_delete                 tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time               datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                 bigint                               null comment '创建人',
    update_time               datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                 bigint                               null comment '修改人',
    trace_id                  varchar(64)                          null comment '调用链路',
    version_num               int        default 0                 null comment '版本号',
    scheduling_version_name   varchar(255)                         null,
    scheduling_constraints_id varchar(255)                         null,
    header_list               text                                 null,
    capacity_header_list      text                                 null,
    capacity_date_list        varchar(1024)                        null,
    scheduling_day_count      bigint                               null,
    version_step              smallint                             null,
    version_step_error        varchar(255)                         null,
    bom_total_end_date        date                                 null comment 'bom汇总结束日期'
    )
    comment '排产版本表';

create index idx_aps_scheduling_version_scheduling_constraints_id
    on aps_scheduling_version (scheduling_constraints_id);

create index idx_aps_scheduling_version_tenant_id
    on aps_scheduling_version (tenant_id);

create table if not exists aps_scheduling_version_capacity
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_version_id bigint                               null,
    current_day           varchar(255)                         null,
    order_id              bigint                               null,
    goods_id              bigint                               null comment '商品ID',
    field0                varchar(255)                         null,
    field1                varchar(255)                         null,
    field2                varchar(255)                         null,
    field3                varchar(255)                         null,
    field4                varchar(255)                         null,
    field5                varchar(255)                         null,
    field6                varchar(255)                         null,
    field7                varchar(255)                         null,
    field8                varchar(255)                         null,
    field9                varchar(255)                         null,
    field10               varchar(255)                         null,
    field11               varchar(255)                         null,
    field12               varchar(255)                         null,
    field13               varchar(255)                         null,
    field14               varchar(255)                         null,
    field15               varchar(255)                         null,
    field16               varchar(255)                         null,
    field17               varchar(255)                         null,
    field18               varchar(255)                         null,
    field19               varchar(255)                         null,
    field20               varchar(255)                         null,
    limit_result          varchar(255)                         null,
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             varchar(255)                         null,
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    result_type           tinyint                              null,
    number_index          varchar(255)                         null,
    factory_id            bigint                               null comment '工厂id',
    goods_status_id       bigint                               null comment '商品状态',
    order_no              varchar(64)                          not null comment '订单号'
    )
    comment '排产版本容量表';

create index idx_aps_scheduling_version_capacity_goods_id
    on aps_scheduling_version_capacity (goods_id);

create index idx_aps_scheduling_version_capacity_order_id
    on aps_scheduling_version_capacity (order_id);

create index idx_aps_scheduling_version_capacity_scheduling_version_id
    on aps_scheduling_version_capacity (scheduling_version_id);

create index idx_aps_scheduling_version_capacity_tenant_id
    on aps_scheduling_version_capacity (tenant_id);

create table if not exists aps_scheduling_version_day
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    version_id  bigint                               null,
    current_day varchar(255)                         null,
    has_enough  tinyint                              null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '排产版本天表';

create index idx_aps_scheduling_version_day_tenant_id
    on aps_scheduling_version_day (tenant_id);

create index idx_aps_scheduling_version_day_version_id
    on aps_scheduling_version_day (version_id);

create table if not exists aps_scheduling_version_item
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    scheduling_version_id bigint                               null,
    order_id              bigint                               null,
    order_no              varchar(64)                          null comment '订单号',
    goods_id              bigint                               null comment '商品ID',
    field0                varchar(255)                         null,
    field1                varchar(255)                         null,
    field2                varchar(255)                         null,
    field3                varchar(255)                         null,
    field4                varchar(255)                         null,
    field5                varchar(255)                         null,
    field6                varchar(255)                         null,
    field7                varchar(255)                         null,
    field8                varchar(255)                         null,
    field9                varchar(255)                         null,
    field10               varchar(255)                         null,
    field11               varchar(255)                         null,
    field12               varchar(255)                         null,
    field13               varchar(255)                         null,
    field14               varchar(255)                         null,
    field15               varchar(255)                         null,
    field16               varchar(255)                         null,
    field17               varchar(255)                         null,
    field18               varchar(255)                         null,
    field19               varchar(255)                         null,
    field20               varchar(255)                         null,
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    result_type           tinyint                              null,
    number_index          smallint                             null,
    factory_id            varchar(255)                         null
    )
    comment '排产版本项表';

create index idx_aps_scheduling_version_item_factory_id
    on aps_scheduling_version_item (factory_id);

create index idx_aps_scheduling_version_item_goods_id
    on aps_scheduling_version_item (goods_id);

create index idx_aps_scheduling_version_item_order_id
    on aps_scheduling_version_item (order_id);

create index idx_aps_scheduling_version_item_scheduling_version_id
    on aps_scheduling_version_item (scheduling_version_id);

create index idx_aps_scheduling_version_item_tenant_id
    on aps_scheduling_version_item (tenant_id);

create table if not exists aps_scheduling_version_limit
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    version_id    bigint                               null,
    show_name     varchar(255)                         null,
    field_name    varchar(255)                         null,
    field_value   varchar(255)                         null,
    current_count tinyint                              null,
    min           tinyint                              null,
    max           tinyint                              null,
    tenant_id     bigint                               null comment '租户ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    current_day   varchar(255)                         null,
    limit_type    varchar(255)                         null
    )
    comment '排产版本限制表';

create index idx_aps_scheduling_version_limit_tenant_id
    on aps_scheduling_version_limit (tenant_id);

create index idx_aps_scheduling_version_limit_version_id
    on aps_scheduling_version_limit (version_id);

create table if not exists aps_status
(
    id                  bigint auto_increment comment 'ID 自增'
    primary key,
    status_code         varchar(255)                         null,
    status_name         varchar(255)                         null,
    factory_id          varchar(255)                         null,
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(64)                          null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    is_order_goods_init tinyint    default 0                 null comment '是否初始化订单状态'
    )
    comment '排产状态表';

create index idx_aps_status_factory_id
    on aps_status (factory_id);

create index idx_aps_status_tenant_id
    on aps_status (tenant_id);

create table if not exists aps_workshop_section
(
    id             bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id      bigint                               null comment '租户ID',
    factory_id     bigint                               null comment '工厂ID',
    section_name   varchar(255)                         null,
    section_code   varchar(255)                         null,
    section_type   varchar(255)                         null,
    section_status varchar(255)                         null,
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      varchar(255)                         null,
    trace_id       varchar(64)                          null comment '调用链路',
    version_num    int        default 0                 null comment '版本号'
    )
    comment '车间/工位表';

create index idx_aps_workshop_section_tenant_id
    on aps_workshop_section (tenant_id);

create table if not exists aps_workshop_station
(
    id             bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id      bigint                               null comment '租户ID',
    factory_id     bigint                               null comment '工厂ID',
    section_id     bigint                               null,
    station_name   varchar(255)                         null,
    station_code   varchar(255)                         null,
    station_type   varchar(255)                         null,
    station_status varchar(255)                         null,
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      varchar(255)                         null,
    trace_id       varchar(64)                          null comment '调用链路',
    version_num    int        default 0                 null comment '版本号'
    )
    comment '车间/工位表';

create index idx_aps_workshop_station_tenant_id
    on aps_workshop_station (tenant_id);

create table if not exists base_app
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    app_code    varchar(64)                          null comment 'app编码',
    app_name    varchar(64)                          null comment 'app名称',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '应用表';

create index idx_app_code
    on base_app (app_code);

create table if not exists base_app_resource
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    app_id      bigint                               null comment '应用ID',
    resource_id bigint                               null comment '资源ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '资源';

create index idx_app_id
    on base_app_resource (app_id);

create table if not exists base_dept
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    dept_code   varchar(64)                          null comment '部门编码',
    dept_name   varchar(64)                          null comment '部门名称',
    parent_id   bigint                               null comment '父部门ID',
    path        varchar(512)                         null comment '部门路径',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '部门表';

create table if not exists base_resource
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    resource_code varchar(64)                          null comment '菜单编码',
    resource_name varchar(64)                          null comment '菜单名称',
    resource_url  varchar(512)                         null comment '菜单URL',
    resource_icon varchar(64)                          null comment '菜单图标',
    resource_type varchar(64)                          null comment '菜单类型',
    is_button     tinyint(1) default 0                 null comment '是否按钮 0 否,1 是',
    is_hidden     tinyint                              null comment '是否因此 0否,1是',
    parent_id     bigint                               null comment '父菜单ID',
    path          varchar(512)                         null comment '菜单路径',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    tenant_id     bigint                               null comment '租户ID'
    )
    comment '资源';

create table if not exists base_role
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    role_code     varchar(64)                          null comment '角色编码',
    role_name     varchar(64)                          null comment '角色名称',
    role_group_id bigint                               null comment '角色组',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    tenant_id     bigint                               null comment '租户ID'
    )
    comment '角色表';

create table if not exists base_role_group
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    role_group_code varchar(64)                          null comment '角色组编码',
    role_group_name varchar(64)                          null comment '角色组名称',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    version_num     int        default 0                 null comment '版本号',
    tenant_id       bigint                               null comment '租户ID'
    )
    comment '角色组表';

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

create table if not exists base_role_resource
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    role_id     bigint                               null comment '角色ID',
    resource_id bigint                               null comment '菜单ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '角色资源表';

create table if not exists base_supplier
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    supplier_name   varchar(255)                         null,
    supplier_code   varchar(255)                         null,
    supplier_phone  varchar(255)                         null,
    supplier_email  varchar(255)                         null,
    supplier_addr   varchar(255)                         null,
    supplier_remark varchar(255)                         null,
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    supplier_status varchar(255)                         null,
    version_num     int        default 0                 null comment '版本号',
    tenant_id       bigint                               null comment '租户ID'
    )
    comment '供应商表';

create index idx_base_supplier_tenant_id
    on base_supplier (tenant_id);

create table if not exists base_table_header
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    biz_key     varchar(255)                         null,
    field_name  varchar(255)                         null,
    show_name   varchar(255)                         null,
    width_px    varchar(255)                         null,
    sort_index  int                                  null comment '排序',
    is_picture  varchar(255)                         null,
    plan_status varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '表头配置表';

create index idx_base_table_header_tenant_id
    on base_table_header (tenant_id);

create index idx_biz_key
    on base_table_header (biz_key);

create table if not exists base_user_dept
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    user_id     bigint                               null comment '用户ID',
    dept_id     bigint                               null comment '部门ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '用户部门表';

create index idx_user_id_dept_id
    on base_user_dept (user_id, dept_id);

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

create table if not exists base_user_role
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    user_id     bigint                               null comment '用户ID',
    role_id     bigint                               null comment '角色ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
    )
    comment '用户角色表';

create index idx_user_id
    on base_user_role (user_id);

create table if not exists base_user_role_group
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    user_id       bigint                               null comment '用户ID',
    role_group_id bigint                               null comment '角色组ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    tenant_id     bigint                               null comment '租户ID'
    )
    comment '用户角色组表';

create index idx_user_id
    on base_user_role_group (user_id);

create table if not exists flow_definition
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    flow_name     varchar(64)                          null comment '工作流名称',
    flow_group_id bigint                               null comment '工作流组ID',
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号',
    flow_key      varchar(64)                          null comment '流程key',
    tenant_id     bigint                               null comment '租户ID',
    flow_form_id  bigint                               null comment '流程表单id'
    )
    comment '工作定义表';

create table if not exists flow_form
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    form_name   varchar(64)                          null comment '表单名称',
    form_code   varchar(64)                          null comment '表单编码',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '工作流表单表';

create index idx_flow_form_code
    on flow_form (form_code);

create index idx_form_code
    on flow_form (form_code);

create table if not exists flow_form_item
(
    id                      bigint auto_increment comment 'ID 自增'
    primary key,
    form_id                 bigint                               null comment '表单ID',
    form_item_name          varchar(64)                          null comment '表单项名称',
    form_item_filed         varchar(64)                          null comment '表单项字段',
    form_item_value         varchar(512)                         null comment '表单项类型',
    form_item_default_value varchar(512)                         null comment '表单项默认值',
    form_item_value_type    varchar(64)                          null comment '表单值类型  text , date , dateTime ,array',
    is_add_flow_value       tinyint(1) default 0                 null comment '是否添加流程变量 0 否,1 是',
    is_required             tinyint(1) default 0                 null comment '是否必填 0 否,1 是',
    sort_index              int        default 0                 null comment '排序',
    lose_focus_event        varchar(512)                         null comment '失去焦点事件',
    tenant_id               bigint                               null comment '租户ID',
    is_delete               tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time             datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by               bigint                               null comment '创建人',
    update_time             datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by               bigint                               null comment '修改人',
    trace_id                varchar(64)                          null comment '调用链路',
    version_num             int        default 0                 null comment '版本号'
    )
    comment '工作流表单项表';

create index idx_form_id
    on flow_form_item (form_id);

create table if not exists flow_form_user_value
(
    id                      bigint auto_increment comment 'ID 自增'
    primary key,
    process_instance_id     varchar(255)                         null comment '流程实例ID',
    business_key            varchar(64)                          null comment '业务主键',
    form_id                 bigint                               null comment '表单ID',
    form_item_default_value varchar(255)                         null comment '表单默认值',
    form_item_filed         varchar(255)                         null comment '表单字段',
    form_item_name          varchar(255)                         null comment '表单名称',
    form_item_value         varchar(255)                         null comment '表单值',
    form_item_value_type    varchar(255)                         null comment '表单值类型',
    is_add_flow_value       tinyint(1) default 0                 null comment '是否添加流程表单值 0 否,1 是',
    is_required             tinyint(1) default 0                 null comment '是否必填 0 否,1 是',
    lose_focus_event        varchar(255)                         null comment '失去焦点事件',
    sort_index              int                                  null comment '排序',
    user_value              varchar(255)                         null comment '用户值',
    tenant_id               bigint                               null comment '租户ID',
    is_delete               tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time             datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by               bigint                               null comment '创建人',
    update_time             datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by               bigint                               null comment '修改人',
    trace_id                varchar(64)                          null comment '调用链路',
    version_num             int        default 0                 null comment '版本号'
    )
    comment '工作流表单用户数据表';

create index idx_business_key
    on flow_form_user_value (business_key);

create index idx_process_instance_id
    on flow_form_user_value (process_instance_id);

create table if not exists flow_group
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    flow_group_code varchar(64)                          null comment '工作流组编码',
    flow_group_name varchar(64)                          null comment '工作流组名称',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    version_num     int        default 0                 null comment '版本号',
    tenant_id       bigint                               null comment '租户ID'
    )
    comment '工作流组表';

create table if not exists jcx_buy_order
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    order_name             varchar(255)                         null,
    order_remark           varchar(255)                         null,
    order_status           tinyint                              null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    tenant_id              bigint                               null comment '租户ID',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    is_used                varchar(255)                         null,
    version_num            int        default 0                 null comment '版本号',
    order_no               varchar(255)                         null,
    goods_name             varchar(255)                         null,
    goods_cost_price_total decimal(15, 6)                       null comment '成本总价'
    )
    comment '进销存-采购订单表';

create index idx_jcx_buy_order_tenant_id
    on jcx_buy_order (tenant_id);

create table if not exists jcx_buy_order_item
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    order_id               bigint                               null,
    goods_id               bigint                               null comment '商品ID',
    goods_cost_price       decimal(15, 6)                       null comment '成本价',
    goods_buy_count        decimal(15, 6)                       null comment '购买数',
    goods_unit             varchar(255)                         null,
    goods_cost_price_total decimal(15, 6)                       null comment '成本总额',
    goods_buy_remark       varchar(255)                         null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    tenant_id              bigint                               null comment '租户ID',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    is_used                varchar(255)                         null,
    version_num            int        default 0                 null comment '版本号',
    goods_name             varchar(255)                         null,
    supplier_id            varchar(255)                         null,
    supplier_name          varchar(255)                         null
    )
    comment '进销存-采购订单明细表';

create index idx_jcx_buy_order_item_goods_id
    on jcx_buy_order_item (goods_id);

create index idx_jcx_buy_order_item_order_id
    on jcx_buy_order_item (order_id);

create index idx_jcx_buy_order_item_supplier_id
    on jcx_buy_order_item (supplier_id);

create index idx_jcx_buy_order_item_tenant_id
    on jcx_buy_order_item (tenant_id);

create table if not exists jcx_buy_plan
(
    id                       bigint auto_increment comment 'ID 自增'
    primary key,
    plan_name                varchar(255)                         null,
    plan_status              varchar(255)                         null,
    is_delete                tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time              datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                bigint                               null comment '创建人',
    update_time              datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                bigint                               null comment '修改人',
    trace_id                 varchar(64)                          null comment '调用链路',
    version_num              int        default 0                 null comment '版本号',
    tenant_id                bigint                               null comment '租户ID',
    cost_price_total         decimal(15, 6)                       null comment '成本总价',
    sales_price_total        decimal(15, 6)                       null comment '销售总额',
    goods_gross_profit_total decimal(15, 6)                       null comment '毛利总额',
    goods_net_profit_total   decimal(15, 6)                       null comment '净利总额',
    buy_order_id             varchar(255)                         null
    )
    comment '进销存-采购计划表';

create index idx_jcx_buy_plan_buy_order_id
    on jcx_buy_plan (buy_order_id);

create index idx_jcx_buy_plan_tenant_id
    on jcx_buy_plan (tenant_id);

create table if not exists jcx_buy_plan_item
(
    id                       bigint auto_increment comment 'ID 自增'
    primary key,
    plan_id                  varchar(255)                         null,
    goods_id                 bigint                               null comment '商品ID',
    cost_price               decimal(15, 6)                       null comment '成本价',
    sales_price              decimal(15, 6)                       null comment '成本价',
    warning_count            varchar(255)                         null,
    goods_gross_profit       decimal(15, 6)                       null comment '毛利',
    goods_net_profit         decimal(15, 6)                       null comment '净利',
    goods_inventory_count    varchar(255)                         null,
    is_delete                tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time              datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                bigint                               null comment '创建人',
    update_time              datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                bigint                               null comment '修改人',
    trace_id                 varchar(64)                          null comment '调用链路',
    version_num              int        default 0                 null comment '版本号',
    tenant_id                bigint                               null comment '租户ID',
    goods_buy_count          varchar(255)                         null,
    cost_price_total         decimal(15, 6)                       null comment '成本总价',
    sales_price_total        decimal(15, 6)                       null comment '总金额',
    goods_gross_profit_total decimal(15, 6)                       null comment '毛利总额',
    goods_net_profit_total   decimal(15, 6)                       null comment '净利总额',
    supplier_id              bigint                               null
    )
    comment '进销存-采购计划明细表';

create index idx_jcx_buy_plan_item_goods_id
    on jcx_buy_plan_item (goods_id);

create index idx_jcx_buy_plan_item_plan_id
    on jcx_buy_plan_item (plan_id);

create index idx_jcx_buy_plan_item_tenant_id
    on jcx_buy_plan_item (tenant_id);

create table if not exists jcx_goods
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    brand_id              bigint                               null,
    goods_name            varchar(255)                         null,
    goods_img             varchar(255)                         null,
    goods_bar_code        bigint                               null,
    goods_qr_code         mediumint                            null,
    goods_unit            varchar(255)                         null,
    goods_type            varchar(255)                         null,
    tenant_id             bigint                               null comment '租户ID',
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    cost_price            decimal(15, 6)                       null comment '成本价',
    sales_price           decimal(15, 6)                       null comment '成本价',
    warning_count         tinyint                              null,
    goods_gross_profit    decimal(15, 6)                       null comment '毛利',
    goods_net_profit      decimal(15, 6)                       null comment '净利',
    goods_inventory_count decimal(15, 6)                       null comment '库存',
    is_inventory          tinyint                              null,
    supplier_id           varchar(255)                         null
    )
    comment '进销存-商品表';

create index idx_jcx_goods_supplier_id
    on jcx_goods (supplier_id);

create index idx_jcx_goods_tenant_id
    on jcx_goods (tenant_id);

create table if not exists jcx_goods_warning
(
    id                    bigint auto_increment comment 'ID 自增'
    primary key,
    report_no             varchar(255)                         null,
    goods_id              bigint                               null comment '商品ID',
    goods_name            varchar(255)                         null,
    cost_price            decimal(15, 6)                       null comment '成本价',
    sales_price           decimal(15, 6)                       null comment '成本价',
    warning_count         tinyint                              null,
    goods_gross_profit    decimal(15, 6)                       null comment '毛利',
    goods_net_profit      decimal(15, 6)                       null comment '净利',
    goods_inventory_count decimal(15, 6)                       null comment '库存',
    is_inventory          tinyint                              null,
    is_done               tinyint                              null,
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(64)                          null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    tenant_id             bigint                               null comment '租户ID'
    )
    comment '进销存-库存预警表';

create index idx_jcx_goods_warning_goods_id
    on jcx_goods_warning (goods_id);

create index idx_jcx_goods_warning_tenant_id
    on jcx_goods_warning (tenant_id);

create table if not exists jcx_order
(
    id                     bigint                               null,
    tenant_id              bigint                               null comment '租户ID',
    order_no               varchar(255)                         null,
    order_remark           varchar(255)                         null,
    order_status           varchar(255)                         null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    order_total_sale_price decimal(15, 6)                       null comment '成本价'
    )
    comment '进销存-订单表';

create index idx_jcx_order_tenant_id
    on jcx_order (tenant_id);

create table if not exists jcx_order_item
(
    id                     bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id              bigint                               null comment '租户ID',
    order_id               bigint                               null,
    order_remark           varchar(255)                         null,
    goods_id               bigint                               null comment '商品ID',
    goods_count            decimal(15, 6)                       null comment '商品量',
    goods_cost_price       decimal(15, 6)                       null comment '成本价',
    goods_sale_price       decimal(15, 6)                       null comment '成本价',
    goods_gross_profit     varchar(255)                         null,
    goods_net_profit       varchar(255)                         null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(64)                          null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    goods_total_sale_price decimal(15, 6)                       null comment '成本价'
    )
    comment '进销存-订单明细表';

create index idx_jcx_order_item_goods_id
    on jcx_order_item (goods_id);

create index idx_jcx_order_item_order_id
    on jcx_order_item (order_id);

create index idx_jcx_order_item_tenant_id
    on jcx_order_item (tenant_id);

create table if not exists msg_message
(
    id                bigint                               null,
    tenant_id         bigint                               null comment '租户ID',
    is_all            tinyint                              null,
    message_title     varchar(255)                         null,
    message_context   varchar(255)                         null,
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    is_used           varchar(255)                         null,
    version_num       int        default 0                 null comment '版本号',
    message_Json_data text                                 null
    )
    comment '消息表';

create index idx_msg_message_tenant_id
    on msg_message (tenant_id);

create table if not exists msg_message_read
(
    id             bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id      bigint                               null comment '租户ID',
    user_id        bigint                               null,
    last_read_time varchar(255)                         null,
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      varchar(255)                         null,
    trace_id       varchar(64)                          null comment '调用链路',
    is_used        varchar(255)                         null,
    version_num    int        default 0                 null comment '版本号'
    )
    comment '消息已读表';

create index idx_msg_message_read_tenant_id
    on msg_message_read (tenant_id);

create index idx_msg_message_read_user_id
    on msg_message_read (user_id);

create table if not exists t_brand
(
    id           bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id    bigint                               null comment '租户ID',
    factory_id   bigint                               null comment '工厂ID',
    brand_code   varchar(255)                         null,
    brand_name   varchar(255)                         null,
    brand_status varchar(255)                         null,
    is_delete    tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by    bigint                               null comment '创建人',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by    bigint                               null comment '修改人',
    trace_id     varchar(64)                          null comment '调用链路',
    is_used      tinyint                              null,
    version_num  int        default 0                 null comment '版本号'
    )
    comment '品牌表';

create index idx_t_brand_factory_id
    on t_brand (factory_id);

create index idx_t_brand_tenant_id
    on t_brand (tenant_id);

create table if not exists t_calendar
(
    id                bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id         bigint                               null comment '租户ID',
    factory_id        bigint                               null comment '工厂ID',
    calendar_name     varchar(255)                         null,
    calendar_code     varchar(255)                         null,
    calendar_type     varchar(255)                         null,
    calendar_desc     varchar(255)                         null,
    calendar_disabled tinyint                              null,
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(64)                          null comment '调用链路',
    version_num       int        default 0                 null comment '版本号'
    )
    comment '日历表';

create index idx_t_calendar_factory_id
    on t_calendar (factory_id);

create index idx_t_calendar_tenant_id
    on t_calendar (tenant_id);

create table if not exists t_calendar_day
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    calendar_id varchar(255)                         null,
    day_month   decimal(15, 6)                       null comment '制造数',
    day_year    decimal(15, 6)                       null comment '制造数',
    day_1       decimal(15, 6)                       null comment '制造数',
    day_2       decimal(15, 6)                       null comment '制造数',
    day_3       decimal(15, 6)                       null comment '制造数',
    day_4       decimal(15, 6)                       null comment '制造数',
    day_5       decimal(15, 6)                       null comment '制造数',
    day_6       decimal(15, 6)                       null comment '制造数',
    day_7       decimal(15, 6)                       null comment '制造数',
    day_8       decimal(15, 6)                       null comment '制造数',
    day_9       decimal(15, 6)                       null comment '制造数',
    day_10      decimal(15, 6)                       null comment '制造数',
    day_11      decimal(15, 6)                       null comment '制造数',
    day_12      decimal(15, 6)                       null comment '制造数',
    day_13      decimal(15, 6)                       null comment '制造数',
    day_14      decimal(15, 6)                       null comment '制造数',
    day_15      decimal(15, 6)                       null comment '制造数',
    day_16      decimal(15, 6)                       null comment '制造数',
    day_17      decimal(15, 6)                       null comment '制造数',
    day_18      decimal(15, 6)                       null comment '制造数',
    day_19      decimal(15, 6)                       null comment '制造数',
    day_20      decimal(15, 6)                       null comment '制造数',
    day_21      decimal(15, 6)                       null comment '制造数',
    day_22      decimal(15, 6)                       null comment '制造数',
    day_23      decimal(15, 6)                       null comment '制造数',
    day_24      decimal(15, 6)                       null comment '制造数',
    day_25      decimal(15, 6)                       null comment '制造数',
    day_26      decimal(15, 6)                       null comment '制造数',
    day_27      decimal(15, 6)                       null comment '制造数',
    day_28      decimal(15, 6)                       null comment '制造数',
    day_29      decimal(15, 6)                       null comment '制造数',
    day_30      decimal(15, 6)                       null comment '制造数',
    day_31      decimal(15, 6)                       null comment '制造数',
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '日历天表';

create index idx_t_calendar_day_calendar_id
    on t_calendar_day (calendar_id);

create index idx_t_calendar_day_factory_id
    on t_calendar_day (factory_id);

create index idx_t_calendar_day_tenant_id
    on t_calendar_day (tenant_id);

create table if not exists t_check_report
(
    id          bigint                               null,
    tenant_id   bigint                               null comment '租户ID',
    factory_id  bigint                               null comment '工厂ID',
    report_code varchar(255)                         null,
    report_name varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    is_over     varchar(255)                         null
    )
    comment '检查报告表';

create index idx_t_check_report_factory_id
    on t_check_report (factory_id);

create index idx_t_check_report_tenant_id
    on t_check_report (tenant_id);

create table if not exists t_check_report_list
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id   bigint                               null comment '租户ID',
    factory_id  bigint                               null comment '工厂ID',
    report_id   varchar(255)                         null,
    property_id bigint                               null,
    remark      varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    room_id     bigint                               null,
    storey_id   bigint                               null,
    version_num int        default 0                 null comment '版本号'
    )
    comment '检查报告明细表';

create index idx_t_check_report_list_factory_id
    on t_check_report_list (factory_id);

create index idx_t_check_report_list_property_id
    on t_check_report_list (property_id);

create index idx_t_check_report_list_report_id
    on t_check_report_list (report_id);

create index idx_t_check_report_list_room_id
    on t_check_report_list (room_id);

create index idx_t_check_report_list_storey_id
    on t_check_report_list (storey_id);

create index idx_t_check_report_list_tenant_id
    on t_check_report_list (tenant_id);

create table if not exists t_dictionary
(
    id               bigint auto_increment comment 'ID 自增'
    primary key,
    dictionary_group varchar(255)                         null,
    dictionary_value varchar(255)                         null,
    dictionary_sort  varchar(255)                         null,
    dictionary_ext   varchar(255)                         null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        varchar(255)                         null,
    trace_id         varchar(64)                          null comment '调用链路',
    version_num      int        default 0                 null comment '版本号'
    )
    comment '数据字典表';

create table if not exists t_district_code
(
    id          bigint                               null,
    code        varchar(255)                         null,
    name        varchar(255)                         null,
    parent_code varchar(255)                         null,
    path        varchar(255)                         null comment '路径',
    level       tinyint                              null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   varchar(255)                         null
    )
    comment '地区代码表';

create index t_district_code_code_index
    on t_district_code (code);

create index t_district_code_parent_code_index
    on t_district_code (parent_code);

create index t_district_code_path_index
    on t_district_code (path);

create table if not exists t_factory
(
    id             bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id      bigint                               null comment '租户ID',
    factory_name   varchar(255)                         null,
    factory_code   varchar(255)                         null,
    factory_status varchar(255)                         null,
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      bigint                               null comment '修改人',
    trace_id       varchar(64)                          null comment '调用链路',
    version_num    int        default 0                 null comment '版本号'
    )
    comment '工厂表';

create index idx_t_factory_tenant_id
    on t_factory (tenant_id);

create table if not exists t_file_upload
(
    id              bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id       bigint                               null comment '租户ID',
    file_name       varchar(255)                         null,
    file_size       varchar(255)                         null,
    local_file_path varchar(255)                         null,
    cloud_file_path varchar(255)                         null,
    expire_time     varchar(255)                         null,
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(64)                          null comment '调用链路',
    version_num     int        default 0                 null comment '版本号',
    file_type       varchar(255)                         null,
    file_suffix     varchar(255)                         null
    )
    comment '文件上传表';

create index idx_t_file_upload_tenant_id
    on t_file_upload (tenant_id);

create table if not exists t_login_account
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    user_name   varchar(255)                         null,
    login_phone bigint                               null,
    pwd         varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    tenant_id   bigint                               null comment '租户ID',
    version_num int        default 0                 null comment '版本号',
    user_pwd    varchar(255)                         null
    user_pwd    varchar(255)                         null,
    is_admin    tinyint(1) default 0                 null comment '是否是管理员'
    )
    comment '登录账号表';

create index idx_t_login_account_tenant_id
    on t_login_account (tenant_id);

create table if not exists t_process_line
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id   bigint                               null comment '租户ID',
    factory_id  bigint                               null comment '工厂ID',
    process_id  bigint                               null,
    line_name   varchar(255)                         null,
    line_code   varchar(255)                         null,
    line_desc   varchar(255)                         null,
    line_sort   varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '产线表';

create table if not exists t_property
(
    id            bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id     bigint                               null comment '租户ID',
    factory_id    bigint                               null comment '工厂ID',
    storey_id     bigint                               null,
    room_id       bigint                               null,
    property_code varchar(255)                         null,
    property_name varchar(255)                         null,
    in_use        tinyint                              null,
    is_delete     tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time   datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by     bigint                               null comment '创建人',
    update_time   datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by     bigint                               null comment '修改人',
    trace_id      varchar(64)                          null comment '调用链路',
    version_num   int        default 0                 null comment '版本号'
    )
    comment '资产属性表';

create index idx_t_property_factory_id
    on t_property (factory_id);

create index idx_t_property_room_id
    on t_property (room_id);

create index idx_t_property_storey_id
    on t_property (storey_id);

create index idx_t_property_tenant_id
    on t_property (tenant_id);

create table if not exists t_room
(
    id          bigint                               null,
    tenant_id   bigint                               null comment '租户ID',
    factory_id  bigint                               null comment '工厂ID',
    storey_id   bigint                               null,
    room_code   varchar(255)                         null,
    room_name   varchar(255)                         null,
    room_sort   tinyint                              null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '房间表';

create index idx_t_room_factory_id
    on t_room (factory_id);

create index idx_t_room_storey_id
    on t_room (storey_id);

create index idx_t_room_tenant_id
    on t_room (tenant_id);

create table if not exists t_shift
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    shift_code  varchar(255)                         null,
    shift_name  varchar(255)                         null,
    factory_id  varchar(255)                         null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '班次表';

create index idx_t_shift_factory_id
    on t_shift (factory_id);

create index idx_t_shift_tenant_id
    on t_shift (tenant_id);

create table if not exists t_shift_item
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    shift_id    bigint                               null,
    begin_time  varchar(255)                         null,
    end_time    varchar(255)                         null,
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
    )
    comment '班次明细表';

create index idx_t_shift_item_shift_id
    on t_shift_item (shift_id);

create index idx_t_shift_item_tenant_id
    on t_shift_item (tenant_id);

create table if not exists t_storey
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_id   bigint                               null comment '租户ID',
    factory_id  bigint                               null comment '工厂ID',
    storey_name varchar(255)                         null,
    storey_sort tinyint                              null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(64)                          null comment '调用链路',
    storey_code varchar(255)                         null,
    version_num int        default 0                 null comment '版本号'
    )
    comment '楼层表';

create index idx_t_storey_factory_id
    on t_storey (factory_id);

create index idx_t_storey_tenant_id
    on t_storey (tenant_id);

create table if not exists t_tenant_info
(
    id          bigint auto_increment comment 'ID 自增'
    primary key,
    tenant_name varchar(255)                         null,
    tenant_code varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(64)                          null comment '调用链路',
    tenant_id   bigint                               null comment '租户ID',
    version_num int        default 0                 null comment '版本号'
    )
    comment '租户信息表';



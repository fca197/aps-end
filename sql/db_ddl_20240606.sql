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
    trace_id        varchar(255)                         null comment '调用链路',
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
    goods_id             bigint                               null comment '商品ID',
    bom_code             varchar(255)                         null comment 'bom 编码',
    bom_name             varchar(255)                         null comment 'bom 名称',
    bom_usage            smallint                             null comment '使用量',
    bom_unit             varchar(255)                         null comment '单位',
    bom_cost_price       mediumint                            null comment '成本价',
    bom_cost_price_unit  varchar(255)                         null comment '单位',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_expression   varchar(255)                         null comment '使用表达式',
    bom_inventory        int        default 0                 null comment '库存',
    is_follow            tinyint(1) default 0                 null comment '是否关注',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(255)                         null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
)
    comment 'BOM 清单';

create index idx_aps_goods_bom_factory_id
    on aps_goods_bom (factory_id);

create index idx_aps_goods_bom_goods_id
    on aps_goods_bom (goods_id);

create index idx_aps_goods_bom_tenant_id
    on aps_goods_bom (tenant_id);

create table if not exists aps_goods_bom_buy_plan
(
    id                bigint auto_increment comment 'ID 自增'
        primary key,
    plan_name         varchar(255)                         null comment '计划名称',
    plan_total_amount decimal                              null comment '计划金额',
    plan_source       varchar(255)                         null comment '计划来源',
    plan_remark       varchar(255)                         null comment '计划备注',
    tenant_id         bigint                               null comment '租户ID',
    is_delete         tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time       datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by         bigint                               null comment '创建人',
    update_time       datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by         bigint                               null comment '修改人',
    trace_id          varchar(255)                         null comment '调用链路',
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
    bom_cost_price      mediumint                            null comment '成本价',
    bom_cost_price_unit varchar(255)                         null comment '单位',
    bom_inventory       int        default 0                 null comment '库存',
    bom_buy_count       int        default 0                 null comment '购买数量',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(255)                         null comment '调用链路',
    version_num         int        default 0                 null comment '版本号'
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
    trace_id            varchar(255)                         null comment '调用链路',
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
    month_01         varchar(255)                         null,
    month_02         varchar(255)                         null,
    month_03         varchar(255)                         null,
    month_04         varchar(255)                         null,
    month_05         varchar(255)                         null,
    month_06         varchar(255)                         null,
    month_07         varchar(255)                         null,
    month_08         varchar(255)                         null,
    month_09         varchar(255)                         null,
    month_10         varchar(255)                         null,
    month_11         varchar(255)                         null,
    month_12         varchar(255)                         null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(255)                         null comment '调用链路',
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
    trace_id            varchar(255)                         null comment '调用链路',
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
    month_01         varchar(255)                         null,
    month_02         varchar(255)                         null,
    month_03         varchar(255)                         null,
    month_04         varchar(255)                         null,
    month_05         varchar(255)                         null,
    month_06         varchar(255)                         null,
    month_07         varchar(255)                         null,
    month_08         varchar(255)                         null,
    month_09         varchar(255)                         null,
    month_10         varchar(255)                         null,
    month_11         varchar(255)                         null,
    month_12         varchar(255)                         null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        varchar(255)                         null,
    trace_id         varchar(255)                         null comment '调用链路',
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
    trace_id                      varchar(255)                         null comment '调用链路',
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
    day_num1         int                                  null,
    day_num2         int                                  null,
    day_num3         int                                  null,
    day_num4         int                                  null,
    day_num5         int                                  null,
    day_num6         int                                  null,
    day_num7         int                                  null,
    day_num8         int                                  null,
    day_num9         int                                  null,
    day_num10        int                                  null,
    day_num11        int                                  null,
    day_num12        int                                  null,
    day_num13        int                                  null,
    day_num14        int                                  null,
    day_num15        int                                  null,
    day_num16        int                                  null,
    day_num17        int                                  null,
    day_num18        int                                  null,
    day_num19        int                                  null,
    day_num20        int                                  null,
    day_num21        int                                  null,
    day_num22        int                                  null,
    day_num23        int                                  null,
    day_num24        int                                  null,
    day_num25        int                                  null,
    day_num26        int                                  null,
    day_num27        int                                  null,
    day_num28        int                                  null,
    day_num29        int                                  null,
    day_num30        int                                  null,
    day_num31        int                                  null,
    day_num32        int                                  null,
    day_num33        int                                  null,
    day_num34        int                                  null,
    day_num35        int                                  null,
    day_num36        int                                  null,
    day_num37        int                                  null,
    day_num38        int                                  null,
    day_num39        int                                  null,
    day_num40        int                                  null,
    day_num41        int                                  null,
    day_num42        int                                  null,
    day_num43        int                                  null,
    day_num44        int                                  null,
    day_num45        int                                  null,
    day_num46        int                                  null,
    day_num47        int                                  null,
    day_num48        int                                  null,
    day_num49        int                                  null,
    day_num50        int                                  null,
    day_num51        int                                  null,
    day_num52        int                                  null,
    day_num53        int                                  null,
    day_num54        int                                  null,
    day_num55        int                                  null,
    day_num56        int                                  null,
    day_num57        int                                  null,
    day_num58        int                                  null,
    day_num59        int                                  null,
    day_num60        int                                  null,
    day_num61        int                                  null,
    day_num62        int                                  null,
    day_num63        int                                  null,
    day_num64        int                                  null,
    day_num65        int                                  null,
    day_num66        int                                  null,
    day_num67        int                                  null,
    day_num68        int                                  null,
    day_num69        int                                  null,
    day_num70        int                                  null,
    day_num71        int                                  null,
    day_num72        int                                  null,
    day_num73        int                                  null,
    day_num74        int                                  null,
    day_num75        int                                  null,
    day_num76        int                                  null,
    day_num77        int                                  null,
    day_num78        int                                  null,
    day_num79        int                                  null,
    day_num80        int                                  null,
    day_num81        int                                  null,
    day_num82        int                                  null,
    day_num83        int                                  null,
    day_num84        int                                  null,
    day_num85        int                                  null,
    day_num86        int                                  null,
    day_num87        int                                  null,
    day_num88        int                                  null,
    day_num89        int                                  null,
    day_num90        int                                  null,
    day_num91        int                                  null,
    day_num92        int                                  null,
    day_num93        int                                  null,
    day_num94        int                                  null,
    day_num95        int                                  null,
    day_num96        int                                  null,
    day_num97        int                                  null,
    day_num98        int                                  null,
    day_num99        int                                  null,
    day_num100       int                                  null,
    day_num101       int                                  null,
    day_num102       int                                  null,
    day_num103       int                                  null,
    day_num104       int                                  null,
    day_num105       int                                  null,
    day_num106       int                                  null,
    day_num107       int                                  null,
    day_num108       int                                  null,
    day_num109       int                                  null,
    day_num110       int                                  null,
    day_num111       int                                  null,
    day_num112       int                                  null,
    day_num113       int                                  null,
    day_num114       int                                  null,
    day_num115       int                                  null,
    day_num116       int                                  null,
    day_num117       int                                  null,
    day_num118       int                                  null,
    day_num119       int                                  null,
    day_num120       int                                  null,
    day_num121       int                                  null,
    day_num122       int                                  null,
    day_num123       int                                  null,
    day_num124       int                                  null,
    day_num125       int                                  null,
    day_num126       int                                  null,
    day_num127       int                                  null,
    day_num128       int                                  null,
    day_num129       int                                  null,
    day_num130       int                                  null,
    day_num131       int                                  null,
    day_num132       int                                  null,
    day_num133       int                                  null,
    day_num134       int                                  null,
    day_num135       int                                  null,
    day_num136       int                                  null,
    day_num137       int                                  null,
    day_num138       int                                  null,
    day_num139       int                                  null,
    day_num140       int                                  null,
    day_num141       int                                  null,
    day_num142       int                                  null,
    day_num143       int                                  null,
    day_num144       int                                  null,
    day_num145       int                                  null,
    day_num146       int                                  null,
    day_num147       int                                  null,
    day_num148       int                                  null,
    day_num149       int                                  null,
    day_num150       int                                  null,
    day_num151       int                                  null,
    day_num152       int                                  null,
    day_num153       int                                  null,
    day_num154       int                                  null,
    day_num155       int                                  null,
    day_num156       int                                  null,
    day_num157       int                                  null,
    day_num158       int                                  null,
    day_num159       int                                  null,
    day_num160       int                                  null,
    day_num161       int                                  null,
    day_num162       int                                  null,
    day_num163       int                                  null,
    day_num164       int                                  null,
    day_num165       int                                  null,
    day_num166       int                                  null,
    day_num167       int                                  null,
    day_num168       int                                  null,
    day_num169       int                                  null,
    day_num170       int                                  null,
    day_num171       int                                  null,
    day_num172       int                                  null,
    day_num173       int                                  null,
    day_num174       int                                  null,
    day_num175       int                                  null,
    day_num176       int                                  null,
    day_num177       int                                  null,
    day_num178       int                                  null,
    day_num179       int                                  null,
    day_num180       int                                  null,
    day_num181       int                                  null,
    day_num182       int                                  null,
    day_num183       int                                  null,
    day_num184       int                                  null,
    day_num185       int                                  null,
    day_num186       int                                  null,
    day_num187       int                                  null,
    day_num188       int                                  null,
    day_num189       int                                  null,
    day_num190       int                                  null,
    day_num191       int                                  null,
    day_num192       int                                  null,
    day_num193       int                                  null,
    day_num194       int                                  null,
    day_num195       int                                  null,
    day_num196       int                                  null,
    day_num197       int                                  null,
    day_num198       int                                  null,
    day_num199       int                                  null,
    day_num200       int                                  null,
    day_num201       int                                  null,
    day_num202       int                                  null,
    day_num203       int                                  null,
    day_num204       int                                  null,
    day_num205       int                                  null,
    day_num206       int                                  null,
    day_num207       int                                  null,
    day_num208       int                                  null,
    day_num209       int                                  null,
    day_num210       int                                  null,
    day_num211       int                                  null,
    day_num212       int                                  null,
    day_num213       int                                  null,
    day_num214       int                                  null,
    day_num215       int                                  null,
    day_num216       int                                  null,
    day_num217       int                                  null,
    day_num218       int                                  null,
    day_num219       int                                  null,
    day_num220       int                                  null,
    day_num221       int                                  null,
    day_num222       int                                  null,
    day_num223       int                                  null,
    day_num224       int                                  null,
    day_num225       int                                  null,
    day_num226       int                                  null,
    day_num227       int                                  null,
    day_num228       int                                  null,
    day_num229       int                                  null,
    day_num230       int                                  null,
    day_num231       int                                  null,
    day_num232       int                                  null,
    day_num233       int                                  null,
    day_num234       int                                  null,
    day_num235       int                                  null,
    day_num236       int                                  null,
    day_num237       int                                  null,
    day_num238       int                                  null,
    day_num239       int                                  null,
    day_num240       int                                  null,
    day_num241       int                                  null,
    day_num242       int                                  null,
    day_num243       int                                  null,
    day_num244       int                                  null,
    day_num245       int                                  null,
    day_num246       int                                  null,
    day_num247       int                                  null,
    day_num248       int                                  null,
    day_num249       int                                  null,
    day_num250       int                                  null,
    day_num251       int                                  null,
    day_num252       int                                  null,
    day_num253       int                                  null,
    day_num254       int                                  null,
    day_num255       int                                  null,
    day_num256       int                                  null,
    day_num257       int                                  null,
    day_num258       int                                  null,
    day_num259       int                                  null,
    day_num260       int                                  null,
    day_num261       int                                  null,
    day_num262       int                                  null,
    day_num263       int                                  null,
    day_num264       int                                  null,
    day_num265       int                                  null,
    day_num266       int                                  null,
    day_num267       int                                  null,
    day_num268       int                                  null,
    day_num269       int                                  null,
    day_num270       int                                  null,
    day_num271       int                                  null,
    day_num272       int                                  null,
    day_num273       int                                  null,
    day_num274       int                                  null,
    day_num275       int                                  null,
    day_num276       int                                  null,
    day_num277       int                                  null,
    day_num278       int                                  null,
    day_num279       int                                  null,
    day_num280       int                                  null,
    day_num281       int                                  null,
    day_num282       int                                  null,
    day_num283       int                                  null,
    day_num284       int                                  null,
    day_num285       int                                  null,
    day_num286       int                                  null,
    day_num287       int                                  null,
    day_num288       int                                  null,
    day_num289       int                                  null,
    day_num290       int                                  null,
    day_num291       int                                  null,
    day_num292       int                                  null,
    day_num293       int                                  null,
    day_num294       int                                  null,
    day_num295       int                                  null,
    day_num296       int                                  null,
    day_num297       int                                  null,
    day_num298       int                                  null,
    day_num299       int                                  null,
    day_num300       int                                  null,
    day_num301       int                                  null,
    day_num302       int                                  null,
    day_num303       int                                  null,
    day_num304       int                                  null,
    day_num305       int                                  null,
    day_num306       int                                  null,
    day_num307       int                                  null,
    day_num308       int                                  null,
    day_num309       int                                  null,
    day_num310       int                                  null,
    day_num311       int                                  null,
    day_num312       int                                  null,
    day_num313       int                                  null,
    day_num314       int                                  null,
    day_num315       int                                  null,
    day_num316       int                                  null,
    day_num317       int                                  null,
    day_num318       int                                  null,
    day_num319       int                                  null,
    day_num320       int                                  null,
    day_num321       int                                  null,
    day_num322       int                                  null,
    day_num323       int                                  null,
    day_num324       int                                  null,
    day_num325       int                                  null,
    day_num326       int                                  null,
    day_num327       int                                  null,
    day_num328       int                                  null,
    day_num329       int                                  null,
    day_num330       int                                  null,
    day_num331       int                                  null,
    day_num332       int                                  null,
    day_num333       int                                  null,
    day_num334       int                                  null,
    day_num335       int                                  null,
    day_num336       int                                  null,
    day_num337       int                                  null,
    day_num338       int                                  null,
    day_num339       int                                  null,
    day_num340       int                                  null,
    day_num341       int                                  null,
    day_num342       int                                  null,
    day_num343       int                                  null,
    day_num344       int                                  null,
    day_num345       int                                  null,
    day_num346       int                                  null,
    day_num347       int                                  null,
    day_num348       int                                  null,
    day_num349       int                                  null,
    day_num350       int                                  null,
    day_num351       int                                  null,
    day_num352       int                                  null,
    day_num353       int                                  null,
    day_num354       int                                  null,
    day_num355       int                                  null,
    day_num356       int                                  null,
    day_num357       int                                  null,
    day_num358       int                                  null,
    day_num359       int                                  null,
    day_num360       int                                  null,
    day_num361       int                                  null,
    day_num362       int                                  null,
    day_num363       int                                  null,
    day_num364       int                                  null,
    day_num365       int                                  null,
    day_num366       int                                  null,
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
    month_01         varchar(255)                         null,
    month_02         varchar(255)                         null,
    month_03         varchar(255)                         null,
    month_04         varchar(255)                         null,
    month_05         varchar(255)                         null,
    month_06         varchar(255)                         null,
    month_07         varchar(255)                         null,
    month_08         varchar(255)                         null,
    month_09         varchar(255)                         null,
    month_10         varchar(255)                         null,
    month_11         varchar(255)                         null,
    month_12         varchar(255)                         null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(255)                         null comment '调用链路',
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
    trace_id                       varchar(255)                         null comment '调用链路',
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
    make_month_id       int                                    null,
    bom_id              bigint                                 null,
    year                smallint                               null,
    day_num1            int                                    null,
    day_num2            int                                    null,
    day_num3            int                                    null,
    day_num4            int                                    null,
    day_num5            int                                    null,
    day_num6            int                                    null,
    day_num7            int                                    null,
    day_num8            int                                    null,
    day_num9            int                                    null,
    day_num10           int                                    null,
    day_num11           int                                    null,
    day_num12           int                                    null,
    day_num13           int                                    null,
    day_num14           int                                    null,
    day_num15           int                                    null,
    day_num16           int                                    null,
    day_num17           int                                    null,
    day_num18           int                                    null,
    day_num19           int                                    null,
    day_num20           int                                    null,
    day_num21           int                                    null,
    day_num22           int                                    null,
    day_num23           int                                    null,
    day_num24           int                                    null,
    day_num25           int                                    null,
    day_num26           int                                    null,
    day_num27           int                                    null,
    day_num28           int                                    null,
    day_num29           int                                    null,
    day_num30           int                                    null,
    day_num31           int                                    null,
    day_num32           int                                    null,
    day_num33           int                                    null,
    day_num34           int                                    null,
    day_num35           int                                    null,
    day_num36           int                                    null,
    day_num37           int                                    null,
    day_num38           int                                    null,
    day_num39           int                                    null,
    day_num40           int                                    null,
    day_num41           int                                    null,
    day_num42           int                                    null,
    day_num43           int                                    null,
    day_num44           int                                    null,
    day_num45           int                                    null,
    day_num46           int                                    null,
    day_num47           int                                    null,
    day_num48           int                                    null,
    day_num49           int                                    null,
    day_num50           int                                    null,
    day_num51           int                                    null,
    day_num52           int                                    null,
    day_num53           int                                    null,
    day_num54           int                                    null,
    day_num55           int                                    null,
    day_num56           int                                    null,
    day_num57           int                                    null,
    day_num58           int                                    null,
    day_num59           int                                    null,
    day_num60           int                                    null,
    day_num61           int                                    null,
    day_num62           int                                    null,
    day_num63           int                                    null,
    day_num64           int                                    null,
    day_num65           int                                    null,
    day_num66           int                                    null,
    day_num67           int                                    null,
    day_num68           int                                    null,
    day_num69           int                                    null,
    day_num70           int                                    null,
    day_num71           int                                    null,
    day_num72           int                                    null,
    day_num73           int                                    null,
    day_num74           int                                    null,
    day_num75           int                                    null,
    day_num76           int                                    null,
    day_num77           int                                    null,
    day_num78           int                                    null,
    day_num79           int                                    null,
    day_num80           int                                    null,
    day_num81           int                                    null,
    day_num82           int                                    null,
    day_num83           int                                    null,
    day_num84           int                                    null,
    day_num85           int                                    null,
    day_num86           int                                    null,
    day_num87           int                                    null,
    day_num88           int                                    null,
    day_num89           int                                    null,
    day_num90           int                                    null,
    day_num91           int                                    null,
    day_num92           int                                    null,
    day_num93           int                                    null,
    day_num94           int                                    null,
    day_num95           int                                    null,
    day_num96           int                                    null,
    day_num97           int                                    null,
    day_num98           int                                    null,
    day_num99           int                                    null,
    day_num100          int                                    null,
    day_num101          int                                    null,
    day_num102          int                                    null,
    day_num103          int                                    null,
    day_num104          int                                    null,
    day_num105          int                                    null,
    day_num106          int                                    null,
    day_num107          int                                    null,
    day_num108          int                                    null,
    day_num109          int                                    null,
    day_num110          int                                    null,
    day_num111          int                                    null,
    day_num112          int                                    null,
    day_num113          int                                    null,
    day_num114          int                                    null,
    day_num115          int                                    null,
    day_num116          int                                    null,
    day_num117          int                                    null,
    day_num118          int                                    null,
    day_num119          int                                    null,
    day_num120          int                                    null,
    day_num121          int                                    null,
    day_num122          int                                    null,
    day_num123          int                                    null,
    day_num124          int                                    null,
    day_num125          int                                    null,
    day_num126          int                                    null,
    day_num127          int                                    null,
    day_num128          int                                    null,
    day_num129          int                                    null,
    day_num130          int                                    null,
    day_num131          int                                    null,
    day_num132          int                                    null,
    day_num133          int                                    null,
    day_num134          int                                    null,
    day_num135          int                                    null,
    day_num136          int                                    null,
    day_num137          int                                    null,
    day_num138          int                                    null,
    day_num139          int                                    null,
    day_num140          int                                    null,
    day_num141          int                                    null,
    day_num142          int                                    null,
    day_num143          int                                    null,
    day_num144          int                                    null,
    day_num145          int                                    null,
    day_num146          int                                    null,
    day_num147          int                                    null,
    day_num148          int                                    null,
    day_num149          int                                    null,
    day_num150          int                                    null,
    day_num151          int                                    null,
    day_num152          int                                    null,
    day_num153          int                                    null,
    day_num154          int                                    null,
    day_num155          int                                    null,
    day_num156          int                                    null,
    day_num157          int                                    null,
    day_num158          int                                    null,
    day_num159          int                                    null,
    day_num160          int                                    null,
    day_num161          int                                    null,
    day_num162          int                                    null,
    day_num163          int                                    null,
    day_num164          int                                    null,
    day_num165          int                                    null,
    day_num166          int                                    null,
    day_num167          int                                    null,
    day_num168          int                                    null,
    day_num169          int                                    null,
    day_num170          int                                    null,
    day_num171          int                                    null,
    day_num172          int                                    null,
    day_num173          int                                    null,
    day_num174          int                                    null,
    day_num175          int                                    null,
    day_num176          int                                    null,
    day_num177          int                                    null,
    day_num178          int                                    null,
    day_num179          int                                    null,
    day_num180          int                                    null,
    day_num181          int                                    null,
    day_num182          int                                    null,
    day_num183          int                                    null,
    day_num184          int                                    null,
    day_num185          int                                    null,
    day_num186          int                                    null,
    day_num187          int                                    null,
    day_num188          int                                    null,
    day_num189          int                                    null,
    day_num190          int                                    null,
    day_num191          int                                    null,
    day_num192          int                                    null,
    day_num193          int                                    null,
    day_num194          int                                    null,
    day_num195          int                                    null,
    day_num196          int                                    null,
    day_num197          int                                    null,
    day_num198          int                                    null,
    day_num199          int                                    null,
    day_num200          int                                    null,
    day_num201          int                                    null,
    day_num202          int                                    null,
    day_num203          int                                    null,
    day_num204          int                                    null,
    day_num205          int                                    null,
    day_num206          int                                    null,
    day_num207          int                                    null,
    day_num208          int                                    null,
    day_num209          int                                    null,
    day_num210          int                                    null,
    day_num211          int                                    null,
    day_num212          int                                    null,
    day_num213          int                                    null,
    day_num214          int                                    null,
    day_num215          int                                    null,
    day_num216          int                                    null,
    day_num217          int                                    null,
    day_num218          int                                    null,
    day_num219          int                                    null,
    day_num220          int                                    null,
    day_num221          int                                    null,
    day_num222          int                                    null,
    day_num223          int                                    null,
    day_num224          int                                    null,
    day_num225          int                                    null,
    day_num226          int                                    null,
    day_num227          int                                    null,
    day_num228          int                                    null,
    day_num229          int                                    null,
    day_num230          int                                    null,
    day_num231          int                                    null,
    day_num232          int                                    null,
    day_num233          int                                    null,
    day_num234          int                                    null,
    day_num235          int                                    null,
    day_num236          int                                    null,
    day_num237          int                                    null,
    day_num238          int                                    null,
    day_num239          int                                    null,
    day_num240          int                                    null,
    day_num241          int                                    null,
    day_num242          int                                    null,
    day_num243          int                                    null,
    day_num244          int                                    null,
    day_num245          int                                    null,
    day_num246          int                                    null,
    day_num247          int                                    null,
    day_num248          int                                    null,
    day_num249          int                                    null,
    day_num250          int                                    null,
    day_num251          int                                    null,
    day_num252          int                                    null,
    day_num253          int                                    null,
    day_num254          int                                    null,
    day_num255          int                                    null,
    day_num256          int                                    null,
    day_num257          int                                    null,
    day_num258          int                                    null,
    day_num259          int                                    null,
    day_num260          int                                    null,
    day_num261          int                                    null,
    day_num262          int                                    null,
    day_num263          int                                    null,
    day_num264          int                                    null,
    day_num265          int                                    null,
    day_num266          int                                    null,
    day_num267          int                                    null,
    day_num268          int                                    null,
    day_num269          int                                    null,
    day_num270          int                                    null,
    day_num271          int                                    null,
    day_num272          int                                    null,
    day_num273          int                                    null,
    day_num274          int                                    null,
    day_num275          int                                    null,
    day_num276          int                                    null,
    day_num277          int                                    null,
    day_num278          int                                    null,
    day_num279          int                                    null,
    day_num280          int                                    null,
    day_num281          int                                    null,
    day_num282          int                                    null,
    day_num283          int                                    null,
    day_num284          int                                    null,
    day_num285          int                                    null,
    day_num286          int                                    null,
    day_num287          int                                    null,
    day_num288          int                                    null,
    day_num289          int                                    null,
    day_num290          int                                    null,
    day_num291          int                                    null,
    day_num292          int                                    null,
    day_num293          int                                    null,
    day_num294          int                                    null,
    day_num295          int                                    null,
    day_num296          int                                    null,
    day_num297          int                                    null,
    day_num298          int                                    null,
    day_num299          int                                    null,
    day_num300          int                                    null,
    day_num301          int                                    null,
    day_num302          int                                    null,
    day_num303          int                                    null,
    day_num304          int                                    null,
    day_num305          int                                    null,
    day_num306          int                                    null,
    day_num307          int                                    null,
    day_num308          int                                    null,
    day_num309          int                                    null,
    day_num310          int                                    null,
    day_num311          int                                    null,
    day_num312          int                                    null,
    day_num313          int                                    null,
    day_num314          int                                    null,
    day_num315          int                                    null,
    day_num316          int                                    null,
    day_num317          int                                    null,
    day_num318          int                                    null,
    day_num319          int                                    null,
    day_num320          int                                    null,
    day_num321          int                                    null,
    day_num322          int                                    null,
    day_num323          int                                    null,
    day_num324          int                                    null,
    day_num325          int                                    null,
    day_num326          int                                    null,
    day_num327          int                                    null,
    day_num328          int                                    null,
    day_num329          int                                    null,
    day_num330          int                                    null,
    day_num331          int                                    null,
    day_num332          int                                    null,
    day_num333          int                                    null,
    day_num334          int                                    null,
    day_num335          int                                    null,
    day_num336          int                                    null,
    day_num337          int                                    null,
    day_num338          int                                    null,
    day_num339          int                                    null,
    day_num340          int                                    null,
    day_num341          int                                    null,
    day_num342          int                                    null,
    day_num343          int                                    null,
    day_num344          int                                    null,
    day_num345          int                                    null,
    day_num346          int                                    null,
    day_num347          int                                    null,
    day_num348          int                                    null,
    day_num349          int                                    null,
    day_num350          int                                    null,
    day_num351          int                                    null,
    day_num352          int                                    null,
    day_num353          int                                    null,
    day_num354          int                                    null,
    day_num355          int                                    null,
    day_num356          int                                    null,
    day_num357          int                                    null,
    day_num358          int                                    null,
    day_num359          int                                    null,
    day_num360          int                                    null,
    day_num361          int                                    null,
    day_num362          int                                    null,
    day_num363          int                                    null,
    day_num364          int                                    null,
    day_num365          int                                    null,
    day_num366          int                                    null,
    factory_id          bigint                                 null comment '工厂ID',
    tenant_id           bigint                                 null comment '租户ID',
    is_delete           tinyint(1)   default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime     default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                                 null comment '创建人',
    update_time         datetime     default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                                 null comment '修改人',
    trace_id            int                                    null comment '调用链路',
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
    day_num1            int                                  null,
    day_num2            int                                  null,
    day_num3            int                                  null,
    day_num4            int                                  null,
    day_num5            int                                  null,
    day_num6            int                                  null,
    day_num7            int                                  null,
    day_num8            int                                  null,
    day_num9            int                                  null,
    day_num10           int                                  null,
    day_num11           int                                  null,
    day_num12           int                                  null,
    day_num13           int                                  null,
    day_num14           int                                  null,
    day_num15           int                                  null,
    day_num16           int                                  null,
    day_num17           int                                  null,
    day_num18           int                                  null,
    day_num19           int                                  null,
    day_num20           int                                  null,
    day_num21           int                                  null,
    day_num22           int                                  null,
    day_num23           int                                  null,
    day_num24           int                                  null,
    day_num25           int                                  null,
    day_num26           int                                  null,
    day_num27           int                                  null,
    day_num28           int                                  null,
    day_num29           int                                  null,
    day_num30           int                                  null,
    day_num31           int                                  null,
    day_num32           int                                  null,
    day_num33           int                                  null,
    day_num34           int                                  null,
    day_num35           int                                  null,
    day_num36           int                                  null,
    day_num37           int                                  null,
    day_num38           int                                  null,
    day_num39           int                                  null,
    day_num40           int                                  null,
    day_num41           int                                  null,
    day_num42           int                                  null,
    day_num43           int                                  null,
    day_num44           int                                  null,
    day_num45           int                                  null,
    day_num46           int                                  null,
    day_num47           int                                  null,
    day_num48           int                                  null,
    day_num49           int                                  null,
    day_num50           int                                  null,
    day_num51           int                                  null,
    day_num52           int                                  null,
    day_num53           int                                  null,
    day_num54           int                                  null,
    day_num55           int                                  null,
    day_num56           int                                  null,
    day_num57           int                                  null,
    day_num58           int                                  null,
    day_num59           int                                  null,
    day_num60           int                                  null,
    day_num61           int                                  null,
    day_num62           int                                  null,
    day_num63           int                                  null,
    day_num64           int                                  null,
    day_num65           int                                  null,
    day_num66           int                                  null,
    day_num67           int                                  null,
    day_num68           int                                  null,
    day_num69           int                                  null,
    day_num70           int                                  null,
    day_num71           int                                  null,
    day_num72           int                                  null,
    day_num73           int                                  null,
    day_num74           int                                  null,
    day_num75           int                                  null,
    day_num76           int                                  null,
    day_num77           int                                  null,
    day_num78           int                                  null,
    day_num79           int                                  null,
    day_num80           int                                  null,
    day_num81           int                                  null,
    day_num82           int                                  null,
    day_num83           int                                  null,
    day_num84           int                                  null,
    day_num85           int                                  null,
    day_num86           int                                  null,
    day_num87           int                                  null,
    day_num88           int                                  null,
    day_num89           int                                  null,
    day_num90           int                                  null,
    day_num91           int                                  null,
    day_num92           int                                  null,
    day_num93           int                                  null,
    day_num94           int                                  null,
    day_num95           int                                  null,
    day_num96           int                                  null,
    day_num97           int                                  null,
    day_num98           int                                  null,
    day_num99           int                                  null,
    day_num100          int                                  null,
    day_num101          int                                  null,
    day_num102          int                                  null,
    day_num103          int                                  null,
    day_num104          int                                  null,
    day_num105          int                                  null,
    day_num106          int                                  null,
    day_num107          int                                  null,
    day_num108          int                                  null,
    day_num109          int                                  null,
    day_num110          int                                  null,
    day_num111          int                                  null,
    day_num112          int                                  null,
    day_num113          int                                  null,
    day_num114          int                                  null,
    day_num115          int                                  null,
    day_num116          int                                  null,
    day_num117          int                                  null,
    day_num118          int                                  null,
    day_num119          int                                  null,
    day_num120          int                                  null,
    day_num121          int                                  null,
    day_num122          int                                  null,
    day_num123          int                                  null,
    day_num124          int                                  null,
    day_num125          int                                  null,
    day_num126          int                                  null,
    day_num127          int                                  null,
    day_num128          int                                  null,
    day_num129          int                                  null,
    day_num130          int                                  null,
    day_num131          int                                  null,
    day_num132          int                                  null,
    day_num133          int                                  null,
    day_num134          int                                  null,
    day_num135          int                                  null,
    day_num136          int                                  null,
    day_num137          int                                  null,
    day_num138          int                                  null,
    day_num139          int                                  null,
    day_num140          int                                  null,
    day_num141          int                                  null,
    day_num142          int                                  null,
    day_num143          int                                  null,
    day_num144          int                                  null,
    day_num145          int                                  null,
    day_num146          int                                  null,
    day_num147          int                                  null,
    day_num148          int                                  null,
    day_num149          int                                  null,
    day_num150          int                                  null,
    day_num151          int                                  null,
    day_num152          int                                  null,
    day_num153          int                                  null,
    day_num154          int                                  null,
    day_num155          int                                  null,
    day_num156          int                                  null,
    day_num157          int                                  null,
    day_num158          int                                  null,
    day_num159          int                                  null,
    day_num160          int                                  null,
    day_num161          int                                  null,
    day_num162          int                                  null,
    day_num163          int                                  null,
    day_num164          int                                  null,
    day_num165          int                                  null,
    day_num166          int                                  null,
    day_num167          int                                  null,
    day_num168          int                                  null,
    day_num169          int                                  null,
    day_num170          int                                  null,
    day_num171          int                                  null,
    day_num172          int                                  null,
    day_num173          int                                  null,
    day_num174          int                                  null,
    day_num175          int                                  null,
    day_num176          int                                  null,
    day_num177          int                                  null,
    day_num178          int                                  null,
    day_num179          int                                  null,
    day_num180          int                                  null,
    day_num181          int                                  null,
    day_num182          int                                  null,
    day_num183          int                                  null,
    day_num184          int                                  null,
    day_num185          int                                  null,
    day_num186          int                                  null,
    day_num187          int                                  null,
    day_num188          int                                  null,
    day_num189          int                                  null,
    day_num190          int                                  null,
    day_num191          int                                  null,
    day_num192          int                                  null,
    day_num193          int                                  null,
    day_num194          int                                  null,
    day_num195          int                                  null,
    day_num196          int                                  null,
    day_num197          int                                  null,
    day_num198          int                                  null,
    day_num199          int                                  null,
    day_num200          int                                  null,
    day_num201          int                                  null,
    day_num202          int                                  null,
    day_num203          int                                  null,
    day_num204          int                                  null,
    day_num205          int                                  null,
    day_num206          int                                  null,
    day_num207          int                                  null,
    day_num208          int                                  null,
    day_num209          int                                  null,
    day_num210          int                                  null,
    day_num211          int                                  null,
    day_num212          int                                  null,
    day_num213          int                                  null,
    day_num214          int                                  null,
    day_num215          int                                  null,
    day_num216          int                                  null,
    day_num217          int                                  null,
    day_num218          int                                  null,
    day_num219          int                                  null,
    day_num220          int                                  null,
    day_num221          int                                  null,
    day_num222          int                                  null,
    day_num223          int                                  null,
    day_num224          int                                  null,
    day_num225          int                                  null,
    day_num226          int                                  null,
    day_num227          int                                  null,
    day_num228          int                                  null,
    day_num229          int                                  null,
    day_num230          int                                  null,
    day_num231          int                                  null,
    day_num232          int                                  null,
    day_num233          int                                  null,
    day_num234          int                                  null,
    day_num235          int                                  null,
    day_num236          int                                  null,
    day_num237          int                                  null,
    day_num238          int                                  null,
    day_num239          int                                  null,
    day_num240          int                                  null,
    day_num241          int                                  null,
    day_num242          int                                  null,
    day_num243          int                                  null,
    day_num244          int                                  null,
    day_num245          int                                  null,
    day_num246          int                                  null,
    day_num247          int                                  null,
    day_num248          int                                  null,
    day_num249          int                                  null,
    day_num250          int                                  null,
    day_num251          int                                  null,
    day_num252          int                                  null,
    day_num253          int                                  null,
    day_num254          int                                  null,
    day_num255          int                                  null,
    day_num256          int                                  null,
    day_num257          int                                  null,
    day_num258          int                                  null,
    day_num259          int                                  null,
    day_num260          int                                  null,
    day_num261          int                                  null,
    day_num262          int                                  null,
    day_num263          int                                  null,
    day_num264          int                                  null,
    day_num265          int                                  null,
    day_num266          int                                  null,
    day_num267          int                                  null,
    day_num268          int                                  null,
    day_num269          int                                  null,
    day_num270          int                                  null,
    day_num271          int                                  null,
    day_num272          int                                  null,
    day_num273          int                                  null,
    day_num274          int                                  null,
    day_num275          int                                  null,
    day_num276          int                                  null,
    day_num277          int                                  null,
    day_num278          int                                  null,
    day_num279          int                                  null,
    day_num280          int                                  null,
    day_num281          int                                  null,
    day_num282          int                                  null,
    day_num283          int                                  null,
    day_num284          int                                  null,
    day_num285          int                                  null,
    day_num286          int                                  null,
    day_num287          int                                  null,
    day_num288          int                                  null,
    day_num289          int                                  null,
    day_num290          int                                  null,
    day_num291          int                                  null,
    day_num292          int                                  null,
    day_num293          int                                  null,
    day_num294          int                                  null,
    day_num295          int                                  null,
    day_num296          int                                  null,
    day_num297          int                                  null,
    day_num298          int                                  null,
    day_num299          int                                  null,
    day_num300          int                                  null,
    day_num301          int                                  null,
    day_num302          int                                  null,
    day_num303          int                                  null,
    day_num304          int                                  null,
    day_num305          int                                  null,
    day_num306          int                                  null,
    day_num307          int                                  null,
    day_num308          int                                  null,
    day_num309          int                                  null,
    day_num310          int                                  null,
    day_num311          int                                  null,
    day_num312          int                                  null,
    day_num313          int                                  null,
    day_num314          int                                  null,
    day_num315          int                                  null,
    day_num316          int                                  null,
    day_num317          int                                  null,
    day_num318          int                                  null,
    day_num319          int                                  null,
    day_num320          int                                  null,
    day_num321          int                                  null,
    day_num322          int                                  null,
    day_num323          int                                  null,
    day_num324          int                                  null,
    day_num325          int                                  null,
    day_num326          int                                  null,
    day_num327          int                                  null,
    day_num328          int                                  null,
    day_num329          int                                  null,
    day_num330          int                                  null,
    day_num331          int                                  null,
    day_num332          int                                  null,
    day_num333          int                                  null,
    day_num334          int                                  null,
    day_num335          int                                  null,
    day_num336          int                                  null,
    day_num337          int                                  null,
    day_num338          int                                  null,
    day_num339          int                                  null,
    day_num340          int                                  null,
    day_num341          int                                  null,
    day_num342          int                                  null,
    day_num343          int                                  null,
    day_num344          int                                  null,
    day_num345          int                                  null,
    day_num346          int                                  null,
    day_num347          int                                  null,
    day_num348          int                                  null,
    day_num349          int                                  null,
    day_num350          int                                  null,
    day_num351          int                                  null,
    day_num352          int                                  null,
    day_num353          int                                  null,
    day_num354          int                                  null,
    day_num355          int                                  null,
    day_num356          int                                  null,
    day_num357          int                                  null,
    day_num358          int                                  null,
    day_num359          int                                  null,
    day_num360          int                                  null,
    day_num361          int                                  null,
    day_num362          int                                  null,
    day_num363          int                                  null,
    day_num364          int                                  null,
    day_num365          int                                  null,
    day_num366          int                                  null,
    factory_id          bigint                               null comment '工厂ID',
    tenant_id           bigint                               null comment '租户ID',
    is_delete           tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time         datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by           bigint                               null comment '创建人',
    update_time         datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by           bigint                               null comment '修改人',
    trace_id            varchar(255)                         null comment '调用链路',
    version_num         int        default 0                 null comment '版本号',
    make_sale_config_id int                                  null
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
    make_month_id    int                                  null,
    sale_config_code varchar(255)                         null,
    year             int                                  null,
    day_num1         int                                  null,
    day_num2         int                                  null,
    day_num3         int                                  null,
    day_num4         int                                  null,
    day_num5         int                                  null,
    day_num6         int                                  null,
    day_num7         int                                  null,
    day_num8         int                                  null,
    day_num9         int                                  null,
    day_num10        int                                  null,
    day_num11        int                                  null,
    day_num12        int                                  null,
    day_num13        int                                  null,
    day_num14        int                                  null,
    day_num15        int                                  null,
    day_num16        int                                  null,
    day_num17        int                                  null,
    day_num18        int                                  null,
    day_num19        int                                  null,
    day_num20        int                                  null,
    day_num21        int                                  null,
    day_num22        int                                  null,
    day_num23        int                                  null,
    day_num24        int                                  null,
    day_num25        int                                  null,
    day_num26        int                                  null,
    day_num27        int                                  null,
    day_num28        int                                  null,
    day_num29        int                                  null,
    day_num30        int                                  null,
    day_num31        int                                  null,
    day_num32        int                                  null,
    day_num33        int                                  null,
    day_num34        int                                  null,
    day_num35        int                                  null,
    day_num36        int                                  null,
    day_num37        int                                  null,
    day_num38        int                                  null,
    day_num39        int                                  null,
    day_num40        int                                  null,
    day_num41        int                                  null,
    day_num42        int                                  null,
    day_num43        int                                  null,
    day_num44        int                                  null,
    day_num45        int                                  null,
    day_num46        int                                  null,
    day_num47        int                                  null,
    day_num48        int                                  null,
    day_num49        int                                  null,
    day_num50        int                                  null,
    day_num51        int                                  null,
    day_num52        int                                  null,
    day_num53        int                                  null,
    day_num54        int                                  null,
    day_num55        int                                  null,
    day_num56        int                                  null,
    day_num57        int                                  null,
    day_num58        int                                  null,
    day_num59        int                                  null,
    day_num60        int                                  null,
    day_num61        int                                  null,
    day_num62        int                                  null,
    day_num63        int                                  null,
    day_num64        int                                  null,
    day_num65        int                                  null,
    day_num66        int                                  null,
    day_num67        int                                  null,
    day_num68        int                                  null,
    day_num69        int                                  null,
    day_num70        int                                  null,
    day_num71        int                                  null,
    day_num72        int                                  null,
    day_num73        int                                  null,
    day_num74        int                                  null,
    day_num75        int                                  null,
    day_num76        int                                  null,
    day_num77        int                                  null,
    day_num78        int                                  null,
    day_num79        int                                  null,
    day_num80        int                                  null,
    day_num81        int                                  null,
    day_num82        int                                  null,
    day_num83        int                                  null,
    day_num84        int                                  null,
    day_num85        int                                  null,
    day_num86        int                                  null,
    day_num87        int                                  null,
    day_num88        int                                  null,
    day_num89        int                                  null,
    day_num90        int                                  null,
    day_num91        int                                  null,
    day_num92        int                                  null,
    day_num93        int                                  null,
    day_num94        int                                  null,
    day_num95        int                                  null,
    day_num96        int                                  null,
    day_num97        int                                  null,
    day_num98        int                                  null,
    day_num99        int                                  null,
    day_num100       int                                  null,
    day_num101       int                                  null,
    day_num102       int                                  null,
    day_num103       int                                  null,
    day_num104       int                                  null,
    day_num105       int                                  null,
    day_num106       int                                  null,
    day_num107       int                                  null,
    day_num108       int                                  null,
    day_num109       int                                  null,
    day_num110       int                                  null,
    day_num111       int                                  null,
    day_num112       int                                  null,
    day_num113       int                                  null,
    day_num114       int                                  null,
    day_num115       int                                  null,
    day_num116       int                                  null,
    day_num117       int                                  null,
    day_num118       int                                  null,
    day_num119       int                                  null,
    day_num120       int                                  null,
    day_num121       int                                  null,
    day_num122       int                                  null,
    day_num123       int                                  null,
    day_num124       int                                  null,
    day_num125       int                                  null,
    day_num126       int                                  null,
    day_num127       int                                  null,
    day_num128       int                                  null,
    day_num129       int                                  null,
    day_num130       int                                  null,
    day_num131       int                                  null,
    day_num132       int                                  null,
    day_num133       int                                  null,
    day_num134       int                                  null,
    day_num135       int                                  null,
    day_num136       int                                  null,
    day_num137       int                                  null,
    day_num138       int                                  null,
    day_num139       int                                  null,
    day_num140       int                                  null,
    day_num141       int                                  null,
    day_num142       int                                  null,
    day_num143       int                                  null,
    day_num144       int                                  null,
    day_num145       int                                  null,
    day_num146       int                                  null,
    day_num147       int                                  null,
    day_num148       int                                  null,
    day_num149       int                                  null,
    day_num150       int                                  null,
    day_num151       int                                  null,
    day_num152       int                                  null,
    day_num153       int                                  null,
    day_num154       int                                  null,
    day_num155       int                                  null,
    day_num156       int                                  null,
    day_num157       int                                  null,
    day_num158       int                                  null,
    day_num159       int                                  null,
    day_num160       int                                  null,
    day_num161       int                                  null,
    day_num162       int                                  null,
    day_num163       int                                  null,
    day_num164       int                                  null,
    day_num165       int                                  null,
    day_num166       int                                  null,
    day_num167       int                                  null,
    day_num168       int                                  null,
    day_num169       int                                  null,
    day_num170       int                                  null,
    day_num171       int                                  null,
    day_num172       int                                  null,
    day_num173       int                                  null,
    day_num174       int                                  null,
    day_num175       int                                  null,
    day_num176       int                                  null,
    day_num177       int                                  null,
    day_num178       int                                  null,
    day_num179       int                                  null,
    day_num180       int                                  null,
    day_num181       int                                  null,
    day_num182       int                                  null,
    day_num183       int                                  null,
    day_num184       int                                  null,
    day_num185       int                                  null,
    day_num186       int                                  null,
    day_num187       int                                  null,
    day_num188       int                                  null,
    day_num189       int                                  null,
    day_num190       int                                  null,
    day_num191       int                                  null,
    day_num192       int                                  null,
    day_num193       int                                  null,
    day_num194       int                                  null,
    day_num195       int                                  null,
    day_num196       int                                  null,
    day_num197       int                                  null,
    day_num198       int                                  null,
    day_num199       int                                  null,
    day_num200       int                                  null,
    day_num201       int                                  null,
    day_num202       int                                  null,
    day_num203       int                                  null,
    day_num204       int                                  null,
    day_num205       int                                  null,
    day_num206       int                                  null,
    day_num207       int                                  null,
    day_num208       int                                  null,
    day_num209       int                                  null,
    day_num210       int                                  null,
    day_num211       int                                  null,
    day_num212       int                                  null,
    day_num213       int                                  null,
    day_num214       int                                  null,
    day_num215       int                                  null,
    day_num216       int                                  null,
    day_num217       int                                  null,
    day_num218       int                                  null,
    day_num219       int                                  null,
    day_num220       int                                  null,
    day_num221       int                                  null,
    day_num222       int                                  null,
    day_num223       int                                  null,
    day_num224       int                                  null,
    day_num225       int                                  null,
    day_num226       int                                  null,
    day_num227       int                                  null,
    day_num228       int                                  null,
    day_num229       int                                  null,
    day_num230       int                                  null,
    day_num231       int                                  null,
    day_num232       int                                  null,
    day_num233       int                                  null,
    day_num234       int                                  null,
    day_num235       int                                  null,
    day_num236       int                                  null,
    day_num237       int                                  null,
    day_num238       int                                  null,
    day_num239       int                                  null,
    day_num240       int                                  null,
    day_num241       int                                  null,
    day_num242       int                                  null,
    day_num243       int                                  null,
    day_num244       int                                  null,
    day_num245       int                                  null,
    day_num246       int                                  null,
    day_num247       int                                  null,
    day_num248       int                                  null,
    day_num249       int                                  null,
    day_num250       int                                  null,
    day_num251       int                                  null,
    day_num252       int                                  null,
    day_num253       int                                  null,
    day_num254       int                                  null,
    day_num255       int                                  null,
    day_num256       int                                  null,
    day_num257       int                                  null,
    day_num258       int                                  null,
    day_num259       int                                  null,
    day_num260       int                                  null,
    day_num261       int                                  null,
    day_num262       int                                  null,
    day_num263       int                                  null,
    day_num264       int                                  null,
    day_num265       int                                  null,
    day_num266       int                                  null,
    day_num267       int                                  null,
    day_num268       int                                  null,
    day_num269       int                                  null,
    day_num270       int                                  null,
    day_num271       int                                  null,
    day_num272       int                                  null,
    day_num273       int                                  null,
    day_num274       int                                  null,
    day_num275       int                                  null,
    day_num276       int                                  null,
    day_num277       int                                  null,
    day_num278       int                                  null,
    day_num279       int                                  null,
    day_num280       int                                  null,
    day_num281       int                                  null,
    day_num282       int                                  null,
    day_num283       int                                  null,
    day_num284       int                                  null,
    day_num285       int                                  null,
    day_num286       int                                  null,
    day_num287       int                                  null,
    day_num288       int                                  null,
    day_num289       int                                  null,
    day_num290       int                                  null,
    day_num291       int                                  null,
    day_num292       int                                  null,
    day_num293       int                                  null,
    day_num294       int                                  null,
    day_num295       int                                  null,
    day_num296       int                                  null,
    day_num297       int                                  null,
    day_num298       int                                  null,
    day_num299       int                                  null,
    day_num300       int                                  null,
    day_num301       int                                  null,
    day_num302       int                                  null,
    day_num303       int                                  null,
    day_num304       int                                  null,
    day_num305       int                                  null,
    day_num306       int                                  null,
    day_num307       int                                  null,
    day_num308       int                                  null,
    day_num309       int                                  null,
    day_num310       int                                  null,
    day_num311       int                                  null,
    day_num312       int                                  null,
    day_num313       int                                  null,
    day_num314       int                                  null,
    day_num315       int                                  null,
    day_num316       int                                  null,
    day_num317       int                                  null,
    day_num318       int                                  null,
    day_num319       int                                  null,
    day_num320       int                                  null,
    day_num321       int                                  null,
    day_num322       int                                  null,
    day_num323       int                                  null,
    day_num324       int                                  null,
    day_num325       int                                  null,
    day_num326       int                                  null,
    day_num327       int                                  null,
    day_num328       int                                  null,
    day_num329       int                                  null,
    day_num330       int                                  null,
    day_num331       int                                  null,
    day_num332       int                                  null,
    day_num333       int                                  null,
    day_num334       int                                  null,
    day_num335       int                                  null,
    day_num336       int                                  null,
    day_num337       int                                  null,
    day_num338       int                                  null,
    day_num339       int                                  null,
    day_num340       int                                  null,
    day_num341       int                                  null,
    day_num342       int                                  null,
    day_num343       int                                  null,
    day_num344       int                                  null,
    day_num345       int                                  null,
    day_num346       int                                  null,
    day_num347       int                                  null,
    day_num348       int                                  null,
    day_num349       int                                  null,
    day_num350       int                                  null,
    day_num351       int                                  null,
    day_num352       int                                  null,
    day_num353       int                                  null,
    day_num354       int                                  null,
    day_num355       int                                  null,
    day_num356       int                                  null,
    day_num357       int                                  null,
    day_num358       int                                  null,
    day_num359       int                                  null,
    day_num360       int                                  null,
    day_num361       int                                  null,
    day_num362       int                                  null,
    day_num363       int                                  null,
    day_num364       int                                  null,
    day_num365       int                                  null,
    day_num366       int                                  null,
    factory_id       int                                  null,
    tenant_id        bigint                               null comment '租户ID',
    is_delete        tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time      datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by        bigint                               null comment '创建人',
    update_time      datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by        bigint                               null comment '修改人',
    trace_id         varchar(255)                         null comment '调用链路',
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
    month_01    varchar(255)                         null,
    month_02    varchar(255)                         null,
    month_03    varchar(255)                         null,
    month_04    mediumint                            null,
    month_05    varchar(255)                         null,
    month_06    varchar(255)                         null,
    month_07    varchar(255)                         null,
    month_08    varchar(255)                         null,
    month_09    varchar(255)                         null,
    month_10    varchar(255)                         null,
    month_11    varchar(255)                         null,
    month_12    varchar(255)                         null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(255)                         null comment '调用链路',
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
    month_01       varchar(255)                         null,
    month_02       varchar(255)                         null,
    month_03       varchar(255)                         null,
    month_04       decimal(9, 8)                        null,
    month_05       varchar(255)                         null,
    month_06       varchar(255)                         null,
    month_07       varchar(255)                         null,
    month_08       varchar(255)                         null,
    month_09       varchar(255)                         null,
    month_10       varchar(255)                         null,
    month_11       varchar(255)                         null,
    month_12       varchar(255)                         null,
    tenant_id      bigint                               null comment '租户ID',
    is_delete      tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time    datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by      bigint                               null comment '创建人',
    update_time    datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by      bigint                               null comment '修改人',
    trace_id       varchar(255)                         null comment '调用链路',
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
    use_forecast    tinyint                              null,
    tenant_id       bigint                               null comment '租户ID',
    is_delete       tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time     datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by       bigint                               null comment '创建人',
    update_time     datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by       bigint                               null comment '修改人',
    trace_id        varchar(255)                         null comment '调用链路',
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
    trace_id                 varchar(255)                         null comment '调用链路',
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

create table if not exists aps_make_capacity_factory
(
    id                     bigint auto_increment comment 'ID 自增'
        primary key,
    factory_id             bigint                               null comment '工厂ID',
    make_capacity_quantity varchar(255)                         null,
    year                   smallint                             null,
    month                  tinyint                              null,
    day_min1               tinyint                              null,
    day_max1               tinyint                              null,
    day_min2               tinyint                              null,
    day_max2               tinyint                              null,
    day_min3               tinyint                              null,
    day_max3               tinyint                              null,
    day_min4               tinyint                              null,
    day_max4               tinyint                              null,
    day_min5               tinyint                              null,
    day_max5               tinyint                              null,
    day_min6               tinyint                              null,
    day_max6               tinyint                              null,
    day_min7               tinyint                              null,
    day_max7               tinyint                              null,
    day_min8               tinyint                              null,
    day_max8               tinyint                              null,
    day_min9               tinyint                              null,
    day_max9               tinyint                              null,
    day_min10              tinyint                              null,
    day_max10              tinyint                              null,
    day_min11              tinyint                              null,
    day_max11              tinyint                              null,
    day_min12              tinyint                              null,
    day_max12              tinyint                              null,
    day_min13              tinyint                              null,
    day_max13              tinyint                              null,
    day_min14              tinyint                              null,
    day_max14              tinyint                              null,
    day_min15              tinyint                              null,
    day_max15              tinyint                              null,
    day_min16              tinyint                              null,
    day_max16              tinyint                              null,
    day_min17              tinyint                              null,
    day_max17              tinyint                              null,
    day_min18              tinyint                              null,
    day_max18              tinyint                              null,
    day_min19              tinyint                              null,
    day_max19              tinyint                              null,
    day_min20              tinyint                              null,
    day_max20              tinyint                              null,
    day_min21              tinyint                              null,
    day_max21              tinyint                              null,
    day_min22              tinyint                              null,
    day_max22              tinyint                              null,
    day_min23              tinyint                              null,
    day_max23              tinyint                              null,
    day_min24              tinyint                              null,
    day_max24              tinyint                              null,
    day_min25              tinyint                              null,
    day_max25              tinyint                              null,
    day_min26              tinyint                              null,
    day_max26              tinyint                              null,
    day_min27              tinyint                              null,
    day_max27              tinyint                              null,
    day_min28              tinyint                              null,
    day_max28              tinyint                              null,
    day_min29              tinyint                              null,
    day_max29              tinyint                              null,
    day_min30              tinyint                              null,
    day_max30              tinyint                              null,
    day_min31              tinyint                              null,
    day_max31              tinyint                              null,
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
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
    day_min1               varchar(255)                         null,
    day_max1               varchar(255)                         null,
    day_min2               varchar(255)                         null,
    day_max2               varchar(255)                         null,
    day_min3               varchar(255)                         null,
    day_max3               varchar(255)                         null,
    day_min4               varchar(255)                         null,
    day_max4               varchar(255)                         null,
    day_min5               varchar(255)                         null,
    day_max5               varchar(255)                         null,
    day_min6               varchar(255)                         null,
    day_max6               varchar(255)                         null,
    day_min7               varchar(255)                         null,
    day_max7               varchar(255)                         null,
    day_min8               varchar(255)                         null,
    day_max8               varchar(255)                         null,
    day_min9               varchar(255)                         null,
    day_max9               varchar(255)                         null,
    day_min10              varchar(255)                         null,
    day_max10              varchar(255)                         null,
    day_min11              varchar(255)                         null,
    day_max11              varchar(255)                         null,
    day_min12              varchar(255)                         null,
    day_max12              varchar(255)                         null,
    day_min13              varchar(255)                         null,
    day_max13              varchar(255)                         null,
    day_min14              varchar(255)                         null,
    day_max14              varchar(255)                         null,
    day_min15              varchar(255)                         null,
    day_max15              varchar(255)                         null,
    day_min16              varchar(255)                         null,
    day_max16              varchar(255)                         null,
    day_min17              varchar(255)                         null,
    day_max17              varchar(255)                         null,
    day_min18              varchar(255)                         null,
    day_max18              varchar(255)                         null,
    day_min19              varchar(255)                         null,
    day_max19              varchar(255)                         null,
    day_min20              varchar(255)                         null,
    day_max20              varchar(255)                         null,
    day_min21              varchar(255)                         null,
    day_max21              varchar(255)                         null,
    day_min22              varchar(255)                         null,
    day_max22              varchar(255)                         null,
    day_min23              varchar(255)                         null,
    day_max23              varchar(255)                         null,
    day_min24              varchar(255)                         null,
    day_max24              varchar(255)                         null,
    day_min25              varchar(255)                         null,
    day_max25              varchar(255)                         null,
    day_min26              varchar(255)                         null,
    day_max26              varchar(255)                         null,
    day_min27              varchar(255)                         null,
    day_max27              varchar(255)                         null,
    day_min28              varchar(255)                         null,
    day_max28              varchar(255)                         null,
    day_min29              varchar(255)                         null,
    day_max29              varchar(255)                         null,
    day_min30              varchar(255)                         null,
    day_max30              varchar(255)                         null,
    day_min31              varchar(255)                         null,
    day_max31              varchar(255)                         null,
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
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
    sale_config_id         tinyint                              null,
    make_capacity_quantity varchar(255)                         null,
    year                   smallint                             null,
    month                  tinyint                              null,
    day_min1               tinyint                              null,
    day_max1               tinyint                              null,
    day_min2               tinyint                              null,
    day_max2               tinyint                              null,
    day_min3               tinyint                              null,
    day_max3               tinyint                              null,
    day_min4               tinyint                              null,
    day_max4               tinyint                              null,
    day_min5               tinyint                              null,
    day_max5               tinyint                              null,
    day_min6               tinyint                              null,
    day_max6               tinyint                              null,
    day_min7               tinyint                              null,
    day_max7               tinyint                              null,
    day_min8               tinyint                              null,
    day_max8               tinyint                              null,
    day_min9               tinyint                              null,
    day_max9               tinyint                              null,
    day_min10              tinyint                              null,
    day_max10              tinyint                              null,
    day_min11              tinyint                              null,
    day_max11              tinyint                              null,
    day_min12              tinyint                              null,
    day_max12              tinyint                              null,
    day_min13              tinyint                              null,
    day_max13              tinyint                              null,
    day_min14              tinyint                              null,
    day_max14              tinyint                              null,
    day_min15              tinyint                              null,
    day_max15              tinyint                              null,
    day_min16              tinyint                              null,
    day_max16              tinyint                              null,
    day_min17              tinyint                              null,
    day_max17              tinyint                              null,
    day_min18              tinyint                              null,
    day_max18              tinyint                              null,
    day_min19              tinyint                              null,
    day_max19              tinyint                              null,
    day_min20              tinyint                              null,
    day_max20              tinyint                              null,
    day_min21              varchar(255)                         null,
    day_max21              varchar(255)                         null,
    day_min22              varchar(255)                         null,
    day_max22              varchar(255)                         null,
    day_min23              varchar(255)                         null,
    day_max23              varchar(255)                         null,
    day_min24              varchar(255)                         null,
    day_max24              varchar(255)                         null,
    day_min25              varchar(255)                         null,
    day_max25              varchar(255)                         null,
    day_min26              varchar(255)                         null,
    day_max26              varchar(255)                         null,
    day_min27              varchar(255)                         null,
    day_max27              varchar(255)                         null,
    day_min28              varchar(255)                         null,
    day_max28              varchar(255)                         null,
    day_min29              varchar(255)                         null,
    day_max29              varchar(255)                         null,
    day_min30              varchar(255)                         null,
    day_max30              varchar(255)                         null,
    day_min31              varchar(255)                         null,
    day_max31              varchar(255)                         null,
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
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
    order_status          tinyint                              null,
    order_total_price     tinyint                              null,
    goods_id              bigint                               null comment '商品ID',
    reserve_amount        mediumint                            null,
    reserve_datetime      varchar(255)                         null,
    finish_payed_amount   tinyint                              null,
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
    trace_id              varchar(255)                         null comment '调用链路',
    version_num           int        default 0                 null comment '版本号'
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
    goods_amount           bigint                               null,
    goods_price            varchar(255)                         null,
    goods_total_price      tinyint                              null,
    goods_unit             varchar(255)                         null,
    goods_unit_price       smallint                             null,
    goods_unit_total_price varchar(255)                         null,
    goods_status_id        bigint                               null comment '订单状态',
    factory_id             bigint                               null comment '工厂ID',
    tenant_id              bigint                               null comment '租户ID',
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
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
    bom_usage            int                                  null comment '使用量',
    bom_unit             varchar(255)                         null comment '单位',
    bom_cost_price       mediumint                            null comment '成本价',
    bom_cost_price_unit  varchar(255)                         null comment '单位',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(255)                         null comment '调用链路',
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
    trace_id           varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    factory_id  tinyint                              null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id      varchar(255)                         null comment '调用链路',
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
    trace_id            varchar(255)                         null comment '调用链路',
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
    trace_id        varchar(255)                         null comment '调用链路',
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
    trace_id        varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id     varchar(255)                         null comment '调用链路',
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
    trace_id        varchar(255)                         null comment '调用链路',
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
    trace_id            varchar(255)                         null comment '调用链路',
    version_num         int        default 0                 null comment '版本号'
)
    comment '排产约束表';

create index idx_aps_scheduling_constraints_tenant_id
    on aps_scheduling_constraints (tenant_id);

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
    bom_usage            int                                  null comment '使用量',
    bom_unit             varchar(255)                         null comment '单位',
    bom_cost_price       mediumint                            null comment '成本价',
    bom_cost_price_unit  varchar(255)                         null comment '单位',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(255)                         null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
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
    bom_code             varchar(255)                         null comment 'bom 编码',
    bom_name             varchar(255)                         null comment 'bom 名称',
    bom_usage            int                                  null comment '使用量',
    bom_unit             varchar(255)                         null comment '单位',
    bom_cost_price       mediumint                            null comment '成本价',
    bom_cost_price_unit  varchar(255)                         null comment '单位',
    bom_use_work_station bigint                               null comment '使用工位',
    bom_use_date         date                                 null comment '使用时间',
    factory_id           bigint                               null comment '工厂ID',
    tenant_id            bigint                               null comment '租户ID',
    is_delete            tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time          datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by            bigint                               null comment '创建人',
    update_time          datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by            bigint                               null comment '修改人',
    trace_id             varchar(255)                         null comment '调用链路',
    version_num          int        default 0                 null comment '版本号'
)
    comment '订单商品零件汇总表';

create index idx_aps_scheduling_goods_bom_total_scheduling_id
    on aps_scheduling_goods_bom_total (scheduling_id);

create index idx_aps_scheduling_goods_bom_total_tenant_id
    on aps_scheduling_goods_bom_total (tenant_id);

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
    trace_id                  varchar(255)                         null comment '调用链路',
    version_num               int        default 0                 null comment '版本号',
    scheduling_version_name   varchar(255)                         null,
    scheduling_constraints_id varchar(255)                         null,
    header_list               text                                 null,
    capacity_header_list      text                                 null,
    capacity_date_list        varchar(1024)                        null,
    scheduling_day_count      tinyint                              null,
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
    trace_id              varchar(255)                         null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    result_type           tinyint                              null,
    number_index          varchar(255)                         null,
    factory_id            bigint                               null comment '工厂id',
    goods_status_id       bigint                               null comment '商品状态'
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id              varchar(255)                         null comment '调用链路',
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
    trace_id      varchar(255)                         null comment '调用链路',
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
    id          bigint auto_increment comment 'ID 自增'
        primary key,
    status_code varchar(255)                         null,
    status_name varchar(255)                         null,
    factory_id  varchar(255)                         null,
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(255)                         null comment '调用链路',
    version_num int        default 0                 null comment '版本号'
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
    trace_id       varchar(255)                         null comment '调用链路',
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
    trace_id       varchar(255)                         null comment '调用链路',
    version_num    int        default 0                 null comment '版本号'
)
    comment '车间/工位表';

create index idx_aps_workshop_station_tenant_id
    on aps_workshop_station (tenant_id);

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
    trace_id        varchar(255)                         null comment '调用链路',
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
    sort_index  tinyint                              null,
    is_picture  varchar(255)                         null,
    plan_status varchar(255)                         null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(255)                         null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   bigint                               null comment '租户ID'
)
    comment '表头配置表';

create index idx_base_table_header_tenant_id
    on base_table_header (tenant_id);

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
    trace_id               varchar(255)                         null comment '调用链路',
    is_used                varchar(255)                         null,
    version_num            int        default 0                 null comment '版本号',
    order_no               varchar(255)                         null,
    goods_name             varchar(255)                         null,
    goods_cost_price_total varchar(255)                         null
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
    goods_cost_price       tinyint                              null,
    goods_buy_count        tinyint                              null,
    goods_unit             varchar(255)                         null,
    goods_cost_price_total smallint                             null,
    goods_buy_remark       varchar(255)                         null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    tenant_id              bigint                               null comment '租户ID',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
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
    trace_id                 varchar(255)                         null comment '调用链路',
    version_num              int        default 0                 null comment '版本号',
    tenant_id                bigint                               null comment '租户ID',
    cost_price_total         varchar(255)                         null,
    sales_price_total        mediumint                            null,
    goods_gross_profit_total smallint                             null,
    goods_net_profit_total   smallint                             null,
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
    cost_price               smallint                             null,
    sales_price              smallint                             null,
    warning_count            varchar(255)                         null,
    goods_gross_profit       smallint                             null,
    goods_net_profit         smallint                             null,
    goods_inventory_count    varchar(255)                         null,
    is_delete                tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time              datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by                bigint                               null comment '创建人',
    update_time              datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by                bigint                               null comment '修改人',
    trace_id                 varchar(255)                         null comment '调用链路',
    version_num              int        default 0                 null comment '版本号',
    tenant_id                bigint                               null comment '租户ID',
    goods_buy_count          varchar(255)                         null,
    cost_price_total         mediumint                            null,
    sales_price_total        mediumint                            null,
    goods_gross_profit_total smallint                             null,
    goods_net_profit_total   smallint                             null,
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
    trace_id              varchar(255)                         null comment '调用链路',
    version_num           int        default 0                 null comment '版本号',
    cost_price            tinyint                              null,
    sales_price           tinyint                              null,
    warning_count         tinyint                              null,
    goods_gross_profit    tinyint                              null,
    goods_net_profit      tinyint                              null,
    goods_inventory_count tinyint                              null,
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
    cost_price            tinyint                              null,
    sales_price           tinyint                              null,
    warning_count         tinyint                              null,
    goods_gross_profit    tinyint                              null,
    goods_net_profit      tinyint                              null,
    goods_inventory_count tinyint                              null,
    is_inventory          tinyint                              null,
    is_done               tinyint                              null,
    is_delete             tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time           datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by             bigint                               null comment '创建人',
    update_time           datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by             bigint                               null comment '修改人',
    trace_id              varchar(255)                         null comment '调用链路',
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
    trace_id               varchar(255)                         null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    order_total_sale_price varchar(255)                         null
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
    goods_count            tinyint                              null,
    goods_cost_price       tinyint                              null,
    goods_sale_price       tinyint                              null,
    goods_gross_profit     varchar(255)                         null,
    goods_net_profit       varchar(255)                         null,
    is_delete              tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time            datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by              bigint                               null comment '创建人',
    update_time            datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by              bigint                               null comment '修改人',
    trace_id               varchar(255)                         null comment '调用链路',
    version_num            int        default 0                 null comment '版本号',
    goods_total_sale_price smallint                             null
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
    trace_id          varchar(255)                         null comment '调用链路',
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
    trace_id       varchar(255)                         null comment '调用链路',
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
    factory_id   tinyint                              null,
    brand_code   varchar(255)                         null,
    brand_name   varchar(255)                         null,
    brand_status varchar(255)                         null,
    is_delete    tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time  datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by    bigint                               null comment '创建人',
    update_time  datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by    bigint                               null comment '修改人',
    trace_id     varchar(255)                         null comment '调用链路',
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
    trace_id          varchar(255)                         null comment '调用链路',
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
    day_month   tinyint                              null,
    day_year    smallint                             null,
    day_1       tinyint                              null,
    day_2       tinyint                              null,
    day_3       tinyint                              null,
    day_4       tinyint                              null,
    day_5       tinyint                              null,
    day_6       tinyint                              null,
    day_7       tinyint                              null,
    day_8       tinyint                              null,
    day_9       tinyint                              null,
    day_10      tinyint                              null,
    day_11      tinyint                              null,
    day_12      tinyint                              null,
    day_13      tinyint                              null,
    day_14      tinyint                              null,
    day_15      tinyint                              null,
    day_16      tinyint                              null,
    day_17      tinyint                              null,
    day_18      tinyint                              null,
    day_19      tinyint                              null,
    day_20      tinyint                              null,
    day_21      tinyint                              null,
    day_22      tinyint                              null,
    day_23      tinyint                              null,
    day_24      tinyint                              null,
    day_25      tinyint                              null,
    day_26      tinyint                              null,
    day_27      tinyint                              null,
    day_28      tinyint                              null,
    day_29      tinyint                              null,
    day_30      tinyint                              null,
    day_31      tinyint                              null,
    factory_id  bigint                               null comment '工厂ID',
    tenant_id   bigint                               null comment '租户ID',
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   bigint                               null comment '修改人',
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id         varchar(255)                         null comment '调用链路',
    version_num      int        default 0                 null comment '版本号'
)
    comment '数据字典表';

create table if not exists t_district_code
(
    id          smallint                             null,
    code        varchar(255)                         null,
    name        varchar(255)                         null,
    parent_code varchar(255)                         null,
    level       tinyint                              null,
    is_delete   tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by   bigint                               null comment '创建人',
    update_time datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by   varchar(255)                         null,
    trace_id    varchar(255)                         null comment '调用链路',
    version_num int        default 0                 null comment '版本号',
    tenant_id   varchar(255)                         null
)
    comment '地区代码表';

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
    trace_id       varchar(255)                         null comment '调用链路',
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
    trace_id        varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
    tenant_id   bigint                               null comment '租户ID',
    version_num int        default 0                 null comment '版本号',
    user_pwd    varchar(255)                         null
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id      varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
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
    trace_id    varchar(255)                         null comment '调用链路',
    tenant_id   bigint                               null comment '租户ID',
    version_num int        default 0                 null comment '版本号'
)
    comment '租户信息表';


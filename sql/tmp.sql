create table if not exists aps_seller_store
(
    id                 bigint auto_increment comment 'ID 自增' primary key,

    seller_store_code          varchar(32) comment '销售门店编码',
    seller_store_name          varchar(32) comment '销售门店名称',
    seller_store_phone         varchar(32) comment '销售门店手机号',
    seller_store_province_code varchar(32) comment '销售门店省编码',
    seller_store_city_code     varchar(32) comment '销售门店市编码',
    seller_store_area_code     varchar(32) comment '销售门店区编码',
    seller_store_addr          varchar(1024) comment '销售门店地址',
    seller_store_gd_lon        decimal(10, 6) comment '销售门店高的经纬度 如117.500244',
    seller_store_gd_lat        decimal(10, 6) comment '销售门店高的经纬度 如 40.417801 ',
    tenant_id          bigint                               null comment '租户ID',
    is_delete          tinyint(1) default 0                 null comment '是否删除 0 否,1 是',
    create_time        datetime   default CURRENT_TIMESTAMP null comment '创建时间',
    create_by          bigint                               null comment '创建人',
    update_time        datetime   default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
    update_by          bigint                               null comment '修改人',
    trace_id           varchar(64)                          null comment '调用链路',
    version_num        int        default 0                 null comment '版本号',
    key idx_scheduling_day_id (tenant_id)

) comment 'aps销售门店';






create table if not exists flow_form
(
  id  bigint auto_increment comment 'ID 自增' primary key,
 form_name  varchar(64)  null comment '表单名称',
 form_code  varchar(64)  null comment '表单编码',
  tenant_id  bigint null comment '租户ID',
  is_delete tinyint(1) default 0 null comment '是否删除 0 否,1 是',
  create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
  create_by bigint null comment '创建人',
  update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
  update_by bigint null comment '修改人',
  trace_id  varchar(64)  null comment '调用链路',
  version_num int  default 0 null comment '版本号',
  key idx_form_code(form_code)

) comment '工作流表单表';





create table if not exists flow_form_item
(
  id  bigint auto_increment comment 'ID 自增' primary key,
  form_id bigint null comment '表单ID',
  form_item_name  varchar(64)  null comment '表单项名称',
  form_item_filed  varchar(64)  null comment '表单项字段',
  form_item_value  varchar(512)  null comment '表单项类型',
  form_item_default_value  varchar(512)  null comment '表单项默认值',
  form_item_value_type  varchar(64) null comment '表单值类型  text , date , dateTime ,array',
  is_add_flow_value  tinyint(1) default 0 null comment '是否添加流程变量 0 否,1 是',
  -- 必填
  is_required tinyint(1) default 0 null comment '是否必填 0 否,1 是',
  sort_index int  default 0 null comment '排序',
  -- 失去焦点事件
  lose_focus_event  varchar(512) null comment '失去焦点事件',
  tenant_id  bigint null comment '租户ID',
  is_delete tinyint(1) default 0 null comment '是否删除 0 否,1 是',
  create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
  create_by bigint null comment '创建人',
  update_time datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '修改时间',
  update_by bigint null comment '修改人',
  trace_id  varchar(64)  null comment '调用链路',
  version_num int  default 0 null comment '版本号',
  key idx_form_id(form_id)

) comment '工作流表单项表';

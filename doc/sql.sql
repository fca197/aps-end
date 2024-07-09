alter table  aps_order

add  column  urgency_level int(10) default 0  comment  '紧急度0最小,越大越紧急';

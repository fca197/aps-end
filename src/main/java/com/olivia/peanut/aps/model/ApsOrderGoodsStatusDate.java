package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)表实体类
 *
 * @author peanut
 * @since 2024-07-05 13:44:45
 */
@Accessors(chain=true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_goods_status_date")
public class ApsOrderGoodsStatusDate  extends  BaseEntity {
     private Long orderId;
        /***
            *  商品ID
            */
     private Long goodsId;
        /***
            *  订单状态
            */
     private Long goodsStatusId;
        /***
            *  期望制造时间
            */
     private LocalDate expectMakeTime;
        /***
            *  实际制造时间
            */
     private LocalDate actualMakeTime;
        /***
            *  工厂ID
            */
     private Long factoryId;
        /***
            *  状态索引
            */
     private Integer statusIndex;
        /***
            *  预计开始时间
            */
     private LocalDate expectMakeBeginTime;
        /***
            *  预计结束时间
            */
     private LocalDate expectMakeEndTime;
        /***
            *  实际开始时间
            */
     private LocalDate actualMakeBeginTime;
        /***
            *  实际结束时间
            */
     private LocalDate actualMakeEndTime;

}


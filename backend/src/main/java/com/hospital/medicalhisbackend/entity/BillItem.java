package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("BillItem")
public class BillItem {
    @TableId(type = IdType.AUTO)
    private Integer billItemId;
    private Integer billId;
    private String itemType;
    private String description;
    private Integer quantity;
    private java.math.BigDecimal unitPrice;
    private java.math.BigDecimal amount;
    private java.math.BigDecimal discountRate;
    private java.math.BigDecimal finalAmount;
    private Integer referenceId;
    private java.time.LocalDateTime createdAt;
}

package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Bill")
public class Bill {
    @TableId(type = IdType.AUTO)
    private Integer billId;
    private Integer patientId;
    private Integer inpatientId;
    private String billType;
    private java.time.LocalDate billDate;
    private java.math.BigDecimal totalAmount;
    private java.math.BigDecimal paidAmount;
    private java.math.BigDecimal balance;
    private String status;
    private String paymentMethod;
    private java.time.LocalDateTime paymentDate;
    private java.time.LocalDateTime createdAt;
}

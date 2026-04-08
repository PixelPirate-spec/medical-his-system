package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("PrescriptionItem")
public class PrescriptionItem {
    @TableId(type = IdType.AUTO)
    private Integer prescriptionItemId;
    private Integer prescriptionId;
    private Integer medicineId;
    private java.math.BigDecimal quantity;
    private String unit;
    private String dosePerTime;
    private String frequency;
    private String route;
    private Integer durationDays;
    private java.math.BigDecimal unitPrice;
    private java.math.BigDecimal totalPrice;
    private String notes;
    private java.time.LocalDateTime createdAt;
}

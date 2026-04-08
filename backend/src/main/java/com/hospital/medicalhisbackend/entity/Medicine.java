package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Medicine")
public class Medicine {
    @TableId(type = IdType.AUTO)
    private Integer medicineId;
    private String medicineName;
    private String genericName;
    private String specification;
    private String unit;
    private String manufacturer;
    private String batchNo;
    private String approvalNo;
    private String category;
    private String form;
    private String contraindications;
    private String sideEffects;
    private String interactions;
    private java.math.BigDecimal price;
    private String status;
    private java.time.LocalDateTime createdAt;
}

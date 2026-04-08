package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Equipment")
public class Equipment {
    @TableId(type = IdType.AUTO)
    private Integer equipmentId;
    private Integer deptId;
    private String equipmentName;
    private String model;
    private String serialNumber;
    private String manufacturer;
    private java.time.LocalDate purchaseDate;
    private java.time.LocalDate warrantyEndDate;
    private String status;
    private String location;
    private java.math.BigDecimal netValue;
    private java.time.LocalDate lastMaintenanceDate;
    private Integer maintenanceFrequency;
    private java.time.LocalDateTime createdAt;
}

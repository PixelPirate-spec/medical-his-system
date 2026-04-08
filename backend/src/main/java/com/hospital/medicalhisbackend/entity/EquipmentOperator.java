package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("EquipmentOperator")
public class EquipmentOperator {
    @TableId(type = IdType.AUTO)
    private Integer operatorId;
    private Integer equipmentId;
    private Integer doctorId;
    private String certificationLevel;
    private java.time.LocalDate certifiedDate;
    private Boolean isActive;
    private String notes;
}

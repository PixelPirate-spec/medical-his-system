package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Registration")
public class Registration {
    @TableId(type = IdType.AUTO)
    private Integer regId;
    private Integer patientId;
    private Integer doctorId;
    private Integer deptId;
    private java.time.LocalDate regDate;
    private String regTimeSlot;
    private java.math.BigDecimal registrationFee;
    private String priority;
    private String status;
    private String regNumber;
    private java.time.LocalDateTime createdAt;
}

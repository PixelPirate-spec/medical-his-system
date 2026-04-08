package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Doctor")
public class Doctor {
    @TableId(type = IdType.AUTO)
    private Integer doctorId;
    private String name;
    private String licenseNo;
    private String position;
    private String phone;
    private String email;
    private String specialization;
    private String educationBg;
    private java.time.LocalDate hireDate;
    private String status;
    private java.time.LocalDateTime createdAt;
}

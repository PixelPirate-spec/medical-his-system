package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Patient")
public class Patient {
    @TableId(type = IdType.AUTO)
    private Integer patientId;
    private String name;
    private String idType;
    private String idNumber;
    private String gender;
    private java.time.LocalDate dateOfBirth;
    private String phone;
    private String email;
    private String emergencyContact;
    private String emergencyPhone;
    private String address;
    private String bloodType;
    private String insuranceId;
    private String status;
    private java.time.LocalDateTime registrationDate;
}

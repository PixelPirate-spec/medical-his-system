package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MedicalRecord")
public class MedicalRecord {
    @TableId(type = IdType.AUTO)
    private Integer recordId;
    private Integer patientId;
    private Integer consultationId;
    private Integer doctorId;
    private String recordType;
    private String recordContent;
    private String assessment;
    private String plan;
    private java.time.LocalDateTime recordDate;
    private java.time.LocalDateTime createdAt;
    private Boolean isFinal;
    private Integer version;
    private Integer inpatientId;
}

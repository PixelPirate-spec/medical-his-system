package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Examination")
public class Examination {
    @TableId(type = IdType.AUTO)
    private Integer examId;
    private Integer patientId;
    private Integer doctorId;
    private Integer consultationId;
    private Integer orderId;
    private String examType;
    private String examName;
    private java.time.LocalDateTime examDate;
    private String examDepartment;
    private Integer technicianId;
    private String status;
    private java.time.LocalDateTime resultDate;
    private String examResult;
    private byte[] reportDoc;
    private String normalRange;
    private String clinicalSignificance;
    private java.time.LocalDateTime createdAt;
}

package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Prescription")
public class Prescription {
    @TableId(type = IdType.AUTO)
    private Integer prescriptionId;
    private Integer patientId;
    private Integer doctorId;
    private Integer consultationId;
    private Integer orderId;
    private java.time.LocalDateTime prescriptionDate;
    private String status;
    private java.time.LocalDateTime expiryDate;
    private String usageInstruction;
    private java.time.LocalDateTime createdAt;
}

package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Surgery")
public class Surgery {
    @TableId(type = IdType.AUTO)
    private Integer surgeryId;
    private Integer patientId;
    private Integer inpatientId;
    private Integer surgeonId;
    private Integer anesthetistId;
    private java.time.LocalDateTime scheduledDate;
    private java.time.LocalDateTime actualStartTime;
    private java.time.LocalDateTime actualEndTime;
    private Integer durationMinutes;
    private String surgeryType;
    private String surgeryCode;
    private String diagnosisBefore;
    private String procedureDescription;
    private String findings;
    private String complications;
    private Integer bloodLoss;
    private String status;
    private String anesthesiaMethod;
    private String postDiagnosis;
    private java.time.LocalDateTime createdAt;
    private Integer orderId;
}

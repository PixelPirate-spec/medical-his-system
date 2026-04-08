package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("MedicalOrder")
public class MedicalOrder {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private Integer consultationId;
    private Integer patientId;
    private Integer doctorId;
    private String orderType;
    private String orderContent;
    private String status;
    private String priority;
    private java.time.LocalDateTime createdDate;
    private java.time.LocalDateTime executeDate;
    private java.time.LocalDateTime completedDate;
    private Boolean isLongTerm;
    private Integer durationDays;
    private String notes;
    private Integer inpatientId;
}

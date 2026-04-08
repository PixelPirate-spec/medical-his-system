package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("InPatient")
public class InPatient {
    @TableId(type = IdType.AUTO)
    private Integer inpatientId;
    private Integer patientId;
    private Integer wardId;
    private Integer deptId;
    private java.time.LocalDateTime admissionDate;
    private java.time.LocalDateTime dischargeDate;
    private String chiefComplaint;
    private String admissionDiagnosis;
    private String dischargeSummary;
    private Integer hospitalDays;
    private String status;
    private java.time.LocalDateTime createdAt;
    private Integer consultationId;
    private Integer attendingDoctorId;
    private String treatmentOutcome;
}

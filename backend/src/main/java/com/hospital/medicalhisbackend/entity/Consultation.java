package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Consultation")
public class Consultation {
    @TableId(type = IdType.AUTO)
    private Integer consultationId;
    private Integer patientId;
    private Integer doctorId;
    private Integer regId;
    private java.time.LocalDateTime consultationDate;
    private String chiefComplaint;
    private String vitalSigns;
    private Integer duration;
    private String impression;
    private String diagnosis;
    private String status;
    private java.time.LocalDateTime createdAt;
}

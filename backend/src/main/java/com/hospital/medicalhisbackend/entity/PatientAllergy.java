package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("PatientAllergy")
public class PatientAllergy {
    @TableId(type = IdType.AUTO)
    private Integer allergyId;
    private Integer patientId;
    private Integer medicineId;
    private String allergenName;
    private String allergyType;
    private String severity;
    private String reactionDescription;
    private java.time.LocalDateTime recordDate;
}

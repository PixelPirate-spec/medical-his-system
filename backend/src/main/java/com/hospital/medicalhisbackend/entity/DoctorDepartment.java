package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("DoctorDepartment")
public class DoctorDepartment {
    @TableId(type = IdType.AUTO)
    private Integer doctorDeptId;
    private Integer doctorId;
    private Integer deptId;
    private Boolean isPrimary;
    private java.time.LocalDate joinedDate;
    private String status;
}

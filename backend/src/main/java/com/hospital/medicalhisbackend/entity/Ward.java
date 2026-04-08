package com.hospital.medicalhisbackend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Ward")
public class Ward {
    @TableId(type = IdType.AUTO)
    private Integer wardId;
    private Integer deptId;
    private String wardName;
    private String wardType;
    private Integer capacity;
    private Integer currentOccupancy;
    private String status;
    private java.time.LocalDateTime createdAt;
}

package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.DoctorDepartmentMapper;
import com.hospital.medicalhisbackend.entity.DoctorDepartment;
import org.springframework.stereotype.Service;

@Service
public class DoctorDepartmentServiceImpl extends ServiceImpl<DoctorDepartmentMapper, DoctorDepartment> implements DoctorDepartmentService {
}

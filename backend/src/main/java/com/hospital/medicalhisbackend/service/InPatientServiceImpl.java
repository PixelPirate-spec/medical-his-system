package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.InPatientMapper;
import com.hospital.medicalhisbackend.entity.InPatient;
import org.springframework.stereotype.Service;

@Service
public class InPatientServiceImpl extends ServiceImpl<InPatientMapper, InPatient> implements InPatientService {
}

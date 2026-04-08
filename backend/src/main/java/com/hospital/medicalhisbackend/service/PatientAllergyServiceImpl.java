package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.PatientAllergyMapper;
import com.hospital.medicalhisbackend.entity.PatientAllergy;
import org.springframework.stereotype.Service;

@Service
public class PatientAllergyServiceImpl extends ServiceImpl<PatientAllergyMapper, PatientAllergy> implements PatientAllergyService {
}

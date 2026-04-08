package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.ConsultationMapper;
import com.hospital.medicalhisbackend.entity.Consultation;
import org.springframework.stereotype.Service;

@Service
public class ConsultationServiceImpl extends ServiceImpl<ConsultationMapper, Consultation> implements ConsultationService {
}

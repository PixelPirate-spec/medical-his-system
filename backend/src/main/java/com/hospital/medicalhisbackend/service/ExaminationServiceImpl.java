package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.ExaminationMapper;
import com.hospital.medicalhisbackend.entity.Examination;
import org.springframework.stereotype.Service;

@Service
public class ExaminationServiceImpl extends ServiceImpl<ExaminationMapper, Examination> implements ExaminationService {
}

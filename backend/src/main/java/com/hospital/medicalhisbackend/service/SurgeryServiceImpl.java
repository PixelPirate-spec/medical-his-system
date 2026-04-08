package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.SurgeryMapper;
import com.hospital.medicalhisbackend.entity.Surgery;
import org.springframework.stereotype.Service;

@Service
public class SurgeryServiceImpl extends ServiceImpl<SurgeryMapper, Surgery> implements SurgeryService {
}

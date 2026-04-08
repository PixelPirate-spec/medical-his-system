package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.MedicalOrderMapper;
import com.hospital.medicalhisbackend.entity.MedicalOrder;
import org.springframework.stereotype.Service;

@Service
public class MedicalOrderServiceImpl extends ServiceImpl<MedicalOrderMapper, MedicalOrder> implements MedicalOrderService {
}

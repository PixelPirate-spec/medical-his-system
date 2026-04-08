package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.EquipmentOperatorMapper;
import com.hospital.medicalhisbackend.entity.EquipmentOperator;
import org.springframework.stereotype.Service;

@Service
public class EquipmentOperatorServiceImpl extends ServiceImpl<EquipmentOperatorMapper, EquipmentOperator> implements EquipmentOperatorService {
}

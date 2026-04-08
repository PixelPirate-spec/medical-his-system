package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.PrescriptionItemMapper;
import com.hospital.medicalhisbackend.entity.PrescriptionItem;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionItemServiceImpl extends ServiceImpl<PrescriptionItemMapper, PrescriptionItem> implements PrescriptionItemService {
}

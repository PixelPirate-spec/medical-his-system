package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.BillItemMapper;
import com.hospital.medicalhisbackend.entity.BillItem;
import org.springframework.stereotype.Service;

@Service
public class BillItemServiceImpl extends ServiceImpl<BillItemMapper, BillItem> implements BillItemService {
}

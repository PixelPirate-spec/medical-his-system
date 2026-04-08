package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.BillMapper;
import com.hospital.medicalhisbackend.entity.Bill;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {
}

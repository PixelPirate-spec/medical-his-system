package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.mapper.WardMapper;
import com.hospital.medicalhisbackend.entity.Ward;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImpl extends ServiceImpl<WardMapper, Ward> implements WardService {
}

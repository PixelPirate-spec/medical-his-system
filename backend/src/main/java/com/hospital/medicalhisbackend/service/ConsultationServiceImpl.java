package com.hospital.medicalhisbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.medicalhisbackend.common.UserContext;
import com.hospital.medicalhisbackend.mapper.ConsultationMapper;
import com.hospital.medicalhisbackend.entity.Consultation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationServiceImpl extends ServiceImpl<ConsultationMapper, Consultation> implements ConsultationService {

    @Override
    public List<Consultation> list() {
        String role = UserContext.getRole();
        Long userId = UserContext.getUserId();

        if ("ADMIN".equals(role)) {
            // 管理员：返回所有就诊记录
            return baseMapper.selectList(null);
        } else if ("DOCTOR".equals(role)) {
            // 医生：仅返回该医生的记录
            QueryWrapper<Consultation> wrapper = new QueryWrapper<>();
            wrapper.eq("doctor_id", userId);
            return baseMapper.selectList(wrapper);
        } else if ("PATIENT".equals(role)) {
            // 患者：仅返回该患者的记录
            QueryWrapper<Consultation> wrapper = new QueryWrapper<>();
            wrapper.eq("patient_id", userId);
            return baseMapper.selectList(wrapper);
        }

        // 如果没有权限角色或角色不匹配，为了安全默认返回空集合
        return java.util.Collections.emptyList();
    }
}

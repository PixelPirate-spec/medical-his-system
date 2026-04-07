package com.hospital.medicalhisbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hospital.medicalhisbackend.mapper") // 新增这一行
public class MedicalHisBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalHisBackendApplication.class, args);
    }

}

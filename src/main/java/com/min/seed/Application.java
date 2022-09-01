package com.min.seed;

import com.min.seed.core.ProjectConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = ProjectConstant.MAPPER_PACKAGE)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}


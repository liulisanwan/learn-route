package com.chlrob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 接口电刷保护应用
 *
 * @author Zhanghui
 * @date 2024/01/09
 */
@SpringBootApplication(scanBasePackages = {"com.chlrob","com.liuli"})
public class InterfaceBrushProtectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(InterfaceBrushProtectionApplication.class, args);
    }


}
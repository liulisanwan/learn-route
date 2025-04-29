package com.liuli;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

public class Knife4jEnvironmentPostProcessor implements EnvironmentPostProcessor {

    private final YamlPropertySourceLoader yml = new YamlPropertySourceLoader();

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        PropertySource<?> iot = yml.load("knife4j", new ClassPathResource("knife4j.yml")).get(0);

        environment.getPropertySources().addLast(iot);
    }
}
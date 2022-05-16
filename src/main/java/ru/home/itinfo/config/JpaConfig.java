package ru.home.itinfo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("ru.home.itinfo.repository.impl")
public class JpaConfig {
}

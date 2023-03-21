package com.komleva.configuration;

import com.komleva.util.RandomValuesGenerator;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebMvc
@ComponentScan("com.komleva")
public class ApplicationConfig {

    @Bean
    public RandomValuesGenerator getRandomGenerator() {
        return new RandomValuesGenerator();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public DataSource hikariDatasource(DatabaseProperties properties) {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setUsername(properties.getLogin());
        hikariDataSource.setPassword(properties.getPassword());
        hikariDataSource.setDriverClassName(properties.getDriverName());
        hikariDataSource.setMaximumPoolSize(properties.getPoolSize());
        hikariDataSource.setJdbcUrl(properties.getJdbcUrl());

        return hikariDataSource;
    }
}

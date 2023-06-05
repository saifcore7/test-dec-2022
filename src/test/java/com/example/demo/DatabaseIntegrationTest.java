package com.example.demo;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@Testcontainers
@SpringJUnitConfig
@SpringBootTest(classes = DemoApplication.class)
public class DatabaseIntegrationTest {
    @Autowired
    private DataSource dataSource;
@Container
private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
        .withDatabaseName("mydatabase")
        .withUsername("dbuser")
        .withPassword("dbpass");
@BeforeAll
public static void setUp(){
    postgresContainer.start();
    System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
    System.setProperty("spring.datasource.username", postgresContainer.getUsername());
    System.setProperty("spring.datasource.password", postgresContainer.getPassword());
}
    @Test
    public void testDatabaseConnection() {
    // Perform database operations and assertions here

    }
}

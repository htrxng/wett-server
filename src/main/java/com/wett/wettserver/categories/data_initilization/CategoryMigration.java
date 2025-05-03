package com.wett.wettserver.categories.data_initilization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CategoryMigration {

    @Bean
    CommandLineRunner initCategoryData(CategoryMigrationDataService categoryMigrationDataService) {
        return (evt) -> {
            categoryMigrationDataService.initData();
        };
    }
}

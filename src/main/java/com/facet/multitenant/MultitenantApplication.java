package com.facet.multitenant;

import com.facet.multitenant.config.DataSourceConfig;
import com.facet.multitenant.repo.DataSourceConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.facet.multitenant.*")
public class MultitenantApplication {
    @Autowired
    private DataSourceConfigRepository dataSourceConfigRepository;


    public static void main(String[] args) {
        SpringApplication.run(MultitenantApplication.class, args);
    }

    @PostConstruct
    public void init() {
        if(dataSourceConfigRepository.count()==0) {
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/tenant2?useSSL=false&createDatabaseIfNotExist=true");
            dataSourceConfig.setName("tenant2");
            dataSourceConfig.setUsername("root");
            dataSourceConfig.setPassword("Shishir@123");
            dataSourceConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceConfig.setInitialize(true);

            DataSourceConfig dataSourceConfig1 = new DataSourceConfig();
            dataSourceConfig1.setUrl("jdbc:mysql://localhost:3306/tenant1?useSSL=false&createDatabaseIfNotExist=true");
            dataSourceConfig1.setName("tenant1");
            dataSourceConfig1.setUsername("root");
            dataSourceConfig1.setPassword("Shishir@123");
            dataSourceConfig1.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceConfig1.setInitialize(true);

            List<DataSourceConfig> dataSourceConfigs = new ArrayList<>();
            dataSourceConfigs.add(dataSourceConfig);
            dataSourceConfigs.add(dataSourceConfig1);

            dataSourceConfigRepository.saveAll(dataSourceConfigs);
        }
    }
}

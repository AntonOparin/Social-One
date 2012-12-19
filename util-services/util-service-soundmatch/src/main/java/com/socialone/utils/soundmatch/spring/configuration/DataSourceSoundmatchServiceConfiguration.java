package com.socialone.utils.soundmatch.spring.configuration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.cloudfoundry.runtime.env.CloudEnvironment;
import org.cloudfoundry.runtime.service.AbstractServiceCreator.ServiceNameTuple;
import org.cloudfoundry.runtime.service.relational.CloudDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.socialone.utils.soundmatch.SoundMatchDataRepository;
import com.socialone.utils.soundmatch.jdbc.JdbcSoundMatchDataRepository;

@Configuration
@Import({ DataSourceSoundmatchServiceConfiguration.CloudFoundryConfigurations.class, DataSourceSoundmatchServiceConfiguration.DefaultConfigurations.class,
        DataSourceSoundmatchServiceConfiguration.TestConfigurations.class })
public class DataSourceSoundmatchServiceConfiguration {

    @Inject
    private DataSource dataSource;

    @Bean
    @Singleton
    public SoundMatchDataRepository soundMatchDataRepository() {
        return new JdbcSoundMatchDataRepository(dataSource);
    }

    @Configuration
    @Profile(value = "soundmatchCloud")
    static class CloudFoundryConfigurations {
        @Inject
        private CloudEnvironment cloudEnvironment;

        @Bean
        @Singleton
        public DataSource dataSource() {
            CloudDataSourceFactory cloudDataSourceFactory = new CloudDataSourceFactory(cloudEnvironment);
            try {
                Collection<ServiceNameTuple<DataSource>> dataSources = cloudDataSourceFactory.createInstances();
                dataSources = Collections2.filter(dataSources, new Predicate<ServiceNameTuple<DataSource>>() {
                    public boolean apply(ServiceNameTuple<DataSource> input) {
                        return input.name.equalsIgnoreCase("sna-mysql");
                    }
                });
                assert dataSources.size() == 1 : "Returned illegal DataSource";
                return dataSources.iterator().next().service;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Configuration
    @Profile(value = { "soundmatchDefault" })
    static class DefaultConfigurations {

        @Bean
        @Singleton
        public DataSource dataSource() {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setEncoding("UTF-8");
            dataSource.setServerName("localhost");
            dataSource.setUser("mavarazy");
            dataSource.setPassword("mavarazy");
            dataSource.setDatabaseName("mavarazy");
            dataSource.setPort(3306);
            return dataSource;
        }

    }

    @Configuration
    @Profile(value = { "soundmatchTest" })
    static class TestConfigurations {

        @Bean
        @Singleton
        public DataSource providerDataSource() throws IOException, SQLException, PropertyVetoException {
            DataSource dataSource = comboPooledDataSource();
            databasePopulator().populate(dataSource.getConnection());
            return dataSource;
        }

        private ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/socialone");
            comboPooledDataSource.setUser("socialone");
            comboPooledDataSource.setPassword("socialone");
            comboPooledDataSource.setInitialPoolSize(10);
            comboPooledDataSource.setMaxPoolSize(50);
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            return comboPooledDataSource;
        }

        // Populates DB with appropriate MySQL Schema
        private DatabasePopulator databasePopulator() {
            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(new ClassPathResource("/com/socialone/data/Soundmatch.sql"));
            return populator;
        }
    }

}

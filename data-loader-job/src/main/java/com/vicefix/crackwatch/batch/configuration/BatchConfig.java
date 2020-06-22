package com.vicefix.crackwatch.batch.configuration;

import io.micrometer.core.lang.NonNull;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchConfig extends DefaultBatchConfigurer {

    @Override
    public void setDataSource(@NonNull DataSource dataSource) {
        // override to do not set datasource even if a datasource exist.
        // initialize will use a Map based JobRepository (instead of database)
    }

}

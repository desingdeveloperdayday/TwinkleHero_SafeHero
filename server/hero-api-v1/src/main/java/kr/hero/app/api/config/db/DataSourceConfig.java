package kr.hero.app.api.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
	
	@Bean("heroDataSource")
	@Primary
	@ConfigurationProperties(prefix = "datasource.db-hero-app")
	public DataSource applicationDataSource() {
		return DataSourceBuilder.create().build();
	}

}
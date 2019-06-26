package kr.hero.app.api.config.db;

import kr.hero.app.api.config.db.annotation.HeroDB;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "kr.hero.app.api", annotationClass = HeroDB.class, sqlSessionFactoryRef = "heroSqlSessionFactory")
public class MybatisApplicationConfig {
	@Bean("heroSqlSessionFactory")
	public SqlSessionFactory heroSqlSessionFactory(@Qualifier("heroDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		PathMatchingResourcePatternResolver pathResolver = new PathMatchingResourcePatternResolver();
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setTypeAliasesPackage("kr.hero.app.api.model");
		sessionFactoryBean.setConfigLocation(pathResolver.getResource("classpath:mybatis/MybatisConfiguration.xml"));
		sessionFactoryBean.setMapperLocations(pathResolver.getResources("classpath:**/*Mapper.xml"));
		sessionFactoryBean.setVfs(SpringBootVFS.class);
		return sessionFactoryBean.getObject();
	}

}

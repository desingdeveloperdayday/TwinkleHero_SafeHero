package kr.hero.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppApplication extends SpringBootServletInitializer {

	//SpringBootServletInitializer 상속 안하면 war배포시 url 인식 못함 (아래 override도 해줘야함 )
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AppApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}

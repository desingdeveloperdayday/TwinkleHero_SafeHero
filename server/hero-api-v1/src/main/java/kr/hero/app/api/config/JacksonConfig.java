package kr.hero.app.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class JacksonConfig {

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
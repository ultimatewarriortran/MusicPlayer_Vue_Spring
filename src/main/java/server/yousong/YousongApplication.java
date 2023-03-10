package server.yousong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class YousongApplication {

	public static void main(String[] args) {
		SpringApplication.run(YousongApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/songs-javaconfig").allowedOrigins("http://localhost:8081").allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true);;
				registry.addMapping("/uploads-javaconfig").allowedOrigins("*").allowedMethods("*")
						.allowedHeaders("*")
						.allowCredentials(true);
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("*")
						.allowedHeaders("*");
						//.allowCredentials(true);

			}
		};
	}
}

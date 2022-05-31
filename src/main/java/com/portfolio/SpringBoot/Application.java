package com.portfolio.SpringBoot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Application {
    
//    @Bean
//    public ModelMapper modelMapper(){
//    return new modelMapper();
//    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
       
//        @Bean
//        
//       public WebMvcConfigurer corsConfigurer(){
//       
//           return new WebMvcConfigurer(){
//           
//               @Override
//               
//              public void addCorsMappings(CorsRegistry registry){
//                  
//              registry.addMapping("/**")
//                      .allowedHeaders("*")
//                      .allowedMethods("*")
//                      .allowCredentials(false)
//                      .allowedOrigins("https://frontend-portfolio-zulyma-j.web.app");
//              }
//           };
//       }
            
            
        @Component
    public class CrossOriginConfiguration {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();

    corsConfiguration.applyPermitDefaultValues();
    corsConfiguration.addAllowedMethod(HttpMethod.PUT);
    corsConfiguration.addAllowedMethod(HttpMethod.DELETE);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);

    return source;
    }
        }
        }
        
        
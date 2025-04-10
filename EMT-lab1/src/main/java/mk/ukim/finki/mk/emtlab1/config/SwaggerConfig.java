package mk.ukim.finki.mk.emtlab1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI airbnbCloneOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Air bnb api clone")
                        .description("api for airbnb ")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tomcat")
                                .email("teodurac@gmail.com")));
    }
}

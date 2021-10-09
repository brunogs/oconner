package br.com.oconner.config;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.function.Predicate

@Configuration
@EnableSwagger2
class SpringFoxConfig : WebMvcConfigurer {

    @Bean
    fun api() = Docket(DocumentationType.SWAGGER_2)
          .apiInfo(
              ApiInfo(
                  "O'Conner API",
                  "O'Conner API Documentation",
                  "1.0",
                  "urn:tos",
                  Contact("Bruno Silva", "https://github.com/brunogs/oconner", "brunogs.sp@gmail.com"),
                  "Apache 2.0",
                  "http://www.apache.org/licenses/LICENSE-2.0",
                  listOf()
              ))
          .select()                                  
          .apis(RequestHandlerSelectors.any())
          .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
          .paths(PathSelectors.any())
          .build()

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
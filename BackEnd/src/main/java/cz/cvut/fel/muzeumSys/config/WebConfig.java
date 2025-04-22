package cz.cvut.fel.muzeumSys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Získá absolutní cestu ke složce `files`
        String uploadPath = Paths.get("files").toAbsolutePath().toUri().toString();

        registry.addResourceHandler("/files/**")
                .addResourceLocations(uploadPath);
    }
}

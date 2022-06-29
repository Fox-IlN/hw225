package hw225.SpringConfig;

import hw225.Service.IFileService;
import hw225.Service.FileService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config{

    @Bean
    public IFileService fileService() {
        return new FileService();
    }

}
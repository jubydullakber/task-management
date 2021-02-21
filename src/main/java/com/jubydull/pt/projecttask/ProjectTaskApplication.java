package com.jubydull.pt.projecttask;

import com.jubydull.pt.projecttask.entity.User;
import com.jubydull.pt.projecttask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ProjectTaskApplication {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(ProjectTaskApplication.class, args);
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        long count = userRepository.count();
        if (count == 0) {
            User admin = User.builder().id(1).userName("admin").password(passwordEncoder.encode("admin")).role("ADMIN").build();
            User user = User.builder().id(0).userName("user").password(passwordEncoder.encode("user")).role("USER").build();
            userRepository.save(user);
            userRepository.save(admin);
        }


    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.jubydull.pt.projecttask.web")).build();
    }

}

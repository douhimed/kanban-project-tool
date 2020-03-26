package org.adex;

import org.adex.repositories.ProjectRepository;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KanbanProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(KanbanProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(ProjectRepository projectRepository) {
        return args -> {
            projectRepository.deleteAll();
            Project p = Project.builder().name("test").projectIdentifier("TEST").description("project description").build();
            Backlog b = Backlog.builder().project(p).projectIdentifier(p.getProjectIdentifier()).build();
            p.setBacklog(b);
            projectRepository.save(p);

            p = Project.builder().name("test2 ").projectIdentifier("TEST2").description("project description").build();
            b = Backlog.builder().project(p).projectIdentifier(p.getProjectIdentifier()).build();
            p.setBacklog(b);
            projectRepository.save(p);
        };
    }

    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

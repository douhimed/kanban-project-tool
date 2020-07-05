package org.adex;

import static org.adex.security.SecurityConstants.JWT_PREFIX;

import org.adex.repositories.ProjectRepository;
import org.adex.security.JwtTokenProvider;
import org.adex.services.IUserServices;
import org.adex.web.models.Backlog;
import org.adex.web.models.Project;
import org.adex.web.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KanbanProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(ProjectRepository projectRepository, IUserServices userServices,
			JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
		return args -> {

			String pass = "pass00";
			String username = "user1";
			User registeredUser = registerUser(userServices, username, pass, "med user1");

			projectRepository.deleteAll();

			this.saveProject(projectRepository, registeredUser, "project 101", "TES11", registeredUser.getUsername(),
					"project description 101");

			this.saveProject(projectRepository, registeredUser, "project 102", "TES10", registeredUser.getUsername(),
					"project description 102");

			registeredUser = registerUser(userServices, "user2", pass, "med user2");

			this.saveProject(projectRepository, registeredUser, "project 201", "TES20", registeredUser.getUsername(),
					"project description 201");

		this.authenticate(jwtTokenProvider, authenticationManager, username, pass);
		};
	}

	private void authenticate(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager,
			String username, String pass) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, pass));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.err.println((JWT_PREFIX + jwtTokenProvider.generateToken(authentication)).trim());
	}

	private void saveProject(ProjectRepository projectRepository, User registeredUser, String name, String identifier,
			String username, String desc) {
		Project p = Project.builder().name(name).projectIdentifier(identifier).user(registeredUser)
				.projectLeader(username).description(desc).build();
		Backlog b = Backlog.builder().project(p).projectIdentifier(p.getProjectIdentifier()).build();
		p.setBacklog(b);
		projectRepository.save(p);
	}

	private User registerUser(IUserServices userServices, String username, String pass, String fullName) {
		return userServices.saveUser(
				User.builder().username(username).password(pass).confirmPassword(pass).fullName(fullName).build());
	}

	@Bean
	public BCryptPasswordEncoder cryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

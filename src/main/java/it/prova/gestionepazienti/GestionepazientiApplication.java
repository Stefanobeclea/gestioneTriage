package it.prova.gestionepazienti;

import java.util.Arrays; 
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.prova.gestionepazienti.model.Authority;
import it.prova.gestionepazienti.model.AuthorityName;
import it.prova.gestionepazienti.model.StatoUtente;
import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.security.repository.AuthorityRepository;
import it.prova.gestionepazienti.security.repository.UserRepository;

@SpringBootApplication
public class GestionepazientiApplication{

	@Autowired
	private UserRepository repository;
	@Autowired
	AuthorityRepository authorityRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(GestionepazientiApplication.class, args);
	}

	@Bean
	public CommandLineRunner initTriage() {
		return (args) -> {
		User user = repository.findByUsername("admin").orElse(null);
		if (user == null) {
	
			/**
			 * Inizializzo i dati del mio test
			 */
	
			Authority authorityAdmin = new Authority();
			authorityAdmin.setName(AuthorityName.ROLE_ADMIN);
			authorityAdmin = authorityRepository.save(authorityAdmin);
	
			Authority authorityUser = new Authority();
			authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
			authorityUser = authorityRepository.save(authorityUser);
	
			List<Authority> authorities = Arrays.asList(new Authority[] { authorityAdmin, authorityUser });
	
			user = new User();
			user.setNome("Mario");
			user.setCognome("Rossi");
			user.setDateCreated(new Date());
			user.setAuthorities(authorities);
			user.setEnabled(true);
			user.setStato(StatoUtente.ATTIVO);
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("admin"));
			
	
			user = repository.save(user);
	
		}
	
		User commonUser = repository.findByUsername("commonUser").orElse(null);
		if (commonUser == null) {
	
			/**
			 * Inizializzo i dati del mio test
			 */
	
			Authority authorityUser = authorityRepository.findByName(AuthorityName.ROLE_SUB_OPERATOR);
			if (authorityUser == null) {
				authorityUser = new Authority();
				authorityUser.setName(AuthorityName.ROLE_SUB_OPERATOR);
				authorityUser = authorityRepository.save(authorityUser);
			}
	
			List<Authority> authorities = Arrays.asList(new Authority[] { authorityUser });
	
			commonUser = new User();
			commonUser.setNome("Francesco");
			commonUser.setCognome("Totti");
			commonUser.setStato(StatoUtente.ATTIVO);
			commonUser.setDateCreated(new Date());
			commonUser.setEnabled(true);
			commonUser.setAuthorities(authorities);
			commonUser.setUsername("commonUser");
			commonUser.setPassword(passwordEncoder.encode("commonUser"));
	
			commonUser = repository.save(commonUser);
	
		}
	
		};
	}
}

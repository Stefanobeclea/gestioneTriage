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
import it.prova.gestionepazienti.model.Dottore;
import it.prova.gestionepazienti.model.Paziente;
import it.prova.gestionepazienti.model.StatoPaziente;
import it.prova.gestionepazienti.model.StatoUtente;
import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.security.repository.AuthorityRepository;
import it.prova.gestionepazienti.security.repository.UserRepository;
import it.prova.gestionepazienti.service.DottoreService;
import it.prova.gestionepazienti.service.PazienteService;

@SpringBootApplication
public class GestionepazientiApplication{

	@Autowired
	PazienteService pazienteService;
	@Autowired
	DottoreService dottoreService;
	
	@Autowired
	UserRepository repository;
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
			
			Paziente paziente = pazienteService.save(new Paziente("Francesco", "Totti", "FRNTOTT01",new Date(),
					 StatoPaziente.IN_VISITA));
			pazienteService.save(new Paziente("Pietro", "Lollo", "FRNTOTT01",new Date(), StatoPaziente.IN_ATTESA_VISITA));
			pazienteService.save(new Paziente("Luigino", "Verdi", "FRNTOTT01",new Date(), StatoPaziente.RICOVERATO));
			pazienteService.save(new Paziente("Marco", "Violi", "FRNTOTT01",new Date(), StatoPaziente.DIMESSO));

			pazienteService.save(new Paziente("Mario", "Rossi", "FRNTOTT01",new Date(), StatoPaziente.RICOVERATO));
			pazienteService.save(new Paziente("Mario", "Bianchi", "FRNTOTT01",new Date(), StatoPaziente.DIMESSO));
			pazienteService.save(new Paziente("Francesco", "Scanna", "FRNTOTT01",new Date(), StatoPaziente.IN_ATTESA_VISITA));
			pazienteService.save(new Paziente("Stefano", "Belli", "FRNTOTT01",new Date(), StatoPaziente.IN_ATTESA_VISITA));

			pazienteService.save(new Paziente("Frank", "Lergio", "FRNTOTT01",new Date(), StatoPaziente.DIMESSO));
			pazienteService.save(new Paziente("Rugero", "Carlo", "FRNTOTT01",new Date(), StatoPaziente.RICOVERATO));
			pazienteService.save(new Paziente("Carlos", "Pedana", "FRNTOTT01",new Date(), StatoPaziente.IN_ATTESA_VISITA));
			pazienteService.save(new Paziente("Meme", "Solare", "FRNTOTT01",new Date(), StatoPaziente.DIMESSO));
			
			dottoreService.save(new Dottore("Francesco", "Totti", "01FRATO"));
			dottoreService.save(new Dottore("Pietro", "Lollo", "01FRATO"));
			dottoreService.save(new Dottore("Luigino", "Verdi", "01FRATO"));
			dottoreService.save(new Dottore("Marco", "Violi", "01FRATO", pazienteService.get(1L)));	
			
			paziente.setDottore(dottoreService.get(4L));
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

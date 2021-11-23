package it.prova.gestionepazienti.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.prova.gestionepazienti.model.Authority;
import it.prova.gestionepazienti.model.AuthorityName;


public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findByName(AuthorityName name);

}
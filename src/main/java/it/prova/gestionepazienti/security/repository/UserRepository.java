package it.prova.gestionepazienti.security.repository;

import java.util.Optional; 

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.prova.gestionepazienti.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
	Optional<User> findByUsername(String username);

}
package it.prova.gestionepazienti.repository.user;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepazienti.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}

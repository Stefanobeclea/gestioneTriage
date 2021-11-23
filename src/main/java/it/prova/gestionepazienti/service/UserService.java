package it.prova.gestionepazienti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepazienti.model.User;

public interface UserService {
	
	List<User> listAll();

	Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy);

	User get(Long idInput);

	User save(User input);
	
	User update(User input);

	void delete(User input);
}

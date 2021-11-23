package it.prova.gestionepazienti.service;

import java.util.List;

import org.springframework.data.domain.Page;

import it.prova.gestionepazienti.model.Dottore;

public interface DottoreService {

	List<Dottore> listAll();

	Page<Dottore> searchAndPaginate(Dottore dottoreExample, Integer pageNo, Integer pageSize, String sortBy);

	Dottore get(Long idInput);

	Dottore save(Dottore input);
	
	Dottore update(Dottore input);

	void delete(Dottore input);
}

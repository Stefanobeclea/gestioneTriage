package it.prova.gestionepazienti.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import it.prova.gestionepazienti.exceptions.IdNotNullForInsertException;
import it.prova.gestionepazienti.exceptions.PazienteNotFoundException;
import it.prova.gestionepazienti.model.Paziente;
import it.prova.gestionepazienti.repository.paziente.PazienteRepository;

@Service
public class PazienteServiceImpl implements PazienteService{

	@Autowired
	PazienteRepository repository;
	
	@Override
	public List<Paziente> listAll() {
		return (List<Paziente>) repository.findAll();
	}

	@Override
	public Page<Paziente> searchAndPaginate(Paziente pazienteExample, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Paziente> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(pazienteExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + pazienteExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(pazienteExample.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + pazienteExample.getCognome().toUpperCase() + "%"));
			
			if (!StringUtils.isEmpty(pazienteExample.getCodiceFiscale()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + pazienteExample.getCodiceFiscale().toUpperCase() + "%"));
			
			if (pazienteExample.getDateCreated() != null)
				predicates.add(cb.greaterThan(root.get("dateCreated"),pazienteExample.getDateCreated()));
			
			if (pazienteExample.getStato() != null)
				predicates.add(cb.equal(root.get("stato"),pazienteExample.getStato()));
			
			if (pazienteExample.getDottore() != null && pazienteExample.getDottore().getId() != null)
				predicates.add(cb.equal(root.get("dottore"),pazienteExample.getDottore()));
			


			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return  repository.findAll(specificationCriteria, paging);
	}

	@Override
	public Paziente get(Long idInput) {
		return repository.findById(idInput)
				.orElseThrow(() -> new PazienteNotFoundException("Element with id " + idInput + " not found."));
	}

	@Override
	public Paziente save(Paziente input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		return repository.save(input);
	}

	@Override
	public Paziente update(Paziente input) {
		return repository.save(input);
	}

	@Override
	public void delete(Paziente input) {
		repository.delete(input);
		
	}

}

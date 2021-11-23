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

import it.prova.gestionepazienti.exceptions.DottoreNotFoundException;
import it.prova.gestionepazienti.exceptions.IdNotNullForInsertException;
import it.prova.gestionepazienti.model.Dottore;
import it.prova.gestionepazienti.repository.dottore.DottoreRepository;
@Service
public class DottoreServiceImpl implements DottoreService{

	@Autowired
	DottoreRepository repository;
	
	@Override
	public List<Dottore> listAll() {
		return (List<Dottore>)repository.findAll();
	}

	@Override
	public Dottore get (Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new DottoreNotFoundException("Element with id " + id + " not found."));
	}

	@Override
	public Dottore update(Dottore input) {
		return repository.save(input);
	}

	@Override
	public Dottore save(Dottore input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		return repository.save(input);
	}

	@Override
	public void delete(Dottore input) {
		repository.delete(input);
		
	}

	@Override
	public Page<Dottore> searchAndPaginate(Dottore dottoreExample, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<Dottore> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(dottoreExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + dottoreExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(dottoreExample.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + dottoreExample.getCognome().toUpperCase() + "%"));
			
			if (!StringUtils.isEmpty(dottoreExample.getCodiceDipendente()))
				predicates.add(cb.like(cb.upper(root.get("codiceFiscale")), "%" + dottoreExample.getCodiceDipendente().toUpperCase() + "%"));
			
			if (dottoreExample.getPzienteAttualmenteInVisita() != null && dottoreExample.getPzienteAttualmenteInVisita().getId() != null)
				predicates.add(cb.equal(root.get("dottore"),dottoreExample.getPzienteAttualmenteInVisita()));
			


			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return  repository.findAll(specificationCriteria, paging);
	}

	
}

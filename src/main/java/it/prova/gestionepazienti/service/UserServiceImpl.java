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
import it.prova.gestionepazienti.exceptions.UserNotFoundException;
import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public List<User> listAll() {
		return (List<User>) repository.findAll();
	}

	@Override
	public Page<User> searchAndPaginate(User userExample, Integer pageNo, Integer pageSize, String sortBy) {
		Specification<User> specificationCriteria = (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (!StringUtils.isEmpty(userExample.getNome()))
				predicates.add(cb.like(cb.upper(root.get("nome")), "%" + userExample.getNome().toUpperCase() + "%"));

			if (!StringUtils.isEmpty(userExample.getCognome()))
				predicates.add(cb.like(cb.upper(root.get("cognome")), "%" + userExample.getCognome().toUpperCase() + "%"));
			
			if (!StringUtils.isEmpty(userExample.getUsername()))
				predicates.add(cb.like(cb.upper(root.get("username")), "%" + userExample.getUsername().toUpperCase() + "%"));
			
			if (userExample.getDateCreated() != null)
				predicates.add(cb.greaterThan(root.get("dateCreated"),userExample.getDateCreated()));
			
			if (userExample.getStato() != null)
				predicates.add(cb.equal(root.get("stato"),userExample.getStato()));
			


			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};

		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

		return repository.findAll(specificationCriteria, paging);
	}

	@Override
	public User get(Long idInput) {
		return repository.findById(idInput)
				.orElseThrow(() -> new UserNotFoundException("Element with id " + idInput + " not found."));
		}

	@Override
	public User save(User input) {
		if(input.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		return repository.save(input);
	}

	@Override
	public User update(User input) {
		return repository.save(input);
	}

	@Override
	public void delete(User input) {
		repository.delete(input);
		
	}

}

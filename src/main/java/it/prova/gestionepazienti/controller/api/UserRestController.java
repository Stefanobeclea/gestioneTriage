package it.prova.gestionepazienti.controller.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepazienti.dto.UserDTO;
import it.prova.gestionepazienti.dto.UserDTO;
import it.prova.gestionepazienti.exceptions.IdNotNullForInsertException;
import it.prova.gestionepazienti.exceptions.UserNotFoundException;
import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.model.StatoUser;
import it.prova.gestionepazienti.model.StatoUtente;
import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public List<UserDTO> getAll() {
		return UserDTO.createUserDTOListFromModelList(userService.listAll());
	}
	
	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable(value = "id", required = true) long id) {
		User user = userService.get(id);

		if (user == null)
			throw new UserNotFoundException("User not found con id: " + id);

		return UserDTO.buildUserDTOFromModel(user);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UserDTO createNew(@Valid @RequestBody UserDTO userInput) {
		if(userInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		userInput.setDateCreated(new Date());
		if (userInput.getStato() == null)
			userInput.setStato(StatoUtente.CREATO);
		User userInserito = userService.save(userInput.buildUserModel());
		return UserDTO.buildUserDTOFromModel(userInserito);
	}
}

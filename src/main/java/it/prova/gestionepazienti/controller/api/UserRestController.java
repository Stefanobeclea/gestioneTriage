package it.prova.gestionepazienti.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepazienti.dto.UserDTO;
import it.prova.gestionepazienti.exceptions.UserNotFoundException;
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
}

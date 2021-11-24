package it.prova.gestionepazienti.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepazienti.dto.PazienteDTO;
import it.prova.gestionepazienti.model.Paziente;
import it.prova.gestionepazienti.service.PazienteService;

@RestController
@RequestMapping("/api/paziente")
public class PazienteRestController {

	@Autowired
	PazienteService pazienteService;
	
	@GetMapping
	public List<PazienteDTO> getAll() {
		return PazienteDTO.createPazienteDTOListFromModelList(pazienteService.listAll());
	}
	
	@GetMapping("/{idInput}")
	public Paziente getPaziente(@PathVariable(required = true) Long idInput) {
		return pazienteService.get(idInput);
	}
}

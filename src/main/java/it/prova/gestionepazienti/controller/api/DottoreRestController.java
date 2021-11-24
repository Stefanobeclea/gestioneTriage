package it.prova.gestionepazienti.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepazienti.dto.DottoreDTO;
import it.prova.gestionepazienti.service.DottoreService;

@RestController
@RequestMapping("/api/dottore")
public class DottoreRestController {

	@Autowired
	DottoreService dottoreService;
	
	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAll());
	}
}

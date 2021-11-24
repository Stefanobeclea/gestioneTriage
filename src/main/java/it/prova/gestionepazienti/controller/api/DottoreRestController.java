package it.prova.gestionepazienti.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import it.prova.gestionepazienti.dto.DottoreDTO;
import it.prova.gestionepazienti.dto.DottoreRequestDTO;
import it.prova.gestionepazienti.dto.DottoreResponseDTO;
import it.prova.gestionepazienti.exceptions.DottoreNotFoundException;
import it.prova.gestionepazienti.model.Dottore;
import it.prova.gestionepazienti.service.DottoreService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/dottore")
public class DottoreRestController {

	@Autowired
	DottoreService dottoreService;
	
	@Autowired
	private WebClient webClient;
	
	@GetMapping
	public List<DottoreDTO> getAll() {
		return DottoreDTO.createDottoreDTOListFromModelList(dottoreService.listAll());
	}
	
	@GetMapping("/{id}")
	public DottoreDTO findById(@PathVariable(value = "id", required = true) long id) {
		Dottore dottore = dottoreService.get(id);

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore not found con id: " + id);

		return DottoreDTO.buildDottoreDTOFromModel(dottore);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DottoreDTO createNew(@RequestBody DottoreDTO dottoreInput) {
		if (dottoreInput.getId() != null)
			throw new RuntimeException("Non è ammesso fornire un id per la creazione");

		ResponseEntity<DottoreResponseDTO> response = webClient.post()
				.uri("")
				.body(Mono.just(new DottoreRequestDTO(dottoreInput.getNome(), dottoreInput.getCognome(),
						dottoreInput.getCodiceDipendente())), DottoreRequestDTO.class)
				.retrieve()
				.toEntity(DottoreResponseDTO.class)
				.block();
		
		if(response.getStatusCode() != HttpStatus.CREATED)
			throw new RuntimeException("Errore nella creazione della nuova voce tramite api esterna!!!");
		
		Dottore dottoreInserito = dottoreService.save(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreInserito);
	}
	
	@PutMapping("/{id}")
	public DottoreDTO update(@Valid @RequestBody DottoreDTO dottoreInput, @PathVariable(required = true) Long id) {
		Dottore dottore = dottoreService.get(id);

		if (dottore == null)
			throw new DottoreNotFoundException("Dottore not found con id: " + id);

		dottoreInput.setId(id);
		if(dottore.getPazienteAttualmenteInVisita() != null) {
			dottoreInput.setPzienteAttualmenteInVisita(dottoreInput.getPzienteAttualmenteInVisita());
		}
		
		ResponseEntity<DottoreResponseDTO> response = webClient.put()
				.uri("")
				.body(Mono.just(new DottoreRequestDTO(dottoreInput.getNome(), dottoreInput.getCognome(),
						dottoreInput.getCodiceDipendente())), DottoreRequestDTO.class)
				.retrieve()
				.toEntity(DottoreResponseDTO.class)
				.block();
		
		if(response.getStatusCode() != HttpStatus.OK)
			throw new RuntimeException("Errore nella creazione della nuova voce tramite api esterna!!!");
		
		Dottore dottoreAggiornato = dottoreService.update(dottoreInput.buildDottoreModel());
		return DottoreDTO.buildDottoreDTOFromModel(dottoreAggiornato);
	}
}

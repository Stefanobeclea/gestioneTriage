package it.prova.gestionepazienti.controller.api;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepazienti.dto.PazienteDTO;
import it.prova.gestionepazienti.exceptions.IdNotNullForInsertException;
import it.prova.gestionepazienti.exceptions.PazienteNotDimessoException;
import it.prova.gestionepazienti.exceptions.PazienteNotFoundException;
import it.prova.gestionepazienti.model.Paziente;
import it.prova.gestionepazienti.model.StatoPaziente;
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
	
	@GetMapping("/{id}")
	public PazienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Paziente paziente = pazienteService.get(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		return PazienteDTO.buildPazienteDTOFromModel(paziente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PazienteDTO createNew(@Valid @RequestBody PazienteDTO pazienteInput) {
		if(pazienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");
		pazienteInput.setDateCreated(new Date());
		pazienteInput.setStato(StatoPaziente.IN_ATTESA_VISITA);
		Paziente pazienteInserito = pazienteService.save(pazienteInput.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteInserito);
	}
	
	@PutMapping("/{id}")
	public PazienteDTO update(@Valid @RequestBody PazienteDTO pazienteInput, @PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.get(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);

		pazienteInput.setId(id);
		pazienteInput.setStato(paziente.getStato());
		pazienteInput.setDateCreated(paziente.getDateCreated());
		if(paziente.getDottore() != null)
			pazienteInput.setDottore(paziente.getDottore());
		Paziente pazienteAggiornato = pazienteService.update(pazienteInput.buildPazienteModel());
		return PazienteDTO.buildPazienteDTOFromModel(pazienteAggiornato);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		Paziente paziente = pazienteService.get(id);

		if (paziente == null)
			throw new PazienteNotFoundException("Paziente not found con id: " + id);
		if (!paziente.getStato().equals(StatoPaziente.DIMESSO))
			throw new PazienteNotDimessoException("Paziente non dimesso");
		pazienteService.delete(paziente);
	}
	
	@PostMapping("/search")
	public ResponseEntity<Page<Paziente>> searchAndPagination(@RequestBody PazienteDTO pazienteExample,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "id") String sortBy) {

		Page<Paziente> results = pazienteService.searchAndPaginate(pazienteExample.buildPazienteModel(), pageNo, pageSize, sortBy);

		return new ResponseEntity<Page<Paziente>>(results, new HttpHeaders(), HttpStatus.OK);
	}
}

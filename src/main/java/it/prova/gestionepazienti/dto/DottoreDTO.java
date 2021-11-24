package it.prova.gestionepazienti.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionepazienti.model.Dottore;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DottoreDTO {

	private Long id;
	
	@NotBlank(message = "{denominazione.notblank}")
	private String nome;
	
	@NotBlank(message = "{denominazione.notblank}")
	private String cognome;
	
	@NotBlank(message = "{denominazione.notblank}")
	private String codiceDipendente;
	
	@JsonIgnoreProperties(value = { "dottore" })
	private PazienteDTO pazienteAttualmenteInVisita;
	
	public DottoreDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodiceDipendente() {
		return codiceDipendente;
	}

	public void setCodiceDipendente(String codiceDipendente) {
		this.codiceDipendente = codiceDipendente;
	}

	public PazienteDTO getPzienteAttualmenteInVisita() {
		return pazienteAttualmenteInVisita;
	}

	public void setPzienteAttualmenteInVisita(PazienteDTO pzienteAttualmenteInVisita) {
		this.pazienteAttualmenteInVisita = pzienteAttualmenteInVisita;
	}

	public DottoreDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceDipendente,
			PazienteDTO pzienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pazienteAttualmenteInVisita = pzienteAttualmenteInVisita;
	}

	public DottoreDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceDipendente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}

	public DottoreDTO(@NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceDipendente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}
	
	public Dottore buildDottoreModel() {
		
		if(this.pazienteAttualmenteInVisita == null)
			return new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente,
					null);
		
		return new Dottore(this.id, this.nome, this.cognome, this.codiceDipendente,
				this.pazienteAttualmenteInVisita.buildPazienteModel());
	}

	public static DottoreDTO buildDottoreDTOFromModel(Dottore input) {
		
		if(input.getPazienteAttualmenteInVisita() == null)
			return new DottoreDTO(input.getId(), input.getNome(), input.getCognome(), input.getCodiceDipendente(),
					null);
		
		return new DottoreDTO(input.getId(), input.getNome(), input.getCognome(), input.getCodiceDipendente(),
				PazienteDTO.buildPazienteDTOFromModel(input.getPazienteAttualmenteInVisita()));
	}
	public static List<DottoreDTO> createDottoreDTOListFromModelList(List<Dottore> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			DottoreDTO result = DottoreDTO.buildDottoreDTOFromModel(dottoreEntity);
			
			return result;
		}).collect(Collectors.toList());
	}
}

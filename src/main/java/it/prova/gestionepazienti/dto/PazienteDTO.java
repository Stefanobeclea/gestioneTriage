package it.prova.gestionepazienti.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestionepazienti.model.Paziente;
import it.prova.gestionepazienti.model.StatoPaziente;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PazienteDTO {

	private Long id;
	
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	
	@NotBlank(message = "{codiceFiscale.notblank}")
	private String codiceFiscale;
	
	private Date dateCreated;
	
	private StatoPaziente stato;
	
	@JsonIgnoreProperties(value = { "pazienteAttualmenteInVisita" })
	private DottoreDTO dottore;
	
	public PazienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public PazienteDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, Date dateCreated, DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.dottore = dottore;
	}
	
	public PazienteDTO(@NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, StatoPaziente stato, Date dateCreated,
			DottoreDTO dottore) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.stato = stato;
		this.dateCreated = dateCreated;
		this.dottore = dottore;
	}

	public PazienteDTO(@NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, StatoPaziente stato,
			Date dateCreated) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.stato = stato;
		this.dateCreated = dateCreated;
	}

	public PazienteDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, Date dateCreated,
			StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public StatoPaziente getStato() {
		return stato;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}

	public PazienteDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, Date dateCreated) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
	}

	public PazienteDTO(@NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, Date dateCreated) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
	}

	public PazienteDTO(Long id, @NotBlank(message = "{denominazione.notblank}") String nome,
			@NotBlank(message = "{denominazione.notblank}") String cognome,
			@NotBlank(message = "{denominazione.notblank}") String codiceFiscale, StatoPaziente stato,
			Date dateCreated) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.stato = stato;
		this.dateCreated = dateCreated;
	}

	public PazienteDTO(Long id, @NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome,
			@NotBlank(message = "{codiceFiscale.notblank}") String codiceFiscale, Date dateCreated, StatoPaziente stato,
			DottoreDTO dottore) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.stato = stato;
		this.dottore = dottore;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public DottoreDTO getDottoreDTO() {
		return dottore;
	}

	public void setDottoreDTO(DottoreDTO dottore) {
		this.dottore = dottore;
	}
	
public Paziente buildPazienteModel() {
		
		if(this.dottore.buildDottoreModel() == null)
			return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dateCreated,
					 null,this.stato);
		
		return new Paziente(this.id, this.nome, this.cognome, this.codiceFiscale, this.dateCreated,
				 this.dottore.buildDottoreModel(),this.stato);
	}

	public static PazienteDTO buildPazienteDTOFromModel(Paziente input) {
		
		if(input.getDottore() == null)
			return new PazienteDTO(input.getId(), input.getNome(), input.getCognome(), input.getCodiceFiscale(),
					input.getDateCreated(), input.getStato(), null);
		
		return new PazienteDTO(input.getId(), input.getNome(), input.getCognome(), input.getCodiceFiscale(),
				input.getDateCreated(), input.getStato(),
				DottoreDTO.buildDottoreDTOFromModel(input.getDottore()));
	}
	public static List<PazienteDTO> createPazienteDTOListFromModelList(List<Paziente> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			PazienteDTO result = PazienteDTO.buildPazienteDTOFromModel(dottoreEntity);
			
			return result;
		}).collect(Collectors.toList());	
	}
	
}

package it.prova.gestionepazienti.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paziente")
public class Paziente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceFiscale")
	private String codiceFiscale;
	
	@Column(name = "dateCreated")
	private Date dateCreated;
	
	@Column(name = "dottore")
	private Dottore dottore;
	
	@Enumerated(EnumType.STRING)
	private StatoPaziente stato;
	
	public Paziente() {
		// TODO Auto-generated constructor stub
	}

	public Paziente(Long id, String nome, String cognome, String codiceFiscale, Date dateCreated, Dottore dottore,
			StatoPaziente stato) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.dottore = dottore;
		this.stato = stato;
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dateCreated, Dottore dottore,
			StatoPaziente stato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.dottore = dottore;
		this.stato = stato;
	}

	public Paziente(String nome, String cognome, String codiceFiscale, Date dateCreated, StatoPaziente stato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceFiscale = codiceFiscale;
		this.dateCreated = dateCreated;
		this.stato = stato;
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

	public Dottore getDottore() {
		return dottore;
	}

	public void setDottore(Dottore dottore) {
		this.dottore = dottore;
	}

	public StatoPaziente getStato() {
		return stato;
	}

	public void setStato(StatoPaziente stato) {
		this.stato = stato;
	}
		
}

package it.prova.gestionepazienti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dottore")
public class Dottore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "codiceDipendente")
	private String codiceDipendente;
	@Column(name = "pzienteAttualmenteInVisita")
	private Paziente pzienteAttualmenteInVisita;
	
	public Dottore() {
		// TODO Auto-generated constructor stub
	}

	public Dottore(Long id, String nome, String cognome, String codiceDipendente, Paziente pzienteAttualmenteInVisita) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pzienteAttualmenteInVisita = pzienteAttualmenteInVisita;
	} 

	public Dottore(Long id, String nome, String cognome, String codiceDipendente) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
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

	public Paziente getPzienteAttualmenteInVisita() {
		return pzienteAttualmenteInVisita;
	}

	public void setPzienteAttualmenteInVisita(Paziente pzienteAttualmenteInVisita) {
		this.pzienteAttualmenteInVisita = pzienteAttualmenteInVisita;
	}

	public Dottore(String nome, String cognome, String codiceDipendente, Paziente pzienteAttualmenteInVisita) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
		this.pzienteAttualmenteInVisita = pzienteAttualmenteInVisita;
	}

	public Dottore(String nome, String cognome, String codiceDipendente) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.codiceDipendente = codiceDipendente;
	}
		
}

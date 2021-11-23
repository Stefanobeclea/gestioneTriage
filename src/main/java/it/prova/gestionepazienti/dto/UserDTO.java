package it.prova.gestionepazienti.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;

import it.prova.gestionepazienti.model.User;
import it.prova.gestionepazienti.model.StatoUtente;

public class UserDTO {

	private Long id;
	@NotBlank(message = "{username.notblank}")
	private String username;
	@NotBlank(message = "{nome.notblank}")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	private String cognome;
	private Date dateCreated;
	
	private StatoUtente stato;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserDTO(@NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome, Date dateCreated, StatoUtente stato) {
		super();
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public UserDTO(Long id, @NotBlank(message = "{username.notblank}") String username,
			@NotBlank(message = "{nome.notblank}") String nome,
			@NotBlank(message = "{cognome.notblank}") String cognome, Date dateCreated, StatoUtente stato) {
		super();
		this.id = id;
		this.username = username;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato = stato;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}
	
	public User buildUserModel() {
		return new User(this.id ,this.nome, this.cognome, this.username, this.dateCreated, this.stato);
	}

	public static UserDTO buildUserDTOFromModel(User userModel) {
		UserDTO result = new UserDTO(userModel.getId(), userModel.getNome(),
				userModel.getCognome(),  userModel.getUsername(), userModel.getDateCreated(), userModel.getStato());
		return result;
	}
	public static List<UserDTO> createUserDTOListFromModelList(List<User> modelListInput) {
		return modelListInput.stream().map(dottoreEntity -> {
			UserDTO result = UserDTO.buildUserDTOFromModel(dottoreEntity);
			
			return result;
		}).collect(Collectors.toList());
	}
}

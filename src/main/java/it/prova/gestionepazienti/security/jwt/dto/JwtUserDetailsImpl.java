package it.prova.gestionepazienti.security.jwt.dto;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.prova.gestionepazienti.model.StatoUtente;
import it.prova.gestionepazienti.model.User;

public class JwtUserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private final String nome;
	private final String cognome;
	private final String username;
	private final String password;
	private final Date dateCreated;
	private final StatoUtente stato;
	private final boolean enabled;
	
	private final Collection<? extends GrantedAuthority> authorities;

	public JwtUserDetailsImpl(String nome,String cognome,String username, String password, Date dateCreated,StatoUtente stato, Collection<? extends GrantedAuthority> authorities
			, boolean enabled) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dateCreated = dateCreated;
		this.stato = stato;
		this.enabled = enabled;
		this.authorities = authorities;
	}
	
	public static JwtUserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
                .collect(Collectors.toList());
		
        return new JwtUserDetailsImpl(
        		user.getNome(),
        		user.getCognome(),
                user.getUsername(),
                user.getPassword(),
                user.getDateCreated(),
                user.getStato(),
                authorities,
                user.getEnabled()
        );
    }

	@Override
	public String getUsername() {
		return username;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JwtUserDetailsImpl user = (JwtUserDetailsImpl) o;
		return Objects.equals(username, user.username);
	}

	public boolean isEnabled() {
		return enabled;
	}

	

}
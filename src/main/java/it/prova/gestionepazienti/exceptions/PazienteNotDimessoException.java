package it.prova.gestionepazienti.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class PazienteNotDimessoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PazienteNotDimessoException(String message) {
		super(message);
	}
}

package com.nissum.pruebaregistro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegistroNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = -7500322046753778196L;

	public RegistroNoEncontradoException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor de la clase
	 * 
	 * @param code
	 */
	public RegistroNoEncontradoException(String recurso, String id) {
		super(String.format("No se encontró registro(s) de %s con el ID %s", recurso, id));
	}
}

package com.nissum.pruebaregistro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nissum.pruebaregistro.service.MensajeService;
import com.nissum.pruebaregistro.utilities.SpringContextUtilidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SolicitudIncorrectaException extends RuntimeException {

	private static final long serialVersionUID = -75003220113778196L;

	/**
	 * Constructor de la clase
	 * 
	 * @param code
	 */
	public SolicitudIncorrectaException(String mensaje) {
		super(mensaje);

	}

	public static SolicitudIncorrectaExceptionBuilder builder() {
		return new SolicitudIncorrectaExceptionBuilder();
	}

	public static class SolicitudIncorrectaExceptionBuilder {
		private String code;
		private String[] args;

		public SolicitudIncorrectaExceptionBuilder code(String code) {
			this.code = code;
			return this;
		}

		public SolicitudIncorrectaExceptionBuilder args(String[] args) {
			this.args = args;
			return this;
		}

		/**
		 * Metodo que retorna la excepcion
		 * 
		 * @return SolicitudIncorrectaException
		 */
		public SolicitudIncorrectaException build() {
			MensajeService mensajeService = SpringContextUtilidades.getBean(MensajeService.class);
			String mensaje = mensajeService.obtenerMensaje(this.code, (Object[]) this.args);

			return new SolicitudIncorrectaException(mensaje);
		}

	}

}

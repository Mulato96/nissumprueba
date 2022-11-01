package com.nissum.pruebaregistro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nissum.pruebaregistro.service.MensajeService;
import com.nissum.pruebaregistro.utilities.SpringContextUtilidades;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

	private static final long serialVersionUID = -8556490604693291788L;

	public ConflictException(String mensaje) {
		super(mensaje);
	}

	public static ConflicExceptionBuilder builder() {
		return new ConflicExceptionBuilder();
	}

	public static class ConflicExceptionBuilder {
		private String code;
		private String[] args;

		public ConflicExceptionBuilder code(String code) {
			this.code = code;
			return this;
		}

		public ConflicExceptionBuilder args(String... args) {
			this.args = args;
			return this;
		}

		/**
		 * Metodo que retorna la excepcion
		 * 
		 * @return ConflicException
		 */
		public ConflictException build() {
			MensajeService mensajeService = SpringContextUtilidades.getBean(MensajeService.class);
			String mensaje = mensajeService.obtenerMensaje(this.code, (Object[]) this.args);

			return new ConflictException(mensaje);
		}

	}

}

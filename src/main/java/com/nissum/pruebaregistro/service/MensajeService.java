package com.nissum.pruebaregistro.service;

public interface MensajeService {
	/**
	 * Obtener mensaje por llave y locale por defecto.
	 * 
	 * @param llave Llave del mensaje.
	 * @return Retorna un mensaje por llave y locale por defecto.
	 */
	String obtenerMensaje(String llave);

	/**
	 * Obtener mensaje por llave, argumento y locale por defecto.
	 * 
	 * @param llave Llave del mensaje.
	 * @param args  Argumentos del mensaje
	 * @return Retorna un mensaje por llave, argumento y locale por defecto.
	 */
	String obtenerMensaje(String llave, Object[] args);
}
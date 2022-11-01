package com.nissum.pruebaregistro.service;

import com.nissum.pruebaregistro.modelo.LoginUser;
import com.nissum.pruebaregistro.modelo.User;

public interface IUserService {

	/**
	 * Metodo para guardar un user
	 * 
	 * @param user
	 * @return User
	 */
	User guardarUser(User mimPlan);

	/**
	 * Metodo si existe un user
	 * 
	 * @param email
	 * @return boolean
	 */
	boolean existUser(String email);

	/**
	 * Metodo que obtiene un user
	 * 
	 * @param idUser
	 * @return User
	 */
	User getUser(Long codigo);

	/**
	 * Metodo para actualizar un user
	 * 
	 * @param user
	 * @return User
	 */
	User actualizarUser(Long idUser, User user);

	/**
	 * Metodo login
	 * 
	 * @param email,password
	 * @return User
	 */
	LoginUser existUserPassword(String email, String password);

}

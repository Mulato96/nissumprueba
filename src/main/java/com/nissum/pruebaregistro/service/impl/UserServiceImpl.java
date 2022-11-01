package com.nissum.pruebaregistro.service.impl;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissum.pruebaregistro.constantes.Constantes;
import com.nissum.pruebaregistro.exception.RegistroNoEncontradoException;
import com.nissum.pruebaregistro.exception.SolicitudIncorrectaException;
import com.nissum.pruebaregistro.modelo.LoginUser;
import com.nissum.pruebaregistro.modelo.User;
import com.nissum.pruebaregistro.repository.LoginUserRepository;
import com.nissum.pruebaregistro.repository.UserRepository;
import com.nissum.pruebaregistro.service.IUserService;
import com.nissum.pruebaregistro.utilities.StringUtilidades;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginUserRepository loginUserRepository;

	@Override
	public User guardarUser(@Valid User user) {
		return userRepository.save(user);
	}

	@Override
	public boolean existUser(String email) {
		boolean bandera = false;
		User usuario = userRepository.existUser(email);

		if (usuario != null) {
			bandera = true;
		}
		return bandera;
	}

	@Override
	public User getUser(Long idUser) {
		if (idUser == null) {
			throw SolicitudIncorrectaException.builder().code(Constantes.GENERAL_NO_ID).build();
		}
		return userRepository.findById(idUser)
				.orElseThrow(() -> new RegistroNoEncontradoException(Constantes.USER, idUser.toString()));
	}

	@Override
	public User actualizarUser(Long idUser, @Valid User user) {

		User userBD = userRepository.existUser(user.getEmail());
		if (idUser == null) {
			throw SolicitudIncorrectaException.builder().code(Constantes.GENERAL_NO_ID).build();
		}

		if (!userRepository.existsById(idUser)) {
			throw new RegistroNoEncontradoException(Constantes.USER, String.valueOf(idUser));
		}

		if (!userBD.getIdUser().equals(idUser) && userBD.getEmail().equals(user.getEmail())) {
			throw SolicitudIncorrectaException.builder().code(Constantes.EMAIL_REPETIDO).build();
		}

		user.setModified(new Date());

		return userRepository.save(user);

	}

	@Override
	public LoginUser existUserPassword(String email, String password) {
		LoginUser loginUser = new LoginUser();
		User usuario = userRepository.existUserPassword(email, password);
		if (usuario != null) {
			loginUser.setLastLogin(new Date());
			loginUser.setToken(StringUtilidades.generateToken(email));
			loginUser.setUser(usuario);
			loginUserRepository.save(loginUser);
		}
		return loginUser;
	}

}

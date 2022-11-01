package com.nissum.pruebaregistro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissum.pruebaregistro.dto.UserDto;
import com.nissum.pruebaregistro.modelo.LoginUser;
import com.nissum.pruebaregistro.modelo.User;
import com.nissum.pruebaregistro.service.IUserService;
import com.nissum.pruebaregistro.utilities.ResponseService;
import com.nissum.pruebaregistro.utilities.Status;
import com.nissum.pruebaregistro.utilities.StringUtilidades;

@RestController
@RequestMapping("/api/nissum")
public class UserController {

	@Autowired
	private IUserService userService;

	private HttpStatus statusHttp;

	@PostMapping("/postUser")
	public ResponseEntity<ResponseService> postUser(@RequestBody @Valid UserDto userDto) {
		ResponseService response = new ResponseService();

		try {

			if (userService.existUser(userDto.getEmail())) {
				response.setMessage("El correo ya registrado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			if (!StringUtilidades.esEmail(userDto.getEmail())) {
				response.setMessage("Email no valido");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			if (!StringUtilidades.passwordValido(userDto.getPassword())) {
				response.setMessage("Password no valido");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			String jwt = StringUtilidades.generateToken(userDto.getEmail());

			userDto.setToken(jwt);
			User user = UserDto.CONVERTER_ENTITY.apply(userDto);

			response.setData(UserDto.CONVERTER_DTO.apply(userService.guardarUser(user)));
			response.setStatus(Status.OK);
			statusHttp = HttpStatus.OK;
		} catch (Exception e) {
			statusHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus(Status.FAILURE);
			response.setMessageError("Ocurrio un error al consultar el servicio." + e.getMessage());
		}

		return new ResponseEntity<>(response, statusHttp);
	}

	@GetMapping("/getUser/{idUser}")
	public ResponseEntity<UserDto> getUser(@PathVariable Long idUser) {
		return ResponseEntity.ok(UserDto.CONVERTER_DTO.apply(userService.getUser(idUser)));
	}

	@PutMapping("/getUser/{idUser}")
	public ResponseEntity<UserDto> putUser(@RequestBody @Valid UserDto userDto, @PathVariable Long idUser) {
		User user = UserDto.CONVERTER_ENTITY_UPDATE.apply(userDto);
		return ResponseEntity.ok(UserDto.CONVERTER_DTO_UPDATE.apply(userService.actualizarUser(idUser, user)));
	}

	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<ResponseService> loginUser(@PathVariable String email, @PathVariable String password) {

		ResponseService response = new ResponseService();

		try {
			LoginUser loginUser = userService.existUserPassword(email, password);

			if (loginUser.getToken() == null) {
				response.setMessage("Email o password incorrecto");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			response.setData(loginUser.getToken());
			response.setStatus(Status.OK);
			statusHttp = HttpStatus.OK;
		} catch (Exception e) {
			statusHttp = HttpStatus.INTERNAL_SERVER_ERROR;
			response.setStatus(Status.FAILURE);
			response.setMessageError("Ocurrio un error al consultar el servicio." + e.getMessage());
		}
		return new ResponseEntity<>(response, statusHttp);
	}

}

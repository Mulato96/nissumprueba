package com.nissum.pruebaregistro.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nissum.pruebaregistro.modelo.LoginUser;
import com.nissum.pruebaregistro.modelo.Phone;
import com.nissum.pruebaregistro.modelo.User;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Function<UserDto, User> CONVERTER_ENTITY_UPDATE = (UserDto t) -> {

		User userEntity = new User();

		userEntity.setIdUser(t.getIdUser());
		userEntity.setName(t.getName());
		userEntity.setEmail(t.getEmail());
		userEntity.setEmail(t.getEmail());
		userEntity.setPassword(t.getPassword());
		userEntity.setCreated(new Date());
		userEntity.setActive(true);
		userEntity.setToken(t.getToken());

		if (t.getPhones() != null && !t.getPhones().isEmpty()) {
			userEntity.setPhonesList(t.getPhones().stream().map(phoneDto -> {
				Phone phone = PhoneDto.CONVERTER_ENTITY.apply(phoneDto);
				phone.setUser(userEntity);
				return phone;
			}).collect(Collectors.toList()));
		}

		return userEntity;

	};

	public static final Function<UserDto, User> CONVERTER_ENTITY = (UserDto t) -> {

		User userEntity = new User();
		LoginUser loginUser = new LoginUser();
		List<LoginUser> lstLoginUser = new ArrayList<>();

		userEntity.setIdUser(t.getIdUser());
		userEntity.setName(t.getName());
		userEntity.setEmail(t.getEmail());
		userEntity.setEmail(t.getEmail());
		userEntity.setPassword(t.getPassword());
		userEntity.setCreated(new Date());
		userEntity.setActive(true);
		userEntity.setToken(t.getToken());

		if (t.getPhones() != null && !t.getPhones().isEmpty()) {
			userEntity.setPhonesList(t.getPhones().stream().map(phoneDto -> {
				Phone phone = PhoneDto.CONVERTER_ENTITY.apply(phoneDto);
				phone.setUser(userEntity);
				return phone;
			}).collect(Collectors.toList()));
		}

		loginUser.setUser(userEntity);
		loginUser.setToken(t.getToken());
		loginUser.setLastLogin(new Date());
		lstLoginUser.add(loginUser);
		userEntity.setLoginUserList(lstLoginUser);

		return userEntity;

	};

	public static final Function<User, UserDto> CONVERTER_DTO = (User t) -> {

		UserDto userDto = new UserDto();
		List<LoginUser> lstLoginUser = t.getLoginUserList();

		userDto.setIdUser(t.getIdUser());
		userDto.setName(t.getName());
		userDto.setEmail(t.getEmail());
		userDto.setPassword(t.getPassword());
		userDto.setToken(t.getToken());
		userDto.setCreated(t.getCreated());
		userDto.setActive(true);
		userDto.setModified(t.getModified());
		userDto.setUuid(UUID.randomUUID().toString());
		userDto.setLastLogin(lstLoginUser.get(lstLoginUser.size() - 1).getLastLogin());

		if (t.getPhonesList() != null && !t.getPhonesList().isEmpty()) {
			userDto.setPhones(t.getPhonesList().stream().map(phone -> {
				phone.setUser(null);
				return PhoneDto.CONVERTER_DTO.apply(phone);
			}).collect(Collectors.toList()));
		}

		return userDto;

	};
	
	public static final Function<User, UserDto> CONVERTER_DTO_UPDATE = (User t) -> {

		UserDto userDto = new UserDto();		

		userDto.setIdUser(t.getIdUser());
		userDto.setName(t.getName());
		userDto.setEmail(t.getEmail());
		userDto.setPassword(t.getPassword());
		userDto.setToken(t.getToken());
		userDto.setCreated(t.getCreated());
		userDto.setActive(true);
		userDto.setModified(t.getModified());
		userDto.setUuid(UUID.randomUUID().toString());		

		if (t.getPhonesList() != null && !t.getPhonesList().isEmpty()) {
			userDto.setPhones(t.getPhonesList().stream().map(phone -> {
				phone.setUser(null);
				return PhoneDto.CONVERTER_DTO.apply(phone);
			}).collect(Collectors.toList()));
		}

		return userDto;

	};

	private Long idUser;

	private String uuid;

	@NotNull(message = "{GeneralCampos.nameEmpty}")
	private String name;

	@NotNull(message = "{GeneralCampos.emailEmpty}")
	@NotEmpty
	private String email;

	@NotNull(message = "{GeneralCampos.passwordEmpty}")
	@NotEmpty
	private String password;

	@NotNull(message = "{GeneralCampos.phonesEmpty}")
	@NotEmpty
	private List<PhoneDto> phones;

	private String token;

	private Date created;

	private Date modified;

	private Date lastLogin;

	private boolean isActive;

}

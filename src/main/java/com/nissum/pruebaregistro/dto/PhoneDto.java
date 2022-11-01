package com.nissum.pruebaregistro.dto;

import java.util.function.Function;

import com.nissum.pruebaregistro.modelo.Phone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {
	private Long number;
	private Long citycode;
	private Long contrycode;
	private UserDto user;

	public static final Function<PhoneDto, Phone> CONVERTER_ENTITY = (PhoneDto t) -> {

		Phone phoneEntiy = new Phone();

		phoneEntiy.setNumber(t.getNumber());
		phoneEntiy.setCityCode(t.getCitycode());
		phoneEntiy.setContryCode(t.getContrycode());

		if (t.getUser() != null) {
			phoneEntiy.setUser(UserDto.CONVERTER_ENTITY.apply(t.getUser()));
		}

		return phoneEntiy;

	};

	public static final Function<Phone, PhoneDto> CONVERTER_DTO = (Phone t) -> {

		PhoneDto phoneDto = new PhoneDto();

		phoneDto.setNumber(t.getNumber());
		phoneDto.setCitycode(t.getCityCode());
		phoneDto.setContrycode(t.getContryCode());

		return phoneDto;

	};
}

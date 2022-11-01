package com.nissum.pruebaregistro.utilities;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;

public class StringUtilidades {
	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("^(?!\\d+\\b)([a-zA-Z\\d_-]+(?:\\.[a-zA-Z\\d_-]+)*)@(?!-)(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");

	private static final Pattern PASSWORD_PATTERN = Pattern
			.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2})([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$");

	// Jwt Configuration
	private static final String secretKey ="nissum*";

	private StringUtilidades() {
	}

	public static String trim(String string) {
		return string == null ? null : string.trim();
	}

	public static String toString(Object o) {
		return o == null ? null : String.valueOf(o);
	}

	public static String toString(Object o, String def) {
		return o == null ? def : String.valueOf(o);
	}

	public static boolean isNullOrEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static boolean esEmail(String email) {
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.find();
	}

	public static boolean passwordValido(String password) {
		Matcher matcher = PASSWORD_PATTERN.matcher(password);
		return matcher.find();
	}

	public static String generateToken(String email) {
		final Instant now = Instant.now();
		return Jwts.builder().setSubject(email).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
				.signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(secretKey)).compact();
	}
}

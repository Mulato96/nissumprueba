package com.nissum.pruebaregistro.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOGIN_USER")
@Getter
@Setter
public class LoginUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_LOGIN_USER")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLoginUser;

	@Basic(optional = false)
	@Column(name = "TOKEN")
	private String token;

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN", nullable = false)
	@NotNull
	private Date lastLogin;

	@JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
	@ManyToOne(optional = false)
	private User user;
}

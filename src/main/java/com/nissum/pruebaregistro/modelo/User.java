package com.nissum.pruebaregistro.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_USER")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUser;

	@Basic(optional = false)
	@Column(name = "NAME")
	private String name;

	@Basic(optional = false)
	@Column(name = "EMAIL")
	private String email;

	@Basic(optional = false)
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "TOKEN")
	private String token;

	@Basic(optional = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false)
	@NotNull
	private Date created;

	@Basic(optional = false)
	@Column(name = "ISACTIVE")
	private boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED")
	private Date modified;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Phone> phonesList;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LoginUser> loginUserList;

}

package com.nissum.pruebaregistro.modelo;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PHONE")
@Getter
@Setter
public class Phone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "ID_PHONE")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPhone;

	@Basic(optional = false)
	@Column(name = "NUMBER")
	private Long number;

	@Basic(optional = false)
	@Column(name = "CITY_CODE")
	private Long cityCode;

	@Basic(optional = false)
	@Column(name = "COUNTRY_CODE")
	private Long contryCode;

	@JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
	@ManyToOne(optional = false)
	private User user;
}

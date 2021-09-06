package com.phoebus.pandemiaapi.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;


/**
 * @author Oliver
 *
 */

@Entity
@Table(name = "TB_HOSPITAL")
public class Hospital {


	@Id
	@Column(name = "CNPJ", unique = true)
	private Long cnpj;

	@NotNull
	@Column(name = "NOME")
	private String nome;

	@NotNull
	@Column(name = "PERCENTUAL_OCUPACAO")
	private float percentualDeOcupacao;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RECURSO_ID")
	@JsonManagedReference
	private Recurso recursos;

	
	
/*
 * ENDEREÇO E LOCALIZAÇÃO
 */
	@Column(name = "UF")
	private String uf;

	@Column(name = "CIDADE")
	private String cidade;

	@Column(name = "BAIRRO")
	private String bairro;

	@Column(name = "LOGRADOURO")
	private String logradouro;

	@Column(name = "NUMERO")
	private String numero;

	@Column(name = "LATITUDE")
	private float latitude;

	@Column(name = "LONGITUDE")
	private float longitude;
	
	
	
	public Recurso getRecursos() {
		return recursos;
	}


	public void setRecursos(Recurso recursos) {
		this.recursos = recursos;
	}


	public Hospital() {
		
	}


	public Long getCnpj() {
		return cnpj;
	}


	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public float getPercentualDeOcupacao() {
		return percentualDeOcupacao;
	}


	public void setPercentualDeOcupacao(float percentualDeOcupacao) {
		this.percentualDeOcupacao = percentualDeOcupacao;
	}


	public String getUf() {
		return uf;
	}


	public void setUf(String uf) {
		this.uf = uf;
	}


	public String getCidade() {
		return cidade;
	}


	public void setCidade(String cidade) {
		this.cidade = cidade;
	}


	public String getBairro() {
		return bairro;
	}


	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	public String getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	

}

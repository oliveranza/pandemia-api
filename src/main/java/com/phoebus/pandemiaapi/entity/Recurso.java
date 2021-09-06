package com.phoebus.pandemiaapi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="TB_RECURSO")
@Embeddable
public class Recurso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="MEDICO")
	private int medico;
	
	@Column(name="ENFERMEIRO")
	private int enfermeiro;
	
	@Column(name="RESPIRADOR")
	private int respirador;
	
	@Column(name="TOMOGRAFO")
	private int tomografo;
	
	@Column(name="AMBULANCIA")
	private int ambulancia;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Hospital hospital;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getMedico() {
		return medico;
	}

	public void setMedico(int medico) {
		this.medico = medico;
	}

	public int getEnfermeiro() {
		return enfermeiro;
	}

	public void setEnfermeiro(int enfermeiro) {
		this.enfermeiro = enfermeiro;
	}

	public int getRespirador() {
		return respirador;
	}

	public void setRespirador(int respirador) {
		this.respirador = respirador;
	}

	public int getTomografo() {
		return tomografo;
	}

	public void setTomografo(int tomografo) {
		this.tomografo = tomografo;
	}

	public int getAmbulancia() {
		return ambulancia;
	}

	public void setAmbulancia(int ambulancia) {
		this.ambulancia = ambulancia;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
}
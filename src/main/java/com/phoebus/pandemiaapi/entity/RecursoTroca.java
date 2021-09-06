package com.phoebus.pandemiaapi.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

//@Entity
@Table(name = "TB_RECURSO")
@Embeddable
public class RecursoTroca {

	@Column(name = "MEDICO")
	private int medico;

	@Column(name = "ENFERMEIRO")
	private int enfermeiro;

	@Column(name = "RESPIRADOR")
	private int respirador;

	@Column(name = "TOMOGRAFO")
	private int tomografo;

	@Column(name = "AMBULANCIA")
	private int ambulancia;

	public RecursoTroca() {
		super();
		// TODO Auto-generated constructor stub
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

}

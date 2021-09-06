package com.phoebus.pandemiaapi.dto;

public class ReportDTO {

	private float porcentagemSuperLotacao;
	
	private float porcentagemAbaixoSuperLotacao;

	private float mediaMedico;
	
	private float mediaEnfermeiro;
	
	private float mediaRespirador;
	
	private float mediaTomografo;
	
	private float mediaAmbulancia;
	
	private String superLotacaoAMaisTempo;
	
	private String abaixoSuperLotacaoAMaisTempo;

	
	
	public ReportDTO() {

	}


	public float getSuperLotacao() {
		return porcentagemSuperLotacao;
	}

	public void setSuperLotacao(float superLotacao) {
		this.porcentagemSuperLotacao = superLotacao;
	}

	public float getAbaixoSuperLotacao() {
		return porcentagemAbaixoSuperLotacao;
	}

	public void setAbaixoSuperLotacao(float abaixoSuperLotacao) {
		this.porcentagemAbaixoSuperLotacao = abaixoSuperLotacao;
	}

	public float getMediaMedico() {
		return mediaMedico;
	}

	public void setMediaMedico(float mediaMedico) {
		this.mediaMedico = mediaMedico;
	}

	public float getMediaEnfermeiro() {
		return mediaEnfermeiro;
	}

	public void setMediaEnfermeiro(float mediaEnfermeiro) {
		this.mediaEnfermeiro = mediaEnfermeiro;
	}

	public float getMediaRespirador() {
		return mediaRespirador;
	}

	public void setMediaRespirador(float mediaRespirador) {
		this.mediaRespirador = mediaRespirador;
	}

	public float getMediaTomografo() {
		return mediaTomografo;
	}

	public void setMediaTomografo(float mediaTomografo) {
		this.mediaTomografo = mediaTomografo;
	}

	public float getMediaAmbulancia() {
		return mediaAmbulancia;
	}

	public void setMediaAmbulancia(float mediaAmbulancia) {
		this.mediaAmbulancia = mediaAmbulancia;
	}

	public String getSuperLotacaoAMaisTempo() {
		return superLotacaoAMaisTempo;
	}

	public void setSuperLotacaoAMaisTempo(String superLotacaoAMaisTempo) {
		this.superLotacaoAMaisTempo = superLotacaoAMaisTempo;
	}

	public String getAbaixoSuperLotacaoAMaisTempo() {
		return abaixoSuperLotacaoAMaisTempo;
	}

	public void setAbaixoSuperLotacaoAMaisTempo(String abaixoSuperLotacaoAMaisTempo) {
		this.abaixoSuperLotacaoAMaisTempo = abaixoSuperLotacaoAMaisTempo;
	}
	
}

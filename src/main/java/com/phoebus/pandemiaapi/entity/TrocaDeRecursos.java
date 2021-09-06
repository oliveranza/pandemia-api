package com.phoebus.pandemiaapi.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "HISTORICO_INTERCAMBIO")
public class TrocaDeRecursos {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="data",  updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date data;

	@Column(name = "CNPJ_HOSPITAL1")
	private Long cnpjHospital1;

	@Column(name = "CNPJ_HOSPITAL2")
	private Long cnpjHospital2;

	@Column(name = "RECURSOS_DE_TROCA1")
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "medico", column = @Column(name = "MEDICO_1")),
			@AttributeOverride(name = "enfermeiro", column = @Column(name = "ENFERMEIRO_1")),
			@AttributeOverride(name = "respirador", column = @Column(name = "RESPIRADOR_1")),
			@AttributeOverride(name = "tomografo", column = @Column(name = "TOMOGRAFO_1")),
			@AttributeOverride(name = "ambulancia", column = @Column(name = "AMBULANCIA_1")),
	})
	private RecursoTroca recursoHospital1;
	

	@Column(name = "RECURSOS_DE_TROCA2")
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "medico", column = @Column(name = "MEDICO_2")),
		@AttributeOverride(name = "enfermeiro", column = @Column(name = "ENFERMEIRO_2")),
		@AttributeOverride(name = "respirador", column = @Column(name = "RESPIRADOR_2")),
		@AttributeOverride(name = "tomografo", column = @Column(name = "TOMOGRAFO_2")),
		@AttributeOverride(name = "ambulancia", column = @Column(name = "AMBULANCIA_2")),
})
	private RecursoTroca recursoHospital2;

	public TrocaDeRecursos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Long getCnpjHospital1() {
		return cnpjHospital1;
	}

	public void setCnpjHospital1(Long cnpjHospital1) {
		this.cnpjHospital1 = cnpjHospital1;
	}

	public Long getCnpjHospital2() {
		return cnpjHospital2;
	}

	public void setCnpjHospital2(Long cnpjHospital2) {
		this.cnpjHospital2 = cnpjHospital2;
	}

	public RecursoTroca getRecursoHospital1() {
		return recursoHospital1;
	}

	public void setRecursoHospital1(RecursoTroca recursoHospital1) {
		this.recursoHospital1 = recursoHospital1;
	}

	public RecursoTroca getRecursoHospital2() {
		return recursoHospital2;
	}


	public void setRecursoHospital2(RecursoTroca recursoHospital2) {
		this.recursoHospital2 = recursoHospital2;
	}

}

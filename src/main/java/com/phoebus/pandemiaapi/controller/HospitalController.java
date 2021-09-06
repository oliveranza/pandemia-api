package com.phoebus.pandemiaapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoebus.pandemiaapi.entity.Hospital;
import com.phoebus.pandemiaapi.entity.TrocaDeRecursos;
import com.phoebus.pandemiaapi.service.HospitalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/*
 * AUTOR: EVANDSON OLIVEIRA
 */

@RestController()
@RequestMapping(value = "/hospital")
@Api(value= "API RESTFul Combate à pandemia")
@CrossOrigin(origins = "*")
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;


	@GetMapping
	@ApiOperation(value="Retorna Lista dos hospitais cadastrados")
	public List<Hospital> findAll() {
		return hospitalService.findAll();
	}

	//Metodo para pesquisar um hospital pelo id(cnpj)
	@GetMapping("/{cnpj}")
	@ApiOperation(value="Retorna um hospital buscando pelo numero do CNPJ")
	public Optional<Hospital> findById(@PathVariable(value = "cnpj") long cnpj) {
		return hospitalService.findById(cnpj);
	}
	
	@PostMapping
	@ApiOperation(value="Cadastra um hospital na base de dados")
	public ResponseEntity<Object> save(@RequestBody Hospital hospital) {
		return hospitalService.save(hospital);
	}

	@PutMapping
	@ApiOperation(value="Atualiza um hospital da base de dados")
	public Hospital update(@RequestBody Hospital hospital) {
		return hospitalService.update(hospital);
	}
	
	@DeleteMapping("/{cnpj}")
	@ApiOperation(value="Apaga um hospital da base de dados")
	public void delete(@PathVariable(value = "cnpj") long cnpj) {
		hospitalService.delete(cnpj);
	}

	
	@GetMapping("/ocupacao/{cnpj}")
	@ApiOperation(value="Consulta o percentual de ocupação de um hospital")
	public ResponseEntity<Object> percentualDeOcupacao(@PathVariable(value = "cnpj") long cnpj) {
		return hospitalService.ocupacao(cnpj);
	}
	
	@PutMapping("/intercambio")
	@ApiOperation(value="Realiza o intercambio de recursos entre dois hospitais")
	public ResponseEntity<Object> intercambio(@RequestBody TrocaDeRecursos trocaDeRecursos) {
		return hospitalService.intercambio(trocaDeRecursos);
	}
	
	@GetMapping("/relatorio")
	@ApiOperation(value="Retorna um relatorio sobre os hospitais e seus recursos")
	public ResponseEntity<Object> gerarRelatorios() {
		return hospitalService.report();
	}
		
	@GetMapping("/historico")
	@ApiOperation(value="Retorna o histórico de todas os intercambios de recursos realizados")
	public ResponseEntity<Object> getHistoricoDeTrocasDeRecursos() {
		return hospitalService.getHistoricoDeTrocasDeRecursos();
	}

}
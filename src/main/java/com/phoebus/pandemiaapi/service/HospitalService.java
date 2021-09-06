package com.phoebus.pandemiaapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.phoebus.pandemiaapi.dto.ReportDTO;
import com.phoebus.pandemiaapi.entity.Hospital;
import com.phoebus.pandemiaapi.entity.Recurso;
import com.phoebus.pandemiaapi.entity.RecursoTroca;
import com.phoebus.pandemiaapi.entity.TrocaDeRecursos;
import com.phoebus.pandemiaapi.repository.HospitalRepository;
import com.phoebus.pandemiaapi.repository.TrocaDeRecursosRepository;

/*
 * AUTOR: EVANDSON OLIVEIRA
 */
@Service
public class HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private TrocaDeRecursosRepository TrocaDeRecursosRepository;
	
	
	public List<Hospital> findAll() {
		return hospitalRepository.findAll();
	}

	public Optional<Hospital> findById(long id) {
		return hospitalRepository.findById(id);
	}

	public ResponseEntity<Object> save(Hospital hospital) {
		if (hospital.getRecursos() == null)
			return new ResponseEntity<Object>("Recursos devem ser informados", HttpStatus.BAD_REQUEST);
		Optional<Hospital> h = findById(hospital.getCnpj());
		if (h.isEmpty())
			return new ResponseEntity<Object>(hospitalRepository.save(hospital), HttpStatus.CREATED);
		else
			return new ResponseEntity<Object>("Hospital com o mesmo CNPJ já cadastrado", HttpStatus.CONFLICT);
	}

	public Hospital update(Hospital hospital) {

		return hospitalRepository.save(hospital);
	}

	public void delete(long cnpj) {
		hospitalRepository.deleteById(cnpj);
	}

	public ResponseEntity<Object> ocupacao(long cnpj) {
		if (findById(cnpj).isEmpty())
			return new ResponseEntity<Object>("Hospital não encontrado", HttpStatus.NOT_FOUND);
		else {
			Optional<Hospital> h = findById(cnpj);
			return new ResponseEntity<Object>(h.get().getPercentualDeOcupacao(), HttpStatus.OK);
		}
	}

	public ResponseEntity<Object> intercambio(TrocaDeRecursos trocaDeRecursos) {
		
		Optional<Hospital> h = findById(trocaDeRecursos.getCnpjHospital1());
		Optional<Hospital> h2 = findById(trocaDeRecursos.getCnpjHospital2());
		int pontos1;
		int pontos2;
		if (h.isEmpty() || h2.isEmpty())
			return new ResponseEntity<Object>("Hospital(is) não encontrado(s)", HttpStatus.NOT_FOUND);
		RecursoTroca r1 = trocaDeRecursos.getRecursoHospital1();
		pontos1 = r1.getAmbulancia() * 10 + r1.getEnfermeiro() * 3 + r1.getMedico() * 3 + r1.getRespirador() * 5+ r1.getTomografo() * 12;
		RecursoTroca r2 = trocaDeRecursos.getRecursoHospital2();
		pontos2 = r2.getAmbulancia() * 10 + r2.getEnfermeiro() * 3 + r2.getMedico() * 3 + r2.getRespirador() * 5+ r2.getTomografo() * 12;
		if (pontos1 == pontos2) {
			return trocarRecursos(trocaDeRecursos);
			
		} else {
			float porcentagemDeOcupacao;
			if (pontos1<pontos2) {
				porcentagemDeOcupacao = h.get().getPercentualDeOcupacao();
			}
			else {
				porcentagemDeOcupacao = h2.get().getPercentualDeOcupacao();
			}
			if(porcentagemDeOcupacao>=90) {
				return trocarRecursos(trocaDeRecursos);
			}
			
			return new ResponseEntity<Object>("O valor dos recursos oferecidos pelos hospitais não é equivalante.\n"
					+ "Nestas condições, só é possivel um hospital enviar menos recursos "
					+ "se o seu percentual de ocupação for maior do que 90%", HttpStatus.NOT_ACCEPTABLE);
		}

	}

	public ResponseEntity<Object> trocarRecursos(TrocaDeRecursos trocaDeRecursos){
		Hospital hospital1 = findById(trocaDeRecursos.getCnpjHospital1()).get();
		Hospital hospital2 = findById(trocaDeRecursos.getCnpjHospital2()).get();
		Recurso recursosHospital1 = hospital1.getRecursos();
		Recurso recursosHospital2 = hospital2.getRecursos();
		RecursoTroca recursosEnviadosHospital1 = trocaDeRecursos.getRecursoHospital1();
		RecursoTroca recursosEnviadosHospital2 = trocaDeRecursos.getRecursoHospital2();
			
		if(recursosHospital1.getMedico()>=recursosEnviadosHospital1.getMedico() && recursosHospital2.getMedico()>=recursosEnviadosHospital2.getMedico()) {
			recursosHospital1.setMedico(recursosHospital1.getMedico()-recursosEnviadosHospital1.getMedico()+recursosEnviadosHospital2.getMedico());
			recursosHospital2.setMedico(recursosHospital2.getMedico()-recursosEnviadosHospital2.getMedico()+recursosEnviadosHospital1.getMedico());
		}else {
			return new ResponseEntity<Object>("Não há medicos o suficiente para realizar a troca de recursos", HttpStatus.NOT_ACCEPTABLE);
		}
		if(recursosHospital1.getEnfermeiro()>=recursosEnviadosHospital1.getEnfermeiro() && recursosHospital2.getEnfermeiro()>=recursosEnviadosHospital2.getEnfermeiro()) {
			recursosHospital1.setEnfermeiro(recursosHospital1.getEnfermeiro()-recursosEnviadosHospital1.getEnfermeiro()+recursosEnviadosHospital2.getEnfermeiro());
			recursosHospital2.setEnfermeiro(recursosHospital2.getEnfermeiro()-recursosEnviadosHospital2.getEnfermeiro()+recursosEnviadosHospital1.getEnfermeiro());
		}else {
			return new ResponseEntity<Object>("Não há enfermeiros o suficiente para realizar a troca de recursos", HttpStatus.NOT_ACCEPTABLE);
		}
		if(recursosHospital1.getRespirador()>=recursosEnviadosHospital1.getRespirador() && recursosHospital2.getRespirador()>=recursosEnviadosHospital2.getRespirador()) {
			recursosHospital1.setRespirador(recursosHospital1.getRespirador()-recursosEnviadosHospital1.getRespirador()+recursosEnviadosHospital2.getRespirador());
			recursosHospital2.setRespirador(recursosHospital2.getRespirador()-recursosEnviadosHospital2.getRespirador()+recursosEnviadosHospital1.getRespirador());
		}else {
			return new ResponseEntity<Object>("Não há respiradores o suficiente para realizar a troca de recursos", HttpStatus.NOT_ACCEPTABLE);
		}
		if(recursosHospital1.getTomografo()>=recursosEnviadosHospital1.getTomografo() && recursosHospital2.getTomografo()>=recursosEnviadosHospital2.getTomografo()) {
			recursosHospital1.setTomografo(recursosHospital1.getTomografo()-recursosEnviadosHospital1.getTomografo()+recursosEnviadosHospital2.getTomografo());
			recursosHospital2.setTomografo(recursosHospital2.getTomografo()-recursosEnviadosHospital2.getTomografo()+recursosEnviadosHospital1.getTomografo());
		}else {
			return new ResponseEntity<Object>("Não há respiradores o suficiente para realizar a troca de recursos", HttpStatus.NOT_ACCEPTABLE);
		}
		if(recursosHospital1.getAmbulancia()>=recursosEnviadosHospital1.getAmbulancia() && recursosHospital2.getAmbulancia()>=recursosEnviadosHospital2.getAmbulancia()) {
			recursosHospital1.setAmbulancia(recursosHospital1.getAmbulancia()-recursosEnviadosHospital1.getAmbulancia()+recursosEnviadosHospital2.getAmbulancia());
			recursosHospital2.setAmbulancia(recursosHospital2.getAmbulancia()-recursosEnviadosHospital2.getAmbulancia()+recursosEnviadosHospital1.getAmbulancia());
		}else {
			return new ResponseEntity<Object>("Não há ambulancias o suficiente para realizar a troca de recursos", HttpStatus.NOT_ACCEPTABLE);
		}
		hospital1.setRecursos(recursosHospital1);
		hospital2.setRecursos(recursosHospital2);
		hospitalRepository.save(hospital1);
		hospitalRepository.save(hospital2);
		TrocaDeRecursosRepository.save(trocaDeRecursos);
		return new ResponseEntity<Object>("Troca de recursos realizada com sucesso!", HttpStatus.OK);
	}
	
	
	public ResponseEntity<Object> getHistoricoDeTrocasDeRecursos() {
		return new ResponseEntity<Object>(TrocaDeRecursosRepository.findAll(), HttpStatus.OK);
	}
	
	
	public ResponseEntity<Object>  report() {
		if (findAll().size()==0) {
			return new ResponseEntity<Object>("Nenhum hospital Cadastrado", HttpStatus.OK);
		}
		ReportDTO report = new ReportDTO();
		report.setSuperLotacao(porcentagemDeHospitaisComSuperlotacao());
		report.setAbaixoSuperLotacao(100 - porcentagemDeHospitaisComSuperlotacao());
		float[] mediaRecursos= mediaDeRecursosPorHospital();
		report.setMediaMedico(mediaRecursos[0]);
		report.setMediaEnfermeiro(mediaRecursos[1]);
		report.setMediaRespirador(mediaRecursos[2]);
		report.setMediaTomografo(mediaRecursos[3]);
		report.setMediaAmbulancia(mediaRecursos[4]);
		String [] aMaisTempo = cituacaoDeLotacaoAMaisTempo();
		report.setSuperLotacaoAMaisTempo(aMaisTempo[0]);
		report.setAbaixoSuperLotacaoAMaisTempo(aMaisTempo[1]);
		
		return new ResponseEntity<Object>(report, HttpStatus.OK);
	}
	
	
	public float porcentagemDeHospitaisComSuperlotacao() {
		List<Hospital> hospitais = findAll();
		int contador = 0; 
		for (Hospital h : hospitais) {
			if (h.getPercentualDeOcupacao()>=90)
				contador++;
		}
		return  (float)((contador*100)/hospitais.size());
	}
	
	
	public float[] mediaDeRecursosPorHospital() {
		List<Hospital> hospitais = findAll();
		int medico=0;
		int enfermeiro=0;
		int respirador=0;
		int tomografo=0;
		int ambulancia=0;
		for (Hospital h : hospitais) {
			medico += h.getRecursos().getAmbulancia();
			enfermeiro += h.getRecursos().getEnfermeiro();
			respirador += h.getRecursos().getRespirador();
			tomografo += h.getRecursos().getTomografo();
			ambulancia += h.getRecursos().getAmbulancia();
		}
		float mediaMedico=(float) medico/hospitais.size();
		float mediaEnfermeiro=(float) enfermeiro/hospitais.size();
		float mediaRespirador=(float)respirador/hospitais.size();
		float mediaTomografo=(float)tomografo/hospitais.size();
		float mediaAmbulancia=(float)ambulancia/hospitais.size();
		
		float[] mediaRecursos= {mediaMedico,mediaEnfermeiro,mediaRespirador,mediaTomografo, mediaAmbulancia};
		return mediaRecursos;
		
	}
	
	
	public String[] cituacaoDeLotacaoAMaisTempo() {
		List<Hospital> hospitais = findAll();
		String superLotado = "";
		String abaixoSuperLotado = ""; 
		for (Hospital h : hospitais) {
			if (h.getPercentualDeOcupacao()>=90) {
				superLotado = "Nome: "+h.getNome()+" - CNPJ: "+h.getCnpj();
				break;
			}
		}
		for (Hospital h : hospitais) {
			if (h.getPercentualDeOcupacao()<90) {
				abaixoSuperLotado = "Nome: "+h.getNome()+" - CNPJ: "+h.getCnpj();
				break;
			}
		}
		String[] retorno = {superLotado,abaixoSuperLotado};
		return retorno;
	}
	

}
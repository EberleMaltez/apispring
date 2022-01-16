package com.example.pessoa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pessoa.dto.PessoaDTO;
import com.example.pessoa.entity.Pessoa;
import com.example.pessoa.service.PessoaService;


@RestController

public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;

	    
	    @PostMapping("/pessoa")
	    public ResponseEntity<Pessoa> salvar(@RequestBody Pessoa pessoa){
	    	try {
	    		return new ResponseEntity<>(pessoaService.salvar(pessoa),
	    				HttpStatus.CREATED);
	    	} catch(Exception e) {
	    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
	    
	    @PutMapping("/pessoa")
	    
	    public ResponseEntity<Pessoa> atualizarPessoa(@RequestBody Pessoa pessoa){
	    	try {
	    		return new ResponseEntity<Pessoa>(pessoaService.salvar(pessoa), HttpStatus.OK);
	    		
	    	}catch(Exception e ) {
	    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
	    
	    @DeleteMapping("/pessoa/{id}")
	    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
	    	try {
	    		Optional<Pessoa> cliente = pessoaService.buscarPorId(id);
	    		if(cliente.isPresent()) {
	    			pessoaService.removerPorId(id);
	    		}
	    		
	    		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	    	} catch(Exception e) {
	    		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR
	    				);
	    	}
	    }
	    
	    
	    @GetMapping("/pessoa")
	    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
	    	try {
	        List<PessoaDTO> pessoas = pessoaService.getListaPessoas();
	        	if(pessoas.isEmpty() || pessoas.size() == 0) {
	        		return new ResponseEntity<List<PessoaDTO>>(HttpStatus.NO_CONTENT);
	        	}
	        		return new ResponseEntity<List<PessoaDTO>>(pessoas, HttpStatus.OK);
	    	}
	    	catch (Exception e){
	    		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    }
	    
	    @GetMapping("/pessoa/{id}")
	    public ResponseEntity<PessoaDTO> getPessoaPorId(@PathVariable Long id){
	    	
	    	PessoaDTO pessoa = pessoaService.buscarId(id);
	    	
	    	if(id > 0) {
	    	
	    		return new ResponseEntity<PessoaDTO>(pessoa, HttpStatus.OK);
	    	}
	 
	    	return new ResponseEntity<PessoaDTO>(HttpStatus.NO_CONTENT);
	    }

}

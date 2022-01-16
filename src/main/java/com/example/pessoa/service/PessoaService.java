package com.example.pessoa.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pessoa.dto.PessoaDTO;
import com.example.pessoa.entity.Pessoa;
import com.example.pessoa.repository.IPessoaRepository;


@Service
public class PessoaService {
	
	   @Autowired
	    private IPessoaRepository pessoaRepository;
	    
		 public Pessoa salvar(Pessoa pessoa){
		        return pessoaRepository.save(pessoa);
		    }

		    public List<Pessoa> listaPessoas(){
		        return pessoaRepository.findAll();
		    }

		    public Optional<Pessoa> buscarPorId(Long id){
		        return pessoaRepository.findById(id);
		    }
		  
           
		    public void removerPorId(Long id){
		    	pessoaRepository.deleteById(id);
		    } 
		    
		    public List<PessoaDTO> getListaPessoas() {
		        return ((List<Pessoa>) pessoaRepository
		                .findAll())
		                .stream()
		                .map(this::convertDTO)
						        .collect(Collectors.toList());
		    }
		   
		    

		    private PessoaDTO convertDTO(Pessoa pessoa) {
		        PessoaDTO pessoaDto = new PessoaDTO();
		        pessoaDto.setNome(pessoa.getNome());
		        pessoaDto.setCidade(pessoa.getCidade());
		        pessoaDto.setTelefone(pessoa.getTelefone());
		        pessoaDto.setIdade(pessoa.getIdade());
		        pessoaDto.setEstado(pessoa.getEstado());
		        
				  if(pessoa.getScore() > 0 && pessoa.getScore() < 201) {
					  pessoaDto.setScoreDescricao("insuficiente");}
				  
				  else if (pessoa.getScore() >= 201 && pessoa.getScore() < 501) {
					  pessoaDto.setScoreDescricao("Inaceitável"); }
				  
				  if(pessoa.getScore() >= 501 && pessoa.getScore() < 701) { 
					  pessoaDto.setScoreDescricao("Aceitável");}
				  
				  else if(pessoa.getScore() >= 701 && pessoa.getScore() < 1001) { 
					  pessoaDto.setScoreDescricao("Recomendável");
				  }
				  
				
				 
				  return pessoaDto;
		  }
		  			  
			  
}

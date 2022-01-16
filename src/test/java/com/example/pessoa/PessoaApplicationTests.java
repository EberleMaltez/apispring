package com.example.pessoa;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.pessoa.entity.Pessoa;
import com.example.pessoa.repository.IPessoaRepository;
import com.example.pessoa.service.PessoaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaApplicationTests {
	
	@Autowired
	private PessoaService service;

	@MockBean
	private IPessoaRepository repository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void getPessoasTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Pessoa(), new Pessoa()).collect(Collectors.toList()));
		assertEquals(2, service.listaPessoas().size());
	}
}

package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;

//Faz a escolha de qualquer outra porta disponivel que não seja a 8080
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//Realiza a instancia de toda classe de teste ou seja tudo o que tem dentro da classe será executado por conta do LifeCycle.PER_CLASS
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	
	//estamos realizando a injestão de dependencia do Spring boot da parte de template test
	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//Antes de tudo realize esse metodo primeiro
	@BeforeAll
	void start() {
		//Deleta todos os dados do banco de dados, ou seja deixa o banco vazio para iniciar os testes.
		usuarioRepository.deleteAll();
		//Deixa um usuário criado no banco (sempre vou ter o id 1 para o usuario, meu banco ficará mais leve/ temos a facilidade de ter um token)
		usuarioService.cadastrarUsuario(new Usuario(0L,"Root", "12345678","root@root.com",""));
	}
	
	@Test
	//posso inserir o que quiser, refere-se a descrição do meu metodo
	@DisplayName("Cadastrar um usuario ")
	public void deveCriarUmUsuario() {
		//httpEntity é uma estrutura do tipo JSON
		//o corpo da requisição é o cadastro que estou criando nesse metodo
		HttpEntity <Usuario> corpoRequisicao = new HttpEntity<Usuario>(
				new Usuario(0L, "Jessica","123456789","jessica@email.com", "campofoto")
				);
		//isso é a resposta toda
		//test Rest realizará os metodos de test
		//
		ResponseEntity<Usuario> corpoResposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);
		
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
	}
				
}


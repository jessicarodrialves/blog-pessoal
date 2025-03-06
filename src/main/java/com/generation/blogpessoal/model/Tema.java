package com.generation.blogpessoal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity // indicação que isso é uma nova tabela no banco de dados ele é quem cria essa tabela
@Table(name = "tb_temas") //nomeia a tabela no banco de dados
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo Descrição é obrigatório")
	private String descricao;
	
	//fetch = Lazy(Preguiçosa) é a forma como ele vai trazer do banco de dados para mim 
	//a forma lazy trás somente o que é necessário ex. nome
	//Eager (Ansiosa) traz tudo ou seja (nome, idade, cpf)
	
	//cascade = como vai se comportar a tabela relacionada ou seja quando eu uso o remove
	//ele apaga todas as postagens relacionada
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "tema",
			cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tema")
	
	//tornamos a postagem uma lista pq podemos receber mais de uma postagem para o mesmo tema
	private List<Postagem> postagem;
	
	public List<Postagem> getPostagem() {
		return postagem;
	}

	public void setPostagem(List<Postagem> postagem) {
		this.postagem = postagem;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}

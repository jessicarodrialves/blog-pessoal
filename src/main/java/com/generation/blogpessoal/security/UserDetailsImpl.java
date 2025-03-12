package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{
	
	//controle de versão para essa classe
	
	//dessa maneira é possivel saber qual a versão,
	//então sempre que for atualizada como alteração da variavel 
	//ou inclusão é considerada uma outra versão
	private static final long serialVersionUID = 1L;

	private String userName;//email
	private String password;//senha
	private List<GrantedAuthority> authorities;//autorizações que o usuario tem
	
	//metodo construtor
	public UserDetailsImpl(Usuario user) {
		this.userName = user.getUsuario();
		this.password = user.getSenha();
	}

	public UserDetailsImpl() {	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return userName;
	}

	//Ajuda a verificar se o acesso ja expirou
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//Auxilia na validação se usuario esta bloqueado
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	//Valida se a credencial expira
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	//Valida se o user está ativo
	@Override
	public boolean isEnabled() {
		return true;
	}

	
	
}

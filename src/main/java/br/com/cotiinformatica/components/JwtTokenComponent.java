package br.com.cotiinformatica.components;

import java.util.Date;

import org.springframework.stereotype.Component;

import br.com.cotiinformatica.entities.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {

	/*
	 * Método para gerar os TOKENS JWT 
	 * para os usuários autenticados
	 */
	public String generateToken(Usuario usuario) {
		
		 //capturar a data atual do sistema
		var dataAtual = new Date();
		
		//gerando o token
		return Jwts.builder()
				.setSubject(usuario.getEmail()) //identificação do usuário
				.setNotBefore(dataAtual) //data de geração do token
				.setExpiration(new Date(dataAtual.getTime() + 900000)) //tempo de expiração em 15min
				.signWith(SignatureAlgorithm.HS256, "51dee78e-9d64-4596-95c3-a242b67a8261") //assinatura do TOKEN
				.compact();
	}
}






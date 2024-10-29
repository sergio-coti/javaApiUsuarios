package br.com.cotiinformatica.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cotiinformatica.components.JwtTokenComponent;
import br.com.cotiinformatica.dtos.AutenticarUsuarioRequestDto;
import br.com.cotiinformatica.dtos.CriarUsuarioRequestDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.CryptoHelper;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired //inicialização automática (injeção de dependência)
	UsuarioRepository usuarioRepository;
	
	@Autowired //inicialização automática (injeção de dependência)
	JwtTokenComponent jwtTokenComponent;
	
	/*
	 * Método para realizar o cadastro do usuário no sistema
	 */
	public String criarUsuario(CriarUsuarioRequestDto dto) {
				
		//criando um objeto da classe usuário
		var usuario = new Usuario();
		
		//capturar os dados do usuário através do DTO
		usuario.setId(UUID.randomUUID());
		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(CryptoHelper.SHA256Encrypt(dto.getSenha()));
		
		//verificar se o email já está cadastrado
		if(usuarioRepository.existsByEmail(usuario.getEmail())) {
			return "O email informado já está cadastrado, tente outro.";
		}
		else {			
			//gravar o usuário no banco de dados
			usuarioRepository.save(usuario);
			
			//retornar resposta
			return "Usuário cadastrado com sucesso.";
		}
	}
	
	/*
	 * Método para realizar a autenticação do usuário
	 */
	public String autenticarUsuario(AutenticarUsuarioRequestDto dto) {
		
		//consultar o usuário no banco de dados através do email e da senha
		var usuario = usuarioRepository.find(dto.getEmail(), CryptoHelper.SHA256Encrypt(dto.getSenha()));
		
		//verificar se o usuário foi encontrado
		if(usuario != null) {			
			//Gerar o TOKEN JWT do usuário
			return jwtTokenComponent.generateToken(usuario);
		}
		else {
			return "Acesso negado. Usuário não encontrado.";
		}		
	}
}













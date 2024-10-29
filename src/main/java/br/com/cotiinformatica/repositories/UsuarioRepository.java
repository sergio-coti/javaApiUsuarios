package br.com.cotiinformatica.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cotiinformatica.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	/*
	 * Método para verificar se o email de usuário
	 * já está cadastrado no banco de dados
	 */
	boolean existsByEmail(String email);
	
	/*
	 * Método para consultar os dados de um usuário
	 * baseado no seu email e senha
	 */
	@Query("FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
	Usuario find(@Param("email") String email, @Param("senha") String senha);
}

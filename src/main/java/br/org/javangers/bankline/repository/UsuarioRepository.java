package br.org.javangers.bankline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.javangers.bankline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}

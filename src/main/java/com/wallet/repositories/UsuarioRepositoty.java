package com.wallet.repositories;

import com.wallet.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositoty extends JpaRepository<Usuario, Integer> {
}

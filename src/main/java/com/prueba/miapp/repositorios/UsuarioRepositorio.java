
package com.prueba.miapp.repositorios;

import com.prueba.miapp.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    
}

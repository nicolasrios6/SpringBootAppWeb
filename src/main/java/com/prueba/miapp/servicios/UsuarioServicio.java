
package com.prueba.miapp.servicios;

import com.prueba.miapp.model.Usuario;
import com.prueba.miapp.repositorios.UsuarioRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    public List<Usuario> obtenerTodos() {
        return usuarioRepositorio.findAll();
    }
    
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
    
    public Usuario encontrarPorId(Long id) {
        return usuarioRepositorio.findById(id).orElse(null);
    }
    
    public void eliminarUsuario(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}

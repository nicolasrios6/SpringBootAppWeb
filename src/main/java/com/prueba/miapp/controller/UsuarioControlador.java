
package com.prueba.miapp.controller;

import com.prueba.miapp.model.Usuario;
import com.prueba.miapp.servicios.UsuarioServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;
    
    @GetMapping
    public String mostrarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioServicio.obtenerTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "formularioUsuario";
    }
    
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
         if (usuario.getId() != null) {
            Usuario usuarioExistente = usuarioServicio.encontrarPorId(usuario.getId());
            if (usuarioExistente != null) {
                usuarioExistente.setNombre(usuario.getNombre());
                usuarioExistente.setCorreo(usuario.getCorreo());
                usuarioServicio.guardarUsuario(usuarioExistente);
            } else {
                usuarioServicio.guardarUsuario(usuario);
            }
        } else {
            usuarioServicio.guardarUsuario(usuario);
        }
        return "redirect:/usuarios";
    }
    
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.encontrarPorId(id);
        model.addAttribute("usuario", usuario);
        return "formularioUsuario";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/detalle/{id}")
    public String mostrarDetalleUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioServicio.encontrarPorId(id);
        model.addAttribute("usuario", usuario);
        return "detalleUsuario";
    }
    
    
//    @GetMapping
//    public List<Usuario> listaUsuarios() {
//        return usuarioServicio.obtenerTodos();
//    }
//    
//    @PostMapping
//    public Usuario crearUsuario(@RequestBody Usuario usuario) {
//        return usuarioServicio.guardarUsuario(usuario);
//    }
//    
//    @GetMapping("/{id}")
//    public Usuario obtenerUsuario(@PathVariable Long id) {
//        return usuarioServicio.encontrarPorId(id);
//    }
//    
//    @DeleteMapping("/{id}")
//    public void eliminarUsuario(@PathVariable Long id) {
//        usuarioServicio.eliminarUsuario(id);
//    }
    
//    @GetMapping("/")
//    public String saludo() {
//        return "index";
//    }
}

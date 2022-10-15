package Alkemy.Disney.services.implementations;

import Alkemy.Disney.models.Usuario;
import Alkemy.Disney.repositories.UsuarioRepository;
import Alkemy.Disney.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicesImp implements UsuarioServices {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario getUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public void saveUser(Usuario usuario){
        usuarioRepository.save(usuario);
    }

}

package Alkemy.Disney.services;

import Alkemy.Disney.models.Usuario;

public interface UsuarioServices {

    Usuario getUsuarioByEmail(String email);

    void saveUser(Usuario usuario);

}

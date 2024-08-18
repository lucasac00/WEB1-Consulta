package org.consulta.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.consulta.dao.IUsuarioDAO;
import org.consulta.domain.Usuario;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
            Usuario usuario = dao.getUserByUsername(username);

            if (usuario == null) {
                throw new UsernameNotFoundException("Could not find user");
            }

            return new UsuarioDetails(usuario);
    }
}
package com.tiendatech.service;

import com.tiendatech.dao.AdministradorDao;
import com.tiendatech.domain.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdministradorDao administradorDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrador admin = administradorDao.findByEmail(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Administrador no encontrado");
        }

        return User.withUsername(admin.getEmail())
                .password(admin.getClave())
                .roles("ADMIN")
                .build();
    }
}

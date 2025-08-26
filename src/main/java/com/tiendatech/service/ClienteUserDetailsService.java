package com.tiendatech.service;

import com.tiendatech.dao.ClienteDao;
import com.tiendatech.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClienteUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteDao clienteDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Cliente c = clienteDao.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente no encontrado"));

        return User.withUsername(c.getEmail())
                .password(c.getClave())
                .roles("CLIENTE")
                .build();
    }
}

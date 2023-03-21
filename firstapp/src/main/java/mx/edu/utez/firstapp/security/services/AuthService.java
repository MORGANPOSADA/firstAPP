package mx.edu.utez.firstapp.security.services;

import mx.edu.utez.firstapp.models.user.User;
import mx.edu.utez.firstapp.security.model.UserAuth;
import mx.edu.utez.firstapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService implements UserDetailsService {
    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = service.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Not found");
        return UserAuth.build(user);
    }
}

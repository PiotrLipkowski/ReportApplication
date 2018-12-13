package reportapplication.demo.Security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reportapplication.demo.Model.User;
import reportapplication.demo.Repository.UserRepository;
import reportapplication.demo.Security.services.UserPrinciple;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Użytkownik nieznaleziony-> nazwa użytkownika lub email : " + username));

        return UserPrinciple.build(user);
    }
}
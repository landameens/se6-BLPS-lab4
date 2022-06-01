package itmo.services;

import itmo.model.User;
import itmo.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = userRepository.findByMail(mail);
        if(user == null) throw new UsernameNotFoundException("User not found");

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("user"));
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), authorities);
    }

    public Long getIdByMail(String mail){
        return userRepository.findByMail(mail).getId();
    }

    public String getMailById(Long id){
        return userRepository.getById(id).getMail();
    }

    public String getNameById(Long id){
        return userRepository.getById(id).getUsername();
    }
}

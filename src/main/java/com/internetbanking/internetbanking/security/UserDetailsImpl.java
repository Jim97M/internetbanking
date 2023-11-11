package main.java.com.internetbanking.internetbanking.security;

import main.java.com.internetbanking.internetbanking.models.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import main.java.com.internetbanking.internetbanking.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {


    private static final long serialVersionUID = 1L;

    @Autowired
     private UserRepository userRepository;


    private User user;

    private Long id;


    private String email;

    @JsonIgnore
    private String password;


    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserDetailsImpl(User user) {
        this.user = user;
    }


    public UserDetailsImpl(Long id,  String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new UserDetailsImpl(user);
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

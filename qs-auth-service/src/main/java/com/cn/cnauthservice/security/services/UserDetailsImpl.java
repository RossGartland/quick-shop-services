package com.cn.cnauthservice.security.services;

import com.cn.cnauthservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
/**
 * Contains modelling attributes user details entity.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String forename;
    private String surname;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities; //Stores the users permissions.

    /**
     * Constructor for user details implementation.
     * @param id
     * @param username
     * @param forename
     * @param surname
     * @param email
     * @param password
     * @param authorities
     */
    public UserDetailsImpl(int id, String username, String forename, String surname, String email, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Builds user details object.
     * @param user
     * @return
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList()); //Creates role list for user.
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getForename(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                authorities);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getForename() {
        return forename;
    }
    public String getSurname() {
        return surname;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
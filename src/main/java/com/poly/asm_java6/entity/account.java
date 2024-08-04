package com.poly.asm_java6.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class account implements UserDetails {
    @Id
    String userID;
    String lastName;
    String firstName;
    String email;
    String password;
    String phoneNumber;
    @Column(length = 512)
    String avatar;
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    List<orders> orders;
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    List<address> address;
    @OneToMany(mappedBy = "account")
    @JsonManagedReference
    List<authorities> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getRole().getRoleName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return userID;
    }
}

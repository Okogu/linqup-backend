package com.example.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Account") //maps account class to a table.
//good practice is to add default name that is normally the java class name
@Table(
        name = "account",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "account_email_unique",
                        columnNames = "email")
                //defines the uniqueness of the email property as well as set  the unique id
        })
//name of table is same as class but in lowercase

public class Account implements UserDetails {

    @Id
    @SequenceGenerator(name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1)
    ///allocation size : how much will the sequence increase from: default is 1
    @GeneratedValue(strategy = SEQUENCE,
            generator = "account_sequence") //Makes id backed by a sequence same as the big serial data type

    @Column(name = "id",
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(name = "full_name",
            nullable = false,
            columnDefinition = "TEXT"
    )    private  String fullName;

    @Column(name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private  String email;

    @Column(name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    @JsonIgnore
    private  String password;

    @OneToMany(targetEntity = Profile.class, cascade = CascadeType.ALL, mappedBy = "id" )

    private List<Profile> profiles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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

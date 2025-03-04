package com.zion.api.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "users")

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class User {

    @Id
    @GeneratedValue
    private UUID id; //Id do Usuario

    private String name; //Name do usuario
    private String email; //Email do usuario
    private String password; //Senha do usuario


}

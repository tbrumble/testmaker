package ru.tbrumble.testmaker.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "users")
@Getter
@Setter
@Accessors(chain = true)
public class UserEntity {
    public UserEntity() {};

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERLOGIN")
    private String userLogin;

    @Column(name = "ISBLOCKED")
    private boolean isBlocked;

    @Column(name = "USERPASSWORD")
    private String userPassword;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "MIDDLENAME")
    private String middleName;
}

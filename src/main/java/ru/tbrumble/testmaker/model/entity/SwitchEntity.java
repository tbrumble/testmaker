package ru.tbrumble.testmaker.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "switch", schema = "users")
@Getter
@Setter
@Accessors(chain = true)
public class SwitchEntity {
    public SwitchEntity() {};

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SWITCHNAME")
    private String switchName;

    @Column(name = "VALUE")
    private boolean value;
}

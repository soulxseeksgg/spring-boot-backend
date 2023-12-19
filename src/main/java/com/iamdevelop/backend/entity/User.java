package com.iamdevelop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tb_user")
public class User extends BaseEntity {

    @Column(length = 120, nullable = false)
    private String email;

    @Column(length = 120, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Address> address;

}

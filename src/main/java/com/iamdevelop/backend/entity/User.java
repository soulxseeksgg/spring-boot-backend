package com.iamdevelop.backend.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tb_user")
public class User extends BaseEntity implements Serializable {

    @Column(length = 120, nullable = false, unique = true)
    private String email;

    @Column(length = 120, nullable = false, unique = true)
    private String userName;

    @Column(length = 120, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Address> address;

}

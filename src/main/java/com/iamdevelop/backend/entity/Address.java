package com.iamdevelop.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "tb_address")
public class Address extends BaseEntity{

    @Column(length = 120)
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

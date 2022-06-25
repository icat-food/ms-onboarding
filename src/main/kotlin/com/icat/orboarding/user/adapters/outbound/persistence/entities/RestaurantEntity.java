package com.icat.orboarding.user.adapters.outbound.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    private String id;
    private String name;
    private String cnpj;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}

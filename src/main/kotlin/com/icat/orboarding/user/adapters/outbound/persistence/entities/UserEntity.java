package com.icat.orboarding.user.adapters.outbound.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    private String id;
    private String email;
    private String password;

    @OneToOne(mappedBy = "userEntity")
    private ConsumerEntity consumerEntity;

    @OneToOne(mappedBy = "userEntity")
    private RestaurantEntity restaurantEntity;

    @OneToOne(mappedBy = "userEntity")
    private DeliveryEntity deliveryEntity;
}

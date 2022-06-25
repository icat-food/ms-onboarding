package com.icat.orboarding.user.adapters.outbound.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "consumer")
public class ConsumerEntity {
    @Id
    private String id;
    private String fullName;
    private String cpf;
    private String imageUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity userEntity;
}

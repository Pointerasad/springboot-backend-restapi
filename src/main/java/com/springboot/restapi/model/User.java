package com.springboot.restapi.model;


import lombok.*;

import javax.persistence.*;

/**
 * Created by User on 3/22/2022.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country" )
    private String country;

    @OneToOne(fetch= FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private UserProgress  userProgress;


}

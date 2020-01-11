package org.fasttrackit.secondhandOnlineshop.domain;


import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produc_id")
    private Product product;
}

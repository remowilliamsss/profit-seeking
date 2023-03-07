package ru.egorov.ProfitSeeking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private Double price;

    @NotNull
    private String priceCurrency;

    private String size;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StoreType storeName;

    @NotEmpty
    @Column(unique = true)
    private String url;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "sku", referencedColumnName = "sku")
    private Item item;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Offer offer = (Offer) o;

        return url.equals(offer.url);
    }

    @Override
    public int hashCode() {
        return url.hashCode();
    }
}

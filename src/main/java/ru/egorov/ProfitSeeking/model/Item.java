package ru.egorov.ProfitSeeking.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    @Column(unique = true)
    private String sku;

    private String category;

    private String brand;

    private String image;

    private String color;

    private String country;

    private String gender;

    private String composition;

    private String coloring;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers;

    {
        offers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        return sku.equals(item.sku);
    }

    @Override
    public int hashCode() {
        return sku.hashCode();
    }
}

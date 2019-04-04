package pl.sa.serwisaukcyjny.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String title;
    @NotNull
    @Type(type = "text")
    private String description;
//    @NotNull
//    private String product_category;
    @NotNull
    private String location;
    private LocalDateTime date_added=LocalDateTime.now();
    private LocalDateTime end_added;

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public Product(@NotNull String title, @NotNull String description, @NotNull String location, LocalDateTime end_added, Category category) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.end_added = end_added;
        this.category = category;
    }

    public Product() {
    }
}

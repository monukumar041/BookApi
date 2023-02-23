package com.Rest.API.Entitiy;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String title;
    @OneToOne(cascade = CascadeType.ALL)
    public Author author;

}

package com.vmware.bookmanagement.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @NotBlank(message = "Name can't be empty")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "Author can't be empty")
    @Column(name = "author")
    private String author;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;
}

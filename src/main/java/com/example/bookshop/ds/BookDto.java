package com.example.bookshop.ds;

import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Category;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BookDto {
    private int id;
    private String title;
    private LocalDate publishedYear;
    private String publisher;
    private int price;
    private int quantity;
    private String genre;
    private String imageUrl;
    private String description;
    private Category category;
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto bookDto)) return false;
        return id == bookDto.id && title.equals(bookDto.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}

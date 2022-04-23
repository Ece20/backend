package finki.emt.lab2.model;

import finki.emt.lab2.model.enumaration.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Long authorId;

    private Integer availableCopies;

    public BookDto(String name, Category category, Long authorId, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
    }
}
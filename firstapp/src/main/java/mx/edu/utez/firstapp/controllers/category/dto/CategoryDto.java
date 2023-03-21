package mx.edu.utez.firstapp.controllers.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.firstapp.models.category.Category;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Long id;
    @NotNull
    @NotBlank
    @Length(min = 1, max = 150)
    private String name;
    private Boolean status;

    public Category castToCategory() {
        return new Category(
                getId(),
                getName(),
                getStatus(),
                null
        );
    }
}

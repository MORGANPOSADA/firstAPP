package mx.edu.utez.firstapp.controllers.category;

import mx.edu.utez.firstapp.controllers.category.dto.CategoryDto;
import mx.edu.utez.firstapp.models.category.Category;
import mx.edu.utez.firstapp.services.category.CategoryService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-market/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Category>>> getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Category>> insert(
            @Valid @RequestBody CategoryDto category,
            @Valid BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.insert(category.castToCategory()),
                HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<CustomResponse<Category>> update(
            @Valid @RequestBody CategoryDto category,
            BindingResult result
    ) {
        return new ResponseEntity<>(
                this.service.update(category.castToCategory()),
                HttpStatus.CREATED);
    }

    @PatchMapping("/")
    public ResponseEntity<CustomResponse<Boolean>> enableOrDisable(
            @RequestBody CategoryDto category
    ) {
        return new ResponseEntity<>(
                this.service.changeStatus(category.castToCategory()),
                HttpStatus.OK
        );
    }
}

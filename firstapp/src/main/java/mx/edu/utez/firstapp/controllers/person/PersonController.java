package mx.edu.utez.firstapp.controllers.person;

import mx.edu.utez.firstapp.controllers.person.dtos.PersonDto;
import mx.edu.utez.firstapp.models.person.Person;
import mx.edu.utez.firstapp.services.person.PersonService;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api-market/person")
@CrossOrigin(origins = {"*"})
public class PersonController {
    @Autowired
    private PersonService service;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Person>>>
    getAll() {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Person>> insert(
            @Valid @RequestBody PersonDto person
    ) {
        return new ResponseEntity<>(
                this.service.insert(person.getPerson()),
                HttpStatus.CREATED
        );
    }
}

package mx.edu.utez.firstapp.controllers.contact.dto;

import mx.edu.utez.firstapp.utils.CustomResponse;
import mx.edu.utez.firstapp.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api-market/contact")
@CrossOrigin(origins = ("*"))
public class ContactController {

@Autowired
   private EmailService service;

@PostMapping("/")

    public ResponseEntity<CustomResponse<Object>> sendMailContact(
            @Valid @RequestBody EmailDto email
){
    if (this.service.senEmail(email))
        return new ResponseEntity<>(
                new CustomResponse<>(null, false, 200, "OK"),
                HttpStatus.OK
        );
    return new ResponseEntity<>(
            new CustomResponse<>(null, true, 400, "BAD REQUEST"),
            HttpStatus.BAD_REQUEST
    );
}

}

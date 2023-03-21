package mx.edu.utez.firstapp.models.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository
        extends JpaRepository<Person, Long> {
    boolean findById(long id);
    Optional<Person> findByCurp(String curp);
    List<Person> findAllByGender(String gender);
}








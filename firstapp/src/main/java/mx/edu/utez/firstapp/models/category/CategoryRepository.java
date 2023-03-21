package mx.edu.utez.firstapp.models.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository
    extends JpaRepository<Category, Long> {
    @Query(
            value = "UPDATE categories SET status = :status " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    @Modifying
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    boolean existsByName(String name);

}

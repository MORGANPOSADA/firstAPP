package mx.edu.utez.firstapp.models.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    boolean existsByName(String name);
    @Query(
            value = "UPDATE subcategories SET status = :status " +
                    "WHERE id = :id",
            nativeQuery = true
    )
    @Modifying
    int updateStatusById(
            @Param("status") Boolean status,
            @Param("id") Long id
    );

    boolean existsByNameAndIdNot(String name, Long id);
}

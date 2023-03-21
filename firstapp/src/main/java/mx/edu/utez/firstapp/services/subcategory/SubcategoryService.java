package mx.edu.utez.firstapp.services.subcategory;

import mx.edu.utez.firstapp.models.subcategory.Subcategory;
import mx.edu.utez.firstapp.models.subcategory.SubcategoryRepository;
import mx.edu.utez.firstapp.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
@Transactional
public class SubcategoryService {
    @Autowired
    private SubcategoryRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<Object> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Object> getOne(Long id) {
        if (this.repository.existsById(id))
            return new CustomResponse<>(this.repository.findById(id).get(), false, 200, "OK");
        return new CustomResponse<>(null, true, 400, "Not found");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Object> save(Subcategory subcategory) {
        if (this.repository.existsByName(subcategory.getName()))
            return new CustomResponse<>(null, true, 400, "Subcategoría ya registrada");
        return new CustomResponse<>(this.repository.saveAndFlush(subcategory), false, 200, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Object> update(Subcategory subcategory) {
        if (!this.repository.existsById(subcategory.getId()))
            return new CustomResponse<>(null, true, 400, "Subcategoría ya registrada");
        return new CustomResponse<>(this.repository.saveAndFlush(subcategory), false, 200, "Subcategoría actualizada");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Object> changeStatus(Subcategory subcategory) {
        if (!this.repository.existsById(subcategory.getId()))
            return new CustomResponse<>(null, true, 400, "Subcategoría ya registrada");
        if (this.repository.existsByNameAndIdNot(subcategory.getName(), subcategory.getId()))
            return new CustomResponse<>(null, true, 400, "Subcategoría ya registrada");
        return new CustomResponse<>(this.repository.saveAndFlush(subcategory), false, 200, "OK");
    }
}

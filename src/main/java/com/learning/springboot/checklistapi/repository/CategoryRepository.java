package com.learning.springboot.checklistapi.repository;

import com.learning.springboot.checklistapi.entity.CategoryEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> findByGuid(String guid);

}

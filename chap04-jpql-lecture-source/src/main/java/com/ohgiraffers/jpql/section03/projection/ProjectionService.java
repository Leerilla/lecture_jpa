package com.ohgiraffers.jpql.section03.projection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectionService {

    private ProjectionRepository projectionRepository;

    @Autowired
    public ProjectionService(ProjectionRepository projectionRepository) {
        this.projectionRepository = projectionRepository;
    }

    public List<Menu> findAllMenusWithEntityProjection() {

        return projectionRepository.findAllMenusWithEntityProjection();
    }

    public List<CategoryName> findAllCategoriesWithEmbeddedTypeProjection() {

        return projectionRepository.findAllCategoriesWithEmbeddedTypeProjection();
    }
}

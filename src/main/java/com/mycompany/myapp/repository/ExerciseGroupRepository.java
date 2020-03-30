package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ExerciseGroup;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ExerciseGroup entity.
 */
@Repository
public interface ExerciseGroupRepository extends JpaRepository<ExerciseGroup, Long> {

    @Query(value = "select distinct exerciseGroup from ExerciseGroup exerciseGroup left join fetch exerciseGroup.keywords",
        countQuery = "select count(distinct exerciseGroup) from ExerciseGroup exerciseGroup")
    Page<ExerciseGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct exerciseGroup from ExerciseGroup exerciseGroup left join fetch exerciseGroup.keywords")
    List<ExerciseGroup> findAllWithEagerRelationships();

    @Query("select exerciseGroup from ExerciseGroup exerciseGroup left join fetch exerciseGroup.keywords where exerciseGroup.id =:id")
    Optional<ExerciseGroup> findOneWithEagerRelationships(@Param("id") Long id);
}

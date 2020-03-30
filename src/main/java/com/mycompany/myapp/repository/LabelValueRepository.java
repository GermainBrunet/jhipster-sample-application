package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LabelValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LabelValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LabelValueRepository extends JpaRepository<LabelValue, Long> {
}

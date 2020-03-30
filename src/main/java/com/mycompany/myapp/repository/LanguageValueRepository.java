package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.LanguageValue;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LanguageValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LanguageValueRepository extends JpaRepository<LanguageValue, Long> {
}

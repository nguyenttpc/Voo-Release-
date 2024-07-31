package com.VooTreeVeeVuu.domain.repository;

import com.VooTreeVeeVuu.domain.entity.AccommodationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType,Long> {
//    List<AccommodationType> findAll ();
//
//    Optional<AccommodationType> findById (Long id);
//
//    AccommodationType save (AccommodationType accommodationType);
//
//    void deleteById (Long id);

}
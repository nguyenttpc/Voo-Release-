package com.VooTreeVeeVuu.usecase.FacilityUsecase.DeleteFacility;


import com.VooTreeVeeVuu.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteFacilityImpl implements DeleteFacility{
    @Autowired
    private FacilityRepository facilityRepository;

    @Transactional
    public void deleteFacility(Long id){facilityRepository.deleteById(id);
    }
}

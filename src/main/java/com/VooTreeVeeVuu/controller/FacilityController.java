package com.VooTreeVeeVuu.controller;


import com.VooTreeVeeVuu.dto.FacilityDTO;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.CreateFacility.CreateFacilityImpl;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.DeleteFacility.DeleteFacilityImpl;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.GetAllFacility.GetAllFacilityImpl;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.GetFacility.GetFacilityImpl;
import com.VooTreeVeeVuu.usecase.FacilityUsecase.UpdateFacility.UpdateFacilityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/facilities")
public class FacilityController {
    @Autowired
    private CreateFacilityImpl createFacilityUseCase;

    @Autowired
    private UpdateFacilityImpl updateFacilityUseCase;

    @Autowired
    private DeleteFacilityImpl deleteFacilityUseCase;

    @Autowired
    private GetAllFacilityImpl getAllFacilityUseCase;

    @Autowired
    private GetFacilityImpl getFacilityUseCase;

    @GetMapping()
    public List<FacilityDTO> getAllFacility(){
        return getAllFacilityUseCase.getAllFacility();
    }

    @GetMapping ("/{id}")
    public Optional<FacilityDTO> getFacilityById (@PathVariable Long id){
        return getFacilityUseCase.getFacilityById(id);
    }

    @PostMapping
    public FacilityDTO createFacility (@RequestBody FacilityDTO dto) {
        return createFacilityUseCase.createFacility(dto);
    }

    @PutMapping ("/update/{id}")
    public Optional<FacilityDTO> updateFacility (@RequestBody FacilityDTO dto, @PathVariable Long id) {
        return updateFacilityUseCase.updateFacility(id,dto);
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteFacility (@PathVariable Long id) {
        deleteFacilityUseCase.deleteFacility(id);
    }
}

package com.VooTreeVeeVuu.controller;
import com.VooTreeVeeVuu.dto.AccommodationTypeDTO;
import com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.CreateAccommodationType.CreateAccommodationTypeImpl;
import com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.DeleteAccommodationType.DeleteAccommodationTypeImpl;
import com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.GetAccommodationType.GetAccommodationTypeImpl;
import com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.GetAllAccommodationType.GetAllAccommodationTypeImpl;
import com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.UpdateAccommodationType.UpdateAccommodationTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/accommodationTypes")
public class AccommodationTypeController {
    @Autowired
    private CreateAccommodationTypeImpl createAccommodationTypeUsecase;

     @Autowired
    private UpdateAccommodationTypeImpl updateAccommodationTypeUsecase;

     @Autowired
    private DeleteAccommodationTypeImpl deleteAccommodationTypeUsecase;

     @Autowired
    private GetAllAccommodationTypeImpl getAllAccommodationTypeUsecase;

     @Autowired
    private GetAccommodationTypeImpl getAccommodationTypeUsecase;

     @GetMapping()
    public List<AccommodationTypeDTO> getAllAccommodationTypeDTOS(){
         return getAllAccommodationTypeUsecase.getAllAccommodationTypeDTO();
     }

    @GetMapping ("/{id}")
    public Optional<AccommodationTypeDTO> getAccommodationType (@PathVariable Long id){
         return getAccommodationTypeUsecase.getAccommodationTypeById(id);
    }

    @PostMapping
    public AccommodationTypeDTO createTypeDTO (@RequestBody AccommodationTypeDTO dto) {
        return createAccommodationTypeUsecase.createAccommodationType(dto);
    }

    @PutMapping ("/update/{id}")
    public Optional<AccommodationTypeDTO> updateTypeDTO (@RequestBody AccommodationTypeDTO dto, @PathVariable Long id) {
        return updateAccommodationTypeUsecase.updateAccommodationTypeDTO(id,dto);
    }

    @DeleteMapping ("/delete/{id}")
    public void deleteTypeDTO (@PathVariable Long id) {
        deleteAccommodationTypeUsecase.deleteAccommodationType(id);
    }

}

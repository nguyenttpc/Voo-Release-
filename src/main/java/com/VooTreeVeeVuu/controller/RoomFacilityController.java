package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.DeleteRoomFacility.DeleteRoomFacilityImpl;
import com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.GetAllRoomFacility.GetAllRoomFacilityImpl;
import com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.GetRoomFacility.GetRoomFacilityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/roomFacilities")
public class RoomFacilityController {
	@Autowired
	private DeleteRoomFacilityImpl deleteRoomFacilityUseCase;

	@Autowired
	private GetAllRoomFacilityImpl getAllRoomFacilityUseCase;

	@Autowired
	private GetRoomFacilityImpl getRoomFacilityUseCase;

//	@GetMapping ()
//	public List<RoomFacilityDTO> getAllRoomFacility(){
//		return getAllRoomFacilityUseCase.getAllRoomFacility();
//	}
//
//	@GetMapping ("/{id}")
//	public Optional<RoomFacilityDTO> getRoomFacilityById (@PathVariable Long id){
//		return getRoomFacilityUseCase.getRoomFacilityById(id);
//	}

//	@PostMapping
//	public RoomFacilityDTO createRoomFacility(@RequestBody RoomFacilityDTO dto) {
//		return createRoomFacilityUseCase.createRoomFacility(dto);
//	}

//	@PutMapping ("/update/{id}")
//	public Optional<RoomFacilityDTO> updateRoomFacility (@RequestBody RoomFacilityDTO dto, @PathVariable Long id) {
//		return updateRoomFacilityUseCase.updateRoomFacility(id,dto);
//	}

	@DeleteMapping ("/delete/{id}")
	public void deleteRoomFacility(@PathVariable Long id) {
		deleteRoomFacilityUseCase.deleteRoomFacility(id);
	}

}

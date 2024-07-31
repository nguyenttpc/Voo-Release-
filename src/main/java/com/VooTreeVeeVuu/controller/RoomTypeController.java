package com.VooTreeVeeVuu.controller;

import com.VooTreeVeeVuu.dto.RoomTypeDTO;
import com.VooTreeVeeVuu.usecase.RoomTypeUsecase.CreateRoomType.CreateRoomTypeImpl;
import com.VooTreeVeeVuu.usecase.RoomTypeUsecase.DeleteRoomType.DeleteRoomTypeImpl;
import com.VooTreeVeeVuu.usecase.RoomTypeUsecase.GetAllRoomType.GetAllRoomTypeImpl;
import com.VooTreeVeeVuu.usecase.RoomTypeUsecase.GetRoomType.GetRoomTypeImpl;
import com.VooTreeVeeVuu.usecase.RoomTypeUsecase.UpdateRoomType.UpdateRoomTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController {
	@Autowired
	private CreateRoomTypeImpl createRoomTypeUseCase;

	@Autowired
	private UpdateRoomTypeImpl updateRoomTypeUseCase;

	@Autowired
	private DeleteRoomTypeImpl deleteRoomTypeUseCase;

	@Autowired
	private GetAllRoomTypeImpl getAllRoomTypeUseCase;

	@Autowired
	private GetRoomTypeImpl getRoomTypeUseCase;

	@GetMapping ()
	public List<RoomTypeDTO> getAllRoomTypes(){
		return getAllRoomTypeUseCase.getAllRoomTypes();
	}

	@GetMapping ("/{id}")
	public Optional<RoomTypeDTO> getRoomTypeById (@PathVariable Long id){
		return getRoomTypeUseCase.getRoomTypeById(id);
	}

	@PostMapping
	public RoomTypeDTO createRoomType(@RequestBody RoomTypeDTO dto) {
		return createRoomTypeUseCase.createRoomType(dto);
	}

	@PutMapping ("/update/{id}")
	public Optional<RoomTypeDTO> updateRoomType (@RequestBody RoomTypeDTO dto, @PathVariable Long id) {
		return updateRoomTypeUseCase.updateRoomType(id,dto);
	}

	@DeleteMapping ("/delete/{id}")
	public void deleteRoomType(@PathVariable Long id) {
		deleteRoomTypeUseCase.deleteRoomType(id);
	}



}

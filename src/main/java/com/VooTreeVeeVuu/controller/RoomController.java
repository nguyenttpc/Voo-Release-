package com.VooTreeVeeVuu.controller;


import com.VooTreeVeeVuu.dto.GetAllRoomDTO;
import com.VooTreeVeeVuu.dto.RoomDTO;
import com.VooTreeVeeVuu.services.RoomService;
import com.VooTreeVeeVuu.usecase.RoomUsecase.CreateRoom.CreateRoom;
import com.VooTreeVeeVuu.usecase.RoomUsecase.DeleteRoom.DeleteRoomImpl;
import com.VooTreeVeeVuu.usecase.RoomUsecase.GetAllRoom.GetAllRoomImpl;
import com.VooTreeVeeVuu.usecase.RoomUsecase.GetRoom.GetRoomImpl;
import com.VooTreeVeeVuu.usecase.RoomUsecase.UpdateRoomStatus.UpdateRoomStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    private DeleteRoomImpl deleteRoomUseCase;

    @Autowired
    private GetAllRoomImpl getAllRoomUseCase;

    @Autowired
    private GetRoomImpl getRoomUseCase;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CreateRoom createRoom;

    @Autowired
    private UpdateRoomStatus updateRoomStatus;

    @GetMapping()
    public List<GetAllRoomDTO> getAllRoom() {
        return getAllRoomUseCase.getAllRoom();
    }

    @GetMapping("/{id}")
    public Optional<GetAllRoomDTO> getRoomById(@PathVariable Long id) {
        return Optional.ofNullable(getRoomUseCase.getRoomById(id));
    }

    @PostMapping("/partner/create/{hotelId}")
    public ResponseEntity<RoomDTO> createRoom(@PathVariable("hotelId") Long hotelId, @RequestBody RoomDTO roomDTO) {
        RoomDTO createdRoom = createRoom.createRoom(hotelId, roomDTO);
        return new ResponseEntity<>(createdRoom, HttpStatus.CREATED);
    }

    @PutMapping("/partner/update/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO
                                              //        , @RequestPart("files") List<MultipartFile> files
    ) throws IOException {
        RoomDTO updatedRoom = roomService.updateRooms(id, roomDTO);
        return ResponseEntity.ok(updatedRoom);
    }

    @PutMapping("/partner/update/{roomId}/images")
    public ResponseEntity<GetAllRoomDTO> updateRoomImages(
            @PathVariable Long roomId,
            @RequestParam("files") List<MultipartFile> files) throws IOException {

        GetAllRoomDTO updatedRoom = roomService.updateRoomImages(roomId, files);
        return ResponseEntity.ok(updatedRoom);
    }

    @PutMapping("/staff/update/{roomId}")
    public Optional<GetAllRoomDTO> updateRoomStatus(@PathVariable("roomId") Long roomId, @RequestBody GetAllRoomDTO dto) {
        return updateRoomStatus.updateRoomStatus(roomId, dto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable Long id) {
        deleteRoomUseCase.deleteRoom(id);
    }

    @PostMapping("/{roomId}/images")
    public ResponseEntity<GetAllRoomDTO> uploadImages(@PathVariable Long roomId,
                                                      @RequestParam("files") List<MultipartFile> files) {
        try {
            GetAllRoomDTO updatedRoom = roomService.saveRoomImages(roomId, files);
            return ResponseEntity.ok(updatedRoom);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}

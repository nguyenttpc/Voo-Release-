package com.VooTreeVeeVuu.usecase.RoomFacilityUsecase.DeleteRoomFacility;


import com.VooTreeVeeVuu.domain.repository.RoomFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteRoomFacilityImpl implements DeleteRoomFacility{
	@Autowired
	private RoomFacilityRepository roomFacilityRepository;

	@Transactional
	public void deleteRoomFacility(Long id){roomFacilityRepository.deleteById(id);
	}
}

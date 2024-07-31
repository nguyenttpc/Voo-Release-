package com.VooTreeVeeVuu.usecase.AccommodationTypeUsecase.DeleteAccommodationType;

import com.VooTreeVeeVuu.domain.repository.AccommodationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteAccommodationTypeImpl implements DeleteAccommodationType {
	@Autowired
	private AccommodationTypeRepository accommodationTypeRepository;

	@Transactional
	public void deleteAccommodationType (Long id) {
		accommodationTypeRepository.deleteById(id);
	}
}

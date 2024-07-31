package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationTypeDTO {
    private Long id;
    private String typeName;
    private Hotel hotels;
}

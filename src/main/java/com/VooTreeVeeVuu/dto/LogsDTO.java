package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.entity.Hotel;

import com.VooTreeVeeVuu.domain.entity.User;
import com.VooTreeVeeVuu.domain.utils.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogsDTO {
    private Long id;
    private Action action;
    private LocalDate date;
    private Hotel hotel;
    private User user;
}

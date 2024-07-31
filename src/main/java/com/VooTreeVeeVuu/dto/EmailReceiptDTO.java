package com.VooTreeVeeVuu.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailReceiptDTO {
    private String hotelName;
    private String roomType;
    private String address;
    private String cusEmail;
    private String checkInDate;
    private String hotelPhoneNum;
    private String checkOutDate;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Integer num_of_rooms;
    private Integer num_of_guests;
    private Long totalPrice;
    private String cusFullName;
    private String cusPhoneNum;
    private String ownerEmail;

}

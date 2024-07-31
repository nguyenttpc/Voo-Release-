package com.VooTreeVeeVuu.dto;

import com.VooTreeVeeVuu.domain.utils.Booking_status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalPrice;
    private String userName; // Tên người đặt, tách biệt để dễ dàng hiển thị
    private String hotelName; // Tên khách sạn
    private String roomType; // Loại phòng
    private String address; // Địa chỉ khách sạn
    private String city;
    private Long hotelId;
    private Long hotelOwnerId;
    private Booking_status status;
    private boolean reviewStatus;
    private Integer numOfRoom;
    private Integer numOfGuest;
    private LocalDate bookingDate;


    public BookingDTO(Long id, LocalDate checkInDate, LocalDate checkOutDate, double totalPrice, String userName, String hotelName, String roomType, String address, String city, Long hotelId, Booking_status status, Integer numOfRoom, Integer numOfGuest, LocalDate bookingDate) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.userName = userName;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.address = address;
        this.city = city;
        this.hotelId = hotelId;
        this.status = status;
        this.numOfRoom = numOfRoom;
        this.numOfGuest = numOfGuest;
        this.bookingDate = bookingDate;
    }
}

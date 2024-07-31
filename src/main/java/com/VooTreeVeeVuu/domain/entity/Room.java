package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Room_status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotNull
    @Min(1)
    @Max(30)
    private int capacity;

    //@NotNull
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double price;

    //@NotNull
    @Min(1)
    private Integer quantity;

    //@NotNull
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private double roomSize;

    //@NotBlank
//    @Column(columnDefinition = "nvarchar(255)", name = "description")
//    private String description;

    @Column(name = "serveBreakfast")
    private boolean serveBreakfast;

    //@JsonIgnored
    @ManyToOne
    @JoinColumn(name = "typeId")
    RoomType roomType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;


    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RoomFacility> roomFacilities;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    List<Booking> listBooking;

    //@JsonIgnored
    @OneToMany(mappedBy = "room")
    List<RoomImage> room_images;

    @Enumerated(EnumType.STRING)
    private Room_status status;

    @Enumerated(EnumType.STRING)
    private Edit_status edit_status;


    public Room(Long id, int capacity, double price, Integer quantity, double roomSize/*, String description*/, boolean serveBreakfast, RoomType roomType, List<RoomFacility> roomFacilities, Room_status status, List<RoomImage> room_images) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.quantity = quantity;
        this.roomSize = roomSize;
//        this.description = description;
        this.serveBreakfast = serveBreakfast;
        this.roomType = roomType;
        this.roomFacilities = roomFacilities;
        this.status = status;
        this.room_images = room_images;
    }
}

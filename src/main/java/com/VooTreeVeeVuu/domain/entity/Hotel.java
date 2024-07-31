package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.domain.utils.Edit_status;
import com.VooTreeVeeVuu.domain.utils.Hotel_status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@NotBlank
    @Column(columnDefinition = "nvarchar(255)", name = "address")
    private String address;

    //@NotBlank
    @Column(columnDefinition = "nvarchar(255)", name = "hotelName")
    private String hotelName;

    //@NotBlank
    @Column(columnDefinition = "nvarchar(50)", name = "city")
    private String city;

    //@NotBlank
    @Size(min = 3, max = 10)
    @Column(name = "hotelPhoneNum")
    private String hotelPhoneNum;

    @NotNull
    @Min(1)
    @Max(5)
    private int hotelStars;

    //@NotBlank
    @Column(columnDefinition = "nvarchar(255)", name = "description")
    private String hotelDescription;

    @Enumerated(EnumType.STRING)
    private Hotel_status status;

    @Enumerated(EnumType.STRING)
    private Edit_status edit_status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Temporal(TemporalType.TIME)
    @Column(name = "checkInTime")
    private LocalTime checkInTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @Temporal(TemporalType.TIME)
    @Column(name = "checkOutTime")
    private LocalTime checkOutTime;

    //@JsonIgnored
    @ManyToOne
    @JoinColumn(name = "typeId")
    AccommodationType accommodationType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    //@JsonIgnored
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<HotelImage> hotelImages;

    //@JsonIgnore
    @OneToMany(mappedBy = ("hotel"))
    List<Room> rooms;

    //@JsonIgnore
    @OneToMany(mappedBy = ("hotel"))
    List<HotelFacility> hotelFacilities;

    //@JsonIgnore
    @OneToMany(mappedBy = "hotel")
    List<Rating> listRating;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel")
    List<Logs> logs;


    public Hotel(Long hotelId) {
        this.id = hotelId;
    }


    public Hotel(Long id, String address, String hotelName, String city, String hotelPhoneNum, int hotelStars, String hotelDescription, Hotel_status status, LocalTime checkInTime, LocalTime checkOutTime, AccommodationType accommodationType) {
        this.id = id;
        this.address = address;
        this.hotelName = hotelName;
        this.city = city;
        this.hotelPhoneNum = hotelPhoneNum;
        this.hotelStars = hotelStars;
        this.hotelDescription = hotelDescription;
        this.status = status;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.accommodationType = accommodationType;
    }
}

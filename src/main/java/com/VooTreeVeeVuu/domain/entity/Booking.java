package com.VooTreeVeeVuu.domain.entity;

import com.VooTreeVeeVuu.domain.utils.Booking_status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column(name = "checkInDate")
    private LocalDate checkInDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column(name = "checkOutDate")
    private LocalDate checkOutDate;

    @NotNull
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private double totalPrice;

    //@JsonIgnored
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    //@JsonIgnored
    //  @NotBlank
    @ManyToOne
    @JoinColumn(name = "roomId")
    Room room;

    @Enumerated(EnumType.STRING)
    private Booking_status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate bookingDate;

    @NotNull
    @Min(1)
    private Integer numOfRoom;

    @NotNull
    @Min(1)
    private Integer numOfGuest;

    @JsonIgnore
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<Payment> payments;

    @OneToOne(mappedBy = "booking")
    private Rating rating;

    private boolean reviewStatus;
}

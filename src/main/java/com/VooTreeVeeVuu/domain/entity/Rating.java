package com.VooTreeVeeVuu.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Min(1)
    @Max(10)
    private int rate;

    @NotBlank
    @Column(columnDefinition = "nvarchar(255)")
    private String comment;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    @Column(name = "rate_date")
    private LocalDate date;

    //@JsonIgnored
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hotelId")
    Hotel hotel;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "bookingId")
    Booking booking;


    public Rating(Long id, int rate, String comment, LocalDate date, User user) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.date = date;
        this.user = user;
    }
}

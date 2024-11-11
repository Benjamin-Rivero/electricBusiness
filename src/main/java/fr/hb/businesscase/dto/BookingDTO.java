package fr.hb.businesscase.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private String stationId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String userId;

    private Long userAddressId;

}

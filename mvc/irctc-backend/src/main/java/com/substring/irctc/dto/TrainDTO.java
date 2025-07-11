package com.substring.irctc.dto;

import com.substring.irctc.entity.Station;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainDTO {


    private Long id;
    @NotEmpty(message = "train number is required !!")


    @Schema(description = "train number", example = "1231314", required = true)
    @Size(min = 3, max = 20, message = "Invalid length of train no.")
    @Pattern(regexp = "^\\d+$", message = "Invalid no , train no contains only numbers.")
    @Id
    private String number;


    @Schema(description = "train name", example = "LKO - DELHI Intercity", required = true)

    @Pattern(regexp = "^[A-Za-z][A-Za-z -]*[A-Za-z]$", message = "Invalid train name. letters, spaces and hyphens are allowed")
    private String name;

//
//    @Email(message = "Invalid email")
//    private  String email;


    private Integer totalDistance;

    private StationDto sourceStation;


    private StationDto destinationStation;


}

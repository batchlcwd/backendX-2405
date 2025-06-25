package com.substring.irctc.controllers.admin;


import com.substring.irctc.config.AppConstants;
import com.substring.irctc.dto.PagedResponse;
import com.substring.irctc.dto.StationDto;
import com.substring.irctc.service.StationService;
import jakarta.validation.Valid;
import org.hibernate.query.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/stations")

public class StationController {


    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    //create stations

    @PostMapping
    public ResponseEntity<StationDto> createStation(
            @Valid @RequestBody StationDto stationDto
    ) {

        StationDto dto = stationService.createStation(stationDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }


    @GetMapping
    public PagedResponse<StationDto> listStations(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir
    ) {


        PagedResponse<StationDto> stationsDto = stationService.listStations(
                page,
                size,
                sortBy,
                sortDir
        );

        return stationsDto;

    }

    @GetMapping("/{id}")
    public StationDto getById(
            @PathVariable Long id
    ) {
        StationDto dto = stationService.getById(id);
        return dto;
    }

    @PutMapping("/{id}")
    public StationDto update(
            @PathVariable Long id,
            @RequestBody StationDto dto
    ) {
        StationDto updatedDto = stationService.update(id, dto);
        return updatedDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        stationService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

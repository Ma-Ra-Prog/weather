package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LocalizationController {

    private final LocalizationCreateService localizationCreateService;
    private final LocalizationFetchService localizationFetchService;
    private final LocalizationMapper localizationMapper;

    @PostMapping("/localization")
    ResponseEntity<LocalizationDto> createLocalisation(@RequestBody LocalizationDto localisationDto) {
        LocalizationDefinition localizationDefinition = localizationMapper.mapToLocalizationDefinition(localisationDto);
        Localization createdNewLocalization = localizationCreateService.createNewLocalization(localizationDefinition);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localizationMapper.mapToLocalizationDto(createdNewLocalization));
    }

    @GetMapping("/localization/{id}")
    LocalizationDto getLocalization(@PathVariable Long id) {
        Localization localization = localizationFetchService.fetchLocalization(id);
        return localizationMapper.mapToLocalizationDto(localization);
    }

    @GetMapping("/localization")
    ResponseEntity<List<LocalizationDto>> getAllLocalization() {
        List<LocalizationDto> localizationDtoList = localizationFetchService
                .fetchAllLocalisations()
                .stream()
                .map(localizationMapper::mapToLocalizationDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(localizationDtoList);
    }
}

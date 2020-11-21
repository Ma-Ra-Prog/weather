package com.sda.weather.localization;

import com.sda.weather.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalizationFetchService {

    private final LocalizationRepository localizationRepository;

    public Localization fetchLocalization(Long id) {
        return localizationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nie znaleziono lokalizacji o id: " + id));
    }
}

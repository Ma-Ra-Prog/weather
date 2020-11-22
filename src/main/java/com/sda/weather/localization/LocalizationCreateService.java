package com.sda.weather.localization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalizationCreateService {

    private final LocalizationRepository localizationRepository;
    private final LocalizationInputValidator localizationInputValidator = new LocalizationInputValidator();

    public Localization createNewLocalization(LocalizationDefinition localizationDefinition) {
        localizationInputValidator.isInputDataCorrect(localizationDefinition);

        Localization localization = new Localization();
        localization.setCityName(localizationDefinition.getCityName());
        localization.setCountryName(localizationDefinition.getCountryName());
        localization.setLatitude(localizationDefinition.getLatitude());
        localization.setLongitude(localizationDefinition.getLongitude());
        if (!localizationDefinition.getRegion().get().isBlank()) {              // todo it may throw an exception
            localization.setRegion(localizationDefinition.getRegion().get());
        }

        return localizationRepository.save(localization);
    }
}

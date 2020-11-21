package com.sda.weather.localization;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocalizationInputValidatorTest {

    private final LocalizationInputValidator localizationInputValidator = new LocalizationInputValidator();

    @Test
    void validateCountryOrCityName_thenReturnTrue() {
        assertThat(localizationInputValidator.isFormatCorrect("Gdańsk")).isTrue();
        assertThat(localizationInputValidator.isFormatCorrect("Polska")).isTrue();
        assertThat(localizationInputValidator.isFormatCorrect("PL")).isTrue();
        assertThat(localizationInputValidator.isFormatCorrect("Polska Rzeczpospolita Ludowa")).isTrue();
    }

    @Test
    void validateCountryOrCityName_whenCityNameIsInvalid_thenReturnFalse() {
        assertThat(localizationInputValidator.isFormatCorrect("Gd@ń5k")).isFalse();
        assertThat(localizationInputValidator.isFormatCorrect("     Gdańsk")).isFalse();
        assertThat(localizationInputValidator.isFormatCorrect("Gda     ńsk")).isFalse();
        assertThat(localizationInputValidator.isFormatCorrect("Gda----ńsk")).isFalse();

    }

    @Test
    void validateLatitudeValue_thenReturnTrue() {
        assertThat(localizationInputValidator.isLatitudeCorrect(0f)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(-90f)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(90f)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(45.5783f)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(-45.5783f)).isTrue();
    }

    @Test
    void validateLatitudeValue_whenValueIsIncorrect_thenReturnFalse() {
        assertThat(localizationInputValidator.isLatitudeCorrect(-91f)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(91f)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(90.0001f)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(-90.0001f)).isFalse();
    }

    @Test
    void validateLongitudeValue_thenReturnTrue() {
        assertThat(localizationInputValidator.isLongitudeCorrect(0f)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(-180f)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(180f)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(90.5783f)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(-90.5783f)).isTrue();
    }

    @Test
    void validateLongitudeValue_whenValueIsIncorrect_thenReturnFalse() {
        assertThat(localizationInputValidator.isLongitudeCorrect(-181f)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(181f)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(180.0001f)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(-180.0001f)).isFalse();
    }
}
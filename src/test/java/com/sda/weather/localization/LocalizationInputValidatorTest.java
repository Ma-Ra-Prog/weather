package com.sda.weather.localization;

import com.sda.weather.exceptions.IllegalParameterValueException;
import com.sda.weather.exceptions.InvalidCountryOrCityException;
import com.sda.weather.exceptions.NoCountryOrCityException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
        assertThat(localizationInputValidator.isFormatCorrect("        ")).isFalse();
    }

    @Test
    void validateLatitudeValue_thenReturnTrue() {
        assertThat(localizationInputValidator.isLatitudeCorrect(0d)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(-90d)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(90d)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(45.5783d)).isTrue();
        assertThat(localizationInputValidator.isLatitudeCorrect(-45.5783d)).isTrue();
    }

    @Test
    void validateLatitudeValue_whenValueIsIncorrect_thenReturnFalse() {
        assertThat(localizationInputValidator.isLatitudeCorrect(-91d)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(91d)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(90.0001d)).isFalse();
        assertThat(localizationInputValidator.isLatitudeCorrect(-90.0001d)).isFalse();
    }

    @Test
    void validateLongitudeValue_thenReturnTrue() {
        assertThat(localizationInputValidator.isLongitudeCorrect(0d)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(-180d)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(180d)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(90.5783d)).isTrue();
        assertThat(localizationInputValidator.isLongitudeCorrect(-90.5783d)).isTrue();
    }

    @Test
    void validateLongitudeValue_whenValueIsIncorrect_thenReturnFalse() {
        assertThat(localizationInputValidator.isLongitudeCorrect(-181d)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(181d)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(180.0001d)).isFalse();
        assertThat(localizationInputValidator.isLongitudeCorrect(-180.0001d)).isFalse();
    }

    @Test
    void validateCityName_thenReturnTrue() {
        assertThat(localizationInputValidator.isCityNameNotEmpty("Gdańsk")).isTrue();
    }

    @Test
    void validateCityName_whenNameIsEmpty_thenReturnFalse() {
        assertThat(localizationInputValidator.isCityNameNotEmpty("")).isFalse();
    }

    @Test
    void validateCountryName_thenReturnTrue() {
        assertThat(localizationInputValidator.isCountryNameNotEmpty("Polska Rzeczpospolita Ludowa")).isTrue();
    }

    @Test
    void validateCountryName_whenNameIsEmpty_thenReturnFalse() {
        assertThat(localizationInputValidator.isCountryNameNotEmpty("")).isFalse();
    }

    @Test
    void isInputDataCorrect_whenCityNameIsEmpty_thenThrowNoCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("", "Polska", 54.356030d, 180.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(NoCountryOrCityException.class);
    }

    @Test
    void isInputDataCorrect_whenCountryNameIsEmpty_thenThrowNoCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdańsk", "", 54.356030d, 180.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(NoCountryOrCityException.class);
    }

    @Test
    void isInputDataCorrect_whenCityNameIsInvalid_thenThrowInvalidCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("   Jaka śna zwa", "Polska", 54.356030d, 180.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(InvalidCountryOrCityException.class);
    }

    @Test
    void isInputDataCorrect_whenCountryNameIsInvalid_thenThrowInvalidCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdańsk", "   Pol  ska", 54.356030d, 180.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(InvalidCountryOrCityException.class);
    }

    @Test
    void isInputDataCorrect_whenLatitudeIsOutOfBound_thenThrowIllegalParameterValueException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdańsk", "Polska", 200d, 180.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(IllegalParameterValueException.class);
    }

    @Test
    void isInputDataCorrect_whenLongitudeIsOutOfBound_thenThrowIllegalParameterValueException() {
        //given
        LocalizationDefinition localizationDefinition = new LocalizationDefinition("Gdańsk", "Polska", 54.356030d, 200.000d, "Pomorskie");
        //when
        Throwable throwable = catchThrowable(() -> localizationInputValidator.isInputDataCorrect(localizationDefinition));
        //then
        assertThat(throwable).isInstanceOf(IllegalParameterValueException.class);
    }
}
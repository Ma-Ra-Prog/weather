package com.sda.weather.localization;

import com.sda.weather.exceptions.NoCountryOrCityExcepton;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalizationCreateServiceTest {

    @Mock
    LocalizationRepository localizationRepository;

    @InjectMocks
    LocalizationCreateService localizationCreateService;

    @Test
    void createNewLocalization_thenCreatesANewLocalization(){
        //given
        when(localizationRepository.save(any(Localization.class))).thenReturn(new Localization());
        LocalizationDefinition localizationDefinition = LocalizationDefinition.builder()
                .cityName("Gdańsk")
                .countryName("Polska")
                .latitude(54.356030f)
                .longitude(180.000f)
                .region("Pomorskie")
                .build();

        //when
        Localization result = localizationCreateService.createNewLocalization(localizationDefinition);

        //then
        assertThat(result).isExactlyInstanceOf(Localization.class);
        verify(localizationRepository, times(1)).save(any(Localization.class));
    }

    @Test
    void createNewLocalisation_whenCityNameIsEmpty_thenThrowsNoCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = LocalizationDefinition.builder()
                .cityName("")
                .countryName("Polska")
                .latitude(54.356030f)
                .longitude(18.646120f)
                .region("Pomorskie")
                .build();

        //when
        Throwable throwable = catchThrowable(() -> localizationCreateService.createNewLocalization(localizationDefinition));

        //then
        assertThat(throwable).isInstanceOf(NoCountryOrCityExcepton.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }

    @Test
    void createNewLocalisation_whenCityNameIsBlank_thenThrowsNoCountryOrCityException() {
        //given
        LocalizationDefinition localizationDefinition = LocalizationDefinition.builder()
                .cityName(" ")
                .countryName("Polska")
                .latitude(54.356030f)
                .longitude(18.646120f)
                .region("Pomorskie")
                .build();

        //when
        Throwable throwable = catchThrowable(() -> localizationCreateService.createNewLocalization(localizationDefinition));

        //then
        assertThat(throwable).isInstanceOf(NoCountryOrCityExcepton.class);
        verify(localizationRepository, times(0)).save(any(Localization.class));
    }
}

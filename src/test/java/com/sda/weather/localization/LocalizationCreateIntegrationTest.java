package com.sda.weather.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class LocalizationCreateIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocalizationRepository localizationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void createNewLocalization_thenCreatesNewLocalizationAndReturn201StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("Polska")
                .latitude(54.356030d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));

        // when
        MvcResult result = mockMvc.perform(post).andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(1);
        assertThat(localizations.get(0)).satisfies(localization -> {
            assertThat(localization.getCityName()).isEqualTo("Gdańsk");
            assertThat(localization.getCountryName()).isEqualTo("Polska");
            assertThat(localization.getLatitude()).isEqualTo(54.356030d);
            assertThat(localization.getLongitude()).isEqualTo(180.000d);
            assertThat(localization.getRegion()).isNotEmpty();
            assertThat(localization.getRegion()).hasValue("Pomorskie");
        });
    }

    @Test
    void createNewLocalization_whenCityNameIsEmpty_thenThrowNoCountryOrCityExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("")
                .countryName("Polska")
                .latitude(54.356030d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenCountryNameIsEmpty_thenThrowNoCountryOrCityExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("")
                .latitude(54.356030d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenCityNameIsInvalid_thenThrowInvalidCountryOrCityExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("  Gd ń sk")
                .countryName("Polska")
                .latitude(54.356030d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenCountryNameIsInvalid_thenThrowInvalidCountryOrCityExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("   Pol ska  ")
                .latitude(54.356030d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenLatitudeIsOver90_thenThrowIllegalParameterValueExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("Polska")
                .latitude(90.01d)
                .longitude(180.000d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenLongitudeIsOver180_thenThrowIllegalParameterValueExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("   Pol ska  ")
                .latitude(54.356030d)
                .longitude(180.001d)
                .region("Pomorskie")
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));
        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();
        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        List<Localization> localizations = localizationRepository.findAll();
        assertThat(localizations.size()).isEqualTo(0);
    }

    @Test
    void createNewLocalization_whenRegionIsNull_thenThrowIllegalParameterValueExceptionAndReturn400StatusCode() throws Exception {
        // given
        localizationRepository.deleteAll();
        LocalizationDto localizationDto = new LocalizationDto().builder()
                .id(null)
                .cityName("Gdańsk")
                .countryName("Polska")
                .latitude(54.356030d)
                .longitude(55.00d)
                .build();

        MockHttpServletRequestBuilder post = post("/localization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(localizationDto));

        // when
        MvcResult result = mockMvc
                .perform(post)
                .andReturn();

        //then
        MockHttpServletResponse response = result.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}

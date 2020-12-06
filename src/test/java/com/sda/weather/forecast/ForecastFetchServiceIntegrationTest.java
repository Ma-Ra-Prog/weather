package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import com.sda.weather.localization.LocalizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
public class ForecastFetchServiceIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocalizationRepository localizationRepository;

    Localization savedLocalization;

    @BeforeEach
    void setUp(){
        localizationRepository.deleteAll();
        Localization localization = new Localization();
        localization.setCityName("Warsaw");
        localization.setCountryName("Poland");
        localization.setLatitude(50);
        localization.setLongitude(40);
        Localization savedLocalization = localizationRepository.save(localization);
    }

    @Test
    void getForecast_returnsCorrectForecastAndStatusCode200() throws Exception {
        // given

        Long id = savedLocalization.getId();

        MockHttpServletRequestBuilder request = get("/localization/"+id+"/forecast")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getForecast_whenPeriodIsOver5_returnsBadRequestStatusCode400() throws Exception {
        // given
        Long id = savedLocalization.getId();

        MockHttpServletRequestBuilder request = get("/localization/"+id+"/forecast?period=6")
                .contentType(MediaType.APPLICATION_JSON);
        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}

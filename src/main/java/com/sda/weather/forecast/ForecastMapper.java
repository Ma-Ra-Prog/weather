package com.sda.weather.forecast;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@NoArgsConstructor
public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast){
        return ForecastDto.builder()
                .id(newForecast.getId())
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windDegree(newForecast.getWindDegree())
                .windSpeed(newForecast.getWindSpeed())
                .build();
    }

    String mapWindDegreeToWindDirection(String degree){
        double doubleDegree = Double.parseDouble(degree);
        return null; //todo: zrobić mapper zmieniający kąt wiatru na kierunek wg tego rozkładu http://snowfence.umn.edu/Components/winddirectionanddegrees.htm
        //todo: dodać windDirection do klas pogodowych
    }
}

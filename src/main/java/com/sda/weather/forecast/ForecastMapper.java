package com.sda.weather.forecast;

import com.sda.weather.localization.Localization;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Component
@NoArgsConstructor
public class ForecastMapper {

    ForecastDto mapToForecastDto(Forecast newForecast) {
        return ForecastDto.builder()
                .temperature(newForecast.getTemperature())
                .airPressure(newForecast.getAirPressure())
                .humidity(newForecast.getHumidity())
                .windDegree(newForecast.getWindDegree())
                .windDirection(newForecast.getWindDirection())
                .windSpeed(newForecast.getWindSpeed())
                .date(newForecast.getDate())
                .localization((newForecast.getLocalization()))
                .build();
    }

    ForecastDto mapToForecastDto(ForecastOpenWeatherResponse forecastResponse, Localization localization, Integer period){
        LocalDateTime newDateTime;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String firstDate = forecastResponse.getList()
                .stream()
                .findFirst()
                .get()
                .getDate();
        newDateTime = LocalDateTime.parse(firstDate, dateTimeFormatter).plusDays(period);

        String newDateWithoutTime = newDateTime.toString()
                .substring(0,(newDateTime.toString().indexOf(" ")));

        ForecastOpenWeatherResponse.SingleForecast singleForecast = forecastResponse
                .getList()
                .stream()
                .filter(x -> x.getDate()
                        .equals(newDateWithoutTime + " 12:00:00"))
                .findFirst().get();

        return ForecastDto.builder()
                .temperature(singleForecast.getWeather().getTemperature())
                .airPressure(singleForecast.getWeather().getPressure())
                .humidity(singleForecast.getWeather().getHumidity())
                .windDegree(singleForecast.getWind().getDegree())
                .windDirection(mapWindDegreeToWindDirection(singleForecast.getWind().getDegree()))
                .windDegree(singleForecast.getWind().getSpeed())
                .date(singleForecast.getDate())
                .localization(localization)
                .build();
    }

    String mapWindDegreeToWindDirection(float degree) {
        return null; //todo: zrobić mapper zmieniający kąt wiatru na kierunek wg tego rozkładu http://snowfence.umn.edu/Components/winddirectionanddegrees.htm
        //todo: dodać windDirection do klas pogodowych
    }
}

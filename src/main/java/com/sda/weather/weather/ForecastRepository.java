package com.sda.weather.weather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {
}

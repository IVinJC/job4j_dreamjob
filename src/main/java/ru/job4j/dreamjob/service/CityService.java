package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.persistence.CityStore;

import javax.annotation.concurrent.ThreadSafe;
import java.util.Collection;
import java.util.List;

@ThreadSafe
@Service
public class CityService {
    private final CityStore cityStore;

    public CityService(CityStore cityStore) {
        this.cityStore = cityStore;
    }

    public Collection<City> getAllCities() {
        return cityStore.getAllCities();
    }

    public boolean add(City city) {
        return cityStore.add(city);
    }

    public City findById(int id) {
        return cityStore.findById(id);
    }

    public City update(City city) {
        return cityStore.update(city);
    }
}
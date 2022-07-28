package ru.job4j.dreamjob.persistence;

import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.model.Post;

import javax.annotation.concurrent.ThreadSafe;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CityStore {
    private Map<Integer, City> cities = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(4);

    public CityStore() {
        cities.put(1, new City(1, "Москва"));
        cities.put(2, new City(2, "СПб"));
        cities.put(3, new City(3, "Екб"));
    }

    public Collection<City> getAllCities() {
        return cities.values();
    }

    public City findById(int id) {
        return cities.get(id);
    }

    public boolean add(City city) {
        city.setId(id.incrementAndGet());
        return cities.put(city.getId(), city) == null;
    }

    public City update(City city) {
        return cities.replace(city.getId(), city);
    }
}

package ru.job4j.dreamjob.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.City;
import ru.job4j.dreamjob.service.CityService;

@Controller
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public String cities(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return "cities";
    }

    @GetMapping("/formAddCity")
    public String addCity(Model model) {
        model.addAttribute("city", new City(0, "Заполните поле"));
        return "addCity";
    }

    @PostMapping("/createCity")
    public String createCity(@ModelAttribute City city) {
        cityService.add(city);
        return "redirect:/cities";
    }

    @PostMapping("/updateCity")
    public String updateCity(@ModelAttribute City city) {
        cityService.update(city);
        return "redirect:/cities";
    }

    @GetMapping("/formUpdateCity/{cityId}")
    public String formUpdateCity(Model model, @PathVariable("cityId") int id) {
        model.addAttribute("city", cityService.findById(id));
        return "updateCity";
    }
}

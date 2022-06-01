package itmo.controllers;

import itmo.model.Film;
import itmo.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FilmController {
    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/film/{id}")
    public Film getFilm(@PathVariable Long id){
        return filmService.getFilm(id);
    }

    @GetMapping("/film/search/{name}")
    public List<Film> findFilm(@PathVariable String name){
        return filmService.findByNameContains(name);
    }
}

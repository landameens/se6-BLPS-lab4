package itmo.services;

import itmo.exceptions.BadRequestException;
import itmo.model.Film;
import itmo.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {
    private final FilmRepository filmRepository;

    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Film getFilm(Long id){
        return filmRepository.findById(id).orElseThrow(
                () -> new BadRequestException("The film doesn't exist")
        );
    }

    public List<Film> findByNameContains(String str){
        return filmRepository.findByNameContains(str);
    }
}

package itmo.services;

import itmo.exceptions.BadRequestException;
import itmo.model.Film;
import itmo.repositories.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;

    public Film getFilm(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new BadRequestException("The film doesn't exist"));
    }

    public List<Film> findByNameContains(String str) {
        return filmRepository.findByNameContains(str);
    }
}

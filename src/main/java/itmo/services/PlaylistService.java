package itmo.services;

import itmo.exceptions.BadRequestException;
import itmo.exceptions.ForbiddenException;
import itmo.model.Film;
import itmo.model.ImportStat;
import itmo.model.Playlist;
import itmo.repositories.PlaylistRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final FilmService filmService;;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public PlaylistService(PlaylistRepository playlistRepository, FilmService filmService, RabbitTemplate rabbitTemplate) {
        this.playlistRepository = playlistRepository;
        this.filmService = filmService;
        this.rabbitTemplate = rabbitTemplate;
    }

    public List<Playlist> getPlayListsByOwnerId(String ownerEmail){
        return playlistRepository.findAllByOwnerEmail(ownerEmail);
    }

    public void addFilm(Long playlistId, Long filmId, String ownerEmail){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist, ownerEmail);
        Film film = filmService.getFilm(filmId);

        if (playlist.getFilms().add(film)){
            playlistRepository.save(playlist);
        } else {
            throw new BadRequestException("The film is already in the playlist");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void importPlaylist(Long playlistId, Long importedPlaylistId, String ownerEmail){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist, ownerEmail);
        Playlist importedPlaylist = getPlaylist(importedPlaylistId);

        if(playlist.getFilms().addAll(importedPlaylist.getFilms())){
            playlistRepository.save(playlist);
            importedPlaylist.setCountTimesImported(importedPlaylist.getCountTimesImported() + 1);
            playlistRepository.save(importedPlaylist);

            rabbitTemplate.convertAndSend("queue",
                    new ImportStat(importedPlaylist.getOwnerEmail(),
                            importedPlaylist.getName(), playlist.getOwnerEmail()));
        }
    }

    public void deleteFilm(Long playlistId, Long filmId, String ownerEmail){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist, ownerEmail);
        Film film = filmService.getFilm(filmId);

        if (playlist.getFilms().remove(film)){
            playlistRepository.save(playlist);
        } else {
            throw new BadRequestException("The film is not in the playlist");
        }
    }

    public Playlist getPlaylist(Long playlistId){
        return playlistRepository.findById(playlistId).orElseThrow(
                () -> new BadRequestException("The playlist doesn't exist")
        );
    }

    public void checkOwner(Playlist playlist, String ownerEmail){
        if (ownerEmail.equals(playlist.getOwnerEmail()))
            throw new ForbiddenException("The playlist is owned by someone else");
    }
}

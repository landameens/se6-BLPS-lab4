package itmo.services;

import itmo.exceptions.BadRequestException;
import itmo.exceptions.ForbiddenException;
import itmo.model.Film;
import itmo.model.ImportStat;
import itmo.model.Playlist;
import itmo.repositories.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistService {
    private final PlaylistRepository playlistRepository;
    private final FilmService filmService;;
    private final RabbitTemplate rabbitTemplate;
    private final IdentityService identityService;

    public List<Playlist> getPlayListsByOwnerEmail(String ownerEmail){
        return playlistRepository.findAllByOwnerEmail(ownerEmail);
    }

    public List<Playlist> findByNameContains(String str) {
        return playlistRepository.findByNameContains(str);
    }

    public void addFilm(Long playlistId, Long filmId){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist);
        Film film = filmService.getFilm(filmId);

        if (playlist.getFilms().add(film)){
            playlistRepository.save(playlist);
        } else {
            throw new BadRequestException("The film is already in the playlist");
        }
    }

    public void deleteFilm(Long playlistId, Long filmId){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist);
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

    public void checkOwner(Playlist playlist){
        String userId = identityService.getCurrentAuthentication().getUserId();
        User user = identityService.createUserQuery().userId(userId).singleResult();
        if (!user.getEmail().equals(playlist.getOwnerEmail()))
            throw new ForbiddenException("The playlist is owned by someone else");
    }

    public List<Playlist> getMyPlayLists() {
        String userId = identityService.getCurrentAuthentication().getUserId();
        User user = identityService.createUserQuery().userId(userId).singleResult();
        return playlistRepository.findAllByOwnerEmail(user.getEmail());
    }

    public void sendStats(Long playlistId, Long importedPlaylistId){
        Playlist playlist = getPlaylist(playlistId);
        Playlist importedPlaylist = getPlaylist(importedPlaylistId);

        rabbitTemplate.convertAndSend("queue",
                new ImportStat(importedPlaylist.getOwnerEmail(),
                        importedPlaylist.getName(), playlist.getOwnerEmail()));
    }

    public void importPlaylist(Long playlistId, List<Film> importedMovies){
        Playlist playlist = getPlaylist(playlistId);
        checkOwner(playlist);
        if(playlist.getFilms().addAll(importedMovies)) playlistRepository.save(playlist);
    }

    public void incrementCountTimesImported(Long playlistId){
        Playlist playlist = getPlaylist(playlistId);
        playlist.setCountTimesImported(playlist.getCountTimesImported() + 1);
        playlistRepository.save(playlist);
    }
}

package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("removeFilmDelegate")
@RequiredArgsConstructor
public class RemoveFilmDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long playlistId = Long.parseLong((String) execution.getVariable("playlist_id"));
        Long filmId = Long.parseLong((String) execution.getVariable("film_id"));
        playlistService.deleteFilm(playlistId, filmId);
    }
}

package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("addFilmDelegate")
@RequiredArgsConstructor
public class AddFilmDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long playlistId = (Long) execution.getVariable("playlist_id");
        Long filmId = (Long) execution.getVariable("film_id");
        playlistService.addFilm(playlistId, filmId);
    }
}

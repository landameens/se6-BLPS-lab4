package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;

import javax.inject.Named;

@Named("removeFilmDelegate")
@RequiredArgsConstructor
public class RemoveFilmDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long playlistId = (Long) execution.getVariable("playlist_id");
        Long filmId = (Long) execution.getVariable("film_id");
        playlistService.deleteFilm(playlistId, filmId);
    }
}

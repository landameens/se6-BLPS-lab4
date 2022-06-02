package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.identity.Authentication;

import javax.inject.Named;

@Named("addFilmDelegate")
@RequiredArgsConstructor
public class AddFilmDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = null;
        Authentication currentAuth = execution.getProcessEngine().getIdentityService().getCurrentAuthentication();
        if (currentAuth != null) {
            userId = currentAuth.getUserId();
            System.out.println(userId);
        }
        Long playlistId = Long.parseLong((String) execution.getVariable("playlist_id"));
        Long filmId = Long.parseLong((String) execution.getVariable("film_id"));
        playlistService.addFilm(playlistId, filmId);
    }
}

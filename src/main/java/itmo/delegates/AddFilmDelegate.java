package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.engine.impl.identity.Authentication;

import javax.inject.Named;

@Named("addFilmDelegate")
@RequiredArgsConstructor
public class AddFilmDelegate implements JavaDelegate {
    private final PlaylistService playlistService;
    private final IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = identityService.getCurrentAuthentication().getUserId();
        User user = identityService.createUserQuery().userId(userId).singleResult();
        Long playlistId = Long.parseLong((String) execution.getVariable("playlist_id"));
        Long filmId = Long.parseLong((String) execution.getVariable("film_id"));
        playlistService.addFilm(playlistId, filmId, user.getEmail());
    }
}

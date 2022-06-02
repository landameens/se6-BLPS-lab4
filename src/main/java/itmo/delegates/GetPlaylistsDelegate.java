package itmo.delegates;


import itmo.model.Playlist;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.identity.User;

import javax.inject.Named;
import java.util.List;


@Named("getPlaylistsDelegate")
@RequiredArgsConstructor
public class GetPlaylistsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;
    private final IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userId = identityService.getCurrentAuthentication().getUserId();
        User user = identityService.createUserQuery().userId(userId).singleResult();
        List<Playlist> playlists = playlistService.getPlayListsByOwnerId(user.getEmail());
        execution.setVariable("playlists", playlists);
    }
}

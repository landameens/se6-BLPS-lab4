package itmo.delegates;


import itmo.model.Playlist;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.impl.identity.Authentication;

import javax.inject.Named;
import java.util.List;


@Named("getPlaylistsDelegate")
@RequiredArgsConstructor
public class GetPlaylistsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String userMail = null;
        Authentication currentAuth = execution.getProcessEngine().getIdentityService().getCurrentAuthentication();
        if (currentAuth != null) {
            userMail = currentAuth.getUserId();
            System.out.println(userMail);
        }
        List<Playlist> playlists = playlistService.getPlayListsByMail(userMail);
        execution.setVariable("playlists", playlists);
    }
}

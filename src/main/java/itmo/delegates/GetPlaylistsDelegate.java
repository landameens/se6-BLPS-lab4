package itmo.delegates;


import itmo.model.Playlist;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.List;


@Named("getPlaylistsDelegate")
@RequiredArgsConstructor
public class GetPlaylistsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Playlist> playlists = playlistService.getMyPlayLists();
        execution.setVariable("playlists", playlists.toString());
    }
}

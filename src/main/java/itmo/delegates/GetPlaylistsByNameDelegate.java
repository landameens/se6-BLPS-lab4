package itmo.delegates;

import itmo.model.Playlist;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.List;

@Named("getPlaylistsByName")
@RequiredArgsConstructor
public class GetPlaylistsByNameDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Playlist> playlists = playlistService.findByNameContains((String) execution.getVariable("playlist_name"));
        execution.setVariable("playlists", playlists);
        execution.setVariable("is_exist", playlists.size() > 0);
    }
}

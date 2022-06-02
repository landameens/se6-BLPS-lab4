package itmo.delegates;

import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("updateStats")
@RequiredArgsConstructor
public class UpdateStatsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long playlistIdFrom = Long.parseLong((String) execution.getVariable("playlist_id_from"));
        playlistService.incrementCountTimesImported(playlistIdFrom);
    }
}

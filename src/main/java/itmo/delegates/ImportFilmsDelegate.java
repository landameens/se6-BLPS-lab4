package itmo.delegates;

import itmo.model.Film;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("importFilms")
@RequiredArgsConstructor
public class ImportFilmsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<Film> importedMovies = (ArrayList<Film>) execution.getVariable("imported_movies");
        Long playlistIdTo = Long.parseLong((String) execution.getVariable("playlist_id_to"));
        playlistService.importPlaylist(playlistIdTo, importedMovies);
    }
}

package itmo.delegates;

import itmo.model.Film;
import itmo.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named("getAllImportedFilms")
@RequiredArgsConstructor
public class GetAllImportedFilmsDelegate implements JavaDelegate {
    private final PlaylistService playlistService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long playlistIdFrom = Long.parseLong((String) execution.getVariable("playlist_id_from"));
        List<Film> importedMovies = new ArrayList<>(playlistService.getPlaylist(playlistIdFrom).getFilms());
        execution.setVariable("imported_movies", importedMovies);
    }
}

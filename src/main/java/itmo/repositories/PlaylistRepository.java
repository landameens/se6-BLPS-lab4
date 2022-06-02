package itmo.repositories;

import itmo.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    List<Playlist> findAllByOwnerEmail(String ownerEmail);

    List<Playlist> findByNameContains(String str);
}

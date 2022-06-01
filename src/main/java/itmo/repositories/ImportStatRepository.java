package itmo.repositories;

import itmo.model.ImportStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface ImportStatRepository extends JpaRepository<ImportStat, Long> {
    @Query("select distinct ownerMail from ImportStat")
    Set<String> getMails();

    List<ImportStat> findByOwnerMail(String ownerMail);

    @Transactional
    void deleteByOwnerMail(String ownerMail);
}

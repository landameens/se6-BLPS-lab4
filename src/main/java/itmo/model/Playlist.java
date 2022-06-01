package itmo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Playlist {
    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int countTimesImported;

    @Column
    private Long ownerId;

    @ManyToMany
    private Set<Film> films;
}

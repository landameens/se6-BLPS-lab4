package itmo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
public class Playlist implements Serializable {
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
    private String ownerEmail;

    @ManyToMany
    private Set<Film> films;
}

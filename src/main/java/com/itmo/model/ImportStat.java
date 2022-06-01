package com.itmo.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table
public class ImportStat implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ownerMail;

    @Column
    private String playlistName;

    @Column
    private String importerName;

    public ImportStat(String ownerMail, String playlistName, String importerName) {
        this.ownerMail = ownerMail;
        this.playlistName = playlistName;
        this.importerName = importerName;
    }

    public ImportStat() {}
}
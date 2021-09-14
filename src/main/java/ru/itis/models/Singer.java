package ru.itis.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "singer_has_album",
            joinColumns = {@JoinColumn(name = "singer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")})
    private List<Album> albums;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "singer_has_song",
            joinColumns = {@JoinColumn(name = "singer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id")})
    private List<Song> songs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Singer singer = (Singer) o;

        return Id != null && Id.equals(singer.Id);
    }

    @Override
    public int hashCode() {
        return 1797661554;
    }
}

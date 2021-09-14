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
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String name;

    private Integer cost;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "user_has_song",
                joinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "favorites",
            joinColumns = {@JoinColumn(name = "song_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> usersFavorites;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    private Album album;

    @ToString.Exclude
    @ManyToMany(mappedBy = "songs")
    private List<Singer> singer;

    @ToString.Exclude
    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Song song = (Song) o;

        return id != null && id.equals(song.id);
    }

    @Override
    public int hashCode() {
        return 10931051;
    }
}

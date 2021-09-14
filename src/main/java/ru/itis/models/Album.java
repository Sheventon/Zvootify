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
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private Integer songsCount;

    private String yearOfIssue;

    private String pathToCover;

    private Integer cost;

    @ToString.Exclude
    @ManyToMany(mappedBy = "albums")
    private List<Singer> singer;

    @ToString.Exclude
    @OneToMany(mappedBy = "album")
    private List<Song> songs;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "user_has_album",
            joinColumns = {@JoinColumn(name = "album_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    private List<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Album album = (Album) o;

        return Id != null && Id.equals(album.Id);
    }

    @Override
    public int hashCode() {
        return 113065996;
    }
}

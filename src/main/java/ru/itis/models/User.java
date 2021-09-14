package ru.itis.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private Integer peso;

    private String password;

    private Boolean isDeleted;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<Song> songs;

    @ToString.Exclude
    @ManyToMany(mappedBy = "usersFavorites")
    private List<Song> songsFavorites;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<Album> albums;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists;

    @Column(name = "user_state")
    @Enumerated(value = EnumType.STRING)
    private State userState;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return id != null && id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.userState == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.userState == State.BANNED;
    }

    public boolean isAdmin() {
        return this.userRole == Role.ADMIN;
    }
}


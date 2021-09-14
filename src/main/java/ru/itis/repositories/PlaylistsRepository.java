package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.models.Playlist;
import ru.itis.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PlaylistsRepository extends JpaRepository<Playlist, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "select * from playlist where is_private = false")
    List<Playlist> findPublicPlaylists();

    Optional<Playlist> findByNameAndUser(String name, User user);
}

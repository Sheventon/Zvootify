package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.models.Song;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface SongsRepository extends JpaRepository<Song, Long> {
    @Transactional
    @Query(nativeQuery = true, value = "select * from song order by id desc limit 10")
    List<Song> findFirstById();

    Optional<Song> findById(Long id);
}

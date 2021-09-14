package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.models.Album;

import java.util.Optional;

public interface AlbumsRepository extends JpaRepository<Album, Long> {
    Optional<Album> findById(Long id);

    @Query(nativeQuery = true, value =
            "select album.id, cost, album.name, path_to_cover, songs_count, year_of_issue " +
            "from album " +
            "left join singer_has_album sha on album.id = sha.album_id " +
            "left join singer s on sha.singer_id = s.id " +
            "where s.name = :singer and album.name = :albumName")
    Optional<Album> findBySingerAndName(@Param("singer") String name, @Param("albumName") String singer);
    //Optional<Album> findBySingerAndSinger(String singer1, String singer2);
}

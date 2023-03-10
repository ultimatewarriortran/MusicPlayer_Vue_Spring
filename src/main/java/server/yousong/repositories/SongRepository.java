package server.yousong.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import server.yousong.models.Song;

@CrossOrigin
//path songs ist dann fpr localhost:8080/api/songs
@RepositoryRestResource(path = "songs", collectionResourceRel = "songs")
public interface SongRepository extends PagingAndSortingRepository<Song, Integer> {
    Page<Song> findByTitleContainingIgnoreCase(@Param("text") String t, Pageable p);
    Page<Song> findByAuthorIgnoreCase(@Param("author") String a, Pageable p);
}


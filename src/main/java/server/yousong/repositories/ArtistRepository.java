package server.yousong.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import server.yousong.models.Artist;

@CrossOrigin

@RepositoryRestResource(path = "artists", collectionResourceRel = "artists")
public interface ArtistRepository extends PagingAndSortingRepository<Artist, Integer> {

}


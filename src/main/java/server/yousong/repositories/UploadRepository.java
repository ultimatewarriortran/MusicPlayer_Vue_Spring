package server.yousong.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import server.yousong.models.Upload;

@CrossOrigin
@RepositoryRestResource(path = "uploads", collectionResourceRel = "uploads")
public interface UploadRepository extends PagingAndSortingRepository<Upload, Long> {
    Upload save(Upload file);
}

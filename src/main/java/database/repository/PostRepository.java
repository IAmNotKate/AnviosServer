package database.repository;

import database.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    PostEntity findPostEntityById(int id);

    List<PostEntity> findPostEntitiesByIdBetweenOrderByDateCreatedDesc(int firstId, int secondId);

}

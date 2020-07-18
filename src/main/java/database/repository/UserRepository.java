package database.repository;

import database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserEntityByUsername(String username);

    UserEntity findUserEntityById(int userId);

    boolean existsUserEntityByUsernameAndPassword(String username, String password);
}

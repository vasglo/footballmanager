package football.Repo;

import org.springframework.data.repository.CrudRepository;

import football.Repo.Entity.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByEmail(@Param("email")String email);
}

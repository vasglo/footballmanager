package football.Repo;

import football.Repo.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {
    List<Team> findByOrderOrderByName();

    @Modifying
    @Query(value = "INSERT INTO  (user_id,course_id) VALUE (:userId,:courseId)")
    @Transactional
    void savePlayerInTeam(Long playerId);
}

package marczakx.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marczakx.league.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}

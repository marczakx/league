package marczakx.league.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import marczakx.league.model.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}

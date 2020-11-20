package marczakx.league.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import marczakx.league.model.League;
import marczakx.league.model.Match;
import marczakx.league.model.Team;
import marczakx.league.repository.LeagueRepository;
import marczakx.league.service.leagueCreator.LeagueTableEntry;
import marczakx.league.service.leagueCreator.LeagueCreator;

@Service
public class LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;

	public List<League> listAllLeague() {
		return leagueRepository.findAll();
	}

	public Iterable<LeagueTableEntry> listLeague(Long id) {

		Optional<League> optional = leagueRepository.findById(id);
		if (optional.isEmpty()) {
			return null;
		}
		League league = optional.get();
		Iterable<Match> matches = league.getMatches();
		Iterable<Team> teams = league.getTeams();
		return LeagueCreator.builder().teams(teams).matches(matches).build().create();
	}
}

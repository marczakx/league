package marczakx.league.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import marczakx.league.model.Match;
import marczakx.league.model.Team;
import marczakx.league.repository.MatchRepository;
import marczakx.league.repository.TeamRepository;
import marczakx.league.service.leagueCreator.LeagueTableEntry;
import marczakx.league.service.leagueCreator.LeagueCreator;

@Service
public class LeagueService {
	@Autowired
	private MatchRepository matchRepository;
	@Autowired
	private TeamRepository teamRepository;

	public Iterable<LeagueTableEntry> listLeague() {
		Iterable<Match> matches = matchRepository.findAll();
		Iterable<Team> teams = teamRepository.findAll();
		return LeagueCreator.builder().teams(teams).matches(matches).build().create();
	}
}

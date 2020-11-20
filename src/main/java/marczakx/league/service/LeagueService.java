package marczakx.league.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import marczakx.league.model.League;
import marczakx.league.model.Match;
import marczakx.league.model.Team;
import marczakx.league.repository.LeagueRepository;
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
	@Autowired
	private LeagueRepository leagueRepository;

	public Iterable<LeagueTableEntry> listLeague() {
		Iterable<Match> matches = matchRepository.findAll();
		Iterable<Team> teams = teamRepository.findAll();
		return LeagueCreator.builder().teams(teams).matches(matches).build().create();
	}
	
	public Iterable<LeagueTableEntry> listLeague(Long id) {

		Optional<League> optional = leagueRepository.findById(id);
		if(optional.isEmpty()) {
			return null;
		}
		League league = optional.get();
		Iterable<Match> matches = league.getMatches();
		Iterable<Team> teams = league.getTeams();
		return LeagueCreator.builder().teams(teams).matches(matches).build().create();
	}
}

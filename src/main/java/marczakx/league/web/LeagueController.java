package marczakx.league.web;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import marczakx.league.model.League;
import marczakx.league.model.Match;
import marczakx.league.model.Team;
import marczakx.league.service.LeagueService;
import marczakx.league.service.leagueCreator.LeagueTableEntry;
import marczakx.league.repository.MatchRepository;
import marczakx.league.repository.TeamRepository;

@RestController
@CrossOrigin
public class LeagueController {

	@Autowired
	private LeagueService leagueService;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private TeamRepository teamRepository;

	@GetMapping("/")
	public List<League> leagueAll() {
		return leagueService.listAllLeague();
	}
	
	@GetMapping("/league{id}")
	public Iterable<LeagueTableEntry> league(@PathVariable Long id) {
		return leagueService.listLeague(id);
	}

	@GetMapping("/match")
	public Iterable<Match> match() {
		return matchRepository.findAll();
	}

	@PostMapping("/match")
	public void addMatch(@Valid @RequestBody Match match) {
		matchRepository.save(match);
	}

	@GetMapping("/team")
	public Iterable<Team> team() {
		return teamRepository.findAll();
	}

	@PostMapping("/teams")
	public void addTeam(@Valid @RequestBody Team team) {
		teamRepository.save(team);
	}

}

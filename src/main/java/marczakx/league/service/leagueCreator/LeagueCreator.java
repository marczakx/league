package marczakx.league.service.leagueCreator;

import java.util.ArrayList;
import java.util.List;
import marczakx.league.model.Match;
import marczakx.league.model.Team;

public class LeagueCreator {
	private Iterable<Match> listMatches;
	private Iterable<Team> listTeams;
	private List<LeagueTableEntry> league;

	private LeagueCreator() {
	}

	public Iterable<Match> getMatches() {
		create();
		return listMatches;
	}

	public Iterable<LeagueTableEntry> create() {
		if (null != listTeams) {
			this.createLeagueFromTeams();
		} else {
			this.createLeagueFromMatches();
		}
		this.calc();
		this.sort();
		this.setPosition();
		return league;
	}

	private void setPosition() {
		int index = 1;
		for (var li : league) {
			li.setPosition(index++);
		}
	}

	private void sort() {
		league.sort((o1, o2) -> Integer.compare(o2.getPoints(), o1.getPoints()));
		league.sort((o1, o2) -> Integer.compare(o2.getScoredGoals(), o1.getScoredGoals()));
	}

	private void addToLeague(Team team) {
		if (!isTeamInLeague(team)) {
			var li = new LeagueTableEntry();
			li.setTeam(team);
			league.add(li);
		}
	}

	private boolean isTeamInLeague(Team team) {
		for (LeagueTableEntry li : league) {
			if (li.getTeam().equals(team)) {
				return true;
			}
		}
		return false;
	}

	private void createLeagueFromMatches() {
		league = new ArrayList<LeagueTableEntry>();
		if (null == listMatches) {
			return;
		}
		for (Match m : listMatches) {
			addToLeague(m.getTeam1());
			addToLeague(m.getTeam2());
		}
	}

	private void createLeagueFromTeams() {
		league = new ArrayList<LeagueTableEntry>();
		if (null == listTeams) {
			return;
		}
		for (Team team : listTeams) {
			this.addToLeague(team);
		}
	}

	private void calc() {
		List<Match> matches;
		if (null == listMatches) {
			return;
		}
		matches = new ArrayList<Match>();
		for (Match match : listMatches) {
			if (this.isTeamInLeague(match.getTeam1()) && this.isTeamInLeague(match.getTeam2())) {
				matches.add(match);
				var leagueTableEntry1 = this.getLeagueTableEntry(match.getTeam1());
				var leagueTableEntry2 = this.getLeagueTableEntry(match.getTeam2());
				addSetsandPoints(match, leagueTableEntry1, leagueTableEntry2);
				addGoals(match, leagueTableEntry1, leagueTableEntry2);
				addPlayedMaches(leagueTableEntry1, leagueTableEntry2);
			}
		}
		listMatches = matches;
	}

	private void addPlayedMaches(LeagueTableEntry leagueTableEntry1, LeagueTableEntry leagueTableEntry2) {
		leagueTableEntry1.addPlayedMatches(1);
		leagueTableEntry2.addPlayedMatches(1);
	}

	private void addGoals(Match match, LeagueTableEntry leagueIteam1, LeagueTableEntry leagueIteam2) {
		leagueIteam1.addScoredGoals(
				match.getSet1().getGoals1() + match.getSet2().getGoals1() + match.getSet3().getGoals1());
		leagueIteam2.addScoredGoals(
				match.getSet1().getGoals2() + match.getSet2().getGoals2() + match.getSet3().getGoals2());
		leagueIteam2
				.addLostGoals(match.getSet1().getGoals1() + match.getSet2().getGoals1() + match.getSet3().getGoals1());
		leagueIteam1
				.addLostGoals(match.getSet1().getGoals2() + match.getSet2().getGoals2() + match.getSet3().getGoals2());
	}

	private void addSetsandPoints(Match match, LeagueTableEntry leagueIteam1, LeagueTableEntry leagueIteam2) {
		int wonSetsTeam1 = 0;
		int wonSetsTeam2 = 0;

		if (match.getSet1().getGoals1() < match.getSet1().getGoals2()) {
			wonSetsTeam2++;
		}
		if (match.getSet2().getGoals1() < match.getSet2().getGoals2()) {
			wonSetsTeam2++;
		}
		if (match.getSet3().getGoals1() < match.getSet3().getGoals2()) {
			wonSetsTeam2++;
		}
		if (match.getSet1().getGoals1() > match.getSet1().getGoals2()) {
			wonSetsTeam1++;
		}
		if (match.getSet2().getGoals1() > match.getSet2().getGoals2()) {
			wonSetsTeam1++;
		}
		if (match.getSet3().getGoals1() > match.getSet3().getGoals2()) {
			wonSetsTeam1++;
		}
		leagueIteam1.addWonSets(wonSetsTeam1);
		leagueIteam2.addWonSets(wonSetsTeam2);
		leagueIteam1.addLostSets(wonSetsTeam2);
		leagueIteam2.addLostSets(wonSetsTeam1);
		if (wonSetsTeam1 < wonSetsTeam2) {
			leagueIteam2.addPoints(1);
		}
		if (wonSetsTeam1 > wonSetsTeam2) {
			leagueIteam1.addPoints(1);
		}
	}

	private LeagueTableEntry getLeagueTableEntry(Team team) {
		return league.stream().filter(l -> l.getTeam() == team).findAny().get();
		  }

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private LeagueCreator leagueCreator = new LeagueCreator();

		public Builder teams(Iterable<Team> listTeams) {
			leagueCreator.listTeams = listTeams;
			return this;
		}

		public Builder matches(Iterable<Match> listMatches) {
			leagueCreator.listMatches = listMatches;
			return this;
		}

		public LeagueCreator build() {
			return leagueCreator;
		}
	}

}

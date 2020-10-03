package marczakx.league.service.leagueCreator;

import marczakx.league.model.Team;

public class LeagueTableEntry {
	private Team team;
	private int points = 0;
	private int scoredGoals = 0;
	private int lostGoals = 0;
	private int wonSets = 0;
	private int lostSets = 0;
	private int position = 0;
	private int playedMatches = 0;

	public int getPlayedMatches() {
		return playedMatches;
	}

	public void setPlayedMatches(int playedMatches) {
		this.playedMatches = playedMatches;
	}

	public void addPlayedMatches(int playedMatches) {
		this.playedMatches += playedMatches;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public int getScoredGoals() {
		return scoredGoals;
	}

	public void setScoredGoals(int scoredGoals) {
		this.scoredGoals = scoredGoals;
	}

	public void addScoredGoals(int scoredGoals) {
		this.scoredGoals += scoredGoals;
	}

	public int getLostGoals() {
		return lostGoals;
	}

	public void setLostGoals(int lostGoals) {
		this.lostGoals = lostGoals;
	}

	public void addLostGoals(int lostGoals) {
		this.lostGoals += lostGoals;
	}

	public int getWonSets() {
		return wonSets;
	}

	public void setWonSets(int wonSets) {
		this.wonSets = wonSets;
	}

	public void addWonSets(int wonSets) {
		this.wonSets += wonSets;
	}

	public int getLostSets() {
		return lostSets;
	}

	public void setLostSets(int lostSets) {
		this.lostSets = lostSets;
	}

	public void addLostSets(int lostSets) {
		this.lostSets += lostSets;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}

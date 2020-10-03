package marczakx.league.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import marczakx.league.model.validation.ValidMatch;

@ValidMatch
@Entity
@Table(name = "matches")
public final class Match {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team1")
	private Team team1;

	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "team2")
	private Team team2;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "set1")
	private Set set1;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "set2")
	private Set set2;

	@Valid
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "set3")
	private Set set3;

	@NotNull
	@Size(min = 3, message = "incorrect date")
	@Column(name = "date")
	private String date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	public Set getSet1() {
		return set1;
	}

	public void setSet1(Set set1) {
		this.set1 = set1;
	}

	public Set getSet2() {
		return set2;
	}

	public void setSet2(Set set2) {
		this.set2 = set2;
	}

	public Set getSet3() {
		return set3;
	}

	public void setSet3(Set set3) {
		this.set3 = set3;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean checkIsMatchCorrect() {
		if (team1.getId() == team2.getId()) {
			return false;
		}
		if (!checkSet1()) {
			return false;
		}
		if (!checkSet2()) {
			return false;
		}
		if (checkIsNeededPlayoff()) {
			if (!checkPlayoff()) {
				return false;
			}
		} else {
			if (!checkIsPlayoffNull()) {
				return false;
			}
		}

		return true;
	}

	private boolean checkIsPlayoffNull() {
		if (0 == set3.getGoals1() && 0 == set3.getGoals2()) {
			return true;
		}
		return false;
	}

	private boolean checkIsNeededPlayoff() {
		if ((isFirstTeamWonSet(this.set1) && !isFirstTeamWonSet(this.set2))
				|| (!isFirstTeamWonSet(this.set1) && isFirstTeamWonSet(this.set2))) {
			return true;
		}
		return false;
	}

	private boolean isFirstTeamWonSet(Set set) {
		if (set.getGoals1() > set.getGoals2()) {
			return true;
		}
		return false;
	}

	private boolean checkSet1() {
		return checkSet(this.set1);
	}

	private boolean checkSet2() {
		return checkSet(this.set2);
	}

	private boolean checkSet(Set set) {
		if (isGoals1OrGoals2Negative(set)) {
			return false;
		}
		if (set.getGoals1() == set.getGoals2()) {
			return false;
		}
		if (10 != set.getGoals1() && 10 != set.getGoals2()) {
			return false;
		}
		if (10 < set.getGoals1() || 10 < set.getGoals2()) {
			return false;
		}
		return true;
	}

	public boolean checkPlayoff() {
		if (isGoals1OrGoals2Negative(set3)) {
			return false;
		}
		if (set3.getGoals1() == set3.getGoals2()) {
			return false;
		}
		if (5 != set3.getGoals1() && 5 != set3.getGoals2()) {
			return false;
		}
		if (5 < set3.getGoals1() || 5 < set3.getGoals2()) {
			return false;
		}
		return true;
	}

	private boolean isGoals1OrGoals2Negative(Set set) {
		if (0 > set.getGoals1() || 0 > set.getGoals2()) {
			return true;
		}
		return false;
	}
}

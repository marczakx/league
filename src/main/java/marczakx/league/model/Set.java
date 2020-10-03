package marczakx.league.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "sets")
public final class Set {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Min(0)
	@Max(10)
	@Column(name = "goals1")
	private int goals1;

	@Min(0)
	@Max(10)
	@Column(name = "goals2")
	private int goals2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGoals1() {
		return goals1;
	}

	public void setGoals1(int goals1) {
		this.goals1 = goals1;
	}

	public int getGoals2() {
		return goals2;
	}

	public void setGoals2(int goals2) {
		this.goals2 = goals2;
	}

}

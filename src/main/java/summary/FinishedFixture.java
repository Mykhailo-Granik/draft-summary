package summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
public class FinishedFixture extends Fixture {

    private int homeTeamGoals;
    private int awayTeamGoals;

    public FinishedFixture(Team homeTeam, Team awayTeam, int homeTeamGoals, int awayTeamGoals, LocalDateTime dateTime) {
        super(homeTeam, awayTeam, dateTime);
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }
}

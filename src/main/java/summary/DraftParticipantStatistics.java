package summary;

import lombok.Getter;

import java.util.List;

@Getter
public class DraftParticipantStatistics {

    private int points;
    private double pointsPerMatch;

    private DraftParticipantStatistics() {

    }

    public static DraftParticipantStatistics create(List<Team> teams, List<FinishedFixture> fixtures) {
        DraftParticipantStatistics statistics = new DraftParticipantStatistics();
        for (FinishedFixture fixture : fixtures) {
            if (teams.contains(fixture.getHomeTeam())) {
                statistics.points += calculateTeamPoints(fixture.getHomeTeamGoals(), fixture.getAwayTeamGoals());
            } else if (teams.contains(fixture.getAwayTeam())) {
                statistics.points += calculateTeamPoints(fixture.getAwayTeamGoals(), fixture.getHomeTeamGoals());
            } else {
                throw new RuntimeException(String.format(
                        "It is expected that all fixtures will include the team, but it was not the case. " +
                                "Teams : %s, home team : %s, away team : %s",
                        teams,
                        fixture.getHomeTeam().toString(),
                        fixture.getAwayTeam().toString())
                );
            }
        }
        if (!fixtures.isEmpty()) {
            statistics.pointsPerMatch = statistics.points / (double) fixtures.size();
        }
        return statistics;
    }

    private static int calculateTeamPoints(int goalsScored, int goalsConceded) {
        if (goalsScored > goalsConceded) {
            return 3;
        }
        if (goalsScored < goalsConceded) {
            return 0;
        }
        return 1;
    }

}

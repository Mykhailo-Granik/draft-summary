package summary;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DraftParticipantStatisticsTest {

    @Test
    public void shouldCorrectlyCountPointsForWin() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(myTeam()),
                List.of(
                        homeFixture( 2, 1),
                        awayFixture(3, 2)
                )
        );
        assertEquals(6, receivedResult.getPoints());
        assertEquals(3, receivedResult.getPointsPerMatch(), 0.001);
    }

    private FinishedFixture homeFixture(int myGoals, int opponentGoals) {
        return new FinishedFixture(myTeam(), opponentTeam(), myGoals, opponentGoals, LocalDateTime.now());
    }

    private Team myTeam() {
        return new Team("Dynamo", Tournament.UPL);
    }

    private Team opponentTeam() {
        return new Team("Veres", Tournament.UPL);
    }

    private FinishedFixture awayFixture(int myGoals, int opponentGoals) {
        return new FinishedFixture(opponentTeam(), myTeam(), opponentGoals, myGoals, LocalDateTime.now());
    }

    @Test
    public void shouldCorrectlyCountPointsForDraw() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(myTeam()),
                List.of(
                        homeFixture(2, 2),
                        awayFixture(3, 3)
                )
        );
        assertEquals(2, receivedResult.getPoints());
        assertEquals(1, receivedResult.getPointsPerMatch(), 0.001);
    }

    @Test
    public void shouldCorrectlyCountPointsForLoss() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(myTeam()),
                List.of(
                        homeFixture(1, 2),
                        awayFixture(2, 3)
                )
        );
        assertEquals(0, receivedResult.getPoints());
        assertEquals(0, receivedResult.getPointsPerMatch(), 0.001);
    }

    @Test
    public void shouldCorrectlyCountPointsWhenZeroFixturesCompleted() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(myTeam()),
                Collections.emptyList()
        );
        assertEquals(0, receivedResult.getPoints());
        assertEquals(0, receivedResult.getPointsPerMatch(), 0.001);
    }

    @Test
    public void shouldCorrectlyCountPointsWithMixtureOfResult() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(myTeam()),
                List.of(
                        homeFixture(2, 1),
                        awayFixture(2, 2),
                        homeFixture(3, 1),
                        awayFixture(2, 3)
                )
        );
        assertEquals(7, receivedResult.getPoints());
        assertEquals(1.75, receivedResult.getPointsPerMatch(), 0.001);
    }

    @Test
    public void shouldCorrectlyCountPointsForListOfTeams() {
        DraftParticipantStatistics receivedResult = DraftParticipantStatistics.create(
                List.of(
                        myTeam(),
                        mySecondTeam()
                ),
                List.of(
                        homeFixture(1, 4),
                        awayFixture(5, 2),
                        homeFixture(mySecondTeam(), 3, 3),
                        awayFixture(mySecondTeam(), 2, 2)
                )
        );
        assertEquals(5, receivedResult.getPoints());
        assertEquals(1.25, receivedResult.getPointsPerMatch(), 0.001);
    }

    private FinishedFixture homeFixture(Team myTeam, int myGoals, int opponentGoals) {
        return new FinishedFixture(myTeam, opponentTeam(), myGoals, opponentGoals, LocalDateTime.now());
    }

    private Team mySecondTeam() {
        return new Team("Man Utd", Tournament.UPL);
    }

    private FinishedFixture awayFixture(Team myTeam, int myGoals, int opponentGoals) {
            return new FinishedFixture(opponentTeam(), myTeam, opponentGoals, myGoals, LocalDateTime.now());
    }

}
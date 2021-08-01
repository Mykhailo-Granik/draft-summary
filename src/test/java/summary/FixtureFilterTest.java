package summary;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FixtureFilterTest {

    public static final Team SHAKHTAR = new Team("Shakhtar", Tournament.UPL);
    public static final Team MARIUPOL = new Team("Mariupol", Tournament.UPL);
    public static final Team DYNAMO = new Team("Dynamo", Tournament.UPL);
    public static final Team CHORNOMORETS = new Team("Chornomorets", Tournament.UPL);
    public static final Team VORSKLA = new Team("Vorskla", Tournament.UPL);

    @Test
    public void shouldCorrectlyPerformFiltering() {
        DraftParticipant me = new DraftParticipant(
                "Petya",
                List.of(
                        DYNAMO,
                        CHORNOMORETS
                )
        );
        DraftParticipant otherParticipant = new DraftParticipant(
                "Vasya",
                List.of(
                        SHAKHTAR,
                        MARIUPOL
                )
        );
        FutureFixture dynamoShkhtar = fixture(DYNAMO, SHAKHTAR);
        FutureFixture dynamoChornomorets = fixture(DYNAMO, CHORNOMORETS);
        FutureFixture mariupolChornomorets = fixture(MARIUPOL, CHORNOMORETS);
        FutureFixture vorsklaDynamo = fixture(VORSKLA, DYNAMO);
        FixtureFilter<FutureFixture> fixtureFilter = new FixtureFilter<>(
                me,
                List.of(me, otherParticipant),
                List.of(
                        dynamoShkhtar,
                        dynamoChornomorets,
                        mariupolChornomorets,
                        vorsklaDynamo
                )
        );
        List<FutureFixture> filterResult = fixtureFilter.filter();
        assertEquals(List.of(dynamoShkhtar, mariupolChornomorets), filterResult);
    }

    private FutureFixture fixture(Team dynamo, Team shakhtar) {
        return new FutureFixture(dynamo, shakhtar, LocalDateTime.now());
    }

    @Test
    public void shouldCorrectlyHandleClubsFromDifferentTournaments() {
        Team arsenalUkraine = new Team("Arsenal", Tournament.UPL);
        Team arsenalEngland = new Team("Arsenal", Tournament.EPL);
        DraftParticipant me = new DraftParticipant(
                "Petya",
                List.of(
                        DYNAMO,
                        arsenalUkraine
                )
        );
        DraftParticipant otherParticipant = new DraftParticipant(
                "Vasya",
                List.of(
                        arsenalEngland
                )
        );
        FutureFixture dynamoArsenalUkraine = fixture(DYNAMO, arsenalUkraine);
        FutureFixture arsenalEnglandDynamo = fixture(arsenalEngland, DYNAMO);
        FixtureFilter<FutureFixture> fixtureFilter = new FixtureFilter<>(
                me,
                List.of(me, otherParticipant),
                List.of(
                        dynamoArsenalUkraine,
                        arsenalEnglandDynamo
                )
        );
        assertEquals(List.of(arsenalEnglandDynamo), fixtureFilter.filter());
    }

}
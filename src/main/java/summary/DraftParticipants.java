package summary;

import java.util.List;
import java.util.stream.Collectors;

public class DraftParticipants {

    private List<DraftParticipant> participants;
    private Fixtures fixtures;

    public DraftParticipantsSummary summary() {
        return new DraftParticipantsSummary(
                participants.stream()
                        .map(this::participantSummary)
                        .collect(Collectors.toList())
        );
    }

    private DraftParticipantSummary participantSummary(DraftParticipant participant) {
        List<FinishedFixture> finishedFixtures = filteredFinishedFixtures(participant);
        return new DraftParticipantSummary(
                participant.getName(),
                finishedFixtures,
                filteredFutureFixtures(participant),
                DraftParticipantStatistics.create(participant.getTeams(), finishedFixtures)
        );
    }

    private List<FinishedFixture> filteredFinishedFixtures(DraftParticipant participant) {
        return new FixtureFilter<>(
                participant,
                participants,
                fixtures.finishedFixtures(participant.getTeams())
        ).filter();
    }

    private List<FutureFixture> filteredFutureFixtures(DraftParticipant participant) {
        return new FixtureFilter<>(
                participant,
                participants,
                fixtures.futureFixtures(participant.getTeams())
        ).filter();
    }

}

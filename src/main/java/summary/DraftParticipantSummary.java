package summary;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class DraftParticipantSummary {

    private String name;
    private List<FinishedFixture> finishedFixtures;
    private List<FutureFixture> futureFixtures;
    private DraftParticipantStatistics statistics;

}

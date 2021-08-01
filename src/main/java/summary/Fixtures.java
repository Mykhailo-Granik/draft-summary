package summary;

import java.util.List;

public interface Fixtures {

    List<FinishedFixture> finishedFixtures(List<Team> teams);

    List<FutureFixture> futureFixtures(List<Team> teams);

}

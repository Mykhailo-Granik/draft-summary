package summary;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FixtureFilter<T extends Fixture> {

    private DraftParticipant participant;
    private List<DraftParticipant> allParticipants;
    private List<T> fixtures;
    private Set<Team> allowedOpponents;

    public FixtureFilter(DraftParticipant participant, List<DraftParticipant> allParticipants, List<T> fixtures) {
        this.participant = participant;
        this.allParticipants = allParticipants;
        this.fixtures = fixtures;
        this.allowedOpponents = allowedOpponents();
    }

    private Set<Team> allowedOpponents() {
        return allParticipants.stream()
                .filter(otherParticipant -> !otherParticipant.getName().equals(participant.getName()))
                .map(DraftParticipant::getTeams)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public List<T> filter() {
        return fixtures.stream()
                .filter(this::fixtureInvolvesAllowedTeam)
                .collect(Collectors.toList());
    }

    private boolean fixtureInvolvesAllowedTeam(T fixture) {
        return allowedOpponents.contains(fixture.getHomeTeam()) || allowedOpponents.contains(fixture.getAwayTeam());
    }
}

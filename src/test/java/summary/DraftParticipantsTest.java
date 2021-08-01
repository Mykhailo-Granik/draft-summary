package summary;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class DraftParticipantsTest {

    public static final Team DYNAMO = new Team("Dynamo", Tournament.UPL);
    public static final Team SHAKHTAR = new Team("Shakhtar", Tournament.UPL);
    public static final Team DNIPRO = new Team("Dnipro", Tournament.UPL);
    public static final Team METALLIST = new Team("Metallist", Tournament.UPL);
    @Mock
    private Fixtures fixtures;

    @Test
    public void shouldCorrectlyReturnSummary() {
        List<Team> participant1Teams = List.of(DYNAMO, SHAKHTAR);
        DraftParticipant participant1 = new DraftParticipant("Kolya", participant1Teams);
        List<Team> participant2Teams = List.of(DNIPRO, METALLIST);
        DraftParticipant participant2 = new DraftParticipant("Vasya", participant2Teams);
    }

}
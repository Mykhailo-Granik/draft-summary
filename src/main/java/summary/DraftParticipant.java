package summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DraftParticipant {

    private String name;
    private List<Team> teams;

}

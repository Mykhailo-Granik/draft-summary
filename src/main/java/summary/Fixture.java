package summary;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Fixture {

    private Team homeTeam;
    private Team awayTeam;
    private LocalDateTime dateTime;

}

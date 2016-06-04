package nl.vpro.poel.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class MatchDTO {

    private Long id;

    private String homeTeam;

    private String awayTeam;

    // This doesn't work because posted string does not contain seconds: @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start;

    private Integer homeTeamGoals;

    private Integer awayTeamGoals;

    private Long roundId;
}

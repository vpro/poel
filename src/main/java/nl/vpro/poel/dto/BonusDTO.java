package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BonusDTO {

    private Long id;

    private String question;

    private BonusCategory category;

    // This doesn't work because posted string does not contain seconds: @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date start;

    private Long answerId;

    private Long roundId;

    private Integer score;
}

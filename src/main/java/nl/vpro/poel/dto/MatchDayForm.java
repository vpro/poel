package nl.vpro.poel.dto;

import lombok.Data;

import java.util.List;

@Data
public class MatchDayForm {

    private List<MatchDayDTO> matchDays;
}

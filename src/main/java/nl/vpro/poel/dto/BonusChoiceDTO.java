package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.BonusCategory;
import nl.vpro.poel.domain.BonusChoice;

@Data
public class BonusChoiceDTO {

    private Long id;

    private BonusCategory category;

    private String value;
}

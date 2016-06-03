package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.Bulletin;

import java.util.List;

@Data
public class BulletinForm {

    private List<Bulletin> bulletins;
}

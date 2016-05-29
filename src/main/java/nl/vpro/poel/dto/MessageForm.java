package nl.vpro.poel.dto;

import lombok.Data;
import nl.vpro.poel.domain.Message;

import java.util.List;

@Data
public class MessageForm {

    private List<Message> messages;
}

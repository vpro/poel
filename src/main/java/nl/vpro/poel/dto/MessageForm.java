package nl.vpro.poel.dto;

import nl.vpro.poel.domain.Message;

import java.util.List;

public class MessageForm {

    private List<Message> messages;

    public List<Message> getMessages() { return messages; }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "MessageForm{" +
                "messages=" + messages +
                '}';
    }
}

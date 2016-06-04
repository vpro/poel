package nl.vpro.poel.service;

import lombok.extern.slf4j.Slf4j;
import nl.vpro.poel.domain.Message;
import nl.vpro.poel.dto.MessageForm;
import nl.vpro.poel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) { this.messageRepository = messageRepository; }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageRepository.findOne(id));
    }

    @Override
    public Optional<Message> findByKey(String key) {
        return messageRepository.findByKey(key);
    }

    @Override
    public Optional<String> getText(String key) {
        Optional<Message> message = messageRepository.findByKey(key);
        if (message.isPresent()) {
            return Optional.of(message.get().getText());
        }
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() { return messageRepository.findAll(); }

    @Override
    public void setMessages(MessageForm messageForm) {
        Set<Long> idsToRemove = findAll().stream().map(Message::getId).collect(Collectors.toSet());

        for (Message postedMessage : messageForm.getMessages()) {

            Long id = postedMessage.getId();

            idsToRemove.remove(id);

            String key = postedMessage.getKey();
            String text = postedMessage.getText();

            if ( key == null ) {
                log.warn("Ignoring message update {}, because it is incomplete");
                continue;
            }

            Message message;
            if ( id == null ) {
                message = new Message();
            } else {
                message = messageRepository.findOne(id);

                if (message == null) {
                    log.warn("Ignoring message update {}, because no message exists for this id", postedMessage);
                    continue;
                }
            }

            message.setKey( key );
            message.setText( text );

            messageRepository.save(message);
        }

        // Messages not included in the form are deleted from the repository
        idsToRemove.stream().forEach(messageRepository::delete);
    }
}

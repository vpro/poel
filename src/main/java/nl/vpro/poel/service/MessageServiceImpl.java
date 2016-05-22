package nl.vpro.poel.service;

import nl.vpro.poel.domain.Message;
import nl.vpro.poel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) { this.messageRepository = messageRepository; }

    @Override
    public Optional<Message> findById(Long id) {
        return Optional.ofNullable(messageRepository.getOne(id));
    }

    @Override
    public Optional<Message> findByKey(String key) {
        return Optional.ofNullable(messageRepository.findByKey(key));
    }

    @Override
    public Optional<String> getText(String key) {
        Message message = messageRepository.findByKey(key);
        if (message == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(message.getText());
    }

    @Override
    public List<Message> findAll() { return messageRepository.findAll(); }
}

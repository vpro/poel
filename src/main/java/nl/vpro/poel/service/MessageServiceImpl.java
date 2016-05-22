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
        return Optional.of(messageRepository.findByKey(key));
    }

    @Override
    public List<Message> findAll() { return messageRepository.findAll(); }
}

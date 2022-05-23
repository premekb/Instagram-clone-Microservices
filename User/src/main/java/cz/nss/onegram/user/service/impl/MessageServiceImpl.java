package cz.nss.onegram.user.service.impl;

import cz.nss.onegram.user.dao.MessageRepository;
import cz.nss.onegram.user.dao.UserRepository;
import cz.nss.onegram.user.model.Message;
import cz.nss.onegram.user.model.User;
import cz.nss.onegram.user.service.interfaces.MessageService;
import cz.nss.onegram.user.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    @Override
    public Message findById(int id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message sendMessage(String message, int receiver_id) {

        //TODO na validaci uzivatele, mozna udelat validaci na public profil
        User currentUser = userService.getCurrentUser();
        User receiver = userService.findById(receiver_id);

        Message newMessage = new Message();
        newMessage.setMessage(message);
        newMessage.setReceiver(receiver);

        messageRepository.save(newMessage);

        log.info("sent message: {}", newMessage);

        return newMessage;
    }

    @Override
    public int removeMessage(int id) {

        User currentUser = userService.getCurrentUser();
        Message messageToDelete = findById(id);

        if (messageToDelete == null) {
            log.error("message does not exists");
            return 0;
        } else {
            messageToDelete.setDeleted(true);
            messageRepository.save(messageToDelete);
            log.info("deleted message: {}", messageToDelete);
        }
        return 0;
    }

    @Override
    public Message makeMessageRead(int id) {
        Message message = findById(id);
        User current = userService.getCurrentUser();

        if (message == null) {
            log.error("message does not exists");
            return null;
        }
        ;

        return null;
    }

    @Override
    public Message makeMessageUnread(int id) {
        Message message = findById(id);
        User current = userService.getCurrentUser();

        if (message == null) {
            log.error("message does not exists");
            return null;
        }
        ;

        return null;
    }

    //TODO
    @Override
    public List<Message> getAllMessageWithUser(int receiver_id) {
        List<Message> m = messageRepository.getAllMessageWithUser(receiver_id, userService.getCurrentUser().getId());
        m.addAll(messageRepository.getAllMessageWithUser(userService.getCurrentUser().getId(), receiver_id));
        return m;
    }
}

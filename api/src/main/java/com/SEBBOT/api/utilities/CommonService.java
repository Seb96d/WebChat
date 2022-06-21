package com.SEBBOT.api.utilities;


import com.SEBBOT.api.entities.Conversation;
import com.SEBBOT.api.entities.Message;
import com.SEBBOT.api.entities.QnA;
import com.SEBBOT.api.entities.User;
import com.SEBBOT.api.repos.ChatRepo;
import com.SEBBOT.api.repos.ConvRepo;
import com.SEBBOT.api.repos.QRepo;
import com.SEBBOT.api.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommonService {

    @Autowired
    ChatRepo chatRepo;

    @Autowired
    ConvRepo convRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    QRepo qRepo;


    public Conversation createConversation(String anonymous) {
        Conversation  conversation = new Conversation();
        conversation.setUser("Anonymous");
        conversation.setStarttime( new Date());
        convRepo.save( conversation );

        return  conversation;
    }

    public void saveChat(Long index, String user, String msg , String owner) {
        Message message = new Message();
        message.setMessage(msg);
        message.setUser(user);
        message.setOwner(owner);
        message.setConversation(index);
        message.setTime( new Date());
        chatRepo.save(message);
    }

    public String isInQnA(String message) {

        List<QnA> qnAList =  qRepo.findAllByQuestion(message);

        if(qnAList.isEmpty()){
            return "Sorry! Couldnt help this moment. Try something else....";
        } else {
            return qnAList.get(0).getAnswer();
        }

    }

    public void saveUser(User user) {
        userRepo.save(user);

    }

    public User getUser(String username) {

        return userRepo.findAllByEmail(username);

    }

    public List<Conversation> getConversations(String username) {
        return convRepo.findAllByUser(username);
    }

    public List<Message> getMessages(Long id , String user) {
        return chatRepo.findAllByConversationAndUser(id , user);
    }

    public List<Conversation> getAdminConversations() {
        return convRepo.findAll();
    }

    public List<Message> getAdminMessages(Long id ) {
        return chatRepo.findAllByConversation(id );
    }
}

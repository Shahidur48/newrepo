package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.model.JmsDTO;
import edu.baylor.cs.se.hibernate.model.Team;
import edu.baylor.cs.se.hibernate.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    private JmsTemplate jmsTemplate;
    @Autowired
    public JmsProducer(JmsTemplate jmsTemplate){
        this.jmsTemplate = jmsTemplate;
    }

    public void sendToJms(UserResponse userResponse) {
        jmsTemplate.convertAndSend("userResponse.queue",userResponse);
    }

}

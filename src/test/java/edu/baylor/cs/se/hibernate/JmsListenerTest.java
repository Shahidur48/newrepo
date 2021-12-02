package edu.baylor.cs.se.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMockMvc
public class JmsListenerTest {
    @Autowired
    private JmsTemplate jmsTemplate;

//    @Test
//    public void test() {
//        this.jmsTemplate.convertAndSend("foo", "Hello, world!");
//        this.jmsTemplate.setReceiveTimeout(10_000);
//        assertThat(this.jmsTemplate.receiveAndConvert("bar")).isEqualTo("HELLO, WOLRD!");
//    }
}

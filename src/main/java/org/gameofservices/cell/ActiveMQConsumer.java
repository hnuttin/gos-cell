package org.gameofservices.cell;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.net.InetAddress;

public class ActiveMQConsumer implements ExceptionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveMQConsumer.class);

    private Session session;
    private Connection connection;
    private TopicSubscriber durableSubscriber;

    public ActiveMQConsumer() {
        try {
            LOGGER.info("creating durable consumer...");

            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://activemq.dev.gos:61616");

            // Create a Connection
            connection = connectionFactory.createConnection();
            connection.setClientID(InetAddress.getLocalHost().getHostName());
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Topic destination = session.createTopic("gos.cell.generation");

            durableSubscriber = session.createDurableSubscriber(destination, InetAddress.getLocalHost().getHostName());
            durableSubscriber.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    LOGGER.info("message: {}", message);
                    CellContext.CELL_STATUS_PROVIDER.newGeneration();
                }

            });
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void stop() {
        try {
            durableSubscriber.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            LOGGER.error(e.getMessage(), e);
        }

    }

    @Override
    public void onException(JMSException ex) {
        LOGGER.error("jms consumer exception", ex);
    }

}

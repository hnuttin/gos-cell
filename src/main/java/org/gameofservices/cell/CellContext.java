package org.gameofservices.cell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellContext.class);

    public static CellStatusProvider CELL_STATUS_PROVIDER;

    public static ActiveMQConsumer ACTIVE_MQ_CONSUMER;

    private static CellPositionService CELL_POSITION_SERVICE;

    private static StatusService STATUS_SERVICE;

    public static void init() {
        ACTIVE_MQ_CONSUMER = new ActiveMQConsumer();
        CELL_POSITION_SERVICE = new CellPositionService();
        STATUS_SERVICE = new StatusService(CELL_POSITION_SERVICE);

        final String initStatus = System.getenv("GOS_INIT_STATUS");
        LOGGER.info("initial status: {}", initStatus);

        CELL_STATUS_PROVIDER = new CellStatusProvider(CELL_POSITION_SERVICE, STATUS_SERVICE, Boolean.parseBoolean(initStatus));
    }

    public static void stop() {
        ACTIVE_MQ_CONSUMER.stop();
    }
}

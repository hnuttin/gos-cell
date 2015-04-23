package org.gameofservices.cell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class CellStatusProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellStatusProvider.class);

    private final CellPositionService cellPositionService;
    private final StatusService statusService;

    private boolean previousStatus;
    private boolean currentStatus;

    private final AtomicInteger currentGeneration = new AtomicInteger(1);

    public CellStatusProvider(CellPositionService cellPositionService, StatusService statusService, boolean initStatus) {
        this.cellPositionService = cellPositionService;
        this.statusService = statusService;
        this.currentStatus = initStatus;
    }

    public boolean isStatusOk() {
        LOGGER.debug("my cell position; {}", cellPositionService.getMyCellPosition());
        LOGGER.info("return currentStatus: {}", currentStatus);
        return currentStatus;
    }

    public boolean getPreviousStatus(int generation) throws InterruptedException {
        LOGGER.debug("get previous status for generation {}", generation);
        while (generation != currentGeneration.get()) {
            LOGGER.info("sleeping for 1000ms");
            Thread.sleep(100);
        }
        LOGGER.debug("previous status: {}", previousStatus);
        return previousStatus;
    }

    public void newGeneration() {
        LOGGER.info("new generation");
        synchronized (currentGeneration) {
            previousStatus = currentStatus;
            currentGeneration.addAndGet(1);
        }
        currentStatus = statusService.determineNewStatus(currentStatus, currentGeneration.get());
    }

}

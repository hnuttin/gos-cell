package org.gameofservices.cell;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StatusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatusService.class);

    private final CellPositionService cellPositionService;

    public StatusService(CellPositionService cellPositionService) {
        this.cellPositionService = cellPositionService;
    }

    public boolean determineNewStatus(boolean currentStatus, int generation) {
        LOGGER.info("determine new status for generation {}", generation);
        final boolean leftStatus = getStatus(Direction.LEFT, generation);
        final boolean rightStatus = getStatus(Direction.RIGHT, generation);
        final boolean upStatus = getStatus(Direction.UP, generation);
        final boolean downStatus = getStatus(Direction.DOWN, generation);
        final boolean upLeftStatus = getStatus(Direction.UPLEFT, generation);
        final boolean upRightStatus = getStatus(Direction.UPRIGHT, generation);
        final boolean downLeftStatus = getStatus(Direction.DOWNLEFT, generation);
        final boolean downRightStatus = getStatus(Direction.DOWNRIGHT, generation);
        final int aliveCount = intValue(leftStatus) +
                intValue(rightStatus) +
                intValue(upStatus) +
                intValue(downStatus) +
                intValue(upLeftStatus) +
                intValue(upRightStatus) +
                intValue(downLeftStatus) +
                intValue(downRightStatus);

        final boolean newStatus;
        if (currentStatus) {
            newStatus = aliveCount == 2 || aliveCount == 3;
        } else {
            newStatus = aliveCount == 3;
        }

        LOGGER.info("new status: {}", newStatus);
        return newStatus;
    }

    private int intValue(boolean status) {
        return status ? 1 : 0;
    }

    boolean getStatus(Direction direction, int generation) {
        boolean status = false;

        Map<String, Integer> urlVariables = new HashMap<String, Integer>();
        urlVariables.put("generation", generation);

        Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(cellPositionService.getMyCellPosition(), direction);
        if (cellPosition != null) {
            final String cellHostName = hostNameForCellPosition(cellPosition);
            final String urlForHostName = urlForHostName(cellHostName, generation);

            status = new CellStatusCommand(urlForHostName).execute();
        }

        LOGGER.info("status for direction {} ({}): {}", direction, cellPosition, status);

        return status;
    }

    private String urlForHostName(String leftHostName, int generation) {
        return "http://" + leftHostName + ":8080/previous?generation=" + generation;
    }

    String hostNameForCellPosition(Pair<Character, Integer> cellPosition) {
        return cellPosition.getLeft() + "" + cellPosition.getRight() + ".cell.dev.gos";
    }
}

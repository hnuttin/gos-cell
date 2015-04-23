package org.gameofservices.cell;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CellPositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CellPositionService.class);

    private final static String HOSTNAME_PATTERN = "([a-z])(\\d{1,2}+)\\.cell.dev.gos";
    public static final char ROW_EDGE = 'd';
    public static final int COLUMN_EDGE = 4;

    public Pair<Character, Integer> determineCellPosition(Pair<Character, Integer> myCellPosition, Direction direction) {
        switch (direction) {
            case UP:
                if (myCellPosition.getLeft() == 'a') {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() - 1), myCellPosition.getRight());
                }
            case DOWN:
                if (myCellPosition.getLeft() == ROW_EDGE) {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() + 1), myCellPosition.getRight());
                }
            case LEFT:
                if (myCellPosition.getRight() == 1) {
                    return null;
                } else {
                    return Pair.of(myCellPosition.getLeft(), myCellPosition.getRight() - 1);
                }
            case RIGHT:
                if (myCellPosition.getRight() == COLUMN_EDGE) {
                    return null;
                } else {
                    return Pair.of(myCellPosition.getLeft(), myCellPosition.getRight() + 1);
                }
            case UPLEFT:
                if (myCellPosition.getLeft() == 'a' || myCellPosition.getRight() == 1) {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() - 1), myCellPosition.getRight() - 1);
                }
            case UPRIGHT:
                if (myCellPosition.getLeft() == 'a' || myCellPosition.getRight() == COLUMN_EDGE) {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() - 1), myCellPosition.getRight() + 1);
                }
            case DOWNLEFT:
                if (myCellPosition.getLeft() == ROW_EDGE || myCellPosition.getRight() == 1) {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() + 1), myCellPosition.getRight() - 1);
                }
            case DOWNRIGHT:
                if (myCellPosition.getLeft() == ROW_EDGE || myCellPosition.getRight() == COLUMN_EDGE) {
                    return null;
                } else {
                    return Pair.of((char) (myCellPosition.getLeft() + 1), myCellPosition.getRight() + 1);
                }
            default:
                throw new IllegalArgumentException(direction.name());
        }
    }

    public Pair<Character, Integer> getMyCellPosition() {
        String myHost = myHostName();
        LOGGER.debug("myHost: {}", myHost);
        final Pattern pattern = Pattern.compile(HOSTNAME_PATTERN);
        Matcher matcher = pattern.matcher(myHost);
        if (matcher.matches()) {
            final String row = matcher.group(1);
            final String column = matcher.group(2);
            return Pair.of(row.charAt(0), Integer.valueOf(column));
        } else {
            throw new IllegalArgumentException(myHost);
        }
    }

    String myHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}

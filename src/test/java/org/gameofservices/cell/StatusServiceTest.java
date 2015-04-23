package org.gameofservices.cell;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class StatusServiceTest {

    @Test
    public void testHostNameForCellPosition() throws Exception {
        final String hostNameForCellPosition = new StatusService(null).hostNameForCellPosition(Pair.of('a', 2));
        assertThat(hostNameForCellPosition).isEqualTo("a2.cell.dev.gos");
    }

    @Test
    public void testDetermineNewStatus_LiveWith1LiveNeighbour() throws Exception {
        final boolean newStatus = new With1LiveNeighbourStatusService().determineNewStatus(true, 1);

        assertThat(newStatus).isFalse();
    }

    @Test
    public void testDetermineNewStatus_LiveWith2LiveNeighbours() throws Exception {
        final boolean newStatus = new With2LiveNeighboursStatusService().determineNewStatus(true, 1);

        assertThat(newStatus).isTrue();
    }

    @Test
    public void testDetermineNewStatus_LiveWith3LiveNeighbours() throws Exception {
        final boolean newStatus = new With3LiveNeighboursStatusService().determineNewStatus(true, 1);

        assertThat(newStatus).isTrue();
    }

    @Test
    public void testDetermineNewStatus_LiveWith4LiveNeighbours() throws Exception {
        final boolean newStatus = new With4LiveNeighboursStatusService().determineNewStatus(true, 1);

        assertThat(newStatus).isFalse();
    }

    @Test
    public void testDetermineNewStatus_DeadWith1LiveNeighbour() throws Exception {
        final boolean newStatus = new With1LiveNeighbourStatusService().determineNewStatus(false, 1);

        assertThat(newStatus).isFalse();
    }

    @Test
    public void testDetermineNewStatus_DeadWith2LiveNeighbours() throws Exception {
        final boolean newStatus = new With2LiveNeighboursStatusService().determineNewStatus(false, 1);

        assertThat(newStatus).isFalse();
    }

    @Test
    public void testDetermineNewStatus_DeadWith3LiveNeighbours() throws Exception {
        final boolean newStatus = new With3LiveNeighboursStatusService().determineNewStatus(false, 1);

        assertThat(newStatus).isTrue();
    }

    @Test
    public void testDetermineNewStatus_DeadWith4LiveNeighbours() throws Exception {
        final boolean newStatus = new With4LiveNeighboursStatusService().determineNewStatus(false, 1);

        assertThat(newStatus).isFalse();
    }

    private static class With1LiveNeighbourStatusService extends StatusService {

        public With1LiveNeighbourStatusService() {
            super(null);
        }

        @Override
        boolean getStatus(Direction direction, int generation) {
            switch (direction) {
                case UP: return true;
                default: return false;
            }
        }
    }


    private static class With2LiveNeighboursStatusService extends StatusService {

        public With2LiveNeighboursStatusService() {
            super(null);
        }

        @Override
        boolean getStatus(Direction direction, int generation) {
            switch (direction) {
                case UP: return true;
                case DOWN: return true;
                default: return false;
            }
        }
    }

    private static class With3LiveNeighboursStatusService extends StatusService {

        public With3LiveNeighboursStatusService() {
            super(null);
        }

        @Override
        boolean getStatus(Direction direction, int generation) {
            switch (direction) {
                case UP: return true;
                case DOWN: return true;
                case LEFT: return true;
                default: return false;
            }
        }
    }

    private static class With4LiveNeighboursStatusService extends StatusService {

        public With4LiveNeighboursStatusService() {
            super(null);
        }

        @Override
        boolean getStatus(Direction direction, int generation) {
            switch (direction) {
                case UP: return true;
                case DOWN: return true;
                case LEFT: return true;
                case RIGHT: return true;
                default: return false;
            }
        }
    }
}

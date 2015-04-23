package org.gameofservices.cell;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class CellPositionServiceTest {

    private final CellPositionService cellPositionService = spy(new CellPositionService());

    @Test
    public void testDetermineCellPosition_Left() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.LEFT);

        assertThat(cellPosition.getLeft()).isEqualTo('b');
        assertThat(cellPosition.getRight()).isEqualTo(1);
    }

    @Test
    public void testDetermineCellPosition_Right() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.RIGHT);

        assertThat(cellPosition.getLeft()).isEqualTo('b');
        assertThat(cellPosition.getRight()).isEqualTo(3);
    }

    @Test
    public void testDetermineCellPosition_Up() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.UP);

        assertThat(cellPosition.getLeft()).isEqualTo('a');
        assertThat(cellPosition.getRight()).isEqualTo(2);
    }

    @Test
    public void testDetermineCellPosition_Down() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', 2), Direction.DOWN);

        assertThat(cellPosition.getLeft()).isEqualTo('b');
        assertThat(cellPosition.getRight()).isEqualTo(2);
    }

    @Test
    public void testDetermineCellPosition_UpLeft() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.UPLEFT);

        assertThat(cellPosition.getLeft()).isEqualTo('a');
        assertThat(cellPosition.getRight()).isEqualTo(1);
    }

    @Test
    public void testDetermineCellPosition_UpRight() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.UPRIGHT);

        assertThat(cellPosition.getLeft()).isEqualTo('a');
        assertThat(cellPosition.getRight()).isEqualTo(3);
    }

    @Test
    public void testDetermineCellPosition_DownLeft() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', 2), Direction.DOWNLEFT);

        assertThat(cellPosition.getLeft()).isEqualTo('c');
        assertThat(cellPosition.getRight()).isEqualTo(1);
    }

    @Test
    public void testDetermineCellPosition_DownRight() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', 2), Direction.DOWNRIGHT);

        assertThat(cellPosition.getLeft()).isEqualTo('b');
        assertThat(cellPosition.getRight()).isEqualTo(3);
    }

    @Test
    public void testDetermineCellPosition_Left_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', 1), Direction.LEFT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_Right_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('b', CellPositionService.COLUMN_EDGE), Direction.RIGHT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_Up_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', 1), Direction.UP);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_Down_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of(CellPositionService.ROW_EDGE, 2), Direction.DOWN);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_UpLeft_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', 1), Direction.UPLEFT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_UpRight_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('a', CellPositionService.COLUMN_EDGE), Direction.UPRIGHT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_DownLeft_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of('d', 1), Direction.DOWNLEFT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testDetermineCellPosition_DownRight_Edge() throws Exception {
        final Pair<Character, Integer> cellPosition = cellPositionService.determineCellPosition(Pair.of(CellPositionService.ROW_EDGE, CellPositionService.COLUMN_EDGE), Direction.DOWNRIGHT);

        assertThat(cellPosition).isNull();
    }

    @Test
    public void testGetMyCellPosition() {
        when(cellPositionService.myHostName()).thenReturn("a1.cell.dev.gos");

        final Pair<Character, Integer> cellPosition = cellPositionService.getMyCellPosition();

        assertThat(cellPosition.getLeft()).isEqualTo('a');
        assertThat(cellPosition.getRight()).isEqualTo(1);
    }

}

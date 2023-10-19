/*
 * Copyright (c) 2023. CryingPun4
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package study01.tictactoe.component.strategy;

import study01.tictactoe.component.ComputerMoveStrategy;
import study01.tictactoe.model.game.Cell;
import study01.tictactoe.model.game.GameTable;
import study01.tictactoe.model.game.Sign;

import java.util.Random;


/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {

    private final int expectedCountEmptyCells;

    protected AbstractComputerMoveStrategy(final int expectedCountEmptyCells) {
        this.expectedCountEmptyCells = expectedCountEmptyCells;
    }

    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        final Sign findSign = getFindSign(moveSign);
        final BestCells bestCells = new BestCells();
        findBestCellForMoveByRows(gameTable, findSign, bestCells);
        findBestCellForMoveByCols(gameTable, findSign, bestCells);
        findBestCellForMoveByMainDiagonal(gameTable, findSign, bestCells);
        findBestCellForMoveBySecondaryDiagonal(gameTable, findSign, bestCells);
        if (bestCells.count > 0) {
            final Cell randomCell = bestCells.emptyCells[new Random().nextInt(bestCells.count)];
            gameTable.setSign(randomCell, moveSign);
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("Convert2MethodRef")
    private void findBestCellForMoveByRows(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        for (int i = 0; i < 3; i++) {
            findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, i, (k, j) -> new Cell(k, j));
        }
    }

    private void findBestCellForMoveByCols(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        for (int i = 0; i < 3; i++) {
            findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, i, (k, j) -> new Cell(j, k));
        }
    }

    private void findBestCellForMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, -1, (k, j) -> new Cell(j, j));
    }

    private void findBestCellForMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final BestCells bestCells) {
        findBestCellForMoveUsingLambdaConversion(gameTable, findSign, bestCells, -1, (k, j) -> new Cell(j, 2 - j));
    }

    protected abstract Sign getFindSign(Sign moveSign);


    private void findBestCellForMoveUsingLambdaConversion(final GameTable gameTable,
                                                          final Sign findSign,
                                                          final BestCells bestCells,
                                                          final int i,
                                                          final Lambda lambda) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        final Cell[] localEmptyCells = new Cell[3];
        int count = 0;
        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.convert(i, j);
            if (gameTable.isEmpty(cell)) {
                localEmptyCells[count++] = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == findSign) {
                countSignCells++;
            } else {
                break;
            }
        }
        if (countEmptyCells == expectedCountEmptyCells && countSignCells == 3 - expectedCountEmptyCells) {
            for (int j = 0; j < count; j++) {
                bestCells.add(localEmptyCells[j]);
            }
        }
    }

    @FunctionalInterface
    private interface Lambda {
        Cell convert(int k, int j);
    }

    private static class BestCells {

        private final Cell[] emptyCells = new Cell[9];

        private int count;

        private void add(final Cell cell) {
            emptyCells[count++] = cell;
        }
    }

}

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

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class WinNowComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public boolean tryToMakeMove(final GameTable gameTable, final Sign sign) {
        return tryToMakeMoveByRows(gameTable, sign) ||
                tryToMakeMoveByCols(gameTable, sign) ||
                tryToMakeMoveByMainDiagonal(gameTable, sign) ||
                tryToMakeMoveBySecondaryDiagonal(gameTable, sign);
    }

    private boolean tryToMakeMoveBySecondaryDiagonal(final GameTable gameTable, final Sign sign) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell lastEmptyCell = null;
        for (int i = 0; i < 3; i++) {
            final Cell cell = new Cell(i, 2 - i);
            if (gameTable.isEmpty(cell)) {
                lastEmptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == sign) {
                countSignCells++;
            } else {
                break;
            }
            if (countEmptyCells == 1 && countSignCells == 2) {
                gameTable.setSign(lastEmptyCell, sign);
                return true;
            }
        }
        return false;
    }

    private boolean tryToMakeMoveByMainDiagonal(final GameTable gameTable, final Sign sign) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell lastEmptyCell = null;
        for (int i = 0; i < 3; i++) {
            final Cell cell = new Cell(i, i);
            if (gameTable.isEmpty(cell)) {
                lastEmptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == sign) {
                countSignCells++;
            } else {
                break;
            }
            if (countEmptyCells == 1 && countSignCells == 2) {
                gameTable.setSign(lastEmptyCell, sign);
                return true;
            }
        }
        return false;
    }

    private boolean tryToMakeMoveByCols(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            int countEmptyCells = 0;
            int countSignCells = 0;
            Cell lastEmptyCell = null;
            for (int j = 0; j < 3; j++) {
                final Cell cell = new Cell(j, i);
                if (gameTable.isEmpty(cell)) {
                    lastEmptyCell = cell;
                    countEmptyCells++;
                } else if (gameTable.getSign(cell) == sign) {
                    countSignCells++;
                } else {
                    break;
                }
                if (countEmptyCells == 1 && countSignCells == 2) {
                    gameTable.setSign(lastEmptyCell, sign);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean tryToMakeMoveByRows(final GameTable gameTable, final Sign sign) {
        for (int i = 0; i < 3; i++) {
            int countEmptyCells = 0;
            int countSignCells = 0;
            Cell lastEmptyCell = null;
            for (int j = 0; j < 3; j++) {
                final Cell cell = new Cell(i, j);
                if (gameTable.isEmpty(cell)) {
                    lastEmptyCell = cell;
                    countEmptyCells++;
                } else if (gameTable.getSign(cell) == sign) {
                    countSignCells++;
                } else {
                    break;
                }
                if (countEmptyCells == 1 && countSignCells == 2) {
                    gameTable.setSign(lastEmptyCell, sign);
                    return true;
                }
            }
        }
        return false;
    }
}

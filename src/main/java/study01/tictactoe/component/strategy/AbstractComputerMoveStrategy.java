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
public abstract class AbstractComputerMoveStrategy implements ComputerMoveStrategy {
    @Override
    public final boolean tryToMakeMove(final GameTable gameTable, final Sign moveSign) {
        final Sign findSign = getFindSign(moveSign);
        return tryToMakeMoveByRows(gameTable, findSign, moveSign) ||
                tryToMakeMoveByCols(gameTable, findSign, moveSign) ||
                tryToMakeMoveByMainDiagonal(gameTable, findSign, moveSign) ||
                tryToMakeMoveBySecondaryDiagonal(gameTable, findSign, moveSign);
    }

    protected abstract Sign getFindSign(Sign moveSign);

    private boolean tryToMakeMoveBySecondaryDiagonal(final GameTable gameTable, final Sign findSign, final Sign moveSign) {
        return (tryToMakeUsingLambdaConversion(gameTable, findSign, moveSign, -1, (k, j) -> new Cell(j, 2 - j)));
    }

    private boolean tryToMakeMoveByMainDiagonal(final GameTable gameTable, final Sign findSign, final Sign moveSign) {
        return (tryToMakeUsingLambdaConversion(gameTable, findSign, moveSign, -1, (k, j) -> new Cell(j, j)));
    }

    private boolean tryToMakeMoveByCols(final GameTable gameTable, final Sign findSign, final Sign moveSign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeUsingLambdaConversion(gameTable, findSign, moveSign, i, (k, j) -> new Cell(j, k))) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("Convert2MethodRef")
    private boolean tryToMakeMoveByRows(final GameTable gameTable, final Sign findSign, final Sign moveSign) {
        for (int i = 0; i < 3; i++) {
            if (tryToMakeUsingLambdaConversion(gameTable, findSign, moveSign, i, (k, j) -> new Cell(k, j))) {
                return true;
            }
        }
        return false;
    }


    private boolean tryToMakeUsingLambdaConversion(final GameTable gameTable,
                                                   final Sign findSign,
                                                   final Sign moveSign,
                                                   final int i,
                                                   final Lambda lambda) {
        int countEmptyCells = 0;
        int countSignCells = 0;
        Cell lastEmptyCell = null;
        for (int j = 0; j < 3; j++) {
            final Cell cell = lambda.convert(i, j);
            if (gameTable.isEmpty(cell)) {
                lastEmptyCell = cell;
                countEmptyCells++;
            } else if (gameTable.getSign(cell) == findSign) {
                countSignCells++;
            } else {
                break;
            }
            if (countEmptyCells == 1 && countSignCells == 2) {
                gameTable.setSign(lastEmptyCell, moveSign);
                return true;
            }
        }
        return false;
    }


    @FunctionalInterface
    private interface Lambda {

        Cell convert(int k, int j);
    }
}

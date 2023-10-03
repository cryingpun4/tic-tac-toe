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

package study01.tictactoe.component.console.keypad;

import study01.tictactoe.component.console.CellNumberConverter;
import study01.tictactoe.model.game.Cell;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class DesktopNumericKeypadCellNumberConverter implements CellNumberConverter {

    private final char[][] mapping = {
            {'7', '8', '9'},
            {'4', '5', '6'},
            {'1', '2', '3'}
    };


    public Cell toCell(final char number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (mapping[i][j] == number) {
                    return new Cell(i, j);
                }
            }
        }
        throw new IllegalArgumentException(
                String.format("Number parameter must be between '1' and '9'! Current value is '%s'!", number)
        );
    }


    public char toNumber(final Cell cell) {
        try {
            return mapping[cell.getRow()][cell.getCol()];
        } catch (ArrayIndexOutOfBoundsException ignore) {
            throw new IllegalArgumentException(
                    String.format("Row and col indexes must be between 0 and 2! Current row is %s, current col is %s!",
                            cell.getRow(), cell.getCol())
            );
        }
    }
}

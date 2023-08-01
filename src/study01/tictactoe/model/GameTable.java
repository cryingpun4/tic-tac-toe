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

package study01.tictactoe.model;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class GameTable {

    private final char[][] table = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    public boolean isEmpty(Cell cell) {
        return table[cell.getRow()][cell.getCol()] == ' ';
    }

    public char getSign(Cell cell) {
        return table[cell.getRow()][cell.getCol()];
    }

    public void setSign(Cell cell, char sign) {
        table[cell.getRow()][cell.getCol()] = sign;
    }
}

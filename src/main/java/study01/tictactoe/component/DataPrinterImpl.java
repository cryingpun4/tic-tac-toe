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

package study01.tictactoe.component;

import study01.tictactoe.model.Cell;
import study01.tictactoe.model.GameTable;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class DataPrinterImpl {

    private final CellNumberConverter cellNumberConverter;

    public DataPrinterImpl(final CellNumberConverter cellNumberConverter) {
        this.cellNumberConverter = cellNumberConverter;
    }

    public void printMappingTable() {
        print((i, j) -> String.valueOf(cellNumberConverter.toNumber(new Cell(i, j))));
    }

    public void printGameTable(final GameTable gameTable) {
        print((i, j) -> String.valueOf(gameTable.getSign(new Cell(i, j))));
    }


    private void print(final Lambda lambda) {
        for (int i = 0; i < 3; i++) {
            System.out.println("-------------");
            for (int j = 0; j < 3; j++) {
                System.out.print("| " + lambda.getValue(i, j) + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    @FunctionalInterface
    private interface Lambda {
        String getValue(int i, int j);
    }
}
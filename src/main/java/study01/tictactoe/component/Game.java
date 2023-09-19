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


import study01.tictactoe.model.GameTable;
import study01.tictactoe.model.Player;

import java.util.Random;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class Game {

    private final DataPrinter dataPrinter;

    private final Player player1;

    private final Player player2;

    private final boolean canSecondPlayerMakeFirstMove;


    private final WinnerVerifier winnerVerifier;
    private final CellVerifier cellVerifier;


    public Game(final DataPrinter dataPrinter,
                final Player player1,
                final Player player2,
                final boolean canSecondPlayerMakeFirstMove, final WinnerVerifier winnerVerifier,
                final CellVerifier cellVerifier) {
        this.dataPrinter = dataPrinter;
        this.player1 = player1;
        this.player2 = player2;
        this.canSecondPlayerMakeFirstMove = canSecondPlayerMakeFirstMove;
        this.winnerVerifier = winnerVerifier;
        this.cellVerifier = cellVerifier;
    }

    public void play() {
        dataPrinter.printInfoMessage("Use the following mapping table to specify a cell using numbers from 1 to 9");
        dataPrinter.printMappingTable();
        final GameTable gameTable = new GameTable();
        if (canSecondPlayerMakeFirstMove && new Random().nextBoolean()) {
            player2.makeMove(gameTable);
            dataPrinter.printGameTable(gameTable);
        }
        final Player[] players = {player1, player2};
        while (true) {
            for (final Player player : players) {
                player.makeMove(gameTable);
                dataPrinter.printGameTable(gameTable);
                if (winnerVerifier.isWinner(gameTable, player)) {
                    dataPrinter.printInfoMessage(player + " WIN!");
                    printGameOver();
                    return;
                }

                if (cellVerifier.allCellsFilled(gameTable)) {
                    dataPrinter.printInfoMessage("Sorry, DRAW!");
                    printGameOver();
                    return;
                }
            }
        }

    }

    private void printGameOver() {
        dataPrinter.printInfoMessage("GAME OVER!");
    }
}

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

import study01.tictactoe.component.keypad.TerminalNumericKeypadCellNumberConverter;
import study01.tictactoe.model.Player;
import study01.tictactoe.model.PlayerType;

import static study01.tictactoe.model.Sign.O;
import static study01.tictactoe.model.Sign.X;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class GameFactory {

    private final PlayerType player1Type = PlayerType.USER;

    private final PlayerType player2Type = PlayerType.COMPUTER;

    public GameFactory(String[] args) {
        // TODO
    }

    public Game create() {
        final CellNumberConverter cellNumberConverter = new TerminalNumericKeypadCellNumberConverter();
        return new Game(
                new DataPrinter(cellNumberConverter),
                // FIXME
                new Player(X, new UserMove(cellNumberConverter)),
                new Player(O, new ComputerMove()),
                true, new WinnerVerifier(),
                new CellVerifier()
        );
    }
}

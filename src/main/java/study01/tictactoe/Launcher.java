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

package study01.tictactoe;

import study01.tictactoe.component.Game;
import study01.tictactoe.component.console.CellNumberConverter;
import study01.tictactoe.component.console.keypad.TerminalNumericKeypadCellNumberConverter;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public final class Launcher {
    public static void main(String[] args) {
        final GameFactory gameFactory = new GameFactory(args);
        final CellNumberConverter cellNumberConverter = new TerminalNumericKeypadCellNumberConverter();
        Game game = gameFactory.create();
        game.play();
    }
}

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

import study01.tictactoe.component.*;
import study01.tictactoe.component.config.CommandLineArgumentParser;
import study01.tictactoe.component.console.CellNumberConverter;
import study01.tictactoe.component.console.ConsoleDataPrinter;
import study01.tictactoe.component.console.ConsoleGameOverHandler;
import study01.tictactoe.component.console.ConsoleUserInputReader;
import study01.tictactoe.component.console.keypad.DesktopNumericKeypadCellNumberConverter;
import study01.tictactoe.component.strategy.*;
import study01.tictactoe.component.swing.GameWindow;
import study01.tictactoe.model.config.PlayerType;
import study01.tictactoe.model.config.UserInterface;
import study01.tictactoe.model.game.Player;

import static study01.tictactoe.model.config.PlayerType.USER;
import static study01.tictactoe.model.config.UserInterface.GUI;
import static study01.tictactoe.model.game.Sign.O;
import static study01.tictactoe.model.game.Sign.X;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class GameFactory {

    private final PlayerType player1Type;

    private final PlayerType player2Type;

    private final UserInterface userInterface;

    public GameFactory(String[] args) {
        final CommandLineArgumentParser.CommandLineArguments commandLineArguments = new CommandLineArgumentParser(args).parse();
        player1Type = commandLineArguments.getPlayer1Type();
        player2Type = commandLineArguments.getPlayer2Type();
        userInterface = commandLineArguments.getUserInterface();
    }

    public Game create() {
        final ComputerMoveStrategy[] strategies = {
                new WinNowComputerMoveStrategy(),
                new PreventUserWinComputerMoveStrategy(),
                new WinOnTheNextStepComputerMoveStrategy(),
                new FirstMoveToTheCenterComputerMoveStrategy(),
                new RandomComputerMoveStrategy()
        };
        final GameOverHandler gameOverHandler;
        final DataPrinter dataPrinter;
        final UserInputReader userInputReader;
        if (userInterface == GUI) {
            final GameWindow gameWindow = new GameWindow();
            dataPrinter = gameWindow;
            userInputReader = gameWindow;
            gameOverHandler = gameWindow;
        } else {
            final CellNumberConverter cellNumberConverter = new DesktopNumericKeypadCellNumberConverter();
            dataPrinter = new ConsoleDataPrinter(cellNumberConverter);
            userInputReader = new ConsoleUserInputReader(cellNumberConverter, dataPrinter);
            gameOverHandler = new ConsoleGameOverHandler(dataPrinter);
        }
        final Player player1;
        if (player1Type == USER) {
            player1 = new Player(X, new UserMove(userInputReader, dataPrinter));
        } else {
            player1 = new Player(X, new ComputerMove(strategies));
        }
        final Player player2;
        if (player2Type == USER) {
            player2 = new Player(O, new UserMove(userInputReader, dataPrinter));
        } else {
            player2 = new Player(O, new ComputerMove(strategies));
        }
        final boolean canSecondPlayerMakeFirstMove = player1Type != player2Type;
        return new Game(
                dataPrinter,
                player1,
                player2,
                canSecondPlayerMakeFirstMove,
                new WinnerVerifier(),
                new CellVerifier(),
                gameOverHandler);
    }
}

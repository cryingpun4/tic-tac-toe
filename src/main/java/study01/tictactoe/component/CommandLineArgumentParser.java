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

import study01.tictactoe.model.PlayerType;

import static study01.tictactoe.model.PlayerType.COMPUTER;
import static study01.tictactoe.model.PlayerType.USER;

/**
 * @author CryingPun4
 * @link <a href="https://github.com/cryingpun4">...</a>
 */
public class CommandLineArgumentParser {
    private final String[] args;

    public CommandLineArgumentParser(final String[] args) {
        this.args = args;
    }

    public PlayerTypes parse() {
        PlayerType player1Type = null;
        PlayerType player2Type = null;
        for (final String arg : args) {
            if (USER.name().equalsIgnoreCase(arg) || COMPUTER.name().equalsIgnoreCase(arg)) {
                if (player1Type == null) {
                    player1Type = PlayerType.valueOf(arg.toUpperCase());
                } else if (player2Type == null) {
                    player2Type = PlayerType.valueOf(arg.toUpperCase());
                } else {
                    System.err.println("Unsupported line argument: '" + arg + "'");
                }
            } else {
                System.err.println("Unsupported line argument: '" + arg + "'");
            }
        }
        if (player1Type == null) {
            return new PlayerTypes(USER, COMPUTER);
        } else if (player2Type == null) {
            return new PlayerTypes(USER, player1Type);
        } else {
            return new PlayerTypes(player1Type, player2Type);
        }
    }

    public static class PlayerTypes {
        private final PlayerType player1Type;

        private final PlayerType player2Type;

        private PlayerTypes(final PlayerType player1Type,
                            final PlayerType player2Type) {
            this.player1Type = player1Type;
            this.player2Type = player2Type;
        }

        public PlayerType getPlayer1Type() {
            return player1Type;
        }

        public PlayerType getPlayer2Type() {
            return player2Type;
        }
    }
}

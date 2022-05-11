package com.chess.console;

import com.chess.console.exception.IncorrectConsoleInput;
import com.chess.piece.Piece;
import com.chess.piece.registry.exception.IncorrectRegistrySettings;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ConsoleInputReader {

    public static Set<Piece> parseInput(String input) throws IncorrectConsoleInput, IncorrectRegistrySettings {

        Set<Piece> pieces = new HashSet<>();

        Optional<String> optionalInput = Optional.ofNullable(input);
        String params = optionalInput.orElseThrow(()-> new IncorrectConsoleInput("Input is empty"));

        String[] paramsArr = params.split(" ");

        if (paramsArr.length < 2) throw new IncorrectConsoleInput("You should set at least two params");

        for(String param : paramsArr) {
            pieces.add(ConsolePieceReader.parseFromString(param));
        }

        return pieces;
    }

}

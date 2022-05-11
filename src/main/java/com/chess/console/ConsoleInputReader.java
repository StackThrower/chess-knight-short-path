package com.chess.console;

import com.chess.piece.Piece;
import com.chess.piece.registry.IncorrectRegistrySettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConsoleInputReader {

    public static List<Piece> parseInput(String input) throws IncorrectConsoleInput, IncorrectRegistrySettings {

        List<Piece> pieces = new ArrayList<>();

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

package com.chess.piece.registry;

public class IncorrectRegistrySettings extends Exception{
    public IncorrectRegistrySettings(String input) {
        super(input);
    }
}

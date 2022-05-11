package com.chess.console;

public class IncorrectConsoleInput extends Exception{
    public IncorrectConsoleInput() {
        super();
    }
    public IncorrectConsoleInput(String e) {
        super(e);
    }
}

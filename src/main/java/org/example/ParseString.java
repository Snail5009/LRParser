package org.example;

import java.util.ArrayList;

public class ParseString {
    private ArrayList<ParseTerminal> string = new ArrayList<>();

    public ParseString() {

    }

    public ParseString(ArrayList<ParseTerminal> string) {
        this.string = string;
    }

    @Override
    public String toString() {
        StringBuilder stringAsChars = new StringBuilder();
        for (ParseTerminal character : string) {
            stringAsChars.append(character.toString());
            stringAsChars.append(" ");
        }
        return stringAsChars.toString();
    }

    public void add(ParseTerminal character) {
        string.add(character);
    }
}

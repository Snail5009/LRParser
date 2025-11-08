package org.example;

public class TemporaryMain {
    public static void main(String[] args) {
        ParseNonterminal S = new ParseNonterminal("S");
        ParseNonterminal E = new ParseNonterminal("E");
        ParseNonterminal B = new ParseNonterminal("B");

        ParseTerminal plus = new ParseTerminal("+");
        ParseTerminal times = new ParseTerminal("*");
        ParseTerminal zero = new ParseTerminal("0");
        ParseTerminal one = new ParseTerminal("1");
        ParseTerminal eof = new ParseTerminal("$");

        Grammar grammar = new Grammar();
        grammar.addProduction(S, new ParseNode[] { E, eof });
        grammar.addProduction(E, new ParseNode[] { E, plus, B });
        grammar.addProduction(E, new ParseNode[] { E, times, B });
        grammar.addProduction(E, B);
        grammar.addProduction(B, zero);
        grammar.addProduction(B, one);

        System.out.printf("Grammarrrrr:\n%s\n", grammar);
        
        ItemsList itemSets = grammar.generateItemSets();
        System.out.printf("Item setsssss:\n%s\n", itemSets);

        GotoActionTable table = new GotoActionTable(itemSets);
        System.out.printf("Tableeee:\n%s\n", table);

        ParseString string = new ParseString();
        string.add(one);
        string.add(plus);
        string.add(one);

        System.out.printf("Stringggg:\n%s\n", string);
    }
}

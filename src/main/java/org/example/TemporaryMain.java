package org.example;

public class TemporaryMain {
    public static void main(String[] args) {
        ParseNonterminal S = new ParseNonterminal("S");
        ParseNonterminal E = new ParseNonterminal("E");
        ParseNonterminal V = new ParseNonterminal("V");

        ParseTerminal plus = new ParseTerminal("+");
        ParseTerminal integer = new ParseTerminal("integer");

        Grammar grammar = new Grammar();
        grammar.addProduction(S, E);
        grammar.addProduction(E, new ParseNode[] { E, plus, V });
        grammar.addProduction(E, V);
        grammar.addProduction(V, integer);

        System.out.printf("Grammarrrrr:\n%s\n", grammar);

        ParseTerminal[] expression = new ParseTerminal[] {
            integer, plus, integer, plus, integer
        };
        
        
    }
}

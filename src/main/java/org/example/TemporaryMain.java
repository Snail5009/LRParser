package org.example;

public class TemporaryMain {
    public static void main(String[] args) {
        ParseNonterminal S = new ParseNonterminal("S");
        ParseNonterminal E = new ParseNonterminal("E");
        ParseNonterminal V = new ParseNonterminal("V");

        ParseTerminal plus = new ParseTerminal("+");
        ParseTerminal integer = new ParseTerminal("integer");
        ParseTerminal eof = new ParseTerminal("$");

        Grammar grammar = new Grammar();
        grammar.addProduction(S, new ParseNode[] { E, eof });
        grammar.addProduction(E, new ParseNode[] { E, plus, V });
        grammar.addProduction(E, V);
        grammar.addProduction(V, integer);

        System.out.printf("Grammarrrrr:\n%s\n", grammar);


        int currentPosition = 0;
        for (Production production : grammar.getProductions()) {
            ParseNode next = production.getTo()[currentPosition];
            System.out.println("Next item would have productions:");
            for (Production innerProduction : grammar.getProductions()) {
                if (innerProduction.getFrom().equalsType(next)) {
                    System.out.println(innerProduction);
                }
            }
        }


        ParseString string = new ParseString();
        string.add(integer);
        string.add(plus);
        string.add(integer);

        System.out.printf("Stringggg:\n%s\n", string);
        
    }
}

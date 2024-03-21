package Parsing.Nodes;

public class GoSub extends StatementNode {


    private final String identifier;

    public GoSub(String identifier){
        this.identifier = identifier;
    }


    public String toString() {
        return "GoSub{" +
                "identifier='" + identifier + '\'' +
                '}';
    }












}

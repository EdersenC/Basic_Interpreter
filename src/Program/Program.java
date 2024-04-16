package Program;

import Parsing.Nodes.LabelNode;
import Parsing.Nodes.Node;
import Parsing.Nodes.StatementNode;
import Parsing.Nodes.StatementsNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class Program {


    private LinkedList<Node> dataStatements;
    private LinkedList<StatementsNode>  statments;
    private HashMap<String, LabelNode> labels;
    private HashMap<String, Integer> integerVariable;
    private HashMap<String, String> stringVariable;
    private HashMap<String, Float> floatVariable;




    public Program(LinkedList<StatementsNode>  statments, LinkedList<Node> dataStatements, HashMap<String, LabelNode> labels, HashMap<String, Integer> integerVariable, HashMap<String, String> stringVariable, HashMap<String, Float> floatVariable){
        this.statments = statments;
        this.dataStatements = dataStatements;
        this.labels = labels;
        this.integerVariable = integerVariable;
        this.stringVariable = stringVariable;
        this.floatVariable = floatVariable;

    }


    /**
     * @return a Linked list with HashMaps of the integer, string, and float variables
     */
    public LinkedList<HashMap<String,?>> getVariableStorage() {
        LinkedList<HashMap<String,?>> data = new LinkedList<>();
        data.add(integerVariable);
        data.add(stringVariable);
        data.add(floatVariable);
        return data;
    }

    /**
     *
     * @return a HashMap of the Labeled variables
     */
    public HashMap<String,LabelNode> getLabels(){
        return new HashMap<>(labels);
    }







    public String toString(){
        return "Program{" +
                "dataStatements=" + dataStatements +
                ", statments=" + statments +
                ", labels=" + labels +
                ", integerVariable=" + integerVariable +
                ", stringVariable=" + stringVariable +
                ", floatVariable=" + floatVariable +
                '}';
    }





}

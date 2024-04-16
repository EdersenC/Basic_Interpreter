package Interpreting;

import java.util.*;

import Parsing.Nodes.*;
import Program.Program;

public class Interpreter {
   private ProgramNode handler;
   private LinkedList<Node> dataStatements;

   private LinkedList<LabelNode> labelNodes;
    private LinkedList<StatementsNode>  statments = new LinkedList<>();
   private HashMap<String, LabelNode> labels = new HashMap<>();
   private HashMap<String, Integer> integerVariable= new HashMap<>();
   private HashMap<String, String> stringVariable= new HashMap<>();
   private HashMap<String, Float> floatVariable = new HashMap<>();


   public Interpreter(ProgramNode programNode){
        this.handler = programNode;
        init();
   }


   /**
    * initializes the program
    */
   public void init(){
       initOptimizations();
       statments.addAll(handler.getNodes());
   }



   /**
    * initializes the optimizations
    */
   public void initOptimizations(){
       if (handler.hasDataNode()){
           dataStatements = handler.getDataNode().getNodes();
       }
       if (handler.hasLabels()){
           labelNodes = handler.getLabelNodes();
           for (LabelNode label : labelNodes) {
               labels.put(label.getName(), label);
           }
       }
   }



   /**
    * @return the program
    * interprets the program
    */
   public Program interpret(){
       while (!statments.isEmpty()){
           StatementsNode node = statments.removeFirst();
           LinkedList<StatementNode> nodes = node.getStatements();
           for (int i =0; i< nodes.size();i++) {
               run(nodes.get(i));
           }
       }
       return new Program(statments, dataStatements, labels, integerVariable, stringVariable, floatVariable);
   }



   /**
    * @param variable the variable to be added
    * @param value the value to be added
    */
   private void addToHashMap(VariableNode variable, Node value){
       String name = variable.getName();
       if (variable.getValue() instanceof IntergerNode){
           integerVariable.put(name,((IntergerNode)value).getValue());
       }
       if (variable.getValue() instanceof StringNode){
           stringVariable.put(name,((StringNode)value).getValue());
       }
       if (variable.getValue() instanceof FloatNode){
           floatVariable.put(name,((FloatNode)value).getValue());
       }
   }

   //Need to implement
   private boolean isInHashMap(HashMap<String, ?> map, String key){
       System.out.println(map);
       System.out.println(map.get(key));
       return map.containsKey(key);
   }


    /**
     * @param node the node to be run
     */
   private void run(Node node){
       if (node instanceof ReadNode){
           read((ReadNode) node);
       }
   }

//   public void doFucntion(FunctionNode function){
//       if (function.getFunctionName().equals("RANDOM")){
//              if (function.getNumParameters() == 0){
//               function.setValue(new IntergerNode(random()));
//           }
//           else
//               throw new RuntimeException("RANDOM FUNCTION TAKES NO ARGUMENTS");
//       }
//
//   }


    /**
     * @param read the read node
     * reads the variables and adds them to the hashmap
     */
   public void read(ReadNode read) {
       LinkedList<VariableNode> variables = read.getVariables();
       while (!variables.isEmpty()) {
           if ((dataStatements.peek() instanceof IntergerNode)
                   && variables.peek().getValue() instanceof IntergerNode) {
               addToHashMap(variables.removeFirst(), dataStatements.removeFirst());
           }
           else if ((dataStatements.peek() instanceof StringNode)
                     && variables.peek().getValue() instanceof StringNode) {
               addToHashMap(variables.removeFirst(), dataStatements.removeFirst());
           }
           else if ((dataStatements.peek() instanceof FloatNode)
                   && variables.peek().getValue() instanceof FloatNode) {
                addToHashMap(variables.removeFirst(), dataStatements.removeFirst());
           }
           else
                throw new RuntimeException("Read And Data Type Mismatch" +variables);
       }
   }


   /**
    * @return a random integer
    */
   public static int random(){
       Random random = new Random();
       return random.nextInt();
   }


   /**
    * @param string the string to be sliced
    * @param i the starting index
    * @return the sliced string
    */
   public static String left(String string, int i) {
      return string.substring(0, i);
   }

   /**
    * @param string the string to be sliced
    * @param i the starting index
    * @return the sliced string
    */

   public static String right(String string, int i) {
       return string.substring(string.length() - i);
   }

   /**
    * @param string the string to be sliced
    * @param i the starting index
    * @param j the ending index
    * @return the sliced string
    */
   public static String mid(String string, int i, int j) {
       return string.substring(i , i + j );
   }


   /**
    * @param num1 the integer to be converted to a string
    * @return the string value of the integer
    */
   public static String num(int num1) {
       return Integer.toString(num1);
   }

   /**
    * @param num1 the float to be converted to a string
    * @return the string value of the float
    */
   public static String num(float num1) {
         return Float.toString(num1);
   }

   /**
    * @param string the string to be converted to a float
    * @return the float value of the string
    */
   public static float valFloat(String string) {
     return Float.parseFloat(string);
   }


 /**
  * @param string the string to be converted to an integer
  * @return the integer value of the string
  */
 public static int valInt(String string) {
        return Integer.parseInt(string);
 }


}

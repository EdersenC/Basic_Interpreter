package Lexing;


public class CodeHandler {
    private String basic;
    private int index;
    private int line;
    public CodeHandler(String basic, int index) {
        this.basic = basic;
        this.index = index;

    }



/**
 * @return the Character at that index
 */
public char peek(int index){
        if(isDone()|| index+this.index >= basic.length())
            return '\0';
        return basic.charAt(this.index+index);

}

/**
 * @return the String from the start to the index
 */
public String peekString(int index){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i <= index; i++){
            text.append(peek(i));
        }
        return text.toString();
}

/**
 * @return the Character at the index
 */
public char getChar(){

    char character = peek(0);
    index++;
       return character;
}

/**
 * @return moves the String index by the amount
 */
public void swallow(int index){
        this.index = this.index+index;
}

/**
 * @return if the index is at the end of the String
 */
public Boolean isDone(){
if (index >= basic.length())
    return true;
return false;
}

/**
 * @return the index of the String
 */
public int getLine(){
    return line;
}

/**
 * @return the index of the String
 */
public int getIndex(){
    return index;
}

/**
 * @return the String from the index to the end
 */
public String remainder(){
    int remainder = this.basic.length() - this.index;
    return peekString(remainder-1);
}






}

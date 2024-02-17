package Parsing;

import Lexing.Token;

import java.util.LinkedList;
import java.util.Optional;

public class TokenManager {



    private LinkedList<Token> tokens;



    public TokenManager(LinkedList<Token> tokens){
        this.tokens = tokens;
    }


    /**
     * @param offset the offset of the token
     * @return the token
     */
    Optional<Token> peek(int offset){
        if(tokens.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(tokens.get(offset));
    }

    /**
     * @return True if there are more tokens
     */
    Boolean moreTokens(){
        return !tokens.isEmpty();
    }

    /**
     * If the Head token is of the given type, remove it and return it
     * @param type the type of the token
     * @return Token
     */
    Optional<Token> matchAndRemove(Token.TokenType type){
        if(tokens.isEmpty()){
            return Optional.empty();
        }
        if(tokens.get(0).getType() == type){
            return Optional.of(tokens.remove(0));
        }
        return Optional.empty();
    }


    /**
     * Accepts and Removes N EndLine(Separator) tokens
     * @return
     */
    Boolean acceptSeparator(){
        boolean accepted = false;
        while(matchAndRemove(Token.TokenType.ENDOFLINE).isPresent()){
            accepted = true;
        }
        return accepted;
    }




}

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import Lexing.*;
import Parsing.*;
import Parsing.Nodes.*;
public class Basic {
    public static void main(String[] args) {
        // Check if the number of arguments is not exactly one
        if (args.length != 1) {
            System.err.println("Error: Please provide exactly one argument.");
            System.exit(1); // Exit with a non-zero status to indicate an error
        }


        String srcDirectoryPath = "src/BasicArea";
        String basicFileName = args[0]; // Use the provided argument for the file name
        Path basicFilePath = Paths.get(srcDirectoryPath, basicFileName);

        Shadow.log("Running: %s \nLocation: %s".formatted(basicFileName,basicFilePath));
            // Assuming Lexer is a class you have defined elsewhere
            Lexer lexer = new Lexer(basicFilePath, 0, 0);
            // Assuming this method is correctly defined in Lexer
            LinkedList<Token> tokens =  lexer.lex(basicFileName);
            Shadow.log("\nLexed Tokens: %s".formatted(tokens.toString()),"DONE LEXING");
            Parser parser = new Parser(tokens);
            ProgramNode programNode = parser.parse();
            Shadow.log("\nParsed Program: %s".formatted(programNode.toString()));
    }
}

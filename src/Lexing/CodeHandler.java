package Lexing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A utility class for handling and navigating through code strings.
 * It supports operations like peeking at characters at a specific offset,
 * extracting substrings, advancing the current index, and checking for completion.
 *
 * Example usage:
 * ```
 * CodeHandler handler = new CodeHandler(Paths.get("path/to/code.file"), 0);
 * char c = handler.peek(1); // Peeks at the character one position ahead of the current index without advancing.
 * String str = handler.peekString(5); // Extracts a substring from the current index to 5 characters ahead.
 * ```
 */
public class CodeHandler {
    private String basic; // The string being handled, representing the code.
    private int index; // The current position within the string.
    private int line; // Intended to track line numbers, but not utilized in the current implementation.

    /**
     * Constructs a CodeHandler to manage code string operations from a file.
     *
     * @param filePath the path to the file containing the code to be analyzed.
     * @param index the initial position to start parsing from within the string.
     * Example:
     * ```
     * CodeHandler handlerFromFile = new CodeHandler(Paths.get("path/to/file"), 0);
     * ```
     */
    public CodeHandler(Path filePath, int index) {
        this.basic = initFile(filePath);
        this.index = index;
    }

    /**
     * Constructs a CodeHandler to manage code string operations from a string.
     *
     * @param basic the string representing the code to be analyzed.
     * @param index the initial position to start parsing from within the string.
     * Example:
     * ```
     * CodeHandler handlerFromString = new CodeHandler("The quick brown fox jumps over the lazy dog", 0);
     * ```
     */
    public CodeHandler(String basic, int index) {
        this.basic = basic;
        this.index = index;
    }

    /**
     * Initializes the code string from a file.
     *
     * @param filePath the path to the file containing the code.
     * @return the code string read from the file.
     * Example:
     * Suppose 'code.txt' contains "Example code".
     * ```
     * String code = handler.initFile(Paths.get("code.txt"));
     * ```
     */
    private String initFile(Path filePath) {
        try {
            return new String(Files.readAllBytes(filePath));
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Peeks at the character at a given offset from the current index without advancing the index.
     *
     * @param offset the offset from the current index to peek at.
     * @return the character at the offset or '\0' if the offset is beyond the string bounds.
     * Example:
     * ```
     * // Assuming the code string is "hello"
     * char c = handler.peek(1); // Returns 'e'
     * ```
     */
    public char peek(int offset) {
        if (isDone() || index + offset >= basic.length()) {
            return '\0';
        }
        return basic.charAt(index + offset);
    }

    /**
     * Generates a substring from the current index up to a given offset.
     *
     * @param offset the offset to peek up to, starting from the current index.
     * @return a String that starts from the current index and ends at the offset.
     * Example:
     * ```
     * // Assuming the code string is "hello world"
     * String str = handler.peekString(4); // Returns "hello"
     * ```
     */
    public String peekString(int offset) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i <= offset; i++) {
            text.append(peek(i));
        }
        return text.toString();
    }

    /**
     * Retrieves the character at the current index and advances the index by one.
     *
     * @return the character at the current index.
     * Example:
     * ```
     * // Assuming the code string is "hello"
     * char c = handler.getChar(); // Returns 'h', and the index advances by 1
     * ```
     */
    public char getChar() {
        char character = peek(0);
        index++;
        return character;
    }

    /**
     * Advances the current index by a specified amount.
     *
     * @param amount the number of positions to advance the index by.
     * Example:
     * ```
     * // Assuming the index is at 0
     * handler.swallow(3); // Advances the index by 3 positions
     * ```
     */
    public void swallow(int amount) {
        this.index += amount;
    }

    /**
     * Checks if the parsing has reached the end of the string.
     *
     * @return true if the current index is at or beyond the end of the string, false otherwise.
     * Example:
     * ```
     * boolean done = handler.isDone(); // Returns true if at the end of the string
     * ```
     */
    public Boolean isDone() {
        return index >= basic.length();
    }

    /**
     * Gets the current line number. Note: This method currently returns a static value because line tracking is not implemented.
     *
     * @return the line number, which is not accurately tracked in the current implementation.
     */
    public int getLine() {
        return line;
    }

    /**
     * Gets the current index within the string.
     *
     * @return the current parsing position.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Generates a substring from the current index to the end of the string.
     *
     * @return a String that starts from the current index to the end of the basic string.
     * Example:
     * ```
     * // Assuming the code string is "hello world" and the index is at 6
     * String remainder = handler.remainder(); // Returns "world"
     * ```
     */
    public String remainder() {
        int length = this.basic.length() - this.index;
        return peekString(length - 1);
    }
}

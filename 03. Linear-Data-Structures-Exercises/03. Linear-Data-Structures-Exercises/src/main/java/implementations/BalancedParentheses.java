package implementations;

import interfaces.Solvable;

public class BalancedParentheses implements Solvable {
    private String parentheses;
    private static final char firstType = '{';
    private static final char firstTypeClosing = '}';
    private static final char secondType = '(';
    private static final char secondTypeClosing = ')';
    private static final char thirdType = '[';
    private static final char thirdTypeClosing = ']';

    public BalancedParentheses(String parentheses) {
        this.parentheses = parentheses;
    }

    @Override
    public Boolean solve() {
        ArrayDeque<Character> stack = new ArrayDeque<>();

        if (this.parentheses.trim().length() == 0 || this.parentheses.length() % 2 == 1) {
            return false;
        }

        this.parentheses = this.parentheses.trim();

        boolean result;
        for (int i = 0; i < this.parentheses.length(); i++) {
            char currentChar = this.parentheses.charAt(i);

            if (currentChar == firstTypeClosing || currentChar == secondTypeClosing || currentChar == thirdTypeClosing) {
                char lastChar = stack.removeFirst();

                result = checkParentheses(lastChar, currentChar);

                if (!result) {
                    return false;
                }
            } else if (currentChar != ' ') {
                stack.addFirst(this.parentheses.charAt(i));
            }
        }

        return true;
    }

    private boolean checkParentheses(Character currentHead, Character currentTail) {
        switch (currentHead) {
            case firstType:
                return currentTail == firstTypeClosing;
            case secondType:
                return currentTail == secondTypeClosing;
            case thirdType:
                return currentTail == thirdTypeClosing;
            case ' ':
                return currentTail == ' ';
            default:
                return false;
        }
    }
}

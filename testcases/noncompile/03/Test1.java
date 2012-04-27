/**
 * Bogus test program 1 with lexical (and other) errors.
 *
 * Lexical errors are annotated with a one-line comment on the line above.
 *
 * Notes:
 * - The Java keywork "private" is not part of our Minijava variant, but should
 *   be lexically recognized as an identifier --- it's a syntax error, however.
 * - Obvious misspelling of "class" is still a valid identifier.
 * - Brackets after Test1C is of course syntax error, but not lexical errors.
 * - Java keywords "switch", "case", "default" and "break" should also be
 *   recognized as identifiers, which will cause syntax error.
 */
class Test1 {
    private static void main(String[] args) {
        // There are no string literals in our Minijava variant.
        System.out.prinln("Hello world!");

        switch (args[0]) {
            // Colon is not a token on its own.
            case 0 :
                System.out.println(0);
                break;
            // The @ character does not exist in our Minijava variant.
            case @:
            // Colon is also not a valid identifier character.
            default:
                System.out.println(1337);
        }
    }
}

clas Test1A {
}

// Java identifier must begin with a Java letter, i.e. not a number.
class 1B {
}

class Test1C [
]

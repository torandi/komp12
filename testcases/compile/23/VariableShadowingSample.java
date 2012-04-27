class VariableShadowingSample {
    public static void main(String[] args) {
    }
}

class VariableShadowing {
    VariableShadowing obj;
    int n;

    /* Legal use of lexical shadowing. */
    public int f() {
        int n;
        n = 42;
        return n;
    }

    /* No shadowing in effect: different name spaces. */
    public VariableShadowing g() {
        int VariableShadowing;
        VariableShadowing = 36;
        obj = new VariableShadowing();
        return obj;
    }

    /*
     * No impact in MiniJava: variables and methods use different
     * syntaxes.
     */
    public int h() {
        int f;
        f = 42;
        return f;
    }
}

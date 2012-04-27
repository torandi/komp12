class IllegalVariableShadowingSample {
    public static void main(String[] args) {
    }
}

class IllegalVariableShadowing {
    IllegalVariableShadowing obj;

    /* Illegal use of lexical shadowing (wrong type). */
    public IllegalVariableShadowing f() {
        boolean obj;
        obj = new IllegalVariableShadowing();
        return obj;
    }
}

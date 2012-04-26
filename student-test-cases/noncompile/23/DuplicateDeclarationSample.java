class DuplicateDeclarationSample {
    public static void main(String[] args) {
    }
}

class DuplicateDeclarationSample {
}

class DuplicateClass {
}

class DuplicateClass {
    public int f() {
        return 0;
    }
}

class Duplicates {
    public int f() {
        return 0;
    }

    public int f() {
        return 1;
    }

    public int g() {
        int x;
        int y;
        DuplicateClass z;
        Duplicates y;
        return 0;
    }
}

class UndeclaredTypeSample {
    public static void main(String[] args) {
    }
}

class DeclaredType {
    UndeclaredType obj;

    public UndeclaredType f() {
        return new UndeclaredType();
    }

    public int g(UndeclaredType x) {
        return 0;
    }

    public int h() {
        UndeclaredType x;
        x = new UndeclaredType();
        return 0;
    }
}

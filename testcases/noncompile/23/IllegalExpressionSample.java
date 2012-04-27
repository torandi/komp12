class IllegalExpressionSample {
    public static void main(String[] args) {
        if (new IES().run(1, 2, 3, 4))
            System.out.println(1111);
        else
            System.out.println(1100);
    }
}

class IES {
    public boolean run(int x, int y, int u, int v) {
        boolean a;
        boolean b;

        a = x * b < y;
        a = x * y < b;
        x = (u < u) * y;

        /* Fails due to compile due to higher priority of '!'. */
        x = !x * y < u - v;

        return b;
    }
}

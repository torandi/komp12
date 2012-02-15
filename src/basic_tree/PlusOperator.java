package basic_tree;

public class PlusOperator implements Operator{

   /* public Operand eval(Operand op1, Operand op2) throws SyntaxError{
        Integer i1=op1.eval().toInt();
        Integer i2=op2.eval().toInt();

        if(i1==null) {
            throw new SyntaxError(op1+" can't be converted into integer for plus operation");
        }
        if(i2==null) {
            throw new SyntaxError(op2+" can't be converted into integer for plus operation");
        }

        return new Operand(new IntPrimary(i1+i2));
    }*/
}

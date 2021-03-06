package syntaxtree;

import error.ImplicitCast;
import error.LossOfPrecision;

public abstract class NumericType extends Type {
    public abstract int precision();
    
    protected NumericType(int line) {
        super(line);
    }
    
    @Override
    public boolean equals(Type t) throws ImplicitCast, LossOfPrecision {
        if(t instanceof NumericType) {
            NumericType nt = (NumericType) t;
            if(nt.precision() < precision()) {
                throw new LossOfPrecision();
            } else if(nt.precision() > precision()) {
                throw new ImplicitCast(t);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}
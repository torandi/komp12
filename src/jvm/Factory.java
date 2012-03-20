package jvm;

import syntaxtree.Type;
import frame.VMFrame;
import frame.VMRecord;
import syntaxtree.FormalList;

/**
 *
 * @author torandi
 */
public class Factory implements frame.VMFactory {

    @Override
    public VMFrame newFrame(String methodName, FormalList formals, Type returnType) {
        return new jvm.Frame(methodName, formals, returnType);
    }

    @Override
    public VMRecord newRecord(String name) {
        return new jvm.Record(name);
    }

}

package basic_tree;



public abstract class Primary {
    public int line_number=-1;
/*
    public Primary eval(ArrayList<Sufix> sufix) throws SyntaxError {
        Primary cur=this;
        for( Sufix s : sufix) {
            cur=s.eval(cur);
        }
        return cur;
    }
*/
    /**
     * Returns this primary as an integer or null if it can't be converted
     */
    //public abstract Integer toInt();
}

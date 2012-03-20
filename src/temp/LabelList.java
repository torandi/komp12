package temp;

public class LabelList {
   public Label head;
   public LabelList tail;
   public LabelList(Label h, LabelList t) {head=h; tail=t;}
   public LabelList(Label h) {head=h; tail=null;}
}


// EXT:ISC
// EXT:ICG
// EXT:IWE
// EXT:NBD
// EXT:CLE
// EXT:CGT
// EXT:CGE
// EXT:CEQ
// EXT:CNE
// EXT:BDJ
// EXT:SPILL
class Test3 {

	public static void main(String[] args) {
		System.out.println(new ListTest().test(1, 2, 3, 4, 5));
	}

}

class ListTest {

	public int test(int p1, int p2, int p3, int p4, int p5) {
		Element first;
		Element last;
		int t;
		int o1;
		int o2;
		int o3;
		int o4;
		int o5;
		int o6;
		int o7;
		int o8;
		int o9;

		first = this.constructList();
		last = first.getNext();

		t = 0;
		o1 = 0;
		o2 = 0;
		o3 = 0;
		o4 = 0;
		o5 = 0;
		o6 = 0;
		o7 = 0;
		o8 = 0;
		o9 = 0;
		while (first != last || t <= 40) {
			last = last.setValue(last.getMagicNumber());
			o9 = o8;
			o8 = o7;
			o7 = o6;
			o6 = o5;
			o5 = o4;
			o4 = o3;
			o3 = o2;
			o2 = o1;
			o1 = last.getValue();
			if (first == last) {
				t = t+1;
			}
			last = last.getNext();
		}

		System.out.println(o1);
		System.out.println(o2);
		System.out.println(o3);
		System.out.println(o4);
		System.out.println(o5);
		System.out.println(o6);
		System.out.println(o7);
		System.out.println(o8);
		System.out.println(o9);

		return p1+p2+p3+p4+p5;
	}

	public Element constructList() {
		int i;
		int q;
		Element first;
		Element last;
		Element e;


		first = new Element();
		first = first.setValue(0);
		first = first.setNext(first);
		last = first;

		i = 0;
		q = 1;

		while (10*10*10*10*10*10 >= i) {
			if (q == 0) {
				e = new Element();
				q = 1;
			}
			else if (q == 1) {
				e = new SpecialElement1();
				q = 2;
			}
			else {
				e = new SpecialElement2();
				q = 0;
			}
			e = e.setValue(i);

			{
				Element temp;
				temp = last.getNext();
				last = last.setNext(e);
				e = e.setNext(temp);
				last = e;
			}

			i = i+1;
		}

		return first;
	}

}

class Element {

	int value;
	Element next;

	public Element setValue(int v) {
		value = v;
		return this;
	}

	public int getValue() {
		return value;
	}

	public Element setNext(Element n) {
		next = n;
		return this;
	}

	public Element getNext() {
		return next;
	}

	public int getMagicNumber() {
		return value;
	}

}

class SpecialElement1 extends Element {

	public int getMagicNumber() {
		return value + 21;
	}

}

class SpecialElement2 extends Element {

	public int getMagicNumber() {
		return value + 100;
	}

}

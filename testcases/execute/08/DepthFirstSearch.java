class DepthFirstSearch {
	public static void main(String[] argv) {
		System.out.println(new DFS().find(new Node().init4(
			new Node().init2(
				new Node().init2(
					new Node().init0(17,13),
					new Node().init1(
						new Node().init4(
							new Node().init0(6,14),
							new Node().init0(89,15),
							new Node().init0(3904,16),
							new Node().init1(
								new Node().init0(-2147483648,17),
								1994,12
							),
							13,11
						),
						1337,10
					),
					19,9
				),
				new Node().init1(
					new Node().init0(1,18),
					12,8
				),
				47701,7
			),
			new Node().init3(
				new Node().init2(
					new Node().init0(0,19),
					new Node().init0(new int[51557].length,26),
					7*12,6
				),
				new Node().init0(99-87,20),
				new Node().init2(
					new Node().init0(4,21),
					new Node().init0(177,22),
					1-4711,5
				),
				14,4
			),
			new Node().init0(4711,23),
			new Node().init2(
				new Node().init0(67111,24),
				new Node().init2(
					new Node().init0(17,25),
					new Node().init1(
						new Node().init0(1337,26),
						1337,3
						),
					77777,2
				),
				-0,1
			),
			12,0
		),67111).getId());
	}
}

class Stack {
	int[] stack;
	int size;
	public int init(){
		stack = new int[1];
		size = 0;
		return 0;
	}

	public boolean isEmpty() {
		boolean i;
		if(new Math().equal(0,size))
			i = true;
		else
			i = false;
		return i;
	}

	public int push(int push) {
		int i;
		int[] tmp;
		i = 0;
		if(new Math().equal(size,stack.length)){
			tmp = new int[2*stack.length];
			while(i < stack.length){
				tmp[i] = stack[i];
				i = i + 1;
			}
			stack = tmp;
		}else{}
		stack[size] = push;
		size = size + 1;
		return push;
	}

	public int pop() {
		size = size + -1;
		return stack[size];
	}

	public int peek() {
		return stack[size-1];
	}
}

class Node{
	Node _a_0;
	Node _a_1;
	Node _a_2;
	Node _a_3;
	Node parent;
	int numChilds;
	int value;
	int id;


	public Node init4(Node a_0, Node a_1, Node a_2, Node a_3,int val, int iid) {
		_a_0 = a_0;
		_a_1 = a_1;
		_a_2 = a_2;
		_a_3 = a_3;
		value = val;
		id = iid;
		numChilds = 4;
		return this;
	}

	public Node init3(Node a_0, Node a_1, Node a_2,int val, int iid) {
		_a_0 = a_0;
		_a_1 = a_1;
		_a_2 = a_2;
		value = val;
		id = iid;
		numChilds = 3;
		return this;
	}

	public Node init2(Node a_0, Node a_1,int val, int iid) {
		_a_0 = a_0;
		_a_1 = a_1;
		value = val;
		id = iid;
		numChilds = 2;
		return this;
	}

	public Node init1(Node a_0,int val, int iid) {
		_a_0 = a_0;
		value = val;
		id = iid;
		numChilds = 1;
		return this;
	}

	public Node init0(int val, int iid) {
		value = val;
		id = iid;
		numChilds = 0;
		return this;
	}

	public int getVal(){
		return value;
	}

	public Node getNode(int i){
		Math math;
		Node node;
		math = new Math();
		if(math.equal(i,0))
			node = _a_0;
		else if(math.equal(i,1))
			node = _a_1;
		else if(math.equal(i,2))
			node = _a_2;
		else if(math.equal(i,3))
			node = _a_3;
		else
			node = this;
		return node;
	}

	public int setParent(Node par) {
		parent = par;
		return 0;
	}

	public Node getParent() {
		return parent;
	}

	public int getNumChilds() {
		return numChilds;
	}

	public int getId(){
		return id;
	}
}

class Math{
	public boolean equal(int i, int j) {
		boolean kase;
		if(!(i < j) && !(j < i))
			kase = true;
		else
			kase = false;
		return kase;
	}
}

class DFS{
Node start;
Node currNode;
int currVal;
int value;
Stack stack;
	public Node find(Node startNode, int val) {
		int tmp;
		start = startNode;
		currNode = start;
		value = val;
		stack = new Stack();
		tmp = stack.init();
		return this.DFS();
	}

	public Node DFS() {
		boolean found;
		Math math;
		int tmp;
		math = new Math();
		found = false;
		currVal = 0;
		while(!found){
			System.out.println(currNode.getId());
			if(math.equal(currNode.getVal(), value)){
				found = true;
			}else{}

			if(!found && math.equal(currVal,currNode.getNumChilds())){
				if(stack.isEmpty()){
					found = true;
				}else{
					currNode = currNode.getParent();
					currVal = stack.pop();
				}
			}else if (!found) {
				tmp = currNode.getNode(currVal).setParent(currNode);
				currNode = currNode.getNode(currVal);

				currVal = currVal + 1;
				tmp = stack.push(currVal);
				currVal = 0;
			}else{}
		}

		return currNode;
	}
}

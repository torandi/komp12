class Test1{
    public static void main(String[] a){
	System.out.println(new CardDeck().Start(5));
    }
}

class CardDeck{

	int[] card;
	int size;
	int max;
	int i;

	public int Start(int i){
		int a;
		a = this.Shuffle(i);
		a = this.searchMax();
		return 0 ;
	}

	public int searchMax() {

		max = 0;
		i = 0;

		while (i < size) {

			if(max < card[i]) {
				max = card[i];
			}
			else {

			}
			i = i + 1 ;
		}

		System.out.println(max);
		return max;
	}

	public int Shuffle(int a){
		size = a;
		card = new int[a] ;

		card[0] = 2 ;
		card[1] = 7  ;
		card[2] = 12 ;
		card[3] = 18 ;
		card[4] = 1

		return 0 ;
	}
}

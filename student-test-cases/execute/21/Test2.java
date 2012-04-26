/*
 * Test 2 (base language)
 *
 * Expected output: 3000369
 *
 */
class Test2 {

	public static void main(String[] args) {
		System.out.println(new QuicksortTest().test());
	}

}

class QuicksortTest {

	int[] data;

	public int test() {
		int r;

		data = this.generateData();

		r = this.quicksort(0, data.length-1);
		r = this.invertData();
		r = this.quicksort(0, data.length-1);
		r = this.invertData();
		r = this.quicksort(0, data.length-1);
		r = this.invertData();
		r = this.quicksort(0, data.length-1);

		System.out.println(data[3000]);
		System.out.println(data[data.length-1]);
		return data.length;
	}

	public int quicksort(int left, int right) {
		int pivotIndex;
		int pivotValue;
		int storeIndex;
		int storeValue;
		int i;
		int r;

		if (left < right) {
			pivotIndex = this.findPivot(left, right);
			pivotValue = data[pivotIndex];

			// Move pivot to end
			data[pivotIndex] = data[right];
			data[right] = pivotValue;

			storeIndex = left;

			i = left;
			while (i < right) {
				if (data[i] < pivotValue+1) {
					storeValue = data[storeIndex];
					data[storeIndex] = data[i];
					data[i] = storeValue;

					storeIndex = storeIndex + 1;
				}
				else {}
				i = i+1;
			}

			storeValue = data[storeIndex];
			data[storeIndex] = data[right];
			data[right] = storeValue;

			pivotIndex = storeIndex;
			r = this.quicksort(left, pivotIndex - 1);
			r = this.quicksort(pivotIndex + 1, right);
		}
		else {}

		return 0;
	}

	public int findPivot(int left, int right) {
		boolean flag;
		int i;

		i = 0;
		flag = true;

		while (left < right+1) {
			if (flag) {
				i = i+1;
			}
			else {}
			flag = !flag;
			left = left+1;
		}

		return right - i;
	}

	public int[] generateData() {
		int[] data;
		int i;
		int o;
		boolean b1;
		boolean b2;
		boolean b3;

		data = new int[1000000];
		i = data.length;
		o = 50;
		b1 = true;
		b2 = false;
		b3 = false;

		while(0 < i) {
			if (b1 && !(b2 && !b3) && 0 < o) {
				o = i + 1;
				b1 = false;
				b2 = false;
				b3 = false;
			}
			else {
				o = i - 1;
				if (!b2) {
					o = o + 20;
					b3 = true;
				}
				else {
					o = o - 25;
					b1 = true;
				}
				b2 = true;
			}

			data[i-1] = (o+100)*3-5*(3-6);
			i = i-1;
		}

		return data;
	}

	public int invertData() {
		int l;
		int r;
		int v;

		l = 0;
		r = data.length-1;

		while (l < r) {
			v = data[l];
			data[l] = data[r];
			data[r] = v;

			l = l+1;
			r = r-1;
		}

		return 0;
	}

}

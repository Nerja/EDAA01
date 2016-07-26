package set;

public class RemoveDuplicates {
	public static int[] removeDup(int[] v) {
		MaxSet<Integer> maxS = new MaxSet<Integer>();
		for(int e : v)
			maxS.add(e);
		int[] unique = new int[maxS.size()];
		for(int i = unique.length-1; i >= 0; i--) {
			int nbr = maxS.getMax();
			maxS.remove(nbr);
			unique[i] = nbr;
		}
		return unique;
	}
	
	public static void main(String[] args) {
		int[] test = {1, 1, 2, 12, 3, 3, 5, 4};
		test = removeDup(test);
		for(int i : test)
			System.out.print(i + " ");
	}
}

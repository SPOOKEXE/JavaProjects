import java.util.*;

public class Main {
	
	// FROM MIN -> MAX
	private static int drawRandomNumber( int min, int max ) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
	// Draw a number for the lotto limits
	public static int drawLottoNumber( ) {
		return drawRandomNumber(0, 90);
	}
	
	public static ArrayList<ArrayList<Integer>> drawLottoNTimes(int times) {
		// an array of n times, of arrays of 5 numbers
		ArrayList<ArrayList<Integer>> lottoResults = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < times; i++) {
			// for each lotto pull 5 numbers that are unique5 numbers that are unique
			ArrayList<Integer> alrdyDrawed = new ArrayList<Integer>();
			for (int n = 0; n < 5; n++) {
				// get a random number that hasn't been produced before
				int randNumber = drawLottoNumber();
				while (alrdyDrawed.contains(randNumber)) {
					randNumber = drawLottoNumber();
				}
				// add the number in the array
				alrdyDrawed.add(randNumber);
			}
			lottoResults.add(alrdyDrawed);
		}
		return lottoResults;
	}
	
	public static void main(String[] args) {

		// Get the lotto results and output them
		ArrayList<ArrayList<Integer>> lottoResults = drawLottoNTimes(200);
		System.out.println(lottoResults.toString().replace("],", "],\n"));
		
		// Create a hashmap for statistic purposes
		Map<String, Integer> statistic = new HashMap<String, Integer>();
		
		// Iterate over each lotto pull in the lotto results and count the relating unique numbers
		Iterator<ArrayList<Integer>> itr = lottoResults.iterator();
		while(itr.hasNext()) {
			ArrayList<Integer> lotto_pull = itr.next();
			Iterator<Integer> itr2 = lotto_pull.iterator();
			while(itr2.hasNext()) {
				Integer lotto_number = itr2.next();
				String index = Integer.toString(lotto_number);
				if ( statistic.containsKey( index ) ) {
					statistic.put(index, statistic.get(index) + 1 );
				} else {
					statistic.put( Integer.toString(lotto_number), 1);
				}
			}
		}
		
		// Output the statistic counts
		System.out.println( statistic.toString() );
		
	}

}

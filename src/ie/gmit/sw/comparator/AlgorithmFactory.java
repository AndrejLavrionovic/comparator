package ie.gmit.sw.comparator;

public class AlgorithmFactory implements Factory {

	/* (non-Javadoc)
	 * @see ie.gmit.sw.comparator.Factory#useAlgorithm(java.lang.String)
	 */
	@Override
	public CompareAlgorithm useAlgorithm(String alg){
		switch(alg){
			case "Damerau-Levenshtein Distance":
				return new DamerauLevenshtein();
			case "Hamming Distance":
				return new HammingDistance();
			case "Levenshtein Distance":
				return new Levenshtein();
			default:
				return new Levenshtein();
		}
	}
}

package ie.gmit.sw;

/**
 * 
 * @author Andrej Lavrinovic - G00196984
 * 
 * Result class represents container for carrying data that must
 * be used by comparator to compare and return result
 *
 */

public class Result implements Resultable {

	// Object fields that represents request data
	private String stringOne;
	private String stringTwo;
	private String algorithm;
	private long jobNumber;
	
	// Constructor section generated by eclipse
	public Result(String stringOne, String stringTwo, String algorithm, long jobNumber) {
		super();
		this.stringOne = stringOne;
		this.stringTwo = stringTwo;
		this.algorithm = algorithm;
		this.jobNumber = jobNumber;
	}
	
	// get/set -ers generated by eclipse using Source -> generate Getters and Setters
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#getStringOne()
	 */
	@Override
	public String getStringOne() {
		return stringOne;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#setStringOne(java.lang.String)
	 */
	@Override
	public void setStringOne(String stringOne) {
		this.stringOne = stringOne;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#getStringTwo()
	 */
	@Override
	public String getStringTwo() {
		return stringTwo;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#setStringTwo(java.lang.String)
	 */
	@Override
	public void setStringTwo(String stringTwo) {
		this.stringTwo = stringTwo;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#getAlgorithm()
	 */
	@Override
	public String getAlgorithm() {
		return algorithm;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#setAlgorithm(java.lang.String)
	 */
	@Override
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#getJobNumber()
	 */
	@Override
	public long getJobNumber() {
		return jobNumber;
	}
	/* (non-Javadoc)
	 * @see ie.gmit.sw.Resultable#setJobNumber(long)
	 */
	@Override
	public void setJobNumber(long jobNumber) {
		this.jobNumber = jobNumber;
	}
}

public class Gift {
	private String description;
	private double price;
	private boolean taxable;
	public static final double TAX_RATE=0.05;
	public Gift (String newDescription, double newPrice, boolean newTaxable){
		description = newDescription;
		setPrice(newPrice);
		setTaxable(newTaxable);
	}
	public void setDescription (String theDescription){
		if (theDescription!=null) description = theDescription;
		else description="none given";
	}
	public void setPrice (double thePrice){
		if (thePrice>0) price = thePrice;
	}
	public void setTaxable (boolean theTaxable){
		taxable = theTaxable;
	}

	public String toString() {
		String a="description = " + description + "\n"+"price = " + price + "\n"+"taxable = " + taxable;
		return a;
	}
	public double getTax() {
		if (taxable) return TAX_RATE*price;
		else return 0;
	}
}
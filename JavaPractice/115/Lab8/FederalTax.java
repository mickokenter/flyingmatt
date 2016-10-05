import java.text.NumberFormat;

public class FederalTax {
	double taxableIncome;
	public FederalTax(){
		taxableIncome=0;
		taxPaid();
	}
	public FederalTax(double incomeInput){
		setTaxableIncome(incomeInput);
		taxPaid();
	}
	public void setTaxableIncome(double incomeInput){
		if (incomeInput>=0){
			taxableIncome=incomeInput;
		}
		else taxableIncome=0;
	}
	public double taxPaid(){
		if (taxableIncome<0)
			return 0;
		else if (taxableIncome<=22100)
			return taxableIncome*0.15;
		else if (taxableIncome<=53500)
			return 3315+(taxableIncome-22100)*0.28;
		else if (taxableIncome<=115000)
			return 12107+(taxableIncome-53500)*0.31;
		else if (taxableIncome<=250000)
			return 31172+(taxableIncome-115000)*0.36;
		else {
			return 79772+(taxableIncome-250000)*0.396;
		}
	}
	NumberFormat myFormater=NumberFormat.getCurrencyInstance();
	public double getTaxableIncome(){
		return taxableIncome;
	}
	public double getTaxPaid(){
		return taxPaid();
	}
	public String toString(){
		return "Taxable Income: " + myFormater.format(taxableIncome) + " Tax Paid: " + myFormater.format(taxPaid());
	}
}
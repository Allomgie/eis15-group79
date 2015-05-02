import java.lang.IllegalArgumentException;

public class Section {
	// String Builder object
	StringBuilder output = new StringBuilder();		
	
	private String sectionName;
	private String[] vals = new String [6];	
	
	// Number of the Section
	private int secNumber;
	
	// Setter for the section number
	public void setNumber(int input){
		if (input >= 0){
			this.secNumber = input;			
		}
		else {
			this.secNumber = 0;
		}
	}
	
	// Setter for sectionName
	public void setName (String input){
		this.sectionName = input;
	}
	
	// Section constructor with name input
	public Section (String name){
		this.sectionName = name;
		output.append("\\section{" + sectionName + "}" + System.getProperty("line.separator"));
	}
	
	public void addCVLine (String val1, String val2){
		// Reset saved vals
		for (int i = 0; i < this.vals.length; i ++) this.vals[i] = null;
		// Set type of latex function

		// Throw exeption if val2 is not given
		if (val2 == "" || val2 == null){
			throw new IllegalArgumentException("Invalid Input! Missing Second string.");
		}
		// Set val1 and val2 into the class array
		else {
			this.vals[0] = val1;
			this.vals[1] = val2;
		}
		
		
		
		// Append the cvline line to the output builder
		output.append("\\cvline{");
		if (this.vals[0] == null)
			output.append("");
		else {
			output.append(this.vals[0]);
		}
		output.append("}{" + this.vals[1] + "}");
		output.append(System.getProperty("line.separator"));
	}
	
	public void addCVEntry (String... vals){
		// Reset saved vals
		for (int i = 0; i < this.vals.length; i ++) this.vals[i] = null;
		
		// Throw exception if first or/and second string is not given
		if (vals.length == 0 || vals.length == 1){
			throw new IllegalArgumentException("Invalid Input! Missing first or second input.");
		}

		// Throw exception if given input is more than allowed
		else if (vals.length > 6){
			throw new IllegalArgumentException("Invalid Input! Too many Strings given!");
		}
		
		// Throw exception if first or second input is not given
		else if (vals[0] == null && vals[1] == null){
			throw new IllegalArgumentException("Invalid Input! Missing first or second input.");
		}
		// If everything went good, set the given input into the class array
		else {
			for (int i = 0; i < vals.length; i ++){
				this.vals[i] = vals[i];
			}
		}
		
		// Append the cventry line to the output builder
		output.append("\\cventry");
		for (int i = 0; i < this.vals.length; i++){
			if (this.vals[i] == null){
				output.append("{}");
			}
			else{
				output.append("{" + this.vals[i] + "}");
			}
		}
		output.append(System.getProperty("line.separator"));
	}
	
	public String toString(){		
		// Make StringBuilder to String
		return output.toString();
	}
	
}

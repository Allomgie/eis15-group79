
public class CurriculumVitae{

	// Set String Builder
	StringBuilder curVit = new StringBuilder("");
	
	// Style and Color enums
	private enum Style {casual, classic, oldstyle, banking};
	private enum ColorScheme{black, blue, green, grey, orange, purple, red};
	
	// Default Style and Color
	private Style style = Style.casual;
	private ColorScheme color = ColorScheme.black;
	private boolean wide_layout = false;	
	
	// Important input
	private String name;
	private String surname; 
	
	// Optional input
	private String subtitle; 
	private String path_photo;
	
	
	// Number of sections
	private int secNumber;
	
	// Array of Sections
	Section[] secArray;
	
	// Methodes
	public CurriculumVitae (int sections){
		this.secNumber = sections;
		this.secArray = new Section[sections];
	}
	
	// Setter
	public void setStyle (Style input){
		this.style = input;
	}
	public void setColor (ColorScheme input){
		this.color = input;
	}
	public void setWide (Boolean input){
		this.wide_layout = input;
	}
	public void setName (String input){
		this.name = input;
	}
	public void setSurname (String input){
		this.surname = input;
	}
	public void setSubtitle (String input){
		this.subtitle = input;
	}
	public void setPhoto (String input){
		this.path_photo = input;
	}
	
	// Methode for setting a section to a spezific number
	public void SetSection (int number, Section section){
		section.setNumber(number);
	}
	
	public String getCV() throws IncompleteCVException{
		if (this.name == null || this.surname == null){
			throw new IncompleteCVException("Print failture. Name or/and Surname missing!");
		}
		else{
			// Line 1 - 4
			curVit.append("% setup document" + System.getProperty("line.separator"));
			curVit.append("\\documentclass[11pt,a4paper]{moderncv}" + System.getProperty("line.separator"));
			curVit.append("\\moderncvtheme[" + this.color + "]{" + this.style + "}" + System.getProperty("line.separator"));
			curVit.append(System.getProperty("line.separator"));
			
			// Line 5 - 9
			curVit.append("\\usepackage[german]{babel}\t" + "% set language" + System.getProperty("line.separator"));
			curVit.append("\\usepackage[utf8]{inputenc}\t" + "% use UTF-8 encoding" + System.getProperty("line.separator"));
			if (this.wide_layout == true){
				curVit.append("\\usepackage{geometry}\t\t" + "% use wide page layout" + System.getProperty("line.separator"));				
			}
			else {
				curVit.append(System.getProperty("line.separator"));
			}
			curVit.append(System.getProperty("line.separator"));
			
			// Line 10 - 15
			curVit.append("\\firstname{" + this.name + "}" + System.getProperty("line.separator"));
			curVit.append("\\familyname{" + this.surname + "}" + System.getProperty("line.separator"));
			curVit.append("\\title{" + this.subtitle + "}" + System.getProperty("line.separator"));
			if (this.path_photo == null){
				curVit.append(System.getProperty("line.separator"));
			}else{
				curVit.append("\\photo[96pt]{" + this.path_photo + "}" + System.getProperty("line.separator"));				
			}
			curVit.append(System.getProperty("line.separator"));	
			
			// Line 16 - 19
			curVit.append("\\begin{document}" + System.getProperty("line.separator"));
			curVit.append("\\makecvtitle" + System.getProperty("line.separator"));
			curVit.append(System.getProperty("line.separator"));
			
			// Line 20 - end
			for(int i = 0; i < this.secNumber; i++){
				curVit.append("% section " + i + System.getProperty("line.separator"));
				curVit.append(this.secArray[i].toString());
				curVit.append(System.getProperty("line.separator"));
			}
			
			// End
			curVit.append("\\end{document}" + System.getProperty("line.separator"));
			
			// Return String Builder
			return this.curVit.toString();	
		}
		

	}
	
	
	
	
	public static void main (String... args) throws IncompleteCVException{
		CurriculumVitae cartman = new CurriculumVitae (3);
		
		cartman.color = ColorScheme.red;
		cartman.style = Style.oldstyle;
		
		cartman.name = "Eric";
		cartman.surname = "Cartman";
		cartman.subtitle = "South Park Elementary School";
		
		cartman.wide_layout = true;
		
		cartman.secArray[0] = new Section ("Personal");
		cartman.secArray[0].addCVLine("Gender", "Male");
		cartman.secArray[0].addCVLine("Birthday", "1st of July");
		cartman.secArray[0].addCVLine("Age", "18");
		cartman.secArray[0].addCVEntry(null, "Hobbies", "Playing with my toys", "Making fun of Kyle");
		
		cartman.secArray[1] = new Section ("Frieds");
		cartman.secArray[1].addCVLine("Best Friends", "Kenny, Butters");
		
		cartman.secArray[2] = new Section ("School");
		cartman.secArray[2].addCVEntry("Today", "1997", "South Park Elementary School");
		
		System.out.println(cartman.getCV());
	}
	
	
}

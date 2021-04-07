package program;

import java.util.Scanner;

public class StudentMember extends DefaultMember {
    public StudentMember(){
        super();
    }
    private String schoolName;

    public String getSchoolName(){
        return this.schoolName;
    }
    public void setSchoolName(String sName){
        Scanner input=new Scanner(System.in);
        while (true) {
            //try block is used find whether user has entered a number for the school name
            //if user has entered a number then asking user to enter a string or text value
            try {
                int check = Integer.parseInt(sName);
                System.out.println("Please enter text value for school name!!!");
                System.out.println("Please enter new member's school name: ");
                sName = input.nextLine();
            } catch (NumberFormatException e) {
                this.schoolName=sName;
                break;
            }

        }
    }
}

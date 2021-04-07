package program;

import java.util.Scanner;

public class DefaultMember {
    public DefaultMember(){
        super();
    }

    private int membershipNumber;
    private String name;
    private String startMembershipDate;

    public int getMembershipNumber(){
        return this.membershipNumber;
    }
    public void setMembershipNumber(int mNum){
        Scanner input=new Scanner(System.in);
        while (true) {
            //checking whether user has entered negative value if so asking user to enter a positive value
            if (mNum < 0) {
                System.out.println("Please enter positive value for membership number!!!");
                System.out.println("Please enter membership number: ");
                mNum = input.nextInt();
            } else {
                this.membershipNumber = mNum;
                break;
            }
        }
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        Scanner input=new Scanner(System.in);
        while (true) {
            //try block is used find whether user has entered a number for the name
            //if user has entered a number then asking user to enter a string or text value
            try {
                int check = Integer.parseInt(name);
                System.out.println("Please enter text value for name!!!");
                System.out.println("Please enter new member's name: ");
                name = input.nextLine();
            } catch (NumberFormatException e) {
                this.name = name;
                break;
            }

        }
    }

    public String getStartMembershipDate(){
        return this.startMembershipDate;
    }
    public void setStartMembershipDate(String date){
        this.startMembershipDate=date;
    }

}

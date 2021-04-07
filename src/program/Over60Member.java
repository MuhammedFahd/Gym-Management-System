package program;

import java.util.Scanner;

public class Over60Member extends DefaultMember {
    public Over60Member(){
        super();
    }
    private int age;

    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        Scanner input=new Scanner(System.in);
        while(true) {
            //checking whether age is less than 60, if so asking user to re-enter the age
            if (age < 60) {
                System.out.println("The age should be above 60!!!");
                System.out.println("Please enter new member's age: ");
                age = input.nextInt();
            } else {
                this.age = age;
                break;
            }
        }
    }
}

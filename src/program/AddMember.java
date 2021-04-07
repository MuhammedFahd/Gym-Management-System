package program;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddMember {

    public static void defaultMember(DefaultMember dMember, DBCollection coll){
        //variables needed to enter data
        int membershipNum;
        String name;
        int day;
        int month;
        int year;

        Scanner input2=new Scanner(System.in);
        Scanner input3=new Scanner(System.in);
        while(true) {
            try {
                System.out.println("Please enter new member's name: ");
                name = input3.nextLine();
                System.out.println("Please enter membership number: ");
                membershipNum = input2.nextInt();
                System.out.println("Please enter starting membership date in following format:");
                System.out.println("Day: ");
                day = input2.nextInt();
                System.out.println("Month: ");
                month = input2.nextInt();
                System.out.println("Year: ");
                year = input2.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!!!");
                System.out.println("please enter valid inputs!!!");
                input2.next();
            }
        }

        //checking whether entered membership number exists in the system and gives proper response to the user
        while (true) {
            BasicDBObject docCheck = new BasicDBObject("Membership number", membershipNum);
            Cursor cValid = coll.find(docCheck);
            int valid = 0;
            while (cValid.hasNext()) {
                valid = Integer.parseInt(cValid.next().get("Membership number").toString());
            }
            if (valid == membershipNum) {
                System.out.println("The membership number that you entered already exist in the system!!!");
                System.out.println("please enter a non-existing value!!!");
                System.out.println("Please enter the membership number of the member you want to add:");
                membershipNum = input2.nextInt();
            }else{
                break;
            }
        }




        dMember.setMembershipNumber(membershipNum);
        dMember.setName(name);
        Date newDate=new Date(day,month,year);
        if(newDate.isValidDate()){
            dMember.setStartMembershipDate(newDate.stringDate());
        }else{
            System.out.println("invalid date!!!");
        }

        //showing the entered details
        System.out.println("--------new member details--------");
        System.out.println("member's name: "+dMember.getName());
        System.out.println("membership number: "+dMember.getMembershipNumber());
        System.out.println("starting membership date: "+dMember.getStartMembershipDate());
    }

    public static void studentMember(StudentMember sMember,DBCollection coll){
        //variables needed to enter data
        int membershipNum;
        String name;
        int day;
        int month;
        int year;
        String schoolName;

        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter new member's name: ");
                name = input3.nextLine();
                System.out.println("Please enter new member's school name: ");
                schoolName = input3.nextLine();
                System.out.println("Please enter membership number: ");
                membershipNum = input2.nextInt();
                System.out.println("Please enter starting membership date in following format:");
                System.out.println("Day: ");
                day = input2.nextInt();
                System.out.println("Month: ");
                month = input2.nextInt();
                System.out.println("Year: ");
                year = input2.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!!!");
                System.out.println("please enter valid inputs!!!");
                input2.next();
            }
        }

        //checking whether entered membership number exists in the system and gives proper response to the user
        while (true) {
            BasicDBObject docCheck = new BasicDBObject("Membership number", membershipNum);
            Cursor cValid = coll.find(docCheck);
            int valid = 0;
            while (cValid.hasNext()) {
                valid = Integer.parseInt(cValid.next().get("Membership number").toString());
            }
            if (valid == membershipNum) {
                System.out.println("The membership number that you entered already exist in the system!!!");
                System.out.println("please enter a non-existing value!!!");
                System.out.println("Please enter the membership number of the member you want to add:");
                membershipNum = input2.nextInt();
            }else {
                break;
            }
        }




        sMember.setMembershipNumber(membershipNum);
        sMember.setName(name);
        sMember.setSchoolName(schoolName);
        Date newDate=new Date(day,month,year);
        if(newDate.isValidDate()){
            sMember.setStartMembershipDate(newDate.stringDate());
        }else{
            System.out.println("invalid date!!!");
        }

        //showing the entered details
        System.out.println("--------new member details--------");
        System.out.println("member's name: "+sMember.getName());
        System.out.println("member's school: "+sMember.getSchoolName());
        System.out.println("membership number: "+sMember.getMembershipNumber());
        System.out.println("starting membership date: "+sMember.getStartMembershipDate());

    }

    public static void over60Member(Over60Member o60Member,DBCollection coll){
        //variables needed to enter data
        int membershipNum;
        String name;
        int day;
        int month;
        int year;
        int age;

        Scanner input2=new Scanner(System.in);
        Scanner input3=new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter new member's name: ");
                name = input3.nextLine();
                System.out.println("Please enter membership number: ");
                membershipNum = input2.nextInt();
                System.out.println("Please enter starting membership date in following format:");
                System.out.println("Day: ");
                day = input2.nextInt();
                System.out.println("Month: ");
                month = input2.nextInt();
                System.out.println("Year: ");
                year = input2.nextInt();
                System.out.println("Please enter new member's age: ");
                age = input2.nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Invalid input!!!");
                System.out.println("please enter valid inputs!!!");
                input2.next();
            }
        }

        //checking whether entered membership number exists in the system and gives proper response to the user
        while (true) {
            BasicDBObject docCheck = new BasicDBObject("Membership number", membershipNum);
            Cursor cValid = coll.find(docCheck);
            int valid = 0;
            while (cValid.hasNext()) {
                valid = Integer.parseInt(cValid.next().get("Membership number").toString());
            }
            if (valid == membershipNum) {
                System.out.println("The membership number that you entered already exist in the system!!!");
                System.out.println("please enter a non-existing value!!!");
                System.out.println("Please enter the membership number of the member you want to add:");
                membershipNum = input2.nextInt();
            }else{
                break;
            }
        }


        o60Member.setMembershipNumber(membershipNum);
        o60Member.setName(name);
        o60Member.setAge(age);
        Date newDate=new Date(day,month,year);
        if(newDate.isValidDate()){
            o60Member.setStartMembershipDate(newDate.stringDate());
        }else{
            System.out.println("invalid date!!!");
        }

        //showing the entered details
        System.out.println("--------new member details--------");
        System.out.println("member's name: "+o60Member.getName());
        System.out.println("membership number: "+o60Member.getMembershipNumber());
        System.out.println("starting membership date: "+o60Member.getStartMembershipDate());
        System.out.println("member's age: "+o60Member.getAge());
    }
}

package program;

import com.mongodb.*;
import javafx.application.Application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MyGymManager {
    public static void main(String[] args){
        //connecting to mongodb database
        MongoClient mongoClient=new MongoClient("localhost",27017);

        //creating database and collection
        DB dbs=mongoClient.getDB("GymManager");
        DBCollection coll=dbs.getCollection("members");
        String action="";

        while (!action.equals("q")) {
            //finding the no of records in the database
            int countMembers = 0;
            Cursor cFind = coll.find();
            while (cFind.hasNext()) {
                cFind.next();
                countMembers += 1;

            }


            //start of the program
            System.out.println("---------Welcome to MyGymManager---------");
            System.out.println("Please select what action u want to perform!!!");
            System.out.println("Press a: Add a new member");
            System.out.println("Press d: Delete a member");
            System.out.println("Press p: Print the list of members");
            System.out.println("Press s: Sort in ascending order by name");
            System.out.println("Press w: write details in a file");
            System.out.println("Press f: find and search member's details");
            System.out.println("Press q: exit the program");


            Scanner input = new Scanner(System.in);
            System.out.println("Please select what action u want to perform: ");
            action = input.nextLine();
            if (action.equals("a")) {
                //check the no of free slots available and display message then proceed
                if (countMembers < 100) {
                    System.out.println((100 - countMembers) + " slots are available");

                    Scanner input2 = new Scanner(System.in);
                    Scanner input3 = new Scanner(System.in); //new scanner class
                    System.out.println("Please enter membership type: ");
                    String type = input2.nextLine();
                    type = type.toLowerCase();


                    //checking the membership type and entering data
                    if (type.equals("default") || type.equals("default member")) {
                        DefaultMember dMember = new DefaultMember();
                        AddMember.defaultMember(dMember, coll); //calling the method to add new member details
                        while (true) {
                            System.out.println("Did you enter correct details(y/n):");
                            String response = input3.nextLine();
                            response = response.toLowerCase();

                            if (response.equals("y")) {
                                System.out.println("u have successfully added a new member");
                                //adds the data to the database
                                BasicDBObject doc = new BasicDBObject("Membership number", dMember.getMembershipNumber());
                                doc.append("Name", dMember.getName());
                                doc.append("Starting membership date", dMember.getStartMembershipDate());
                                doc.append("Membership type", type);
                                coll.insert(doc);
                                break;
                            } else if (response.equals("n")) {
                                AddMember.defaultMember(dMember, coll);
                            } else {
                                System.out.println("please enter proper response!!!");
                            }
                        }

                    } else if (type.equals("student") || type.equals("student member")) {
                        StudentMember sMember = new StudentMember();
                        AddMember.studentMember(sMember, coll); //calling the method to add new member details
                        while (true) {
                            System.out.println("Did you enter correct details(y/n):");
                            String response = input3.nextLine();
                            response = response.toLowerCase();

                            if (response.equals("y")) {
                                System.out.println("u have successfully added a new member");
                                //adds the data to the database
                                BasicDBObject doc = new BasicDBObject("Membership number", sMember.getMembershipNumber());
                                doc.append("Name", sMember.getName());
                                doc.append("School name", sMember.getSchoolName());
                                doc.append("Starting membership date", sMember.getStartMembershipDate());
                                doc.append("Membership type", type);
                                coll.insert(doc);
                                break;
                            } else if (response.equals("n")) {
                                AddMember.studentMember(sMember, coll);
                            } else {
                                System.out.println("please enter proper response!!!");
                            }
                        }

                    } else if (type.equals("over60") || type.equals("over 60") || type.equals("over60 member") || type.equals("over 60 member")) {
                        Over60Member o60Member = new Over60Member();
                        AddMember.over60Member(o60Member, coll); //calling the method to add new member details
                        while (true) {
                            System.out.println("Did you enter correct details(y/n):");
                            String response = input3.nextLine();
                            response = response.toLowerCase();

                            if (response.equals("y")) {
                                System.out.println("u have successfully added a new member");
                                //adds the data to the database
                                BasicDBObject doc = new BasicDBObject("Membership number", o60Member.getMembershipNumber());
                                doc.append("Name", o60Member.getName());
                                doc.append("Age", o60Member.getAge());
                                doc.append("Starting membership date", o60Member.getStartMembershipDate());
                                doc.append("Membership type", type);
                                coll.insert(doc);
                                break;
                            } else if (response.equals("n")) {
                                AddMember.over60Member(o60Member, coll);
                            } else {
                                System.out.println("please enter proper response!!!");
                            }
                        }

                    } else {
                        System.out.println("please enter proper response!!!");
                    }
                } else {
                    System.out.println("no slots available!!!");

                }


            } else if (action.equals("d")) {
                Scanner input2 = new Scanner(System.in);
                Scanner input3 = new Scanner(System.in);
                System.out.println((100 - countMembers) + " slots are available");

                String response = "";

                System.out.println("Please enter the membership number of the member you want to delete:");
                int membershipNum = input2.nextInt();

                while (true) {
                    //asking the user to confirm to delete
                    System.out.println("Are you sure you want to delete this member?(y/n)");
                    response = input3.nextLine();
                    response = response.toLowerCase();
                    if (response.equals("y")) {
                        //deleting the record from the database
                        BasicDBObject docDelete = new BasicDBObject("Membership number", membershipNum);
                        Cursor cc2 = coll.find(docDelete);
                        Cursor cValid = coll.find(docDelete);
                        int valid = 0;


                        //checking whether entered membership number exists in the system and gives proper response to the user
                        while (cValid.hasNext()) {
                            valid = Integer.parseInt(cValid.next().get("Membership number").toString());
                        }
                        if (valid != membershipNum) {
                            System.out.println("The membership number that you entered doesn't exist in the system!!!");
                            System.out.println("please enter a existing value!!!");
                            System.out.println("Please enter the membership number of the member you want to delete:");
                            membershipNum = input2.nextInt();
                        } else {
                            while (cc2.hasNext()) {
                                System.out.println("The membership type of the deleted member is " + cc2.next().get("Membership type"));

                            }
                            coll.remove(docDelete);
                            break;
                        }

                    } else if (response.equals("n")) {
                        System.out.println("Please enter the membership number of the member you want to delete:");
                        membershipNum = input2.nextInt();
                    } else {
                        System.out.println("please enter a proper response!!!");
                    }
                }
            } else if (action.equals("p")) {
                System.out.println("---------list of members---------");
                System.out.println("membership number\tname\tstart membership date\tmembership type");
                Cursor cPrint1 = coll.find();
                Cursor cPrint2 = coll.find();
                Cursor cPrint3 = coll.find();
                Cursor cPrint4 = coll.find();
                ArrayList<String> list1 = new ArrayList<String>();
                ArrayList<String> list2 = new ArrayList<String>();
                ArrayList<String> list3 = new ArrayList<String>();
                ArrayList<String> list4 = new ArrayList<String>();

                //obtaining all the membership numbers in order
                while (cPrint1.hasNext()) {
                    String mNum = cPrint1.next().get("Membership number").toString();
                    list1.add(mNum);
                }
                cPrint1.close();

                //obtaining all the members names in order
                while (cPrint2.hasNext()) {
                    String name = cPrint2.next().get("Name").toString();
                    list2.add(name);
                }
                cPrint2.close();

                //obtaining all the dates in order
                while (cPrint3.hasNext()) {
                    String sDate = cPrint3.next().get("Starting membership date").toString();
                    list3.add(sDate);
                }
                cPrint3.close();

                //obtaining all the membership types in order
                while (cPrint4.hasNext()) {
                    String mType = cPrint4.next().get("Membership type").toString();
                    list4.add(mType);
                }
                cPrint4.close();

                //printing the list of members in a table structure
                for (int i = 0; i < list1.size(); i++) {
                    System.out.println("\t\t" + list1.get(i) + "\t\t\t" + list2.get(i) + "\t\t\t" + list3.get(i) + "\t\t\t" + list4.get(i));
                }
            } else if (action.equals("s")) {
                System.out.println("---------sorted list of members---------");
                System.out.println("membership number\tname\tstart membership date\tmembership type");

                //sorting the documents according to name and saving in these cursors
                Cursor cSort1 = coll.find().sort(new BasicDBObject("Name", 1));
                Cursor cSort2 = coll.find().sort(new BasicDBObject("Name", 1));
                Cursor cSort3 = coll.find().sort(new BasicDBObject("Name", 1));
                Cursor cSort4 = coll.find().sort(new BasicDBObject("Name", 1));
                ArrayList<String> list1 = new ArrayList<String>();
                ArrayList<String> list2 = new ArrayList<String>();
                ArrayList<String> list3 = new ArrayList<String>();
                ArrayList<String> list4 = new ArrayList<String>();

                //obtaining all the membership numbers in order
                while (cSort1.hasNext()) {
                    String mNum = cSort1.next().get("Membership number").toString();
                    list1.add(mNum);
                }
                cSort1.close();

                //obtaining all the members names in order
                while (cSort2.hasNext()) {
                    String name = cSort2.next().get("Name").toString();
                    list2.add(name);
                }
                cSort2.close();

                //obtaining all the dates in order
                while (cSort3.hasNext()) {
                    String sDate = cSort3.next().get("Starting membership date").toString();
                    list3.add(sDate);
                }
                cSort3.close();

                //obtaining all the membership types in order
                while (cSort4.hasNext()) {
                    String mType = cSort4.next().get("Membership type").toString();
                    list4.add(mType);
                }
                cSort4.close();

                //printing the sorted list of members in a table structure
                for (int i = 0; i < list1.size(); i++) {
                    System.out.println("\t\t" + list1.get(i) + "\t\t\t" + list2.get(i) + "\t\t\t" + list3.get(i) + "\t\t\t" + list4.get(i));
                }


            } else if (action.equals("w")) {

                try {
                    FileWriter dataWrite = new FileWriter("members list.txt");
                    dataWrite.write("membership number\tname\tstart membership date\tmembership type\t\tschool name\t\tage\n");
                    Cursor cSave1 = coll.find();
                    Cursor cSave2 = coll.find();
                    Cursor cSave3 = coll.find();
                    Cursor cSave4 = coll.find();
                    Cursor cSave5 = coll.find();
                    Cursor cSave6 = coll.find();
                    ArrayList<String> list1 = new ArrayList<String>();
                    ArrayList<String> list2 = new ArrayList<String>();
                    ArrayList<String> list3 = new ArrayList<String>();
                    ArrayList<String> list4 = new ArrayList<String>();
                    ArrayList<String> list5 = new ArrayList<String>();
                    ArrayList<String> list6 = new ArrayList<String>();

                    //obtaining all the membership numbers in order
                    while (cSave1.hasNext()) {
                        String mNum = cSave1.next().get("Membership number").toString();
                        list1.add(mNum);
                    }
                    cSave1.close();

                    //obtaining all the members names in order
                    while (cSave2.hasNext()) {
                        String name = cSave2.next().get("Name").toString();
                        list2.add(name);
                    }
                    cSave2.close();

                    //obtaining all the dates in order
                    while (cSave3.hasNext()) {
                        String sDate = cSave3.next().get("Starting membership date").toString();
                        list3.add(sDate);
                    }
                    cSave3.close();

                    //obtaining all the membership types in order
                    while (cSave4.hasNext()) {
                        String mType = cSave4.next().get("Membership type").toString();
                        list4.add(mType);
                    }
                    cSave4.close();

                    //obtaining all the school names in order
                    while (cSave5.hasNext()) {
                        String sName;
                        try {
                            sName = cSave5.next().get("School name").toString();
                            list5.add(sName);
                        } catch (NullPointerException e) {
                            //if school name doesn't exits for a certain record it assigns "-" value
                            sName = "-";
                            list5.add(sName);
                        }
                    }
                    cSave5.close();

                    //obtaining all the age values in order
                    while (cSave6.hasNext()) {
                        String age;
                        try {
                            age = cSave6.next().get("Age").toString();
                            list6.add(age);
                        } catch (NullPointerException e) {
                            //if age value doesn't exits for a certain record it assigns "-" value
                            age = "-";
                            list6.add(age);
                        }
                    }
                    cSave6.close();

                    //writes the list of members in a table structure to members list.txt file
                    for (int i = 0; i < list1.size(); i++) {
                        dataWrite.write("\t\t" + list1.get(i) + "\t\t\t" + list2.get(i) + "\t\t\t" + list3.get(i) + "\t\t\t" + list4.get(i) + "\t\t\t" + list5.get(i) + "\t\t\t\t" + list6.get(i) + "\n");
                    }
                    dataWrite.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (action.equals("f")) {
                try {
                    Application.launch(GymManager.class, args);
                }catch (IllegalStateException e){
                    System.out.println("can be run only once!!!");
                    System.out.println("please restart the program!!!");
                }
            }else if(!action.equals("q")){
                System.out.println("please enter a proper response!!!");
            }
        }

    }
}

package program;

import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCollection;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class SearchMember {
    public static void search(BasicDBObject docSearch, DBCollection coll, TableView searchMember){
        Cursor cSearch1 = coll.find(docSearch);
        Cursor cSearch2= coll.find(docSearch);
        Cursor cSearch3= coll.find(docSearch);
        Cursor cSearch4= coll.find(docSearch);
        Cursor cSearch5= coll.find(docSearch);
        Cursor cSearch6= coll.find(docSearch);
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> list3 = new ArrayList<String>();
        ArrayList<String> list4 = new ArrayList<String>();
        ArrayList<String> list5 = new ArrayList<String>();
        ArrayList<String> list6 = new ArrayList<String>();
        while (cSearch1.hasNext()) {
            String mNum = cSearch1.next().get("Membership number").toString();
            list1.add(mNum);
        }
        cSearch1.close();
        while (cSearch2.hasNext()) {
            String name = cSearch2.next().get("Name").toString();
            list2.add(name);
        }
        cSearch2.close();
        while (cSearch3.hasNext()) {
            String sDate = cSearch3.next().get("Starting membership date").toString();
            list3.add(sDate);
        }
        cSearch3.close();
        while (cSearch4.hasNext()) {
            String mType = cSearch4.next().get("Membership type").toString();
            list4.add(mType);
        }
        cSearch4.close();

        while (cSearch5.hasNext()) {
            String sName;
            try {
                sName = cSearch5.next().get("School name").toString();
                list5.add(sName);
            } catch (NullPointerException e) {
                sName = "-";
                list5.add(sName);
            }
        }
        cSearch5.close();

        while (cSearch6.hasNext()) {
            String age;
            try {
                age = cSearch6.next().get("Age").toString();
                list6.add(age);
            } catch (NullPointerException e) {
                age = "-";
                list6.add(age);
            }
        }
        cSearch6.close();

        for (int i = 0; i < list1.size(); i++) {
            searchMember.getItems().add(new GymMembers(list1.get(i),list2.get(i),list3.get(i),list4.get(i),list5.get(i),list6.get(i)));
        }

    }
}

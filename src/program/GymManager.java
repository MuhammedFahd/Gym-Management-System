package program;

import com.mongodb.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GymManager extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gym Manager");

        //connecting to mongodb database
        MongoClient mongoClient=new MongoClient("localhost",27017);

        DB dbs=mongoClient.getDB("GymManager");
        DBCollection coll=dbs.getCollection("members");

        //creating table view
        TableView members=new TableView();
        members.setPrefSize(700,300);

        //creating column fields
        TableColumn<String,GymMembers> column1=new TableColumn<>("Membership Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("membershipNum"));

        TableColumn<String,GymMembers> column2=new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String,GymMembers> column3=new TableColumn<>("Start Membership Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("Date"));

        TableColumn<String,GymMembers> column4=new TableColumn<>("Membership Type");
        column4.setCellValueFactory(new PropertyValueFactory<>("membershipType"));

        TableColumn<String,GymMembers> column5=new TableColumn<>("School Name");
        column5.setCellValueFactory(new PropertyValueFactory<>("schoolName"));

        TableColumn<String,GymMembers> column6=new TableColumn<>("Age");
        column6.setCellValueFactory(new PropertyValueFactory<>("age"));

        members.getColumns().addAll(column1,column2,column3,column4,column5,column6);

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
        while (cSave1.hasNext()) {
            String mNum = cSave1.next().get("Membership number").toString();
            list1.add(mNum);
        }
        cSave1.close();
        while (cSave2.hasNext()) {
            String name = cSave2.next().get("Name").toString();
            list2.add(name);
        }
        cSave2.close();
        while (cSave3.hasNext()) {
            String sDate = cSave3.next().get("Starting membership date").toString();
            list3.add(sDate);
        }
        cSave3.close();
        while (cSave4.hasNext()) {
            String mType = cSave4.next().get("Membership type").toString();
            list4.add(mType);
        }
        cSave4.close();

        while (cSave5.hasNext()) {
            String sName;
            try {
                sName = cSave5.next().get("School name").toString();
                list5.add(sName);
            } catch (NullPointerException e) {
                sName = "-";
                list5.add(sName);
            }
        }
        cSave5.close();

        while (cSave6.hasNext()) {
            String age;
            try {
                age = cSave6.next().get("Age").toString();
                list6.add(age);
            } catch (NullPointerException e) {
                age = "-";
                list6.add(age);
            }
        }
        cSave6.close();

        for (int i = 0; i < list1.size(); i++) {
            members.getItems().add(new GymMembers(list1.get(i),list2.get(i),list3.get(i),list4.get(i),list5.get(i),list6.get(i)));
        }

        //creating textfield
        TextField txtGet=new TextField();
        txtGet.setLayoutX(14);
        txtGet.setLayoutY(313);
        txtGet.setPromptText("Search bar");

        //creating button
        Button btnSearch=new Button("search");
        btnSearch.setLayoutX(183);
        btnSearch.setLayoutY(313);

        AnchorPane pane=new AnchorPane();
        pane.getChildren().add(members);
        pane.getChildren().add(txtGet);
        pane.getChildren().add(btnSearch);


        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //new Tableview
                TableView searchMember=new TableView();
                searchMember.setPrefSize(700,141);
                searchMember.setLayoutX(0);
                searchMember.setLayoutY(352);

                //creating column fields
                TableColumn<String,GymMembers> sColumn1=new TableColumn<>("Membership Number");
                sColumn1.setCellValueFactory(new PropertyValueFactory<>("membershipNum"));

                TableColumn<String,GymMembers> sColumn2=new TableColumn<>("Name");
                sColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));

                TableColumn<String,GymMembers> sColumn3=new TableColumn<>("Start Membership Date");
                sColumn3.setCellValueFactory(new PropertyValueFactory<>("Date"));

                TableColumn<String,GymMembers> sColumn4=new TableColumn<>("Membership Type");
                sColumn4.setCellValueFactory(new PropertyValueFactory<>("membershipType"));

                TableColumn<String,GymMembers> sColumn5=new TableColumn<>("School Name");
                sColumn5.setCellValueFactory(new PropertyValueFactory<>("schoolName"));

                TableColumn<String,GymMembers> sColumn6=new TableColumn<>("Age");
                sColumn6.setCellValueFactory(new PropertyValueFactory<>("age"));

                searchMember.getColumns().addAll(sColumn1,sColumn2,sColumn3,sColumn4,sColumn5,sColumn6);



                List<List<String>> arrSearch=new ArrayList<>();
                BasicDBObject docSearch=new BasicDBObject();
                for(int j=0;j<list1.size();j++){
                    String searchValue=txtGet.getText();
                    if(searchValue.equals(list1.get(j))){
                        docSearch = new BasicDBObject("Membership number",Integer.parseInt(searchValue));

                    }else if(searchValue.equals(list2.get(j))){
                        docSearch = new BasicDBObject("Name",searchValue);

                    }else if(searchValue.equals(list3.get(j))){
                        docSearch = new BasicDBObject("Starting membership date",searchValue);
                    }else if(searchValue.equals(list4.get(j))){
                        docSearch = new BasicDBObject("Membership type",searchValue);

                    }else if(searchValue.equals(list5.get(j))){
                        docSearch = new BasicDBObject("School name",searchValue);

                    }else if(searchValue.equals(list6.get(j))){
                        docSearch = new BasicDBObject("Age",Integer.parseInt(searchValue));

                    }
                }
                SearchMember.search(docSearch,coll,searchMember);
                pane.getChildren().add(searchMember);




            }
        });








        primaryStage.setScene(new Scene(pane,700,500));
        primaryStage.show();






    }
}

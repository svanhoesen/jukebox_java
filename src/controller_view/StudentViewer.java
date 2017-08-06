package controller_view;

/**
  * Author: Steffan Van Hoesen and Anthony Middleton
  * 
  * Class StudentViewer: This is the class that handles the student list in the admin functions. 
  */

import java.io.Serializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import model.StudentCollection;

public class StudentViewer extends TableView implements Serializable{

  private ObservableList<Student> students;
  private StudentCollection studentCollection;
  
  @SuppressWarnings("unchecked")
  public StudentViewer() {
    
    // Add columns and rows 
    TableColumn<Student, String> name = new TableColumn<>("Name");
    TableColumn<Student, Double> pw = new TableColumn<>("Password");
    
    name.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
    pw.setCellValueFactory(new PropertyValueFactory<Student, Double>("pw"));
    
    this.getColumns().addAll(name, pw);
    
    name.setPrefWidth(100);
    pw.setPrefWidth(60);
    this.setWidth(228);
    
    // Set the model for this TableView
    studentCollection = new StudentCollection();
    students = FXCollections.observableArrayList();
    for(int i = 0; i < studentCollection.size(); i++) {
      students.add(studentCollection.get(i));
    }
    this.setItems(students);

  }

  public StudentCollection getList() {
    return studentCollection;
  }
}

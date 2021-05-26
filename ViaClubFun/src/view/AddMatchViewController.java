package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;

import java.time.LocalDate;

public class AddMatchViewController
{
  private Region root;
  private ViaClubModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button searchButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;
  @FXML private Button saveButton;
  @FXML private Button cancelButton;

  @FXML private DatePicker datePicker;

  @FXML private ComboBox<Integer> startTimeHourBox;
  @FXML private ComboBox<Integer> endTimeHourBox;
  @FXML private ComboBox<Integer> startTimeMinuteBox;
  @FXML private ComboBox<Integer> endTimeMinuteBox;
  @FXML private ComboBox<String> searchBox;
  @FXML private ComboBox<String> matchTypeBox;

  @FXML private TextField opponentField;
  @FXML private TextField searchField;

  @FXML private MenuItem exitMenuItem;

  @FXML private ListView<Player> allPlayersList;
  @FXML private ListView<Player> lineUpAndBenchList;

  @FXML private ToggleGroup fieldAndBenchGroup;
  @FXML private RadioButton fieldRadio;
  @FXML private RadioButton benchRadio;

  @FXML private ToggleGroup gamePlaceGroup;
  @FXML private RadioButton homeRadio;
  @FXML private RadioButton awayRadio;

  private Match editMatch;
  private boolean gamePlaceBoolean;
  private boolean lineUpListBoolean;

  public void init(ViewHandler viewHandler, ViaClubModelManager modelManager,
      Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    datePicker.setValue(LocalDate.now());
    reset();
  }

  public void reset()
  {
    updateTimeBoxes();
    setToggle();
    setMatchTypeBox();

  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == searchButton)
    {

    }
    else if (e.getSource() == addButton)
    {

    }
    else if (e.getSource() == removeButton)
    {

    }
    else if (e.getSource() == saveButton)
    {
      Time stTemp = new Time(
          startTimeHourBox.getSelectionModel().getSelectedItem(),
          startTimeMinuteBox.getSelectionModel().getSelectedItem(), 0);

      Time etTemp = new Time(
          endTimeHourBox.getSelectionModel().getSelectedItem(),
          endTimeMinuteBox.getSelectionModel().getSelectedItem(), 0);

      Date dTemp = new Date(datePicker.getValue().getDayOfMonth(),
          datePicker.getValue().getMonthValue(),
          datePicker.getValue().getYear());

      Match temp = new Match(stTemp, etTemp, dTemp, opponentField.getText(),
          matchTypeBox.getSelectionModel().getSelectedItem(), gamePlaceBoolean);

      MatchList tempList = modelManager.getAllMatches();

      tempList.add(temp);

      modelManager.saveMatches(tempList);
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == cancelButton)
    {
      viewHandler.openView("MainView");
    }
    else if (awayRadio.isSelected())
    {
      gamePlaceBoolean = true;
    }
    else if (homeRadio.isSelected())
    {
      gamePlaceBoolean = false;
    }
    else if (fieldRadio.isSelected())
    {
      lineUpListBoolean = true;
    }
    else if (benchRadio.isSelected())
    {
      lineUpListBoolean = false;
    }

    else if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?", ButtonType.YES,
          ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
  }

  public void updateTimeBoxes()
  {
    for (int i = 0; i < 24; i++)
    {
      startTimeHourBox.getItems().add(i);
      endTimeHourBox.getItems().add(i);
    }
    for (int i = 0; i < 60; i++)
    {
      startTimeMinuteBox.getItems().add(i);
      endTimeMinuteBox.getItems().add(i);
    }
  }

  public void setToggle()
  {
    fieldRadio.setSelected(true);

    homeRadio.setSelected(true);
  }

  public void setMatchTypeBox()
  {
    matchTypeBox.getItems().add("League");
    matchTypeBox.getItems().add("Cup");
    matchTypeBox.getItems().add("Friendly");
  }
}

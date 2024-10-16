package edu.westga.cs1302.bill.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;

import edu.westga.cs1302.bill.model.Bill;
import edu.westga.cs1302.bill.model.BillItem;
import edu.westga.cs1302.bill.model.BillPersistenceManager;
import edu.westga.cs1302.bill.model.CSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.TSVBillPersistenceManager;
import edu.westga.cs1302.bill.model.BillAscendingCostComparator;
import edu.westga.cs1302.bill.model.BillDescendingCostComparator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * The codebehind for the MainWindow of the application
 * 
 * @author CS 1302
 * @version Fall 2024
 */
public class MainWindow {
	private Bill bill;
	private BillPersistenceManager persistenceManager;

	@FXML
	private TextField name;
	@FXML
	private TextField amount;
	@FXML
	private TextArea receiptArea;
	@FXML
	private ComboBox<String> serverName;
	@FXML
	private ComboBox<String> format;
	@FXML
	private ComboBox<Comparator<BillItem>> orderComboBox;

	@FXML
	void addItem(ActionEvent event) {
		try {
			BillItem item = new BillItem(this.name.getText(), Double.parseDouble(this.amount.getText()));
			this.bill.addItem(item);
			this.name.clear();
			this.amount.clear();
			this.updateReceipt();
		} catch (NumberFormatException numError) {
			this.displayErrorPopup("Invalid amount provided, please correct and try again.");
		} catch (IllegalArgumentException argError) {
			this.displayErrorPopup("Unable to add new bill item");
		}
	}

	@FXML
	void sortChanged(ActionEvent event) {
		this.bill.sortBillItems(this.orderComboBox.getValue());
		this.updateReceipt();
	}

	private void updateReceipt() {
		this.receiptArea.setText(BillTextifier.getText(this.bill));
	}

	@FXML
	void selectServer(ActionEvent event) {
		String name = this.serverName.getValue();
		if (name != null) {
			this.bill.setServerName(name);
			this.updateReceipt();
		}
	}

	@FXML
	void saveBillData(ActionEvent event) {
		try {
			this.persistenceManager.saveBillData(this.bill);
		} catch (IOException writeError) {
			this.displayErrorPopup("Unable to save data to file!");
		}
	}

	private void displayErrorPopup(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.showAndWait();
	}

	@FXML
	void changeFormat(ActionEvent event) {
		this.saveBillData(event);
	}

	@FXML
	void initialize() {
		this.serverName.getItems().add("Bob");
		this.serverName.getItems().add("Alice");
		this.serverName.getItems().add("Trudy");

		this.bill = new Bill();

		this.format.getItems().add("CSV Format");
		this.format.getItems().add("TSV Format");
		this.format.setValue("CSV Format");
		this.orderComboBox.getItems().add(new BillAscendingCostComparator());
		this.orderComboBox.getItems().add(new BillDescendingCostComparator());
		this.orderComboBox.setValue(this.orderComboBox.getItems().get(0));

		this.format.setOnAction(event -> {
			String selectedFormat = this.format.getValue();
			if ("CSV Format".equals(selectedFormat)) {
				this.persistenceManager = new CSVBillPersistenceManager();
			} else if ("TSV Format".equals(selectedFormat)) {
				this.persistenceManager = new TSVBillPersistenceManager();
			}
		});

		this.persistenceManager = new CSVBillPersistenceManager();

		try {
			this.bill = this.persistenceManager.loadBillData();
			this.updateReceipt();
		} catch (FileNotFoundException fileError) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("No save data file found, loading with no student data.");
			alert.showAndWait();
		} catch (IOException parseError) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("File not in valid format.");
			alert.setContentText(parseError.getMessage());
			alert.showAndWait();
		}
	}
}
package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Die UI des {@link BarzahlungWrkzeug}.
 * 
 * @author JPRitter
 * @version SoSe 2018
 */

public class BarzahlungWerkzeugUI
{
	private static final String TITEL = "Barzahlung";

	// Die Widgets, aus denen das UI sich zusammensetzt
	private BorderPane _hauptPanel;
	private Stage _stage;

	private Button _abbruchButton;
	private Button _okButton;

	/**
	 * Initialisiert die UI.
	 */
	public BarzahlungWerkzeugUI()
	{
		_stage = new Stage();
		_stage.setTitle(TITEL);
		_hauptPanel = erstellePanel();
	}

	private BorderPane erstellePanel()
	{
		BorderPane hauptPane = new BorderPane();
		Scene scene = new Scene(hauptPane, 850, 650);

		SplitPane splitter = new SplitPane();
		splitter.setDividerPositions(0.75f);
		splitter.getItems().addAll(erstelleBarzahlungAuswahl());

		BorderPane bottomPanel = new BorderPane();
		bottomPanel.setRight(abbruchPane());
		bottomPanel.setLeft(okPane());

		// SplitPane splitterZwei = new SplitPane();
		// splitterZwei.setDividerPositions(0.5f);
		// splitterZwei.getItems().addAll(erstelleAbbruchPanel(), erstelleOkPanel());

		hauptPane.setTop(erstelleUeberschriftPanel());
		hauptPane.setCenter(splitter);
		hauptPane.setBottom(bottomPanel);

		_stage.setScene(scene);

		return hauptPane;
	}

	/**
	 * Liefert den UI-Container, in dem die Widgets angeordnet sind.
	 */
	public Pane getUIPane()
	{
		return _hauptPanel;
	}

	/**
	 * Zeigt das Fenster an.
	 */
	public void zeigeFenster()
	{
		_stage.show();
	}

	/**
	 * Erzeugt das Panel mit der Überschrift fuer das Programm.
	 */
	private Pane erstelleUeberschriftPanel()
	{
		StackPane topPanel = new StackPane(); // Kinder per Default zentriert
		topPanel.setStyle("-fx-background-color: darkblue");

		Label label = new Label(TITEL);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		label.setTextFill(Color.WHITE);

		topPanel.getChildren().add(label);

		return topPanel;
	}

	/**
	 * Erzeugt Abbruch-Pane.
	 */
	private Pane abbruchPane()
	{
		FlowPane abbruchPane = new FlowPane();

		_abbruchButton = new Button("Abbruch");
		_abbruchButton.setOnAction(e -> _stage.close());

		abbruchPane.getChildren().add(_abbruchButton);
		abbruchPane.setAlignment(Pos.CENTER_RIGHT);
		abbruchPane.setPadding(new Insets(10));

		return abbruchPane;
	}

	/**
	 * Erzeugt Ok-Pane.
	 */
	private Pane okPane()
	{
		FlowPane okPane = new FlowPane();

		_okButton = new Button("Ok");
		_okButton.setOnAction(e -> _stage.close());

		okPane.getChildren().add(_okButton);
		okPane.setAlignment(Pos.CENTER_LEFT);
		okPane.setPadding(new Insets(10));

		return okPane;
	}

	private Pane erstelleBarzahlungAuswahl()
	{
		GridPane barzahlungAuswahl = new GridPane();
		barzahlungAuswahl.setAlignment(Pos.CENTER);
		barzahlungAuswahl.setHgap(10);
		barzahlungAuswahl.setVgap(10);
		barzahlungAuswahl.setPadding(new Insets(25, 25, 25, 25));

		Text scenetitle = new Text("Bargeld Kassensystem");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		barzahlungAuswahl.add(scenetitle, 0, 0, 2, 1);

		Label ticketPreis = new Label("TicketPreis:");
		barzahlungAuswahl.add(ticketPreis, 0, 1);

		TextField ticketPreisTextField = new TextField();
		barzahlungAuswahl.add(ticketPreisTextField, 1, 1);

		Label bargeld = new Label("Bargeld:");
		barzahlungAuswahl.add(bargeld, 0, 2);

		TextField bargeldTextField = new TextField();
		barzahlungAuswahl.add(bargeldTextField, 1, 2);

		Label rueckgeld = new Label("Rückgeld:");
		barzahlungAuswahl.add(rueckgeld, 0, 3);

		TextField rueckgeldTextField = new TextField();
		barzahlungAuswahl.add(rueckgeldTextField, 1, 3);

		return barzahlungAuswahl;

	}

}

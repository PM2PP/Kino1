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
	// private ListView<VorstellungsFormatierer> _vorstellungAuswahlList;
	private Stage _stage;

	private Button _abbruchButton;
	private Button _okButton;

	/**
	 * Initialisiert die UI.
	 */
	public BarzahlungWerkzeugUI()
	{
		Stage primaryStage = new Stage();
		primaryStage.setTitle(TITEL);
		_hauptPanel = new BorderPane();
		Scene scene = new Scene(_hauptPanel, 750, 650);

		SplitPane splitter = new SplitPane();
		splitter.setDividerPositions(0.32f);

		splitter.getItems().addAll(erstelleBarzahlungAuswahl());

		SplitPane splitterZwei = new SplitPane();
		splitterZwei.setDividerPositions(0.5f);

		splitterZwei.getItems().addAll(erstelleAbbruchPanel(), erstelleOkPanel());

		_hauptPanel.setTop(erstelleUeberschriftPanel());
		_hauptPanel.setCenter(splitter);
		_hauptPanel.setBottom(splitterZwei);

		primaryStage.setScene(scene);
		_stage = primaryStage;
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
		topPanel.setStyle("-fx-background-color: lightblue");

		Label label = new Label(TITEL);
		label.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
		label.setTextFill(Color.BLUE);

		topPanel.getChildren().add(label);

		return topPanel;
	}

	/**
	 * Schließt das Fenster.
	 */
	public void schliesseFenster()
	{
		System.exit(0);
	}

	/**
	 * Erzeugt das Panel mit dem Beenden-Button.
	 */
	private Pane erstelleAbbruchPanel()
	{
		FlowPane bottomPane = new FlowPane();
//		bottomPane.setOrientation(Orientation.HORIZONTAL);
		bottomPane.setAlignment(Pos.BASELINE_LEFT);
//		bottomPane.setHgap(600);
//		bottomPane.setVgap(10);

		bottomPane.setPadding(new Insets(5));
		_abbruchButton = new Button("Abbruch");
		bottomPane.getChildren().add(_abbruchButton);

		return bottomPane;
	}

	/**
	 * Erzeugt das Panel mit dem Beenden-Button.
	 */
	private Pane erstelleOkPanel()
	{
		FlowPane bottomPane = new FlowPane();
//		bottomPane.setOrientation(Orientation.HORIZONTAL);
		bottomPane.setAlignment(Pos.BASELINE_RIGHT);
		bottomPane.setPadding(new Insets(5));
		_okButton = new Button("OK");
		bottomPane.getChildren().add(_okButton);

		return bottomPane;
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

		TextField userTextField = new TextField();
		barzahlungAuswahl.add(userTextField, 1, 1);

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

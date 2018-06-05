package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import javafx.geometry.Insets;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Die UI des {@link BarzahlungWerkzeug}.
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
	private GridPane _barzahlungAuswahl;

	private Button _abbruchButton;
	private Button _okButton;

	private Label _preisLabel;
	private Label _preisAnzeige;
	private Label _rueckgeldLabel;
	private Label _rueckgeldAnzeige;
	private TextField _bargeldTextField;

	private Label _bargeldLabel;

	/**
	 * Initialisiert die UI.
	 */
	public BarzahlungWerkzeugUI()
	{
		_stage = new Stage();
		_stage.setTitle(TITEL);
		_hauptPanel = erstellePanel();
		_stage.initModality(Modality.APPLICATION_MODAL); //hier auskommentieren, falls dieses Fenster nicht immer im Vordergrund sein soll
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

		hauptPane.setTop(erstelleUeberschriftPanel());
		hauptPane.setCenter(splitter);
		hauptPane.setBottom(bottomPanel);

		_stage.setScene(scene);

		return hauptPane;
	}

	/**
	 * Erzeugt das Panel mit der Überschrift fuer das Programm.
	 */
	private Pane erstelleUeberschriftPanel()
	{
		StackPane topPanel = new StackPane(); // Kinder per Default zentriert
		topPanel.setStyle("-fx-background-color: darkred");

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

		okPane.getChildren().add(_okButton);
		okPane.setAlignment(Pos.CENTER_LEFT);
		okPane.setPadding(new Insets(10));

		return okPane;
	}

	private Pane erstelleBarzahlungAuswahl()
	{
		_barzahlungAuswahl = new GridPane();
		_barzahlungAuswahl.setAlignment(Pos.CENTER);
		_barzahlungAuswahl.setHgap(20);
		_barzahlungAuswahl.setVgap(20);
		_barzahlungAuswahl.setPadding(new Insets(35, 35, 35, 35));


		Text scenetitle = new Text("Bargeld Kassensystem");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		_barzahlungAuswahl.add(scenetitle, 0, 0, 2, 1);

		_preisLabel = new Label("Preis: ");
		_preisLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		_barzahlungAuswahl.add(_preisLabel, 0, 1);
		
		_preisAnzeige = new Label();
		_preisAnzeige.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		_barzahlungAuswahl.add(_preisAnzeige, 1, 1);

		// Text ticketPreisTextField = new Text("Preis: ");
		// barzahlungAuswahl.add(ticketPreisTextField, 1, 1);

		_bargeldLabel = new Label("Bargeld:");
		_bargeldLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		_barzahlungAuswahl.add(_bargeldLabel, 0, 2);

		_bargeldTextField = new TextField();
		_bargeldTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		_barzahlungAuswahl.add(_bargeldTextField, 1, 2);

		// Text rueckgeldTextField = new Text("Rückgeld: ");
		// barzahlungAuswahl.add(rueckgeldTextField, 1, 3);

		_rueckgeldLabel = new Label("Rueckgeld: ");
		_rueckgeldLabel.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 30));
		_rueckgeldLabel.setTextFill(Color.DARKRED);
		_barzahlungAuswahl.add(_rueckgeldLabel, 0, 3);
		
		_rueckgeldAnzeige = new Label();
		_rueckgeldAnzeige.setFont(Font.font("TimesNewRoman", FontWeight.SEMI_BOLD, 30));
		_rueckgeldAnzeige.setTextFill(Color.DARKRED);
		_barzahlungAuswahl.add(_rueckgeldAnzeige, 1, 3);

		return _barzahlungAuswahl;

	}

	/**
	 * Liefert den Abbruch-Button.
	 */
	public Button getAbbruchButton()
	{
		return _abbruchButton;
	}

	/**
	 * Liefert den Ok-Button.
	 */
	public Button getOkButton()
	{
		return _okButton;
	}

	/**
	 * Liefert die GridPane für die BarzahungsAuswahl.
	 */
	public GridPane getBarzahlungsAuswahl()
	{
		return _barzahlungAuswahl;
	}

	/**
	 * Liefert das Preis-Label.
	 */
	public Label getPreisLabel()
	{
		return _preisLabel;
	}

	/**
	 * Liefert das Rueckgeld-Label.
	 */
	public Label getRueckgeldLabel()
	{
		return _rueckgeldLabel;
	}

	/**
	 * Liefert das Rueckgeld-Label.
	 */
	public Label getBargeldLabel()
	{
		return _bargeldLabel;
	}

	/**
	 * Liefert den UI-Container, in dem die Widgets angeordnet sind.
	 */
	public Pane getUIPane()
	{
		return _hauptPanel;
	}

	/**
	 * Liefert die Stage der UI.
	 */
	public Stage getStage()
	{
		return _stage;
	}

	/**
	 * Liefert das Fenster.
	 */
	public void zeigeFenster()
	{
		_stage.show();
	}

	/**
	 * Liefert das BargeldTextFeld.
	 */
	public TextField getBargeldTextField()
	{
		return _bargeldTextField;
	}
	
	public Label getPreisanzeige()
	{
		return _preisAnzeige;
	}

	public Label getRueckgeldanzeige()
	{
		return _rueckgeldAnzeige;
	}
}

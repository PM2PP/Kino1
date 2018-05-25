package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
	import javafx.scene.layout.BorderPane;
	import javafx.scene.layout.Pane;
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
	    //private ListView<VorstellungsFormatierer> _vorstellungAuswahlList;
	    private Stage _stage;

	    /**
	     * Initialisiert die UI.
	     */
	    public BarzahlungWerkzeugUI()
	    {
	    	Stage primaryStage = new Stage();
	        primaryStage.setTitle(TITEL);
	        _hauptPanel = new BorderPane();
	        //_hauptPanel.setTop(new Label("Barzahlung:"));
	        Scene scene = new Scene(_hauptPanel,750,650);
	        
	         Button center = new Button("summePlaetze:"); 
	        _hauptPanel.setCenter(center); 
	        _hauptPanel.setRight(new Label("Bargeld:"));
	        _hauptPanel.setLeft(new Label("Rueckgeld:"));
	        _hauptPanel.setBottom(new Label("Ok:"));
	        //_hauptPanel.setCenter(new Label("Abbruch:"));
	        
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
}

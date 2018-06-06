package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import java.util.Observable;
import java.util.Observer;

import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.SubwerkzeugObserver;
import de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

public class BarzahlungWerkzeug //extends ObservableSubwerkzeug
{

	// UI dieses Werkzeugs
	private BarzahlungWerkzeugUI _ui;
	private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;

//	private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
//
//	private Vorstellung _vorstellung;
//	private int _preis;
//	private int _rueckgeld;
//	private int _bargeld;
//	private VorstellungsAuswaehlWerkzeug _vorstellungsAuswaehlWerkzeug;

	public BarzahlungWerkzeug()
	{

		_ui = new BarzahlungWerkzeugUI();				
		_ui.zeigeFenster();
//		_platzVerkaufsWerkzeug = new PlatzVerkaufsWerkzeug();
//		registriereBeobachter(_platzVerkaufsWerkzeug);
//		bargeldAnzeige();
//		registriereUIAktionen();
	}

	private void registriereUIAktionen()
	{
//		preisAnzeige();
//		rueckgeldAnzeige();
//		bargeldAnzeige();
//		_ui.getPreisanzeige().setOnAction(new EventHandler<ActionEvent>
//		@Override
//		public void handle(ActionEvent ae)
//		{
//			_ui.getPreisanzeige().setText(PlatzVerkaufsWerkzeug._preisFuerAuswahl + " Eurocent");
//		}
	}

	/**
	 * Der anzuzeigenden Gesamtpreis
	 */
//	private void preisAnzeige()
//	{
		// TODO
//		int preis = 10;
		// int preis =
		// _vorstellung.getPreisFuerPlaetze(_platzVerkauf.getUI().getPlatzplan().getAusgewaehltePlaetze());
//		int preis = _vorstellungsAuswaehlWerkzeug.getAusgewaehlteVorstellung().getPreis();
//		Text preisTextField = new Text(preis + " Eurocent");
//		preisTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
//		_ui.getBarzahlungsAuswahl().add(preisTextField, 1, 1);
//		_preis = preis;

//	}
//
//	private void rueckgeldAnzeige()
//	{
//		int rueckgeld = _preis - _bargeld;
//		Text rueckgeldTextField = new Text(rueckgeld + " Eurocent");
//		rueckgeldTextField.setFont(Font.font("TimesNewRoman", FontWeight.NORMAL, 35));
//		rueckgeldTextField.setFill(Color.DARKRED);
//		_ui.getBarzahlungsAuswahl().add(rueckgeldTextField, 1, 3);
//		_rueckgeld = rueckgeld;
//	}
//
//	private void bargeldAnzeige()
//	{
//		    _ui.getBargeldTextField().textProperty().addListener((observable, oldValue, newValue) -> {
//		    	
//			if(textOfTextField() != null && !textOfTextField().matches("[0-9]*"))
//			{
//				Alert alert = new Alert(AlertType.INFORMATION, "Bitte nur Zahlen eingeben!");
//				alert.showAndWait();
//			}
//			else if(textOfTextField() != null && textOfTextField().matches("[0-9]+"))
//			{
//				informiereUeberAenderung();
//			}
//		    });
//	}
		    
    public String textOfTextField()
    {
    	return _ui.getBargeldTextField().getText().toString();
    }


	/**
	 * Gibt das Panel dieses Subwerkzeugs zurück. Das Panel sollte von einem
	 * Kontextwerkzeug eingebettet werden.
	 * 
	 * @ensure result != null
	 */
	public Pane getUIPane()
	{
		return _ui.getUIPane();
	}

	public BarzahlungWerkzeugUI getUI()
	{
		return _ui;
	}	
}

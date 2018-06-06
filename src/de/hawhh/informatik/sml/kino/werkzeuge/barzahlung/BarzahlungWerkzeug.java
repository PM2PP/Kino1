package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import de.hawhh.informatik.sml.kino.fachwerte.Platz;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.SubwerkzeugObserver;
import de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BarzahlungWerkzeug extends Observable
{

	// UI dieses Werkzeugs
	private BarzahlungWerkzeugUI _ui;
	// private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
	private int _preis;
	Boolean _verkauft;

	// private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
	//
	// private Vorstellung _vorstellung;
	// private int _preis;
	// private int _rueckgeld;
	// private int _bargeld;
	// private VorstellungsAuswaehlWerkzeug _vorstellungsAuswaehlWerkzeug;

	public BarzahlungWerkzeug(int preis, Observer observer)
	{

		_ui = new BarzahlungWerkzeugUI();
		// _ui.zeigeFenster();
		addObserver(observer);
		_preis = preis;
		_verkauft = new Boolean(false);
		registriereUIAktionen();
		_ui.zeigeFenster();
		// registriereUIAktionen();
	}

	private void registriereUIAktionen()
	{
		bargeldAnzeige();
		preisAnzeige();
		okButton();
		abbruchButton();
	}

	/**
	 * Der anzuzeigenden Gesamtpreis
	 */
	private void preisAnzeige()
	{
		_ui.getPreisanzeige().setText(_preis + " Eurocent");
		// TODO
		// int preis = 10;
		// int preis =
		// _vorstellung.getPreisFuerPlaetze(_platzVerkauf.getUI().getPlatzplan().getAusgewaehltePlaetze());
		// int preis =
		// _vorstellungsAuswaehlWerkzeug.getAusgewaehlteVorstellung().getPreis();
		// Text preisTextField = new Text(preis + " Eurocent");
		// preisTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		// _ui.getBarzahlungsAuswahl().add(preisTextField, 1, 1);
		// _preis = preis;

	}

	//
	private void rueckgeldAnzeige()
	{
		int rueckgeld;
		if (textOfTextField().isEmpty())
		{
			_ui.getRueckgeldanzeige().setText("");
		}
		else
		{
			rueckgeld = Integer.parseInt(textOfTextField()) - _preis;
			if (rueckgeld >= 0)
			{
				_ui.getRueckgeldanzeige().setText(rueckgeld + " Eurocent");
			}
			else
			{
				_ui.getRueckgeldanzeige().setText("");
			}
		}
		// int rueckgeld = _preis - _bargeld;
		// Text rueckgeldTextField = new Text(rueckgeld + " Eurocent");
		// rueckgeldTextField.setFont(Font.font("TimesNewRoman", FontWeight.NORMAL,
		// 35));
		// rueckgeldTextField.setFill(Color.DARKRED);
		// _ui.getBarzahlungsAuswahl().add(rueckgeldTextField, 1, 3);
		// _rueckgeld = rueckgeld;
	}

	//
	private void bargeldAnzeige()
	{
		_ui.getBargeldTextField().textProperty().addListener((observable, oldValue, newValue) ->
		{

			// if(!textOfTextField().matches("[0-9]+"))
			// {
			// Alert alert = new Alert(AlertType.INFORMATION, "Bitte nur Zahlen eingeben!");
			// alert.showAndWait();
			// }

			if(textOfTextField().isEmpty())
			{
				_ui.getOkButton().setDisable(true);
			}
			else if (textOfTextField().length() > 6 || !textOfTextField().matches("\\d*"))
			{
				_ui.getOkButton().setDisable(true);
				_ui.getInfo().setFill(Color.FIREBRICK);
				_ui.getInfo().setText("Bitte höchstens 6 Ziffern eingeben!");
				_ui.getRueckgeldanzeige().setText("");
			}
			else
			{
				_ui.getOkButton().setDisable(false);
				_ui.getInfo().setText("");
				if (textOfTextField().matches("\\d+"))
					;
				{
					rueckgeldAnzeige();
				}
			}
		});
	}

	private void abbruchButton()
	{
		_ui.getAbbruchButton().setOnAction(e -> // Lambda
		{
			_verkauft = false;
			setChanged();
			notifyObservers(_verkauft);
			_ui.getStage().close();
		});
	}

	private void okButton()
	{
		_ui.getOkButton().setDisable(true);
		_ui.getOkButton().setOnAction(e -> // Lambda
		{
			// if(textOfTextField().length() > 6 || !textOfTextField().matches("\\d+"))
			// {
			// _ui.getOkButton().setDisable(true);
			//
			// }
			int gegeben = Integer.parseInt(_ui.getBargeldTextField().getText());
			if (_preis <= gegeben)
			{
				_verkauft = true;
				setChanged();
				notifyObservers(_verkauft);
				_ui.getStage().close();
			}
		});
	}

	public String textOfTextField()
	{
		return _ui.getBargeldTextField().getText().toString();
	}

	public boolean verkauft()
	{
		return _verkauft;
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

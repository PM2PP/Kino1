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
	private int _preis;
	Boolean _verkauft;


	public BarzahlungWerkzeug(int preis, Observer observer)
	{

		_ui = new BarzahlungWerkzeugUI();
		addObserver(observer);
		_preis = preis;
		_verkauft = new Boolean(false);
		registriereUIAktionen();
		_ui.zeigeFenster();
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
	}

	//
	private void bargeldAnzeige()
	{
		_ui.getBargeldTextField().textProperty().addListener((observable, oldValue, newValue) ->
		{
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

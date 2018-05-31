package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.hawhh.informatik.sml.kino.fachwerte.Platz;
import de.hawhh.informatik.sml.kino.materialien.Kino;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import de.hawhh.informatik.sml.kino.werkzeuge.kasse.KassenWerkzeugUI;
import de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.vorstellungsauswaehler.VorstellungsAuswaehlWerkzeug;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class BarzahlungWerkzeug
{

	// UI dieses Werkzeugs
	private BarzahlungWerkzeugUI _ui;

	private PlatzVerkaufsWerkzeug _platzVerkauf;

	private Vorstellung _vorstellung;
	private int _preis;
	private int _rueckgeld;
	private int _bargeld;

	public BarzahlungWerkzeug()
	{

		_ui = new BarzahlungWerkzeugUI();
		_ui.zeigeFenster();
		registriereUIAktionen();
	}

	private void registriereUIAktionen()
	{
		preisAnzeige();
		rueckgeldAnzeige();
		bargeldAnzeige();

	}

	/**
	 * Der anzuzeigenden Gesamtpreis
	 */
	private void preisAnzeige()
	{
		// TODO
		int preis = 10;
		// int preis =
		// _vorstellung.getPreisFuerPlaetze(_platzVerkauf.getUI().getPlatzplan().getAusgewaehltePlaetze());
		Text preisTextField = new Text(preis + " Eurocent");
		preisTextField.setFont(Font.font("Tahoma", FontWeight.NORMAL, 25));
		_ui.getBarzahlungsAuswahl().add(preisTextField, 1, 1);
		_preis = preis;
	}

	private void rueckgeldAnzeige()
	{
		int rueckgeld = _preis - _bargeld;
		Text rueckgeldTextField = new Text(rueckgeld + " Eurocent");
		rueckgeldTextField.setFont(Font.font("TimesNewRoman", FontWeight.NORMAL, 35));
		rueckgeldTextField.setFill(Color.DARKRED);
		_ui.getBarzahlungsAuswahl().add(rueckgeldTextField, 1, 3);
		_rueckgeld = rueckgeld;
	}

	private void bargeldAnzeige()
	{
		// TODO
		int bargeld = 20;
		// int bargeld = Integer.parseInt(_ui.getBargeldTextField().toString());
		_bargeld = bargeld;
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

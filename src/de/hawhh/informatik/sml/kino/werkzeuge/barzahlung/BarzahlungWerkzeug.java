package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import javafx.scene.layout.Pane;

public class BarzahlungWerkzeug
{

	// UI dieses Werkzeugs
	private BarzahlungWerkzeugUI _ui;

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
//		registriereUIAktionen();
	}

//	private void registriereUIAktionen()
//	{
//		preisAnzeige();
//		rueckgeldAnzeige();
//		bargeldAnzeige();
//
//	}

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
		//FERTIG
//		int bargeld = 20;
//		int bargeld = Integer.parseInt(_ui.getBargeldTextField().toString());
//		_bargeld = bargeld;
//	}

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

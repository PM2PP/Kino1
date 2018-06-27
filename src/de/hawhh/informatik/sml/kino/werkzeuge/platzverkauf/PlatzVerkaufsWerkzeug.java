package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import de.hawhh.informatik.sml.kino.fachwerte.Geldbetrag;
import de.hawhh.informatik.sml.kino.fachwerte.Platz;
import de.hawhh.informatik.sml.kino.materialien.Kinosaal;
import de.hawhh.informatik.sml.kino.materialien.Vorstellung;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.SubwerkzeugObserver;
import de.hawhh.informatik.sml.kino.werkzeuge.barzahlung.BarzahlungWerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.barzahlung.BarzahlungWerkzeugUI;

/**
 * Mit diesem Werkzeug können Plätze verkauft und storniert werden. Es arbeitet
 * auf einer Vorstellung als Material. Mit ihm kann angezeigt werden, welche
 * Plätze schon verkauft und welche noch frei sind.
 * 
 * Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es kann nicht beobachtet
 * werden.
 * 
 * @author SE2-Team (Uni HH), PM2-Team
 * @version SoSe 2018
 */
public class PlatzVerkaufsWerkzeug implements Observer
{
	private Geldbetrag _preisFuerAuswahl;

	// Die aktuelle Vorstellung, deren Plätze angezeigt werden. Kann null sein.
	private Vorstellung _vorstellung;

	private BarzahlungWerkzeug _barzahlungWerkzeug;

	private PlatzVerkaufsWerkzeugUI _ui;
	
//	private Map<Platz, Boolean> _auswahl; Alternative mit Map
//Commit

	/**
	 * Initialisiert das PlatzVerkaufsWerkzeug.
	 */
	public PlatzVerkaufsWerkzeug()
	{
		_ui = new PlatzVerkaufsWerkzeugUI();
		registriereUIAktionen();
		// Am Anfang wird keine Vorstellung angezeigt:
		setVorstellung(null);
//		_auswahl = new Map<Platz, Boolean>; Alternative mit Map
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

	/**
	 * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
	 */
	private void registriereUIAktionen()
	{
		_ui.getVerkaufenButton().setOnAction(actionEvent -> // Lambda
		{
			_barzahlungWerkzeug = new BarzahlungWerkzeug(_preisFuerAuswahl, this);

			if (_barzahlungWerkzeug.verkauft())
			{
				deselektiereAllePlaetze(); //damit die Plätze beim Verkauf rot werden und nicht gelb bleiben
				verkaufePlaetze(_vorstellung);
			}
			else
			{
				deselektiereAllePlaetze(); //damit die Plätze beim Abbruch wieder grün werden und nicht gelb bleiben
				aktualisierePlatzplan();
			}
		});

		_ui.getStornierenButton().setOnAction(ae -> // Lambda
		{
			deselektiereAllePlaetze(); //damit die Plätze beim Stornieren wieder grün werden und nicht gelb bleiben
			stornierePlaetze(_vorstellung);
		});

		_ui.getPlatzplan().addPlatzSelectionListener(event -> // Lambda
		{
			//läuft jetzt !! 
			if (_vorstellung != null && ausgewaehltePlaetze() != null) //2. Bedingung in if-Klausel hat gefehlt
			{
				for(Platz platz : event.getAusgewaehltePlaetze())
				{
					_vorstellung.auswahlPlatz(platz);	
//					_ui.getPlatzplan().markierePlatzAlsAusgewaehlt(platz); macht der Listener in Platzplan schon 
//					_auswahl.put(platz, true); Alternative mit Map
				}
				
				for (Platz platz : _vorstellung.getKinosaal().getPlaetze()) //falls verkaufter Platz deselektiert wird
				{
					if (_vorstellung.istPlatzVerkauft(platz))
					{
						_ui.getPlatzplan().markierePlatzAlsVerkauft(platz);
					}
				}
				
				reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());
//				if (istAuswaehlenMoeglich(event.getAusgewaehltePlaetze())) ...      alte Lösung: nicht möglich so zu lösen, 
//				{                                                                   da event.getAusgewaehltePlaetze jedes Mal alle Plaetze
//					auswahlPlaetze(_vorstellung);                                   die ausgewählt sind umfasst... das heißt
//					reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());    bei der Auswahl des ersten Platzes wird der
//				}                                                                   erste Platz ausgewählt, bei der Auswahl 
//				else if (istDeselektierenMoeglich(event.getAusgewaehltePlaetze()))  eines zweiten Platzes wird aber nun für die die beiden Plätze aus
//				{                                                                   event.getAusgewaehltePlaetze der Wahrheitswert "falsch" für  
//					deselektierePlaetze(_vorstellung);                              istAuswaehlenMoeglich zurückgegeben, da der erste bereits ausgewählt wurde...
//					reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());    
//				}
			}
//			reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());
		});
	}

	// Test
	/**
	 * Reagiert darauf, dass sich die Menge der ausgewählten Plätze geändert hat.
	 * 
	 * @param plaetze
	 *            die jetzt ausgewählten Plätze.
	 */
	private void reagiereAufNeuePlatzAuswahl(Set<Platz> plaetze)
	{
		_ui.getVerkaufenButton().setDisable(!istVerkaufenMoeglich(plaetze));
		_ui.getStornierenButton().setDisable(!istStornierenMoeglich(plaetze));
		aktualisierePreisanzeige(plaetze);
	}
	
	public Set<Platz> ausgewaehltePlaetze()
	{ 
		return _ui.getPlatzplan().getAusgewaehltePlaetze(); 
	}

	/**
	 * Aktualisiert den anzuzeigenden Gesamtpreis
	 */
	private void aktualisierePreisanzeige(Set<Platz> plaetze)
	{

		if (istVerkaufenMoeglich(plaetze))
		{
			Geldbetrag preis = _vorstellung.getPreisFuerPlaetze(plaetze);
			_ui.getPreisLabel().setText("Gesamtpreis: " + preis.toString() + " €");
			_preisFuerAuswahl = preis;
		}
		else
		{
			_ui.getPreisLabel().setText("Gesamtpreis:");
		}
	}

	/**
	 * Prüft, ob die angegebenen Plätze alle storniert werden können.
	 */
	private boolean istStornierenMoeglich(Set<Platz> plaetze)
	{
		return !plaetze.isEmpty() && _vorstellung.sindStornierbar(plaetze);
	}

	/**
	 * Prüft, ob die angegebenen Plätze alle verkauft werden können.
	 */
	private boolean istVerkaufenMoeglich(Set<Platz> plaetze)
	{
		return !plaetze.isEmpty() && _vorstellung.sindVerkaufbar(plaetze);
	}

	/**
	 * Prüft, ob die angegebenen Plätze alle ausgewaehlt werden können.
	 */
	private boolean istAuswaehlenMoeglich(Set<Platz> plaetze)
	{
		return !plaetze.isEmpty() && _vorstellung.sindAuswaehlbar(plaetze);
	}

	/**
	 * Prüft, ob die angegebenen Plätze alle deselektiert werden können.
	 */
	private boolean istDeselektierenMoeglich(Set<Platz> plaetze)
	{
		return !plaetze.isEmpty() && _vorstellung.sindDeselektierbar(plaetze);
	}

	/**
	 * Setzt die Vorstellung. Sie ist das Material dieses Werkzeugs. Wenn die
	 * Vorstellung gesetzt wird, muss die Anzeige aktualisiert werden. Die
	 * Vorstellung darf auch null sein.
	 */
	public void setVorstellung(Vorstellung vorstellung)
	{
		
		if (_vorstellung != null && ausgewaehltePlaetze() != null)
		{
		for(Platz platz : _vorstellung.getKinosaal().getPlaetze())
		{
			if(!ausgewaehltePlaetze().contains(platz) && _vorstellung.istPlatzAusgewaehlt(platz))
			{
				_vorstellung.deselektionPlatz(platz); //um vor einem Vorstellungswechsel alle deselektierten Plätze zu deselektieren
			}
//			else if(ausgewaehltePlaetze().contains(platz) && !_vorstellung.istPlatzAusgewaehlt(platz)) ... nicht nötig, macht der Listener schon
//			{
//				_vorstellung.auswahlPlatz(platz);					
//			}
		}
		}
		_vorstellung = vorstellung;
		aktualisierePlatzplan();
		reagiereAufNeuePlatzAuswahl(ausgewaehltePlaetze()); //wichtig um Preisanzeige etc. zu aktualisieren
	}

	/**
	 * Aktualisiert den Platzplan basierend auf der ausgwählten Vorstellung.
	 */
	private void aktualisierePlatzplan()
	{
		if (_vorstellung != null)
		{
			Kinosaal saal = _vorstellung.getKinosaal();
			_ui.getPlatzplan().setAnzahlPlaetze(saal.getAnzahlReihen(), saal.getAnzahlSitzeProReihe());

			for (Platz platz : saal.getPlaetze())
			{
				if (_vorstellung.istPlatzVerkauft(platz) && !_vorstellung.istPlatzAusgewaehlt(platz)) // 2. &&-Bedingung wichtig, damit ausgewählte verkaufte
				{                                                                                     // Plätze gelb statt rot angezeigt werden
					_ui.getPlatzplan().markierePlatzAlsVerkauft(platz);                               
				}
				else if (_vorstellung.istPlatzAusgewaehlt(platz))
				{
					_ui.getPlatzplan().markierePlatzAlsAusgewaehlt(platz);
				}
			}
		}
		else
		{
			_ui.getPlatzplan().setAnzahlPlaetze(0, 0);
		}
	}

	/**
	 * Verkauft die ausgewählten Plaetze.
	 */
	private void verkaufePlaetze(Vorstellung vorstellung)
	{
		Set<Platz> plaetze = _ui.getPlatzplan().getAusgewaehltePlaetze();
		vorstellung.verkaufePlaetze(plaetze);
		aktualisierePlatzplan();
	}

	/**
	 * Storniert die ausgewählten Plaetze.
	 */
	private void stornierePlaetze(Vorstellung vorstellung)
	{
		Set<Platz> plaetze = _ui.getPlatzplan().getAusgewaehltePlaetze();
		vorstellung.stornierePlaetze(plaetze);
		aktualisierePlatzplan();
	}

	/**
	 * Wählt die ausgewählten Plaetze.
	 */
	private void auswahlPlaetze(Vorstellung vorstellung)
	{
		Set<Platz> plaetze = _ui.getPlatzplan().getAusgewaehltePlaetze();
		vorstellung.auswaehlenPlaetze(plaetze);
		aktualisierePlatzplan();
	}

	/**
	 * Storniert die ausgewählten Plaetze.
	 */
	private void deselektierePlaetze(Vorstellung vorstellung)
	{
		Set<Platz> plaetze = _ui.getPlatzplan().getAusgewaehltePlaetze();
		vorstellung.deselektierePlaetze(plaetze);
		aktualisierePlatzplan();
	}

	public PlatzVerkaufsWerkzeugUI getUI()
	{
		return _ui;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		// if((Boolean) arg1)
		// {
		// verkaufePlaetze(_vorstellung);
		// }
		// else
		// {
		// aktualisierePlatzplan();
		// }
	}
	
	private void deselektiereAllePlaetze()
	{
		for(Platz platz : _vorstellung.getKinosaal().getPlaetze())
		{
			if(_vorstellung.istPlatzAusgewaehlt(platz))
			{
				_vorstellung.deselektionPlatz(platz);
			}
		}
	}
}

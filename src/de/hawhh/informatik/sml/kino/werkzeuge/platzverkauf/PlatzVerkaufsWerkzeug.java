package de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf;

import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

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
	private int _preisFuerAuswahl;

	// Die aktuelle Vorstellung, deren Plätze angezeigt werden. Kann null sein.
	private Vorstellung _vorstellung;

	private BarzahlungWerkzeug _barzahlungWerkzeug;

	private PlatzVerkaufsWerkzeugUI _ui;

	/**
	 * Initialisiert das PlatzVerkaufsWerkzeug.
	 */
	public PlatzVerkaufsWerkzeug()
	{
		_ui = new PlatzVerkaufsWerkzeugUI();
		registriereUIAktionen();
		// Am Anfang wird keine Vorstellung angezeigt:
		setVorstellung(null);
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
		_ui.getVerkaufenButton().setOnAction(actionEvent -> //Lambda
		{
				_barzahlungWerkzeug = new BarzahlungWerkzeug(_preisFuerAuswahl, this);
				
				if(_barzahlungWerkzeug.verkauft())
				{
					verkaufePlaetze(_vorstellung);
				}
				else 
				{
					aktualisierePlatzplan();
				}	
		});

		_ui.getStornierenButton().setOnAction(ae -> //Lambda
		{
				stornierePlaetze(_vorstellung);
		});

		_ui.getPlatzplan().addPlatzSelectionListener(event -> //Lambda
		{
				reagiereAufNeuePlatzAuswahl(event.getAusgewaehltePlaetze());
		});
	}
//Test
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

	/**
	 * Aktualisiert den anzuzeigenden Gesamtpreis
	 */
	private void aktualisierePreisanzeige(Set<Platz> plaetze)
	{

		if (istVerkaufenMoeglich(plaetze))
		{
			int preis = _vorstellung.getPreisFuerPlaetze(plaetze);
			_ui.getPreisLabel().setText("Gesamtpreis: " + preis + " Eurocent");
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
	 * Setzt die Vorstellung. Sie ist das Material dieses Werkzeugs. Wenn die
	 * Vorstellung gesetzt wird, muss die Anzeige aktualisiert werden. Die
	 * Vorstellung darf auch null sein.
	 */
	public void setVorstellung(Vorstellung vorstellung)
	{
		_vorstellung = vorstellung;
		aktualisierePlatzplan();
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
				if (_vorstellung.istPlatzVerkauft(platz))
				{
					_ui.getPlatzplan().markierePlatzAlsVerkauft(platz);
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
	
	public PlatzVerkaufsWerkzeugUI getUI()
	{
		return _ui;
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
//		if((Boolean) arg1)
//		{
//			verkaufePlaetze(_vorstellung);			
//		}
//		else
//		{
//			aktualisierePlatzplan();
//		}		
	}
}

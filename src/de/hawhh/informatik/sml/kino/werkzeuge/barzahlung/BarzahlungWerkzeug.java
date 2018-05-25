package de.hawhh.informatik.sml.kino.werkzeuge.barzahlung;

import de.hawhh.informatik.sml.kino.werkzeuge.kasse.KassenWerkzeugUI;
import de.hawhh.informatik.sml.kino.werkzeuge.platzverkauf.PlatzVerkaufsWerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.vorstellungsauswaehler.VorstellungsAuswaehlWerkzeug;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BarzahlungWerkzeug
{
	private VorstellungsAuswaehlWerkzeug _vorstellungAuswaehlWerkzeug;
	private PlatzVerkaufsWerkzeug _platzVerkaufsWerkzeug;
	// UI dieses Werkzeugs
	private BarzahlungWerkzeugUI _ui;

	public BarzahlungWerkzeug()
	{
		
		_ui = new BarzahlungWerkzeugUI();
		
		_ui.zeigeFenster();
		

	}
	
//	private void registriereUIAktionen()
//    {
//		_platzVerkaufsWerkzeug.getUIPane().setOnAction(new EventHandler<ActionEvent>()
//                {
//                    @Override
//                    public void handle(ActionEvent ae)
//                    {
//                        
//                    }
//                });
//    }


}

package de.hawhh.informatik.sml.kino.fachwerte;

/**
 * Ein Geldbetrag in Euro, gespeichert als ganze Euro- und ganze Cent-Beträge.
 * 
 * @author JP.Ritter & P.A.Bremer
 * @version SoSe 2018
 */
public final class Geldbetrag
{

	private final int _euroAnteil;
	private final int _centAnteil;
	
	
	private Geldbetrag(int eurocent)
	{
		_euroAnteil = eurocent / 100;
		_centAnteil = eurocent % 100;
	}
	
	/**
	 * Wählt einen Geldbetrag aus.
	 * 
	 * @param eurocent
	 *            Der Betrag in ganzen Euro-Cent
	 * 
	 * @require eurocent >= 0;
	 */
	public static Geldbetrag get(int eurocent)
	{
		assert eurocent >= 0 : "Vorbedingung verletzt: eurocent >= 0";
		return new Geldbetrag(eurocent);
	}

	/**
	 * Wählt einen Geldbetrag aus.
	 * 
	 * @param eurocent
	 *            Der Geldbetrag als String bsp. "10,00"
	 * 
	 * @require eurocent.matches"(\\d+),?(\\d\\d)";
	 */
	public static Geldbetrag getString(String eurocent)
	{
		assert eurocent.matches("(\\d+),?(\\d\\d)") : "Vorbedingung verletzt: eurocent.matches(\"(\\d+),?(\\d\\d)\")";

		String neu = eurocent; 
		if ((char) eurocent.charAt(eurocent.length() - 3) == ',')
		{
			neu = eurocent.replace(",", "");
		}
		return new Geldbetrag(Integer.parseInt(neu));
	}

	/**
	 * Addiert zwei Geldbeträge.
	 * 
	 * @param g1
	 *            der erste Geldbetrag
	 * @param g2
	 *            der zweite Geldebetrag
	 *            
	 * @return einen Geldbetrag aus der summe der beiden Geldbeträge
	 */
	public static Geldbetrag addieren(Geldbetrag g1, Geldbetrag g2)
	{
		int gb1 = geldbetragInt(g1.toString());
		int gb2 = geldbetragInt(g2.toString());

		int summe = gb1 + gb2;

		return get(summe);
	}

	/**
	 * Subtrahiert zwei Geldbeträge.
	 * 
	 * @param g1
	 *            der erste Geldbetrag
	 * @param g2
	 *            der zweite Geldebetrag
	 *            
	 * @return einen Geldbetrag aus der summe der beiden Geldbeträge
	 */
	public static Geldbetrag subtrahieren(Geldbetrag g1, Geldbetrag g2)
	{
		//assert Integer.parseInt(g1.toString()) >= Integer.parseInt(g2.toString()): "Vorbedingung verletzt: g1 >= g2";
		int gb1 = geldbetragInt(g1.toString());
		int gb2 = geldbetragInt(g2.toString());

		int summe = gb1 - gb2;

		return get(summe);
	}

	/**
	 * Multipliziert einen Geldbetrag mit einer belibigen Zahl.
	 * 
	 * @param g1
	 *            der Geldbetrag
	 * @param zahl
	 *            der Multiplikator
	 *            
	 * @return den Multiplizierten Geldbetrag
	 */
	public static Geldbetrag multiplizieren(Geldbetrag g1, int zahl)
	{
		int gb1 = geldbetragInt(g1.toString());

		int summe = gb1 * zahl;

		return get(summe);
	}

	/**
	 * Geldbetrag als String in passenden Int für berechnungen 
	 * 
	 * @param eurocent
	 *            Der Geldbetrag als String bsp. "10,00"
	 * 
	 * @require eurocent.matches"(\\d+),?(\\d\\d)";
	 */
	public static int geldbetragInt(String eurocent)
	{
		
		String neu = eurocent.replace(",",""); 
		
		return Integer.parseInt(neu);
	}

	/**
	 * Gibt den Eurobetrag ohne Cent zurück.
	 * 
	 * @return Den Eurobetrag ohne Cent.
	 */
	public int getEuroAnteil()
	{
		return _euroAnteil;
	}

	/**
	 * Gibt den Centbetrag ohne Eurobetrag zurück.
	 */
	public int getCentAnteil()
	{
		return _centAnteil;
	}

	/**
	 * Liefert einen formatierten String des Geldbetrags in der Form "10,23" zurück.
	 * 
	 * @return eine String-Repräsentation.
	 */
	public String getFormatiertenString()
	{
		return _euroAnteil + "," + getFormatiertenCentAnteil();
	}

	/**
	 * Liefert einen zweistelligen Centbetrag zurück.
	 * 
	 * @return eine String-Repräsentation des Cent-Anteils.
	 */
	private String getFormatiertenCentAnteil()
	{
		String result = "";
		if (_centAnteil < 10)
		{
			result += "0";
		}
		result += _centAnteil;
		return result;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime + _centAnteil;
		result = prime * result + _euroAnteil;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result = false;
		if (obj instanceof Geldbetrag)
		{
			Geldbetrag other = (Geldbetrag) obj;
			result = (_centAnteil == other._centAnteil) && (_euroAnteil == other._euroAnteil);
		}
		return result;
	}

	/**
	 * Gibt diesen Geldbetrag in der Form "10,21" zurück.
	 */
	@Override
	public String toString()
	{
		return getFormatiertenString();
	}
}

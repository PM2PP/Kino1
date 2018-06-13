package de.hawhh.informatik.sml.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author JP.Ritter & P.A.Bremer
 * @version SoSe 2018
 * 
 */
public class GeldbetragTest
{

    @Test
    public final void testGeldbetrag()
    {
        Geldbetrag betrag = Geldbetrag.get(100);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("1,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(0);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("0,00", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(99);
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(99, betrag.getCentAnteil());
        assertEquals("0,99", betrag.getFormatiertenString());

        betrag = Geldbetrag.get(101);
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(1, betrag.getCentAnteil());
        assertEquals("1,01", betrag.getFormatiertenString());
        
        betrag = Geldbetrag.get(2500);
        assertEquals(25, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("25,00", betrag.getFormatiertenString());
        
        betrag = Geldbetrag.get(12500);
        assertEquals(125, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("125,00", betrag.getFormatiertenString());
        
    }
    
    @Test
    public final void testGeldbetragString()
    { 
    	
        Geldbetrag betrag = Geldbetrag.getString("1,00");
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("1,00", betrag.getFormatiertenString());
        
        betrag = Geldbetrag.getString("0,99");
        assertEquals(0, betrag.getEuroAnteil());
        assertEquals(99, betrag.getCentAnteil());
        assertEquals("0,99", betrag.getFormatiertenString());
        
        betrag = Geldbetrag.getString("1,01");
        assertEquals(1, betrag.getEuroAnteil());
        assertEquals(1, betrag.getCentAnteil());
        assertEquals("1,01", betrag.getFormatiertenString());
          
        betrag = Geldbetrag.getString("25,00");
        assertEquals(25, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("25,00", betrag.getFormatiertenString());
        
        betrag = Geldbetrag.getString("125,00");
        assertEquals(125, betrag.getEuroAnteil());
        assertEquals(0, betrag.getCentAnteil());
        assertEquals("125,00", betrag.getFormatiertenString());
    }
    
    @Test 
    public final void testAddierenSubtrahierenGeldbetrag()
    { 
    	   Geldbetrag betrag1 = Geldbetrag.get(100);
    	   Geldbetrag betrag2 = Geldbetrag.get(100);
    	   Geldbetrag betrag3 = Geldbetrag.get(200); 
    	   
    	   assertEquals(Geldbetrag.addieren(betrag1,betrag2), Geldbetrag.get(200)); 
    	   assertEquals(Geldbetrag.addieren(betrag1,betrag3), Geldbetrag.get(300)); 
    	   
    	   assertEquals(Geldbetrag.subtrahieren(betrag1,betrag2), Geldbetrag.get(0)); 
    	   assertEquals(Geldbetrag.subtrahieren(betrag3,betrag1), Geldbetrag.get(100)); 
    }
    
    @Test 
    public final void testMultipliziereGeldbetrag()
    { 
    	   Geldbetrag betrag1 = Geldbetrag.get(100);
    	   Geldbetrag betrag2 = Geldbetrag.get(100);
    	   Geldbetrag betrag3 = Geldbetrag.get(200); 
    	   
    	   assertEquals(Geldbetrag.multiplizieren(betrag1,2), Geldbetrag.get(200)); 
    	   assertEquals(Geldbetrag.multiplizieren(betrag2,5), Geldbetrag.get(500)); 
    	   
    	   assertEquals(Geldbetrag.multiplizieren(betrag3,10), Geldbetrag.get(2000)); 
    	   assertEquals(Geldbetrag.multiplizieren(betrag1,100), Geldbetrag.get(10000)); 
    }

    @Test
    public final void testEqualsHashcode()
    {
        Geldbetrag betrag1 = Geldbetrag.get(100);
        Geldbetrag betrag2 = Geldbetrag.get(100);
        assertTrue(betrag1.equals(betrag2));
        assertTrue(betrag1.hashCode() == betrag2.hashCode());

        Geldbetrag betrag3 = Geldbetrag.get(101);
        assertFalse(betrag1.equals(betrag3));
        assertFalse(betrag1.hashCode() == betrag3.hashCode());

        Geldbetrag betrag4 = Geldbetrag.get(1000);
        assertFalse(betrag1.equals(betrag4));
        assertFalse(betrag1.hashCode() == betrag4.hashCode());
    }
}

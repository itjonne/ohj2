package bongari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.09.04 16:56:52 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class JasenTest {



  // Generated by ComTest BEGIN
  /** testGetEtunimi17 */
  @Test
  public void testGetEtunimi17() {    // Jasen: 17
    Jasen testi = new Jasen(); 
    Jasen tyhja = new Jasen(); 
    testi.setEtunimi("Testaaja"); 
    assertEquals("From: Jasen line: 21", "Testaaja", testi.getEtunimi()); 
    assertEquals("From: Jasen line: 22", "", tyhja.getEtunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetEtunimi32 */
  @Test
  public void testSetEtunimi32() {    // Jasen: 32
    Jasen testi = new Jasen(); 
    testi.setEtunimi("Veijo"); 
    assertEquals("From: Jasen line: 35", "Veijo", testi.getEtunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetSukunimi45 */
  @Test
  public void testSetSukunimi45() {    // Jasen: 45
    Jasen testi = new Jasen(); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 48", "Testaaja", testi.getSukunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetSukunimi58 */
  @Test
  public void testGetSukunimi58() {    // Jasen: 58
    Jasen testi = new Jasen(); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 61", "Testaaja", testi.getSukunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKokonimi71 */
  @Test
  public void testGetKokonimi71() {    // Jasen: 71
    Jasen testi = new Jasen(); 
    testi.setEtunimi("Testi"); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 75", "Testi Testaaja", testi.getKokonimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi86 */
  @Test
  public void testRekisteroi86() {    // Jasen: 86
    Jasen testi = new Jasen(); 
    testi.rekisteroi(); 
    Jasen testi2 = new Jasen(); 
    testi2.rekisteroi(); 
    assertEquals("From: Jasen line: 91", 1, testi.getJasenId()); 
    assertEquals("From: Jasen line: 92", 2, testi2.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetJasenId105 */
  @Test
  public void testSetJasenId105() {    // Jasen: 105
    Jasen jasen = new Jasen(); 
    jasen.setJasenId(3); 
    assertEquals("From: Jasen line: 108", 3, jasen.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetJasenId118 */
  @Test
  public void testGetJasenId118() {    // Jasen: 118
    Jasen testi = new Jasen(); 
    testi.setJasenId(1); 
    assertEquals("From: Jasen line: 121", 1, testi.getJasenId()); 
  } // Generated by ComTest END
}
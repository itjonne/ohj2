package bongari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.11.02 11:17:00 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class JasenTest {



  // Generated by ComTest BEGIN
  /** testGetEtunimi21 */
  @Test
  public void testGetEtunimi21() {    // Jasen: 21
    Jasen testi = new Jasen(); 
    Jasen tyhja = new Jasen(); 
    testi.setEtunimi("Testaaja"); 
    assertEquals("From: Jasen line: 25", "Testaaja", testi.getEtunimi()); 
    assertEquals("From: Jasen line: 26", "", tyhja.getEtunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetEtunimi36 */
  @Test
  public void testSetEtunimi36() {    // Jasen: 36
    Jasen testi = new Jasen(); 
    testi.setEtunimi("Veijo"); 
    assertEquals("From: Jasen line: 39", "Veijo", testi.getEtunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetSukunimi49 */
  @Test
  public void testSetSukunimi49() {    // Jasen: 49
    Jasen testi = new Jasen(); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 52", "Testaaja", testi.getSukunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetSukunimi62 */
  @Test
  public void testGetSukunimi62() {    // Jasen: 62
    Jasen testi = new Jasen(); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 65", "Testaaja", testi.getSukunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKokonimi75 */
  @Test
  public void testGetKokonimi75() {    // Jasen: 75
    Jasen testi = new Jasen(); 
    testi.setEtunimi("Testi"); 
    testi.setSukunimi("Testaaja"); 
    assertEquals("From: Jasen line: 79", "Testi Testaaja", testi.getKokonimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi90 */
  @Test
  public void testRekisteroi90() {    // Jasen: 90
    Jasen testi = new Jasen(); 
    testi.rekisteroi(); 
    Jasen testi2 = new Jasen(); 
    testi2.rekisteroi(); 
    assertEquals("From: Jasen line: 95", 1, testi.getJasenId()); 
    assertEquals("From: Jasen line: 96", 2, testi2.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testParse110 
   * @throws ExceptionHandler when error
   */
  @Test
  public void testParse110() throws ExceptionHandler {    // Jasen: 110
    Jasen jasen = new Jasen(); 
    jasen.parse("1|Aku|Ankka"); 
    assertEquals("From: Jasen line: 114", "Aku", jasen.getEtunimi()); 
    assertEquals("From: Jasen line: 115", "Ankka", jasen.getSukunimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetJasenId137 */
  @Test
  public void testSetJasenId137() {    // Jasen: 137
    Jasen jasen = new Jasen(); 
    jasen.setJasenId(3); 
    assertEquals("From: Jasen line: 140", 3, jasen.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetJasenId151 */
  @Test
  public void testGetJasenId151() {    // Jasen: 151
    Jasen testi = new Jasen(); 
    testi.setJasenId(1); 
    assertEquals("From: Jasen line: 154", 1, testi.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString171 */
  @Test
  public void testToString171() {    // Jasen: 171
    Jasen jasen = new Jasen(); 
    jasen.setJasenId(1); 
    jasen.setEtunimi("Aku"); 
    jasen.setSukunimi("Ankka"); 
    assertEquals("From: Jasen line: 176", "1|Aku|Ankka;", jasen.toString()); 
    Jasen jasen2 = new Jasen(); 
    jasen2.setJasenId(2); 
    jasen2.setEtunimi("Roope"); 
    jasen2.setSukunimi("Setä"); 
    assertEquals("From: Jasen line: 181", "2|Roope|Setä;", jasen2.toString()); 
  } // Generated by ComTest END
}
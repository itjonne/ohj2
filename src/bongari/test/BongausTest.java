package bongari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.11.14 18:23:02 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BongausTest {



  // Generated by ComTest BEGIN
  /** testGetBongausId27 */
  @Test
  public void testGetBongausId27() {    // Bongaus: 27
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaus line: 30", 1, bongaus.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetBongausId41 */
  @Test
  public void testSetBongausId41() {    // Bongaus: 41
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaus line: 44", 1, bongaus.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi56 */
  @Test
  public void testRekisteroi56() {    // Bongaus: 56
    Bongaus bongaus = new Bongaus(); 
    bongaus.rekisteroi(); 
    assertEquals("From: Bongaus line: 59", 1, bongaus.getBongausId()); 
    Bongaus bongaus2 = new Bongaus(); 
    bongaus2.rekisteroi(); 
    assertEquals("From: Bongaus line: 62", 2, bongaus2.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetJasenId74 */
  @Test
  public void testGetJasenId74() {    // Bongaus: 74
    Bongaus bongaus = new Bongaus(); 
    bongaus.setJasenId(1); 
    assertEquals("From: Bongaus line: 77", 1, bongaus.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetJasenId88 */
  @Test
  public void testSetJasenId88() {    // Bongaus: 88
    Bongaus bongaus = new Bongaus(); 
    bongaus.setJasenId(1); 
    assertEquals("From: Bongaus line: 91", 1, bongaus.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetBongattavaId102 */
  @Test
  public void testGetBongattavaId102() {    // Bongaus: 102
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongattavaId(1); 
    assertEquals("From: Bongaus line: 105", 1, bongaus.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetBongattavaId116 */
  @Test
  public void testSetBongattavaId116() {    // Bongaus: 116
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongattavaId(1); 
    assertEquals("From: Bongaus line: 119", 1, bongaus.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetKaupunki130 */
  @Test
  public void testSetKaupunki130() {    // Bongaus: 130
    Bongaus bongaus = new Bongaus(); 
    bongaus.setKaupunki("Helsinki"); 
    assertEquals("From: Bongaus line: 133", "Helsinki", bongaus.getKaupunki()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKaupunki144 */
  @Test
  public void testGetKaupunki144() {    // Bongaus: 144
    Bongaus bongaus = new Bongaus(); 
    bongaus.setKaupunki("Helsinki"); 
    assertEquals("From: Bongaus line: 147", "Helsinki", bongaus.getKaupunki()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetPvm158 */
  @Test
  public void testSetPvm158() {    // Bongaus: 158
    Bongaus bongaus = new Bongaus(); 
    bongaus.setPvm("12/01/1999"); 
    assertEquals("From: Bongaus line: 161", "12/01/1999", bongaus.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPvm172 */
  @Test
  public void testGetPvm172() {    // Bongaus: 172
    Bongaus bongaus = new Bongaus(); 
    bongaus.setPvm("12/01/1999"); 
    assertEquals("From: Bongaus line: 175", "12/01/1999", bongaus.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTietoja186 */
  @Test
  public void testSetTietoja186() {    // Bongaus: 186
    Bongaus bongaus = new Bongaus(); 
    bongaus.setTietoja("tietoja"); 
    assertEquals("From: Bongaus line: 189", "tietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTietoja200 */
  @Test
  public void testGetTietoja200() {    // Bongaus: 200
    Bongaus bongaus = new Bongaus(); 
    bongaus.setTietoja("tietoja"); 
    assertEquals("From: Bongaus line: 203", "tietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testParse215 
   * @throws ExceptionHandler when error
   */
  @Test
  public void testParse215() throws ExceptionHandler {    // Bongaus: 215
    Bongaus bongaus = new Bongaus(); 
    bongaus.parse("1|1|2|Helsinki|12/01/2009|Lisätietoja"); 
    assertEquals("From: Bongaus line: 219", 1, bongaus.getBongausId()); 
    assertEquals("From: Bongaus line: 220", 1, bongaus.getJasenId()); 
    assertEquals("From: Bongaus line: 221", 2, bongaus.getBongattavaId()); 
    assertEquals("From: Bongaus line: 222", "12/01/2009", bongaus.getPvm()); 
    assertEquals("From: Bongaus line: 223", "Lisätietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END
}
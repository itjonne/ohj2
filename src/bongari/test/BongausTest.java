package bongari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.09.07 10:51:19 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BongausTest {



  // Generated by ComTest BEGIN
  /** testGetBongausId28 */
  @Test
  public void testGetBongausId28() {    // Bongaus: 28
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaus line: 31", 1, bongaus.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetBongausId42 */
  @Test
  public void testSetBongausId42() {    // Bongaus: 42
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaus line: 45", 1, bongaus.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi57 */
  @Test
  public void testRekisteroi57() {    // Bongaus: 57
    Bongaus bongaus = new Bongaus(); 
    bongaus.rekisteroi(); 
    assertEquals("From: Bongaus line: 60", 1, bongaus.getBongausId()); 
    Bongaus bongaus2 = new Bongaus(); 
    bongaus2.rekisteroi(); 
    assertEquals("From: Bongaus line: 63", 2, bongaus2.getBongausId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetJasenId75 */
  @Test
  public void testGetJasenId75() {    // Bongaus: 75
    Bongaus bongaus = new Bongaus(); 
    bongaus.setJasenId(1); 
    assertEquals("From: Bongaus line: 78", 1, bongaus.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetJasenId89 */
  @Test
  public void testSetJasenId89() {    // Bongaus: 89
    Bongaus bongaus = new Bongaus(); 
    bongaus.setJasenId(1); 
    assertEquals("From: Bongaus line: 92", 1, bongaus.getJasenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetBongattavaId103 */
  @Test
  public void testGetBongattavaId103() {    // Bongaus: 103
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongattavaId(1); 
    assertEquals("From: Bongaus line: 106", 1, bongaus.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetBongattavaId117 */
  @Test
  public void testSetBongattavaId117() {    // Bongaus: 117
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongattavaId(1); 
    assertEquals("From: Bongaus line: 120", 1, bongaus.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetKaupunki131 */
  @Test
  public void testSetKaupunki131() {    // Bongaus: 131
    Bongaus bongaus = new Bongaus(); 
    bongaus.setKaupunki("Helsinki"); 
    assertEquals("From: Bongaus line: 134", "Helsinki", bongaus.getKaupunki()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKaupunki145 */
  @Test
  public void testGetKaupunki145() {    // Bongaus: 145
    Bongaus bongaus = new Bongaus(); 
    bongaus.setKaupunki("Helsinki"); 
    assertEquals("From: Bongaus line: 148", "Helsinki", bongaus.getKaupunki()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetPvm159 */
  @Test
  public void testSetPvm159() {    // Bongaus: 159
    Bongaus bongaus = new Bongaus(); 
    bongaus.setPvm("12/01/1999"); 
    assertEquals("From: Bongaus line: 162", "12/01/1999", bongaus.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPvm173 */
  @Test
  public void testGetPvm173() {    // Bongaus: 173
    Bongaus bongaus = new Bongaus(); 
    bongaus.setPvm("12/01/1999"); 
    assertEquals("From: Bongaus line: 176", "12/01/1999", bongaus.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTietoja187 */
  @Test
  public void testSetTietoja187() {    // Bongaus: 187
    Bongaus bongaus = new Bongaus(); 
    bongaus.setTietoja("tietoja"); 
    assertEquals("From: Bongaus line: 190", "tietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTietoja201 */
  @Test
  public void testGetTietoja201() {    // Bongaus: 201
    Bongaus bongaus = new Bongaus(); 
    bongaus.setTietoja("tietoja"); 
    assertEquals("From: Bongaus line: 204", "tietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testParse216 
   * @throws ExceptionHandler when error
   */
  @Test
  public void testParse216() throws ExceptionHandler {    // Bongaus: 216
    Bongaus bongaus = new Bongaus(); 
    bongaus.parse("1|1|2|Helsinki|12/01/2009|Lisätietoja"); 
    assertEquals("From: Bongaus line: 220", 1, bongaus.getBongausId()); 
    assertEquals("From: Bongaus line: 221", 1, bongaus.getJasenId()); 
    assertEquals("From: Bongaus line: 222", 2, bongaus.getBongattavaId()); 
    assertEquals("From: Bongaus line: 223", "12/01/2009", bongaus.getPvm()); 
    assertEquals("From: Bongaus line: 224", "Lisätietoja", bongaus.getTietoja()); 
  } // Generated by ComTest END
}
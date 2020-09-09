package bongari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.09.09 16:43:26 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BongattavaTest {



  // Generated by ComTest BEGIN
  /** testGetBongattavaId25 */
  @Test
  public void testGetBongattavaId25() {    // Bongattava: 25
    Bongattava bongattava = new Bongattava(); 
    bongattava.setBongattavaId(1); 
    assertEquals("From: Bongattava line: 28", 1, bongattava.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetNimi39 */
  @Test
  public void testGetNimi39() {    // Bongattava: 39
    Bongattava bongattava = new Bongattava(); 
    bongattava.setNimi("Kalle"); 
    assertEquals("From: Bongattava line: 42", "Kalle", bongattava.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetBongattavaId54 */
  @Test
  public void testSetBongattavaId54() {    // Bongattava: 54
    Bongattava bongattava = new Bongattava(); 
    bongattava.setBongattavaId(1); 
    assertEquals("From: Bongattava line: 57", 1, bongattava.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi69 */
  @Test
  public void testRekisteroi69() {    // Bongattava: 69
    Bongattava bongattava = new Bongattava(); 
    bongattava.rekisteroi(); 
    assertEquals("From: Bongattava line: 72", 1, bongattava.getBongattavaId()); 
    Bongattava bongattava2 = new Bongattava(); 
    bongattava2.rekisteroi(); 
    assertEquals("From: Bongattava line: 75", 2, bongattava2.getBongattavaId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetNimi88 */
  @Test
  public void testSetNimi88() {    // Bongattava: 88
    Bongattava bongattava = new Bongattava(); 
    bongattava.setNimi("Kissa"); 
    assertEquals("From: Bongattava line: 91", "Kissa", bongattava.getNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTieteellinenNimi102 */
  @Test
  public void testSetTieteellinenNimi102() {    // Bongattava: 102
    Bongattava bongattava = new Bongattava(); 
    bongattava.setTieteellinenNimi("Latinaksinimi"); 
    assertEquals("From: Bongattava line: 105", "Latinaksinimi", bongattava.getTieteellinenNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTieteellinenNimi117 */
  @Test
  public void testGetTieteellinenNimi117() {    // Bongattava: 117
    Bongattava bongattava = new Bongattava(); 
    bongattava.setTieteellinenNimi("Latinaksinimi"); 
    assertEquals("From: Bongattava line: 120", "Latinaksinimi", bongattava.getTieteellinenNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetHeimo131 */
  @Test
  public void testSetHeimo131() {    // Bongattava: 131
    Bongattava bongattava = new Bongattava(); 
    bongattava.setHeimo("Kissakalat"); 
    assertEquals("From: Bongattava line: 134", "Kissakalat", bongattava.getHeimo()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetHeimo145 */
  @Test
  public void testGetHeimo145() {    // Bongattava: 145
    Bongattava bongattava = new Bongattava(); 
    bongattava.setHeimo("Kissakalat"); 
    assertEquals("From: Bongattava line: 148", "Kissakalat", bongattava.getHeimo()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetLaji159 */
  @Test
  public void testSetLaji159() {    // Bongattava: 159
    Bongattava bongattava = new Bongattava(); 
    bongattava.setLaji("Kala"); 
    assertEquals("From: Bongattava line: 162", "Kala", bongattava.getLaji()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLaji173 */
  @Test
  public void testGetLaji173() {    // Bongattava: 173
    Bongattava bongattava = new Bongattava(); 
    bongattava.setLaji("Kala"); 
    assertEquals("From: Bongattava line: 176", "Kala", bongattava.getLaji()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testParse205 
   * @throws ExceptionHandler when error
   */
  @Test
  public void testParse205() throws ExceptionHandler {    // Bongattava: 205
    Bongattava bongattava = new Bongattava(); 
    bongattava.parse("2|Harmaahaikara|Ardea cinerea|Haikarat, Ardeidae|Lintu"); 
    assertEquals("From: Bongattava line: 209", 2, bongattava.getBongattavaId()); 
    assertEquals("From: Bongattava line: 210", "Harmaahaikara", bongattava.getNimi()); 
    assertEquals("From: Bongattava line: 211", "Ardea cinerea", bongattava.getTieteellinenNimi()); 
    assertEquals("From: Bongattava line: 212", "Haikarat, Ardeidae", bongattava.getHeimo()); 
    assertEquals("From: Bongattava line: 213", "Lintu", bongattava.getLaji()); 
  } // Generated by ComTest END
}
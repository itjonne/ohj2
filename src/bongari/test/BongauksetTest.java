package bongari.test;
// Generated by ComTest BEGIN
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import bongari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.11.14 18:22:19 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BongauksetTest {



  // Generated by ComTest BEGIN
  /** testLisaa30 */
  @Test
  public void testLisaa30() {    // Bongaukset: 30
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    assertEquals("From: Bongaukset line: 33", 0, bongaukset.getLkm()); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 35", 1, bongaukset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetLkm47 */
  @Test
  public void testGetLkm47() {    // Bongaukset: 47
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaukset line: 51", 0, bongaukset.getLkm()); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 53", 1, bongaukset.getLkm()); 
    bongaukset.poista(bongaus.getBongausId()); 
    assertEquals("From: Bongaukset line: 55", 0, bongaukset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoista67 */
  @Test
  public void testPoista67() {    // Bongaukset: 67
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    assertEquals("From: Bongaukset line: 71", 0, bongaukset.getLkm()); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 73", 1, bongaukset.getLkm()); 
    bongaukset.poista(0); 
    assertEquals("From: Bongaukset line: 75", 1, bongaukset.getLkm()); 
    bongaukset.poista(1); 
    assertEquals("From: Bongaukset line: 77", 0, bongaukset.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testMuokkaa135 */
  @Test
  public void testMuokkaa135() {    // Bongaukset: 135
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    bongaus.setJasenId(1); 
    Bongaus muokattava = new Bongaus(); 
    muokattava.setBongausId(1); 
    muokattava.setJasenId(2); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 144", 1, bongaukset.haeJasenenBongaukset(1).size()); 
    bongaukset.muokkaa(muokattava); 
    assertEquals("From: Bongaukset line: 146", 0, bongaukset.haeJasenenBongaukset(1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHaeJasenenBongaukset163 */
  @Test
  public void testHaeJasenenBongaukset163() {    // Bongaukset: 163
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    bongaus.setJasenId(1); 
    Bongaus bongaus2 = new Bongaus(); 
    bongaus2.setBongausId(2); 
    bongaus2.setJasenId(1); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 172", 1, bongaukset.haeJasenenBongaukset(1).size()); 
    bongaukset.lisaa(bongaus2); 
    assertEquals("From: Bongaukset line: 174", 2, bongaukset.haeJasenenBongaukset(1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHaeBongattavanBongaukset190 */
  @Test
  public void testHaeBongattavanBongaukset190() {    // Bongaukset: 190
    Bongaukset bongaukset = new Bongaukset(); 
    Bongaus bongaus = new Bongaus(); 
    bongaus.setBongausId(1); 
    bongaus.setBongattavaId(1); 
    Bongaus bongaus2 = new Bongaus(); 
    bongaus2.setBongausId(2); 
    bongaus2.setBongattavaId(1); 
    bongaukset.lisaa(bongaus); 
    assertEquals("From: Bongaukset line: 199", 1, bongaukset.haeBongattavanBongaukset(1).size()); 
    bongaukset.lisaa(bongaus2); 
    assertEquals("From: Bongaukset line: 201", 2, bongaukset.haeBongattavanBongaukset(1).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaaTietorakenteeseen217 
   * @throws ExceptionHandler when error
   */
  @Test
  public void testLisaaTietorakenteeseen217() throws ExceptionHandler {    // Bongaukset: 217
    List<String> rivit = new ArrayList<String>(); 
    String rivi = "1|1|1|Jyväskylä|01/01/2020|Helppo löytö"; 
    rivit.add(rivi); 
    Bongaukset bongaukset = new Bongaukset(); 
    assertEquals("From: Bongaukset line: 224", 0, bongaukset.getLkm()); 
    bongaukset.lisaaTietorakenteeseen(rivit); 
    assertEquals("From: Bongaukset line: 226", 1, bongaukset.getLkm()); 
  } // Generated by ComTest END
}
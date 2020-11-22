package bongari;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Jonne
 * @version 3 Sep 2020
 *
 */
public class Bongattavat {
    private HashMap<Integer, Bongattava> alkiot = new HashMap<Integer, Bongattava>();
    private String tiedostonPerusNimi = "linnut";
    private int lkm = 0;
    
    /**
     * Palauttaa alkioiden lukumäärän
     * @return alkioiden lukumäärä
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * bongattavat.getLkm() === 0;
     * Bongattava bongattava = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getLkm() === 1;
     * </pre>
     */
    public int getLkm() {
        return this.lkm;
    }
    
    /**
     * Palauttaa kaikki tietorakenteesta löytyvät alkiot
     * @return kaikki alkiot
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getAlkiot().size() === 1;
     * </pre>
     */
    public HashMap<Integer, Bongattava> getAlkiot() {
        return this.alkiot;
    }
    
    /**
     * Lisätään bongattava-olio tietorakenteeseen
     * @param bongattava lisättävä bongattava-olio
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * Bongattava bongattava2 = new Bongattava();
     * bongattavat.lisaa(bongattava);
     * bongattavat.getLkm() === 1;
     * bongattavat.lisaa(bongattava2);
     * bongattavat.getLkm() === 2;
     * </pre>
     */
    public void lisaa(Bongattava bongattava) {      
        alkiot.put(bongattava.getBongattavaId(), bongattava);
        lkm++;
    }
    
    /**
     * Etsii tietorakenteesta merkkijonon nimi-ehdon täyttävät otukset
     * @param hakuehto merkkijono, jolla haetaan
     * @return palauttaa nimi-hakuehdon täyttävät bongattavat otukset
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattava.setNimi("Kissa");
     * Bongattava bongattava2 = new Bongattava();
     * bongattava2.setBongattavaId(2);
     * bongattava2.setNimi("Kassa");
     * bongattavat.lisaa(bongattava); bongattavat.lisaa(bongattava2);
     * bongattavat.etsi("ssa").size() === 2;
     * bongattavat.etsi("kissa").size() === 1;
     * bongattavat.etsi("IsSa").size() === 1;
     * bongattavat.etsi("koira").size() === 0;
     * </pre>
     */
    public List<Bongattava> etsi(String hakuehto) {
        List<Bongattava> loydetyt = new ArrayList<Bongattava>();
        // Tyhjällä haulla palauttaa kaikki.
        if (hakuehto == "") {
            alkiot.forEach((key, value) -> {
                loydetyt.add(value);
            });
        }
        
        alkiot.forEach((key,value) -> {
            if (value.getNimi().toLowerCase().contains(hakuehto.toLowerCase())) loydetyt.add(value);
        });
        return loydetyt;
    }
    
    /**
     * @param id haettavan olion id
     * @return palauttaa id:llä haettavan olion, jos ei löydy niin null
     * @example
     * <pre name="test">
     * Bongattavat bongattavat = new Bongattavat();
     * Bongattava bongattava = new Bongattava();
     * bongattava.setBongattavaId(1);
     * bongattavat.lisaa(bongattava);
     * Bongattava loytynyt = bongattavat.anna(1);
     * Bongattava loytynyt2 = bongattavat.anna(0);
     * loytynyt.getBongattavaId() === 1;
     * loytynyt2 === null;
     * </pre>
     */
    public Bongattava anna(int id) {
        return alkiot.get(id);
    }
    
    /**
     * @param tied tiedoston nimi
     * @throws ExceptionHandler jos joku menee pieleen, heitetään oma exceptioni
     * <pre name="test">
     * #THROWS ExceptionHandler 
     * #import java.io.File;
     * 
     *  Bongattavat bongattavat = new Bongattavat();
     *  String hakemisto = "data/testikerho/";
     *  bongattavat.lueTiedostosta(hakemisto + "linnt"); #THROWS ExceptionHandler
     *  bongattavat = new Bongattavat();            // Poistetaan vanhat luomalla uusi
     *  bongattavat.lueTiedostosta(hakemisto + "linnut");  // johon ladataan tiedot tiedostosta.
     *  bongattavat.getAlkiot().size() === 257; 
     * </pre>
     */
    public void lueTiedostosta(String tied) throws ExceptionHandler {
        setTiedostonPerusNimi(tied);
        try ( BufferedReader fi = new BufferedReader(new FileReader(getTiedostonNimi())) ) {

            String rivi;
            while ( (rivi = fi.readLine()) != null ) {
                rivi = rivi.trim();
                if ( "".equals(rivi) || rivi.charAt(0) == ';' ) continue;
                Bongattava bongattava = new Bongattava();
                bongattava.parse(rivi); // voisi olla virhekäsittely
                lisaa(bongattava);
            }
        } catch ( FileNotFoundException e ) {
            throw new ExceptionHandler("Tiedosto " + getTiedostonNimi() + " ei aukea");
        } catch ( IOException e ) {
            throw new ExceptionHandler("Ongelmia tiedoston kanssa: " + e.getMessage());
        }
    }
    
    /**
     * Luetaan aikaisemmin annetun nimisestä tiedostosta
     * @throws ExceptionHandler jos tulee poikkeus
     */
    public void lueTiedostosta() throws ExceptionHandler {
        lueTiedostosta(getTiedostonPerusNimi());
    }

    /**
     * Asettaa tiedoston perusnimen ilman tarkenninta
     * @param tied tallennustiedoston perusnimi
     */
    public void setTiedostonPerusNimi(String tied) {
        tiedostonPerusNimi = tied;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonPerusNimi() {
        return tiedostonPerusNimi;
    }


    /**
     * Palauttaa tiedoston nimen, jota käytetään tallennukseen
     * @return tallennustiedoston nimi
     */
    public String getTiedostonNimi() {
        return tiedostonPerusNimi + ".dat";
    }


    /**
     * Palauttaa varakopiotiedoston nimen
     * @return varakopiotiedoston nimi
     */
    public String getBakNimi() {
        return tiedostonPerusNimi + ".bak";
    }
    
    /**
     * Tulostaa kaikki tietorakenteessa olevat avaimet ( == nimet)
     */
    public void tulostaBongattavat() {
        for (Integer key : alkiot.keySet()) {
            System.out.println(alkiot.get(key).getNimi());
        }
    }
    
    /**
     * Alustaa bongattavat linnut tiedostoon jos tiedosto jostain syystä hukkunut
     * @throws ExceptionHandler jos ei alustaminen onnistu
     * 
     */
    public void alusta() throws ExceptionHandler {
        File ftied = new File(getTiedostonNimi());
        try {
            ftied.createNewFile();
        } catch (IOException ex) {
            throw new ExceptionHandler("Tiedosto " + ftied.getName() + " ei alustu");
        }
        
        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
        fo.print(    
        ";Luontobongaajien kerho ry\r\n" + 
        "; Kenttien järjestys tiedostossa on seuraava:\r\n" + 
        ";id|nimi|tieteellinen_nimi|heimo|laji\r\n" + 
        "1|Ruokokerttunen|Acrocephalus schoenobaenus|Kerttuset, Acrocephalidae|Lintu\r\n" + 
        "2|Harmaahaikara|Ardea cinerea|Haikarat, Ardeidae|Lintu\r\n" + 
        "3|Suopöllö|Asio flammeus|Pöllöt, Strigidae|Lintu\r\n" + 
        "4|Luhtakana|Rallus aquaticus|Rantakanat, Rallidae|Lintu\r\n" + 
        "5|Merikihu|Stercorarius parasiticus|Kihut, Stercorariidae|Lintu\r\n" + 
        "6|Pohjansirkku|Schoeniclus rusticus|Sirkut, Emberizidae|Lintu\r\n" + 
        "7|Härkälintu|Podiceps grisegena|Uikut, Podicipedidae|Lintu\r\n" + 
        "8|Törmäpääsky|Riparia riparia|Pääskyt, Hirundinidae|Lintu\r\n" + 
        "9|Räkättirastas|Turdus pilaris|Rastaat, Turdidae|Lintu\r\n" + 
        "10|Pajusirkku|Schoeniclus schoeniclus|Sirkut, Emberizidae|Lintu\r\n" + 
        "11|Jouhisorsa|Anas acuta|Sorsat, Anatidae|Lintu\r\n" + 
        "12|Kivitasku|Oenanthe oenanthe|Siepot, Muscicapidae|Lintu\r\n" + 
        "13|Valkoposkihanhi|Branta leucopsis|Sorsat, Anatidae|Lintu\r\n" + 
        "14|Tilhi|Bombycilla garrulus|Tilhet, Bombycillidae|Lintu\r\n" + 
        "15|Punatulkku|Pyrrhula pyrrhula|Peipot, Fringillidae|Lintu\r\n" + 
        "16|Pensaskerttu|Sylvia communis|Kertut, Sylviidae|Lintu\r\n" + 
        "17|Kirjosiipikäpylintu|Loxia leucoptera|Peipot, Fringillidae|Lintu\r\n" + 
        "18|Pyrstötiainen|Aegithalos caudatus|Pyrstötiaiset, Aegithalidae|Lintu\r\n" + 
        "19|Lapintiainen|Poecile cinctus|Tiaiset, Paridae|Lintu\r\n" + 
        "20|Sepelkyyhky|Columba palumbus|Kyyhkyt, Columbidae|Lintu\r\n" + 
        "21|Pulmussirri|Calidris alba|Kurpat, Scolopacidae|Lintu\r\n" + 
        "22|Lehtokerttu|Sylvia borin|Kertut, Sylviidae|Lintu\r\n" + 
        "23|Kiljuhanhi|Anser erythropus|Sorsat, Anatidae|Lintu\r\n" + 
        "24|Kulorastas|Turdus viscivorus|Rastaat, Turdidae|Lintu\r\n" + 
        "25|Käki|Cuculus canorus|Käet, Cuculidae|Lintu\r\n" + 
        "26|Naurulokki|Chroicocephalus ridibundus|Lokit, Laridae|Lintu\r\n" + 
        "27|Mustajoutsen|Cygnus atratus|Sorsat, Anatidae|Lintu\r\n" + 
        "28|Mustapyrstökuiri|Limosa limosa|Kurpat, Scolopacidae|Lintu\r\n" + 
        "29|Virtavästäräkki|Motacilla cinerea|Västäräkit, Motacillidae|Lintu\r\n" + 
        "30|Lapasotka|Aythya marila|Sorsat, Anatidae|Lintu\r\n" + 
        "31|Sinisuohaukka|Circus cyaneus|Haukat, Accipitridae|Lintu\r\n" + 
        "32|Laulurastas|Turdus philomelos|Rastaat, Turdidae|Lintu\r\n" + 
        "33|Kuovisirri|Calidris ferruginea|Kurpat, Scolopacidae|Lintu\r\n" + 
        "34|Naakka|Corvus monedula|Varikset, Corvidae|Lintu\r\n" + 
        "35|Tuulihaukka|Falco tinnunculus|Jalohaukat, Falconidae|Lintu\r\n" + 
        "36|Lampiviklo|Tringa stagnatilis|Kurpat, Scolopacidae|Lintu\r\n" + 
        "37|Metsäviklo|Tringa ochropus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "38|Isokäpylintu|Loxia pytyopsittacus|Peipot, Fringillidae|Lintu\r\n" + 
        "39|Selkälokki|Larus fuscus|Lokit, Laridae|Lintu\r\n" + 
        "40|Satakieli|Luscinia luscinia|Siepot, Muscicapidae|Lintu\r\n" + 
        "41|Närhi|Garrulus glandarius|Varikset, Corvidae|Lintu\r\n" + 
        "42|Suokukko|Calidris pugnax|Kurpat, Scolopacidae|Lintu\r\n" + 
        "43|Liejukana|Gallinula chloropus|Rantakanat, Rallidae|Lintu\r\n" + 
        "44|Keräkurmitsa|Eudromias morinellus|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "45|Kangaskiuru|Lullula arborea|Kiurut, Alaudidae|Lintu\r\n" + 
        "46|Varpuspöllö|Glaucidium passerinum|Pöllöt, Strigidae|Lintu\r\n" + 
        "47|Turturikyyhky|Streptopelia turtur|Kyyhkyt, Columbidae|Lintu\r\n" + 
        "48|Heinätavi|Spatula querquedula|Sorsat, Anatidae|Lintu\r\n" + 
        "49|Punarinta|Erithacus rubecula|Siepot, Muscicapidae|Lintu\r\n" + 
        "50|Teeri|Lyrurus tetrix|Aitokanat, Phasianidae|Lintu\r\n" + 
        "51|Laulujoutsen|Cygnus cygnus|Sorsat, Anatidae|Lintu\r\n" + 
        "52|Pikkusirkku|Schoeniclus pusillus|Sirkut, Emberizidae|Lintu\r\n" + 
        "53|Pohjantikka|Picoides tridactylus|Tikat, Picidae|Lintu\r\n" + 
        "54|Luhtahuitti|Porzana porzana|Rantakanat, Rallidae|Lintu\r\n" + 
        "55|Varpushaukka|Accipiter nisus|Haukat, Accipitridae|Lintu\r\n" + 
        "56|Uuttukyyhky|Columba oenas|Kyyhkyt, Columbidae|Lintu\r\n" + 
        "57|Rantasipi|Actitis hypoleucos|Kurpat, Scolopacidae|Lintu\r\n" + 
        "58|Harmaasorsa|Mareca strepera|Sorsat, Anatidae|Lintu\r\n" + 
        "59|Suosirri|Calidris alpina|Kurpat, Scolopacidae|Lintu\r\n" + 
        "60|Urpiainen|Acanthis flammea|Peipot, Fringillidae|Lintu\r\n" + 
        "61|Merimetso|Phalacrocorax carbo|Merimetsot, Phalacrocoracidae|Lintu\r\n" + 
        "62|Vesipääsky|Phalaropus lobatus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "63|Kottarainen|Sturnus vulgaris|Kottaraiset, Sturnidae|Lintu\r\n" + 
        "64|Maakotka|Aquila chrysaetos|Haukat, Accipitridae|Lintu\r\n" + 
        "65|Tunturihaukka|Falco rusticolus|Jalohaukat, Falconidae|Lintu\r\n" + 
        "66|Sinirinta|Luscinia svecica|Siepot, Muscicapidae|Lintu\r\n" + 
        "67|Harmaalokki|Larus argentatus|Lokit, Laridae|Lintu\r\n" + 
        "68|Punakaulahanhi|Branta ruficollis|Sorsat, Anatidae|Lintu\r\n" + 
        "69|Haapana|Mareca penelope|Sorsat, Anatidae|Lintu\r\n" + 
        "70|Pikku-uikku|Tachybaptus ruficollis|Uikut, Podicipedidae|Lintu\r\n" + 
        "71|Morsiosorsa|Aix sponsa|Sorsat, Anatidae|Lintu\r\n" + 
        "72|Haarahaukka|Milvus migrans|Haukat, Accipitridae|Lintu\r\n" + 
        "73|Ruisrääkkä|Crex crex|Rantakanat, Rallidae|Lintu\r\n" + 
        "74|Tervapääsky|Apus apus|Kiitäjät, Apodidae|Lintu\r\n" + 
        "75|Kapustarinta|Pluvialis apricaria|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "76|Peltopyy|Perdix perdix|Aitokanat, Phasianidae|Lintu\r\n" + 
        "77|Lapinsirri|Calidris temminckii|Kurpat, Scolopacidae|Lintu\r\n" + 
        "78|Sinipyrstö|Tarsiger cyanurus|Siepot, Muscicapidae|Lintu\r\n" + 
        "79|Kuhankeittäjä|Oriolus oriolus|Kuhankeittäjät, Oriolidae|Lintu\r\n" + 
        "80|Metso|Tetrao urogallus|Aitokanat, Phasianidae|Lintu\r\n" + 
        "81|Keltahemppo|Serinus serinus|Peipot, Fringillidae|Lintu\r\n" + 
        "82|Etelänkiisla|Uria aalge|Ruokit, Alcidae|Lintu\r\n" + 
        "83|Tavi|Anas crecca|Sorsat, Anatidae|Lintu\r\n" + 
        "84|Luotokirvinen|Anthus petrosus|Västäräkit, Motacillidae|Lintu\r\n" + 
        "85|Käpytikka|Dendrocopos major|Tikat, Picidae|Lintu\r\n" + 
        "86|Harakka|Pica pica|Varikset, Corvidae|Lintu\r\n" + 
        "87|Karikukko|Arenaria interpres|Kurpat, Scolopacidae|Lintu\r\n" + 
        "88|Meriharakka|Haematopus ostralegus|Meriharakat, Haematopodidae|Lintu\r\n" + 
        "89|Heinäkurppa|Gallinago media|Kurpat, Scolopacidae|Lintu\r\n" + 
        "90|Isokoskelo|Mergus merganser|Sorsat, Anatidae|Lintu\r\n" + 
        "91|Sinisorsa|Anas platyrhynchos|Sorsat, Anatidae|Lintu\r\n" + 
        "92|Tukkakoskelo|Mergus serrator|Sorsat, Anatidae|Lintu\r\n" + 
        "93|Varis|Corvus corone cornix|Varikset, Corvidae|Lintu\r\n" + 
        "94|Tundrakurmitsa|Pluvialis squatarola|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "95|Fasaani|Phasianus colchicus|Aitokanat, Phasianidae|Lintu\r\n" + 
        "96|Ruskosuohaukka|Circus aeruginosus|Haukat, Accipitridae|Lintu\r\n" + 
        "97|Sitruunavästäräkki|Motacilla citreola|Västäräkit, Motacillidae|Lintu\r\n" + 
        "98|Kuovi|Numenius arquata|Kurpat, Scolopacidae|Lintu\r\n" + 
        "99|Liro|Tringa glareola|Kurpat, Scolopacidae|Lintu\r\n" + 
        "100|Kehrääjä|Caprimulgus europaeus|Kehrääjät, Caprimulgidae|Lintu\r\n" + 
        "101|Västäräkki|Motacilla alba|Västäräkit, Motacillidae|Lintu\r\n" + 
        "102|Räyskä|Hydroprogne caspia|Tiirat, Sternidae|Lintu\r\n" + 
        "103|Puukiipijä|Certhia familiaris|Puukiipijät, Certhiidae|Lintu\r\n" + 
        "104|Pikkukuovi|Numenius phaeopus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "105|Punajalkaviklo|Tringa totanus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "106|Tundraurpiainen|Acanthis hornemanni|Peipot, Fringillidae|Lintu\r\n" + 
        "107|Tundrahanhi|Anser albifrons|Sorsat, Anatidae|Lintu\r\n" + 
        "108|Haahka|Somateria mollissima|Sorsat, Anatidae|Lintu\r\n" + 
        "109|Kalalokki|Larus canus|Lokit, Laridae|Lintu\r\n" + 
        "110|Arosuohaukka|Circus macrourus|Haukat, Accipitridae|Lintu\r\n" + 
        "111|Pajulintu|Phylloscopus trochilus|Uunilinnut, Phylloscopidae|Lintu\r\n" + 
        "112|Varpunen|Passer domesticus|Varpuset, Passeridae|Lintu\r\n" + 
        "113|Mustahaikara|Ciconia nigra|Kattohaikarat, Ciconiidae|Lintu\r\n" + 
        "114|Pikkukajava|Rissa tridactyla|Lokit, Laridae|Lintu\r\n" + 
        "115|Kaulushaikara|Botaurus stellaris|Haikarat, Ardeidae|Lintu\r\n" + 
        "116|Lehtokurppa|Scolopax rusticola|Kurpat, Scolopacidae|Lintu\r\n" + 
        "117|Metsäkirvinen|Anthus trivialis|Västäräkit, Motacillidae|Lintu\r\n" + 
        "118|Pyy|Tetrastes bonasia|Aitokanat, Phasianidae|Lintu\r\n" + 
        "119|Kyhmyjoutsen|Cygnus olor|Sorsat, Anatidae|Lintu\r\n" + 
        "120|Niittysuohaukka|Circus pygargus|Haukat, Accipitridae|Lintu\r\n" + 
        "121|Kuukkeli|Perisoreus infaustus|Varikset, Corvidae|Lintu\r\n" + 
        "122|Leppälintu|Phoenicurus phoenicurus|Siepot, Muscicapidae|Lintu\r\n" + 
        "123|Nokkavarpunen|Coccothraustes coccothraustes|Peipot, Fringillidae|Lintu\r\n" + 
        "124|Hemppo|Linaria cannabina|Peipot, Fringillidae|Lintu\r\n" + 
        "125|Tiltaltti|Phylloscopus collybita|Uunilinnut, Phylloscopidae|Lintu\r\n" + 
        "126|Lapinkirvinen|Anthus cervinus|Västäräkit, Motacillidae|Lintu\r\n" + 
        "127|Kultarinta|Hippolais icterina|Kerttuset, Acrocephalidae|Lintu\r\n" + 
        "128|Kiiruna|Lagopus muta|Aitokanat, Phasianidae|Lintu\r\n" + 
        "129|Sääksi|Pandion haliaetus|Haukat, Accipitridae|Lintu\r\n" + 
        "130|Kyhmyhaahka|Somateria spectabilis|Sorsat, Anatidae|Lintu\r\n" + 
        "131|Hernekerttu|Sylvia curruca|Kertut, Sylviidae|Lintu\r\n" + 
        "132|Mustaviklo|Tringa erythropus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "133|Hiiripöllö|Surnia ulula|Pöllöt, Strigidae|Lintu\r\n" + 
        "134|Valkoviklo|Tringa nebularia|Kurpat, Scolopacidae|Lintu\r\n" + 
        "135|Punakuiri|Limosa lapponica|Kurpat, Scolopacidae|Lintu\r\n" + 
        "136|Pikkusirri|Calidris minuta|Kurpat, Scolopacidae|Lintu\r\n" + 
        "137|Viherpeippo|Chloris chloris|Peipot, Fringillidae|Lintu\r\n" + 
        "138|Ristisorsa|Tadorna tadorna|Sorsat, Anatidae|Lintu\r\n" + 
        "139|Punakylkirastas|Turdus iliacus|Rastaat, Turdidae|Lintu\r\n" + 
        "140|Kanadanhanhi|Branta canadensis|Sorsat, Anatidae|Lintu\r\n" + 
        "141|Mandariinisorsa|Aix galericulata|Sorsat, Anatidae|Lintu\r\n" + 
        "142|Mehiläishaukka|Pernis apivorus|Haukat, Accipitridae|Lintu\r\n" + 
        "143|Turkinkyyhky|Streptopelia decaocto|Kyyhkyt, Columbidae|Lintu\r\n" + 
        "144|Lapasorsa|Spatula clypeata|Sorsat, Anatidae|Lintu\r\n" + 
        "145|Harmaasieppo|Muscicapa striata|Siepot, Muscicapidae|Lintu\r\n" + 
        "146|Järripeippo|Fringilla montifringilla|Peipot, Fringillidae|Lintu\r\n" + 
        "147|Viiriäinen|Coturnix coturnix|Aitokanat, Phasianidae|Lintu\r\n" + 
        "148|Allihaahka|Polysticta stelleri|Sorsat, Anatidae|Lintu\r\n" + 
        "149|Hömötiainen|Poecile montanus|Tiaiset, Paridae|Lintu\r\n" + 
        "150|Ampuhaukka|Falco columbarius|Jalohaukat, Falconidae|Lintu\r\n" + 
        "151|Kurki|Grus grus|Kurjet, Gruidae|Lintu\r\n" + 
        "152|Piekana|Buteo lagopus|Haukat, Accipitridae|Lintu\r\n" + 
        "153|Isolokki|Larus hyperboreus|Lokit, Laridae|Lintu\r\n" + 
        "154|Pensastasku|Saxicola rubetra|Siepot, Muscicapidae|Lintu\r\n" + 
        "155|Lehtopöllö|Strix aluco|Pöllöt, Strigidae|Lintu\r\n" + 
        "156|Keltavästäräkki|Motacilla flava|Västäräkit, Motacillidae|Lintu\r\n" + 
        "157|Peltosirkku|Emberiza hortulana|Sirkut, Emberizidae|Lintu\r\n" + 
        "158|Mustapääkerttu|Sylvia atricapilla|Kertut, Sylviidae|Lintu\r\n" + 
        "159|Punasotka|Aythya ferina|Sorsat, Anatidae|Lintu\r\n" + 
        "160|Riekko|Lagopus lagopus|Aitokanat, Phasianidae|Lintu\r\n" + 
        "161|Mehiläissyöjä|Merops apiaster|Mehiläissyöjät, Meropidae|Lintu\r\n" + 
        "162|Alli|Clangula hyemalis|Sorsat, Anatidae|Lintu\r\n" + 
        "163|Mustavaris|Corvus frugilegus|Varikset, Corvidae|Lintu\r\n" + 
        "164|Kuikka|Gavia arctica|Kuikat, Gaviidae|Lintu\r\n" + 
        "165|Korppi|Corvus corax|Varikset, Corvidae|Lintu\r\n" + 
        "166|Jänkäsirriäinen|Calidris falcinellus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "167|Taviokuurna|Pinicola enucleator|Peipot, Fringillidae|Lintu\r\n" + 
        "168|Talitiainen|Parus major|Tiaiset, Paridae|Lintu\r\n" + 
        "169|Merikotka|Haliaeetus albicilla|Haukat, Accipitridae|Lintu\r\n" + 
        "170|Telkkä|Bucephala clangula|Sorsat, Anatidae|Lintu\r\n" + 
        "171|Niittykirvinen|Anthus pratensis|Västäräkit, Motacillidae|Lintu\r\n" + 
        "172|Rantakurvi|Xenus cinereus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "173|Kalatiira|Sterna hirundo|Tiirat, Sternidae|Lintu\r\n" + 
        "174|Viirupöllö|Strix uralensis|Pöllöt, Strigidae|Lintu\r\n" + 
        "175|Sepelrastas|Turdus torquatus|Rastaat, Turdidae|Lintu\r\n" + 
        "176|Haarapääsky|Hirundo rustica|Pääskyt, Hirundinidae|Lintu\r\n" + 
        "177|Pilkkasiipi|Melanitta fusca|Sorsat, Anatidae|Lintu\r\n" + 
        "178|Kiuru|Alauda arvensis|Kiurut, Alaudidae|Lintu\r\n" + 
        "179|Lapintiira|Sterna paradisaea|Tiirat, Sternidae|Lintu\r\n" + 
        "180|Tunturipöllö|Bubo scandiacus|Pöllöt, Strigidae|Lintu\r\n" + 
        "181|Kuusitiainen|Periparus ater|Tiaiset, Paridae|Lintu\r\n" + 
        "182|Punajalkahaukka|Falco vespertinus|Jalohaukat, Falconidae|Lintu\r\n" + 
        "183|Metsähanhi|Anser fabalis|Sorsat, Anatidae|Lintu\r\n" + 
        "184|Kanahaukka|Accipiter gentilis|Haukat, Accipitridae|Lintu\r\n" + 
        "185|Idänuunilintu|Phylloscopus trochiloides|Uunilinnut, Phylloscopidae|Lintu\r\n" + 
        "186|Keltasirkku|Emberiza citrinella|Sirkut, Emberizidae|Lintu\r\n" + 
        "187|Vuorihemppo|Linaria flavirostris|Peipot, Fringillidae|Lintu\r\n" + 
        "188|Tylli|Charadrius hiaticula|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "189|Isolepinkäinen|Lanius excubitor|Lepinkäiset, Laniidae|Lintu\r\n" + 
        "190|Peukaloinen|Troglodytes troglodytes|Peukaloiset, Troglodytidae|Lintu\r\n" + 
        "191|Jalohaikara|Ardea alba|Haikarat, Ardeidae|Lintu\r\n" + 
        "192|Merilokki|Larus marinus|Lokit, Laridae|Lintu\r\n" + 
        "193|Pikkulepinkäinen|Lanius collurio|Lepinkäiset, Laniidae|Lintu\r\n" + 
        "194|Jänkäkurppa|Lymnocryptes minimus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "195|Töyhtötiainen|Lophophanes cristatus|Tiaiset, Paridae|Lintu\r\n" + 
        "196|Pikkutylli|Charadrius dubius|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "197|Lyhytnokkahanhi|Anser brachyrhynchus|Sorsat, Anatidae|Lintu\r\n" + 
        "198|Uivelo|Mergellus albellus|Sorsat, Anatidae|Lintu\r\n" + 
        "199|Rautiainen|Prunella modularis|Rautiaiset, Prunellidae|Lintu\r\n" + 
        "200|Pikkukäpylintu|Loxia curvirostra|Peipot, Fringillidae|Lintu\r\n" + 
        "201|Hiirihaukka|Buteo buteo|Haukat, Accipitridae|Lintu\r\n" + 
        "202|Pähkinänakkeli|Sitta europaea|Nakkelit, Sittidae|Lintu\r\n" + 
        "203|Mustarastas|Turdus merula|Rastaat, Turdidae|Lintu\r\n" + 
        "204|Tunturikihu|Stercorarius longicaudus|Kihut, Stercorariidae|Lintu\r\n" + 
        "205|Punavarpunen|Carpodacus erythrinus|Peipot, Fringillidae|Lintu\r\n" + 
        "206|Avosetti|Recurvirostra avosetta|Avosetit, Recurvirostridae|Lintu\r\n" + 
        "207|Nokikana|Fulica atra|Rantakanat, Rallidae|Lintu\r\n" + 
        "208|Taivaanvuohi|Gallinago gallinago|Kurpat, Scolopacidae|Lintu\r\n" + 
        "209|Käenpiika|Jynx torquilla|Tikat, Picidae|Lintu\r\n" + 
        "210|Sinitiainen|Cyanistes caeruleus|Tiaiset, Paridae|Lintu\r\n" + 
        "211|Muuttohaukka|Falco peregrinus|Jalohaukat, Falconidae|Lintu\r\n" + 
        "212|Harmaapäätikka|Picus canus|Tikat, Picidae|Lintu\r\n" + 
        "213|Viitatiainen|Poecile palustris|Tiaiset, Paridae|Lintu\r\n" + 
        "214|Kattohaikara|Ciconia ciconia|Kattohaikarat, Ciconiidae|Lintu\r\n" + 
        "215|Silkkiuikku|Podiceps cristatus|Uikut, Podicipedidae|Lintu\r\n" + 
        "216|Koskikara|Cinclus cinclus|Koskikarat, Cinclidae|Lintu\r\n" + 
        "217|Harjalintu|Upupa epops|Harjalinnut, Upupidae|Lintu\r\n" + 
        "218|Sepelhanhi|Branta bernicla|Sorsat, Anatidae|Lintu\r\n" + 
        "219|Mustatiira|Chlidonias niger|Tiirat, Sternidae|Lintu\r\n" + 
        "220|Tiibetinhanhi|Anser indicus|Sorsat, Anatidae|Lintu\r\n" + 
        "221|Pikkujoutsen|Cygnus columbianus ssp. bewickii|Sorsat, Anatidae|Lintu\r\n" + 
        "222|Pikkulokki|Hydrocoloeus minutus|Lokit, Laridae|Lintu\r\n" + 
        "223|Isosirri|Calidris canutus|Kurpat, Scolopacidae|Lintu\r\n" + 
        "224|Töyhtöhyyppä|Vanellus vanellus|Kurmitsat, Charadriidae|Lintu\r\n" + 
        "225|Mustalintu|Melanitta nigra|Sorsat, Anatidae|Lintu\r\n" + 
        "226|Pulmunen|Plectrophenax nivalis|Pulmuset, Calcariidae|Lintu\r\n" + 
        "227|Vihervarpunen|Spinus spinus|Peipot, Fringillidae|Lintu\r\n" + 
        "228|Räystäspääsky|Delichon urbicum|Pääskyt, Hirundinidae|Lintu\r\n" + 
        "229|Lapinsirkku|Calcarius lapponicus|Pulmuset, Calcariidae|Lintu\r\n" + 
        "230|Palokärki|Dryocopus martius|Tikat, Picidae|Lintu\r\n" + 
        "231|Nuolihaukka|Falco subbuteo|Jalohaukat, Falconidae|Lintu\r\n" + 
        "232|Kuningaskalastaja|Alcedo atthis|Kuningaskalastajat, Alcedinidae|Lintu\r\n" + 
        "233|Valkoselkätikka|Dendrocopos leucotos|Tikat, Picidae|Lintu\r\n" + 
        "234|Huuhkaja|Bubo bubo|Pöllöt, Strigidae|Lintu\r\n" + 
        "235|Kaakkuri|Gavia stellata|Kuikat, Gaviidae|Lintu\r\n" + 
        "236|Tikli|Carduelis carduelis|Peipot, Fringillidae|Lintu\r\n" + 
        "237|Riskilä|Cepphus grylle|Ruokit, Alcidae|Lintu\r\n" + 
        "238|Pikkuruokki|Alle alle|Ruokit, Alcidae|Lintu\r\n" + 
        "239|Pähkinähakki|Nucifraga caryocatactes|Varikset, Corvidae|Lintu\r\n" + 
        "240|Pikkuvarpunen|Passer montanus|Varpuset, Passeridae|Lintu\r\n" + 
        "241|Hippiäisuunilintu|Phylloscopus proregulus|Uunilinnut, Phylloscopidae|Lintu\r\n" + 
        "242|Tukkasotka|Aythya fuligula|Sorsat, Anatidae|Lintu\r\n" + 
        "243|Merihanhi|Anser anser|Sorsat, Anatidae|Lintu\r\n" + 
        "244|Tunturikiuru|Eremophila alpestris|Kiurut, Alaudidae|Lintu\r\n" + 
        "245|Mustakurkku-uikku|Podiceps auritus|Uikut, Podicipedidae|Lintu\r\n" + 
        "246|Ruokki|Alca torda|Ruokit, Alcidae|Lintu\r\n" + 
        "247|Hippiäinen|Regulus regulus|Hippiäiset, Regulidae|Lintu\r\n" + 
        "248|Sarvipöllö|Asio otus|Pöllöt, Strigidae|Lintu\r\n" + 
        "249|Lapinpöllö|Strix nebulosa|Pöllöt, Strigidae|Lintu\r\n" + 
        "250|Helmipöllö|Aegolius funereus|Pöllöt, Strigidae|Lintu\r\n" + 
        "251|Pikkutiira|Sternula albifrons|Tiirat, Sternidae|Lintu\r\n" + 
        "252|Peippo|Fringilla coelebs|Peipot, Fringillidae|Lintu\r\n" + 
        "253|Pikkutikka|Dendrocopos minor|Tikat, Picidae|Lintu\r\n" + 
        "254|Kesykyyhky|Columba livia domestica|Kyyhkyt, Columbidae|Lintu\r\n" + 
        "255|Kirjosieppo|Ficedula hypoleuca|Siepot, Muscicapidae|Lintu\r\n" + 
        "256|Merisirri|Calidris maritima|Kurpat, Scolopacidae|Lintu\r\n" + 
        "257|Viiksitimali|Panurus biarmicus|Kekonokkatimalit, Panuridae|Lintu");
        } catch ( FileNotFoundException ex ) {
            throw new ExceptionHandler("Tiedosto " + ftied.getName() + " ei aukea");
        } catch ( IOException ex ) {
            throw new ExceptionHandler("Tiedoston " + ftied.getName() + " kirjoittamisessa ongelmia");
        }
    }
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws ExceptionHandler Jos jotain menee rikki, heittää kustomoidun exceptionhandlerin
     */
    public static void main(String args[]) throws ExceptionHandler  {
        Bongattavat bongattavat = new Bongattavat();
        Bongattava lintu = new Bongattava();
        lintu.setBongattavaId(1);
        lintu.setNimi("Lintu");
        Bongattava kissa = new Bongattava();
        kissa.setBongattavaId(2);
        kissa.setNimi("Kissa");
        
        try {
            bongattavat.lisaa(lintu);
            bongattavat.lisaa(kissa);
            // TODO: Kuinka iteroidaan ns. virallisesti? Saako tehdä aliohjelman
            // getAlkiot() ja rullata sitä? Miksi pitää tehdä iteraattoreita?!
            for (Integer key : bongattavat.getAlkiot().keySet()) {
                System.out.println(key);
            }
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
  
}

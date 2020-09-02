package bongari;

/**
 * @author Jonne
 * @version 2 Sep 2020
 *
 */
public class ExceptionHandler extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * @param viesti tuotava exception-message
     */
    public ExceptionHandler(String viesti) {
        super(viesti);
    }
}

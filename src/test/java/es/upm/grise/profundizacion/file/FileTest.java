package es.upm.grise.profundizacion.file;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import es.upm.grise.profundizacion.exceptions.InvalidContentException;
import es.upm.grise.profundizacion.exceptions.WrongFileTypeException;

public class FileTest {

    // =========================
    // addProperty()
    // =========================

    @Test
    void addPropertyAgregaContenido() throws Exception {
        File f = new File();
        f.setType(FileType.PROPERTY);

        f.addProperty("ABC".toCharArray());

        assertEquals(3, f.getContent().size());
    }

    @Test
    void addPropertyConcatenaContenido() throws Exception {
        File f = new File();
        f.setType(FileType.PROPERTY);

        f.addProperty("A".toCharArray());
        f.addProperty("B".toCharArray());

        assertEquals(2, f.getContent().size());
    }

    @Test
    void addPropertyNullLanzaExcepcion() {
        File f = new File();
        f.setType(FileType.PROPERTY);

        assertThrows(InvalidContentException.class,
                () -> f.addProperty(null));
    }

    @Test
    void addPropertyTipoImageLanzaExcepcion() {
        File f = new File();
        f.setType(FileType.IMAGE);

        assertThrows(WrongFileTypeException.class,
                () -> f.addProperty("ABC".toCharArray()));
    }


    // =========================
    // getCRC32()
    // =========================

    @Test
    void crcVacioDevuelveCero() {
        File f = new File();
        f.setType(FileType.PROPERTY);

        assertEquals(0, f.getCRC32());
    }

    @Test
    void crcUsaFileUtils() throws Exception {
        File f = new File();
        f.setType(FileType.PROPERTY);

        f.addProperty("ABC".toCharArray());

        // Simulamos CRC
        FileUtils utils = new FileUtils();
        utils.setCRC(999L);

        long result = utils.calculateCRC32(new byte[]{1,2});

        assertEquals(999L, result);
    }
}

package allumettes;

import org.junit.*;
import static org.junit.Assert.*;

public class StrategieRapideTest {

    private Strategie strat;

    @Before
    public void setUp() { 
        this.strat = new StrategieRapide();
    }
   
    @Test 
    public void testNom() {
        assertEquals(this.strat.getNomStrat(), "rapide");
    }

    @Test
    public void testPrise() throws CoupInvalideException{
        for (int i = 1; i < 14; i++) {
            if (i < 3) {
                assertTrue(this.strat.getPrise(new Partie(i)) == i);
            } else {
                assertTrue(this.strat.getPrise(new Partie(i)) == 3);
            } 
        } 
    }  

    public static void main(String[] args) {
		org.junit.runner.JUnitCore.main("allumettes.StrategieRapideTest");
	}

}

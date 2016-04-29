package Game;

import static org.junit.Assert.*;

import org.junit.Test;

public class AirplanePanelTest {

	@Test
	public void testAirplanePanel() {
		AirplanePanel tester = new AirplanePanel("test", "test", 1, 2);
		assertNotNull(tester);
	}
}

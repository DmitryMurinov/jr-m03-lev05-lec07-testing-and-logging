import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {

    @Test
    @Timeout(22L)
    @Disabled
    public void testMain_timeOk() throws Exception {
        Main.main(new String[]{});
    }


}

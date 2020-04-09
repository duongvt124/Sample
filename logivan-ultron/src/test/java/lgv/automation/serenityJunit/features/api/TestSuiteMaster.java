package lgv.automation.serenityJunit.features.api;

import lgv.automation.util.Log;
import lgv.automation.util.api.Config;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class TestSuiteMaster {

    @BeforeClass
    public static void init(){
        Config.init();
        Log.highlight("TestSuiteMaster - Begin Test Suite !");
    }

    @AfterClass
    public static void quitTest() {

        Log.highlight("TestSuiteMaster - Finish Test Suite !");
    }
}

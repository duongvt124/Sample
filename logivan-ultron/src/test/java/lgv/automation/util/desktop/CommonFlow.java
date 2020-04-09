package lgv.automation.util.desktop;

import lgv.automation.util.Log;
import org.openqa.selenium.WebDriver;

public class CommonFlow {

    public static void openBrowser(WebDriver driver, String url) {

        driver.get(url);

        Log.debug("Page title: " + driver.getTitle());
    }

    public static String parseAPIOrderTypeToUIOrderType(String apiOrderType) {

        switch (apiOrderType.toLowerCase()) {

            case "marketplace":
                return "Marketplace order";

            case "corporate":
                return "Corporate order";

            case "original_hybrid":
                return "Logivan Direct order";

            case "converted_hybrid":
                return "Logivan Bidding order";

            case "enterprise":
                return "Enterprise order";

            default:
                Log.error("Format wrong, there are only 5 order types: marketplace | corporate | original_hybrid | converted_hybrid | enterprise");
                return null;
        }
    }
}

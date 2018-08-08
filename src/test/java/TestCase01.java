import com.google.common.base.Joiner;
import org.apache.metamodel.query.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

public class TestCase01 {

  private static Logger LOGGER=LoggerFactory.getLogger("Test");

  @Test(dataProvider = "Data")
  public void case01(SelectItem[] cols, Object[] data){
    String aRow=Joiner.on("|").join(data);
    LOGGER.info(aRow);
  }

  @DataProvider(name = "Data")
  public Object[][] gatherData() throws URISyntaxException {
    return Data.getExcelData( "DP" );
  }
}

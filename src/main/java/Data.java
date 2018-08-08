import org.apache.metamodel.DataContext;
import org.apache.metamodel.DataContextFactory;
import org.apache.metamodel.data.DataSet;
import org.apache.metamodel.data.Row;
import org.apache.metamodel.excel.ExcelConfiguration;
import org.apache.metamodel.query.SelectItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Data {

  private static Logger LOGGER=LoggerFactory.getLogger("Data");

  public static Object[][] getExcelData(String sheetName) throws URISyntaxException {
    URL path=Data.class.getResource("Test.xls");
    File file=new File(path.toURI());
    ExcelConfiguration configuration=new ExcelConfiguration(1,true,false);
    DataContext context=DataContextFactory.createExcelDataContext(file,configuration);
    DataSet dataSet=context.query().from(sheetName).selectAll().where("run").eq("Y").execute();
    List<Row> rows=dataSet.toRows();
    return getRowData(rows);
  }

  private static Object[][] getRowData(List<Row> rows){
    Object[][] myArray=new Object[rows.size()][2];
    SelectItem[] cols=rows.get(0).getSelectItems();
    int i=0;
    for(Row row:rows){
      Object[] data=row.getValues();
      for(int j=0;j<cols.length;j++){
        if(data[j]==null){data[j]="";}
      }
      myArray[i][0]=cols;
      myArray[i][1]=data;
      i++;
    }
    LOGGER.info("Total rows {}",rows.size());
    LOGGER.info("Column Names: {}", Arrays.toString(cols));
    return myArray;
  }
}

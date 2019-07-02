import com.example.Item;
import com.example.FourSquareResponse;
import com.example.Group;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception{

        File excelFile = new File("C:\\Users\\geatalay\\Desktop\\Kayseri_Mahalle.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);

        //we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        //we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(2);

        //we iterate rows
        Iterator<Row> rowIterator = sheet.iterator();

        rowIterator.next();

        File file=new File("C:\\Users\\geatalay\\Desktop\\file.txt");
        FileOutputStream fos=new FileOutputStream(file);
        PrintWriter pw=new PrintWriter(fos);

        while (rowIterator.hasNext()){
            Row row = rowIterator.next();

            //iterate on cells for the current row
            Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell1 = cellIterator.next();
                    String ne = cell1.getStringCellValue();
                    Cell cell2 = cellIterator.next();
                    String sw = cell2.getStringCellValue();
                    System.out.print(cell1.toString()+"\t");
                    System.out.println(cell2.toString());

                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .url("https://api.foursquare.com/v2/venues/explore?ll=38.728072,%2035.483238&ne= "+ne+"&sw= "
                                    +sw+"&llAcc=10000.0&altAcc=10000.0&client_id=P2LSYZYHYSXCSQYZ35CSZ3STLP2QDAMVVAL52VGVWPZMYKLX&client_secret=0NW1MOARKBEKFVYNNOUEU5WTJ5P35MEGVZDAFDZZ2JYWETFJ&v=20190418&query=food&limit=200")
                            .get()
                            .addHeader("cache-control", "no-cache")
                            .addHeader("Postman-Token", "1fd05c8b-80dd-460d-8a8c-a4ed4c3a0a49")
                            .build();

                    Response response = client.newCall(request).execute();

                    System.out.println("limit:" + response.header("x-ratelimit-limit"));
                    System.out.println("remaining:" + response.header("x-ratelimit-remaining"));
                    System.out.println("reset:" + response.header("x-ratelimit-reset"));

                    ObjectMapper mapper = new ObjectMapper();

                    FourSquareResponse fourSquareResponse = mapper.readValue(
                            response.body().string(),
                            new TypeReference<FourSquareResponse>() {});
                        String id="";
                        String name="";
                        Double lat=0.0;
                        Double lon=0.0;



                        List<Group> groups = fourSquareResponse.getResponse().getGroups();

                        if (groups == null) {
                            System.out.println(fourSquareResponse.getMeta().getCode());
                            continue;
                        }

                        for (int i = 0; i < groups.size(); i++) {
                            com.example.Group group = groups.get(i);
                            List<Item> items = group.getItems();
                            for (int j = 0; j < items.size(); j++) {
                                Item item = items.get(j);
                                id = item.getVenue().getId();
                                name = item.getVenue().getName();
                                lat = item.getVenue().getLocation().getLat();
                                lon = item.getVenue().getLocation().getLng();

                                pw.print(ne);
                                pw.print("$"+sw);
                                pw.print("$"+id);
                                pw.print("$"+name);
                                pw.print("$"+lat);
                                pw.print("$"+lon);
                                pw.println(System.getProperty("line.separator"));

                            }

                        }


                     }
                }
        try{
            fis.close();
            pw.close();
            fos.close();
        }catch(Exception e){}
            System.out.println();
            workbook.close();
        }


}

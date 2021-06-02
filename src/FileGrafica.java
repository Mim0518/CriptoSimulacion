import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class FileGrafica {

    public ArrayList<Grafica> obtenerDeArchivo(){
        ArrayList<Grafica> a = new ArrayList<>();
        String nom = "";
        double[] datos = new double[5];
        int cont = 0, contLlenado = 0;
        try
        {
            File file = new File("src/Graficas.xlsx");
            FileInputStream fis = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if(contLlenado != 0){
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                nom = cell.getStringCellValue();
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                datos[cont] = cell.getNumericCellValue();
                                break;
                            default:
                        }
                    }
                    a.add(new Grafica(nom, datos[0]));
                    cont = 0;
                }
                contLlenado++;
            }
        }
        catch(Exception e) { e.printStackTrace(); }
        return a;
    };


}
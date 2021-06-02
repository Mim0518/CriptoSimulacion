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


public class FileRendimientoGraficas {

    public ArrayList<RendimientoGraficas> obtenerDeArchivo(){
        ArrayList<RendimientoGraficas> a = new ArrayList<>();
        String[] nom = new String[10];
        double[] datosF = new double[10];
        int cont = 0, contS = 0, contLlenado = 0;
        try
        {
            File file = new File("src/RendimientoGraficas.xlsx");
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
                                nom[contS] = cell.getStringCellValue();
                                //System.out.print(cell.getStringCellValue() +"  ");
                                contS++;
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                //System.out.print(cell.getNumericCellValue()+"  ");
                                datosF[cont] = cell.getNumericCellValue();
                                cont++;
                                break;
                            default:
                        }
                    }
                    if(datosF[0]!=0){
                        a.add(new RendimientoGraficas(nom[0], nom[1], datosF[0] / 1000000, datosF[1]));
                    }
                    cont = 0;
                    contS = 0;
                }
                contLlenado++;
            }
        }
        catch(Exception e) { e.printStackTrace(); }
        return a;
    };


}
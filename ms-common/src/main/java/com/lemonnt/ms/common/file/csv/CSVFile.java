/**
 * @author : Gavin Li
 * @date   : Oct 17, 2016
 * @version : 1.0
 * @class  :com.cisco.webex.daily.common.csv.CSVUtil
 */
package com.lemonnt.ms.common.file.csv;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.supercsv.io.*;
import org.supercsv.prefs.CsvPreference;
import com.lemonnt.ms.common.bean.AdvancedFile;
import com.lemonnt.ms.common.logger.LoggerFactory;
import com.lemonnt.ms.common.util.Util;


/**
 * @author : Gavin Li
 * @date   : Oct 17, 2016
 * @version : 1.0
 */
public class CSVFile extends AdvancedFile{

    private static final Logger logger = LoggerFactory.getLogger("CSVFile");
    
    private String fileName;
    private String charset = "UTF-8";
    
    public CSVFile(){};
    
    public CSVFile(String fileName){
        this.fileName = fileName;
        this.charset = "UTF-8";
    }
    
    public CSVFile(String fileName,String charset){
        this.fileName = fileName;
        this.charset = charset == null ? "utf-8":charset;
    }
    
    public List<List<String>> csv2List() throws IOException{
        return readCSVFile(fileName);
    }
    
    public List<List<String>> csv2List(String file) throws IOException{
        return readCSVFile(file);
    }
    
    public <T> List<T> csv2JavaObject(Class<T> clazz) throws IOException{
        return csv2Java(fileName, clazz);
    }
    
    public <T> List<T> csv2JavaObject(String file,Class<T> clazz) throws IOException{
        return csv2Java(file, clazz);
    }
    
    public <T> void downLoad(List<T> elemtents,HttpServletResponse response){
        downLoad(elemtents, this.fileName,response);
    }
    
    public <T> void downLoad(List<T> elemtents,String [] headers,String[] contents,HttpServletResponse response){
        downLoad(elemtents,this.fileName, headers, contents, response);
    }
    
    public <T> void downLoad(List<T> elemtents,String fileName,HttpServletResponse response){
        downLoad(elemtents,fileName,buildTableConstruction(elemtents.get(0),true),buildTableConstruction(elemtents.get(0),false),response);
    }
    
    public <T>  File buildCSVFile(List<T> elemtents,String outPutPath,String [] headers,String[] contents){
        return this.buildCSVFile(elemtents, outPutPath, this.fileName, headers, contents);
    }
    
    public <T>  File buildCSVFile(List<T> elemtents,String [] headers,String[] contents){
        if(this.fileName == null || !this.fileName.contains("."))
            throw new RuntimeException("Error file path");
        return this.buildCSVFile(elemtents, prefix(this.fileName), names(this.fileName), headers, contents);
    }

    public <T>  File buildCSVFile(List<T> elemtents){
        if(this.fileName == null || !this.fileName.contains("."))
            throw new RuntimeException("Error file path");
        return this.buildCSVFile(elemtents, prefix(this.fileName), names(this.fileName), buildTableConstruction(elemtents.get(0),true), buildTableConstruction(elemtents.get(0),false));
    }
    
    /**
     * 
     */
    public <T> void downLoad(List<T> elemtents,String fileName,String [] headers,String[] contents,HttpServletResponse response){
        if(null == elemtents || elemtents.isEmpty())
            return;
        ServletOutputStream outputStream = null;
        ICsvBeanWriter writer = null;
        try {           
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename="
                    + fileName+".csv");
            response.setContentType("text/comma-separated-values; charset="+charset);
            StringWriter csvContent = new StringWriter();           
            outputStream = response.getOutputStream();   
            if (null != headers && headers.length > 0) {
                writer = new CsvBeanWriter(csvContent, CsvPreference.EXCEL_PREFERENCE);
                writer.writeHeader(headers);
                for (T blisSkuAmount : elemtents) {
                    writer.write(blisSkuAmount, contents);
                }
                writer.flush();
            }
            outputStream.write(csvContent.toString().getBytes(charset));
            outputStream.flush();

        } catch (Exception e) {
            logger.warning("Failed to downLoad []");
        } finally {
            try {
                if (outputStream != null) {
                   outputStream.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                logger.warning("Failed to closeStream []");
            }
        }
    }
    

    public <T>  File buildCSVFile(List<T> elemtents,String outPutPath,String fileName,String [] headers,String[] contents){
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        ICsvBeanWriter writer = null;
        try {  
            File file = new File(outPutPath);
            if (!file.exists()) {
                file.mkdir();
            }           
            csvFile = new File(outPutPath+"/"+fileName+".csv");
            csvFile.createNewFile();
            logger.info("csvFile : " + csvFile);
            csvFileOutputStream =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile),
                            charset), 1024);
            StringWriter csvContent = new StringWriter();                  
            if (null != headers && headers.length > 0) {
                writer = new CsvBeanWriter(csvContent, CsvPreference.EXCEL_PREFERENCE);
                writer.writeHeader(headers);
                for (T blisSkuAmount : elemtents) {
                    writer.write(blisSkuAmount, contents);
                }
                writer.flush();
            }            
            csvFileOutputStream.write(csvContent.toString());
            csvFileOutputStream.flush();
            return csvFile;
        } catch (Exception e) {
            e.printStackTrace();
            logger.warning("Failed to build file []");
        } finally {
            try {
                if (csvFileOutputStream != null) 
                    csvFileOutputStream.close();
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                logger.warning("Failed to closeStream []");
            }
        }
        return null;
    }

    /**
     * if <isHeader>true</isHeader> build the header ,on the contrary building the field
     * if the annotation for sort is default value that's 0 ,then it was sorted by field's order 
     */
    private static <T> String[] buildTableConstruction(T t,boolean isHeader){
        List<String> allFields = new ArrayList<String>();
        Field[] fields = t.getClass().getDeclaredFields();
        Map<Integer, String> sortFields = new HashMap<Integer, String>();
        boolean isSort = false;
        for(Field field : fields){            
            CSVHeader csvHeader = field.getAnnotation(CSVHeader.class);    
            if(null == csvHeader) continue;
            String value = csvHeader.value();
            if(value != null && !"".equals(value)){
                Integer sort = csvHeader.sort();
                if(sort != 0){
                    if(isHeader){
                        sortFields.put(sort, value);
                    }else{
                        sortFields.put(sort, field.getName()); 
                    }
                    isSort = true;
                }else{
                    if(isHeader){
                        allFields.add(value); 
                    }else{
                        allFields.add(field.getName()); 
                    }
                      
                }
                
            }
                
        }
        if(isSort){
            allFields = new ArrayList<String>();
            for(int i = 1,length = sortFields.size();i <= length;i++){
                String value = sortFields.get(i);
                if(null == value) continue;
                allFields.add(sortFields.get(i));
            }
        }
       String[] result= allFields.toArray(new String[allFields.size()]);
       return result;
    }
    
    private Map<Integer, Field> obtainCSVObjectField(Class<?> clazz){
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, Field> sortMapField = new HashMap<Integer, Field>();
        for(Integer i = 0,length = fields.length-1;i <= length;i++){
            Field field = fields[i];
            CSVField csvField = field.getAnnotation(CSVField.class);    
            if(null == csvField) continue;
              Integer sort = csvField.sort();
              if(sort != 0){
                 sortMapField.put(sort, field);
              }else{
                 sortMapField.put(i+1, field);
              }
        }
        return sortMapField;
    }
    
    private <T> List<T> csv2Java(String file,Class<T> clazz) throws IOException {  
        if(Util.isEmpty(file)) return new ArrayList<T>();
        if(!file.endsWith(".csv")) return new ArrayList<T>();
        InputStreamReader fr = new InputStreamReader(new FileInputStream(file)); 
        BufferedReader br = new BufferedReader(fr);  
        String rec = null;
        String str;
        List<T> tObject = new ArrayList<T>();  
        try {  
            Map<Integer,Field> fields = obtainCSVObjectField(clazz);
            if(fields.isEmpty()) return new ArrayList<T>();
            while ((rec = br.readLine()) != null) {  
                rec = rec.endsWith(",") ? rec:rec+",";
                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");  
                Matcher mCells = pCells.matcher(rec);  
                Integer count = 1;
                T t = clazz.newInstance();
                while (mCells.find()) {  
                    str = mCells.group().replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");  
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");
                    if(!fields.containsKey(count)){
                        count++;
                        continue;                    
                    }
                    Field field = fields.get(count);
                    field.setAccessible(true);
                    field.set(t, str);
                    field.setAccessible(false);
                    count++;
                }  
                tObject.add(t);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();
            logger.warning("Failed to parse csv file");
        } finally {  
            if (fr != null) fr.close();  
            if (br != null) br.close();   
        }  
        return tObject;  
    }  
    
    private List<List<String>> readCSVFile(String file) throws IOException {  
        if(Util.isEmpty(file)) return new ArrayList<List<String>>();
        if(!file.endsWith(".csv")) return new ArrayList<List<String>>();
        InputStreamReader fr = new InputStreamReader(new FileInputStream(file)); 
        BufferedReader br = new BufferedReader(fr);  
        String rec = null;
        String str;
        List<List<String>> listFile = new ArrayList<List<String>>();  
        try {  
            while ((rec = br.readLine()) != null) {  
                rec = rec.endsWith(",") ? rec:rec+",";
                Pattern pCells = Pattern.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");  
                Matcher mCells = pCells.matcher(rec);  
                List<String> cells = new ArrayList<String>();
                while (mCells.find()) {  
                    str = mCells.group().replaceAll("(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");  
                    str = str.replaceAll("(?sm)(\"(\"))", "$2");  
                    cells.add(str);  
                }  
                listFile.add(cells);  
            }  
        } catch (Exception e) {  
            logger.warning("Failed to parse csv file");
        } finally {  
            if (fr != null) fr.close();  
            if (br != null) br.close();   
        }  
        return listFile;  
    } 
    

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the charset
     */
    public String getCharset() {
        return charset;
    }

    /**
     * @param charset the charset to set
     */
    public void setCharset(String charset) {
        this.charset = charset;
    }
    

}

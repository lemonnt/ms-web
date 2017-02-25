package com.lemonnt.ms.common.bean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lemonnt.ms.common.exception.RunFileException;
import com.lemonnt.ms.common.logger.LoggerFactory;

import static com.lemonnt.ms.common.util.Util.*;

public class AdvancedFile implements Constant{
    
    private static Logger logger = LoggerFactory.getLogger("AdvancedFile");

	public List<File> scanFiles(String filePath, boolean isRecursive, String fileSuffix) {
	    if(isEmpty(filePath))
	        throw new RunFileException("File path is null");
		List<File> resulst = new ArrayList<File>();
		File file = new File(filePath);
		if(!file.isDirectory())
		    throw new RunFileException("Error file path []");
		File[] files = file.listFiles();
		for (File f : files) {
			try {
				if (f.isFile()) {
					if (fileSuffix.equals(suffix(f.getAbsolutePath())))
						resulst.add(f);
				} else {
					if (isRecursive) {
						resulst.addAll(scanFiles(f.getAbsolutePath(), isRecursive, fileSuffix));
					}
				}
			} catch (Exception e) {
				logger.warning("Failed check files ["+e.getMessage() == null ? "unknow reason" : e.getMessage()+"]");
			}

		}
		return resulst;
	}

	public void write(String str, String filePath, boolean flag) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filePath, flag);
			int length = 0;
			char[] buffer = new char[BUFFER];
			BufferedReader br = new BufferedReader(new StringReader(str));
			while ((length = br.read(buffer)) != -1) {
				fos.write(new String(buffer, 0, length).getBytes());
			}

		} catch (Exception e) {
			throw new RunFileException("Failed to write file to ["+filePath+"]",e);
		} finally {
			try {
				if (null != fos) {
					fos.close();
				}
			} catch (IOException e) {
				throw new RunFileException("Failed to close stream");
			}

		}
	}

	public StringBuffer read(String filePath) throws Exception {
		if (isEmpty(filePath))
			throw new Exception("The file is not exist");
		StringBuffer result = new StringBuffer(1024 * 20);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				result.append(tempString+"\n");
			}
			reader.close();
		} catch (IOException e) {
			throw new RunFileException("Failed to read file to ["+filePath+"]");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					throw new RunFileException("Failed to close stream");
				}
			}
		}
		return result;
	}
	
	public List<String> readLine(String filePath) throws Exception {
	    if (isEmpty(filePath))
            throw new Exception("The file is not exist");
		List<String> result = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				result.add(tempString);
			}
			reader.close();
		} catch (IOException e) {
			throw new RunFileException("Failed to read file to ["+filePath+"]");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					throw new RunFileException("Failed to close stream");
				}
			}
		}
		return result;
	}
	
	public String prefix(String filePath) {
		filePath = formatFilePath(filePath);
		return filePath.substring(0, filePath.lastIndexOf(LEFT_SLASH) + 1);
	}

	public String name(String filePath) {
		filePath = formatFilePath(filePath);
		return filePath.substring(filePath.lastIndexOf(LEFT_SLASH) + 1);
	}

	public String suffix(String filePath) {
		if (!filePath.contains(DOT))
			return "";
		filePath = name(filePath);
		return filePath.substring(filePath.lastIndexOf(DOT) + 1, filePath.length());
	}

	protected String names(String filePath) {
	    if (!filePath.contains(DOT))
            return "";
		filePath = name(filePath);
		return filePath.substring(0, filePath.lastIndexOf(DOT));
	}

	protected String formatFilePath(String filePathWasFormated) {
	    if(isEmpty(filePathWasFormated)) return "";
		if (filePathWasFormated.contains(RIGHT_SLASH))
			filePathWasFormated = filePathWasFormated.replaceAll(RIGHT_SLASH + RIGHT_SLASH, LEFT_SLASH);
		if (filePathWasFormated.endsWith(LEFT_SLASH))
			filePathWasFormated = filePathWasFormated.substring(0, filePathWasFormated.lastIndexOf(LEFT_SLASH));
		return filePathWasFormated;
	
	}
	
	protected void delete(File file){
		if(null == file)
			return;
		file.deleteOnExit();
	}
	
	protected void delete(String file){
		if(null == file)
			return;
		new File(file).deleteOnExit();
	}
	
	protected String obtainCurrentPath(int count) {
		String filePath = System.getProperty("user.dir");
		if (null != filePath && !"".equals(filePath))
			for (int i = 0; i <= count; i++) {
				filePath = filePath.substring(0, filePath.lastIndexOf("\\"));
			}
		return filePath;
	}
	
	public static void main(String[] args) {
        System.out.println(new AdvancedFile().prefix("c:\\test\\file\\test.csv"));
        System.out.println(new AdvancedFile().names("c:\\test\\file\\test.csv"));
    }
}



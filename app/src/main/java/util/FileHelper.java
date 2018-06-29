package util;

import android.util.Log;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

public class FileHelper {
	
	/**
	 * 文件是否存在
	 * @param filePath
	 * @return
	 */
	public static Boolean Exists(String filePath){
		File f = new File(filePath);
		return f.exists();
	}
	
	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static Boolean Delete(String filePath){
		File f = new File(filePath);
		return f.delete();
	}
	
	/**
	 * 创建文件
	 * @param filePath
	 * @return
	 */
	public static Boolean CreateFile(String filePath){
		File f = new File(filePath);
		File pf = f.getParentFile();
		if (!pf.exists()) {
			pf.mkdirs();
		}
		try {
			f.createNewFile();
			return true;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 写入文本文件
	 * @param filePath
	 * @param content
	 * @param append
	 * @return
	 */
	public static Boolean WriteText(String filePath,String content,Boolean append){
		if (!Exists(filePath)){
			CreateFile(filePath);
		}
		FileWriter fw = null;
        BufferedWriter bw = null;
        try {        	
            fw = new FileWriter(filePath,append);
            // 创建FileWriter对象，用来写入字符流  
            bw = new BufferedWriter(fw); // 将缓冲对文件的输出
            bw.write(content); // 写入文件
            bw.flush(); // 刷新该流的缓冲 
            bw.close();
            fw.close();  
            return true;
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            try {  
                bw.close();  
                fw.close();  
            } catch (IOException e1) {  
                // TODO Auto-generated catch block  
            }  
        }  
        return false;
	}
	
	/**
	 * 读取文本文件
	 * @param filePath
	 * @param def
	 * @return
	 */
	public static String ReadText(String filePath,String def){
		FileInputStream fis = null;
		try
		{
			File file = new File(filePath);
	        fis = new FileInputStream(file);
	        int length = fis.available();
	        byte [] buffer = new byte[length];   
	        fis.read(buffer);
	        String res = EncodingUtils.getString(buffer, "UTF-8");
	        fis.close();
	        return res;    
		}
		catch(Exception ex){
			try
			{
				fis.close();
			}catch(Exception ex1){}
		}
		return def;
	}
	
	/**
	 * 解压文件
	 * @param sourcePath zip文件路径
	 * @param targetPath 解压后文件路径
	 */
	@SuppressWarnings("rawtypes")
	public static void releaseZip(String sourcePath,String targetPath) throws IOException{
		ZipFile zFile = new ZipFile(sourcePath);
		OutputStream os = null;
		InputStream is = null;
		try {
			Enumeration zlist = zFile.entries();
			ZipEntry ze = null;
			byte[] buf = new byte[1024];
			while (zlist.hasMoreElements()) {
				ze = (ZipEntry) zlist.nextElement();
				if (ze.isDirectory())
					continue;
			}

			// 以ZipEntry为参数得到一个InputStream，并写到OutputStream中
			os = new BufferedOutputStream(new FileOutputStream(targetPath));
			is = new BufferedInputStream(zFile.getInputStream(ze));
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
			zFile.close();
		} catch (Exception e) {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			zFile.close();
			throw new IOException(e.getMessage());
		}
	}
	
	/**
	    * 解压缩功能.
	    * 将zipFile文件解压到folderPath目录下.
	    * @throws Exception
	*/
	    @SuppressWarnings("rawtypes")
		public static int upZipFile(String zipFile, String folderPath)throws ZipException,IOException {	    	
	        ZipFile zfile=new ZipFile(zipFile);
	        Enumeration zList=zfile.entries();
	        ZipEntry ze=null;
	        byte[] buf=new byte[1024];
	        while(zList.hasMoreElements()){
	            ze=(ZipEntry)zList.nextElement();    
	            if(ze.isDirectory()){
	                Log.d("upZipFile", "ze.getName() = "+ze.getName());
	                String dirstr = folderPath + ze.getName();
	                //dirstr.trim();
	                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
	                Log.d("upZipFile", "str = "+dirstr);
	                File f=new File(dirstr);
	                f.mkdir();
	                continue;
	            }
	            Log.d("upZipFile", "ze.getName() = "+ze.getName());
	            OutputStream os=new BufferedOutputStream(new FileOutputStream(getRealFileName(folderPath, ze.getName())));
	            InputStream is=new BufferedInputStream(zfile.getInputStream(ze));
	            int readLen=0;
	            while ((readLen=is.read(buf, 0, 1024))!=-1) {
	                os.write(buf, 0, readLen);
	            }
	            is.close();
	            os.close();    
	        }
	        zfile.close();
	        Log.d("upZipFile", "finishssssssssssssssssssss");
	        return 0;
	    }

	    /**
	    * 给定根目录，返回一个相对路径所对应的实际文件名.
	    * @param baseDir 指定根目录
	    * @param absFileName 相对路径名，来自于ZipEntry中的name
	    * @return java.io.File 实际的文件
	*/
	    public static File getRealFileName(String baseDir, String absFileName){
	    	String[] dirs=absFileName.split("/"); 
	    	String lastDir=baseDir;
	    	if(dirs.length>1){ 
	    	for (int i = 0; i < dirs.length-1;i++) { 
	    	lastDir +=(dirs[i]+"/");
	    	File dir =new File(lastDir); 
	    	if(!dir.exists())
	    	{
	    	dir.mkdirs();
	    	Log.d("getRealFileName", "create dir = "+(lastDir+"/"+dirs[i]));
	    	}
	    	} 
	    	File ret = new File(lastDir,dirs[dirs.length-1]);
	    	Log.d("upZipFile", "2ret = "+ret);
	    	return ret;
	    	}
	    	else {
	    	return new File(baseDir,absFileName); 
	    	}

	    	}
}

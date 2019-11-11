package top.pxyz.util;

import org.apache.poi.poifs.filesystem.FileMagic;

import java.io.InputStream;

/**
 * POI工具类
 * @author Ijiran
 * @Package top.pxyz.util
 * @date 2019-11-09 21:21
 */
public class POIUtils {

    /**
     * 检测文件类型
     * @param is
     * @return
     */
    private static String getDtd(InputStream is){
        String dtd = "";
        if(getFileType(is) == FileMagic.OLE2){
            dtd = "doc";
        }else if(getFileType(is) == FileMagic.OOXML){
            dtd = "docx";
        }else if(getFileType(is) == FileMagic.BIFF2){
            dtd = "xls";
        }else if(getFileType(is) == FileMagic.BIFF3){
            dtd = "xls";
        }else if(getFileType(is) == FileMagic.BIFF4){
            dtd = "xls";
        }else if(getFileType(is) == FileMagic.GIF){
            dtd = "gif";
        }else if(getFileType(is) == FileMagic.HTML){
            dtd = "html";
        }else if(getFileType(is) == FileMagic.JPEG){
            dtd = "jpeg";
        }else if(getFileType(is) == FileMagic.PNG){
            dtd = "png";
        }else if(getFileType(is) == FileMagic.XML){
            dtd = "xml";
        }else if(getFileType(is) == FileMagic.TIFF){
            dtd = "tiff";
        }else if(getFileType(is) == FileMagic.PDF){
            dtd = "pdf";
        }else if(getFileType(is) == FileMagic.RTF){
            dtd = "rtf";
        }else if(getFileType(is) == FileMagic.UNKNOWN){
            dtd = "un know dtd";
        }
        return dtd;
    }

    /**
     * 获取文件类型
     * @param is
     * @return
     */
    public static FileMagic getFileType(InputStream is){
        try {
            return FileMagic.valueOf(FileMagic.prepareToCheckMagic(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

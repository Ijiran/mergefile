package top.pxyz.util;

import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;

import java.io.InputStream;
import java.math.BigInteger;

/**
 * POI工具类
 *
 * @author Ijiran
 * @Package top.pxyz.util
 * @date 2019-11-09 21:21
 */
public class POIUtils {

    /**
     * 插入分页符
     *
     * @param document
     */
    public static void insertPageBreak(XWPFDocument document) {
        XWPFParagraph p = document.createParagraph();
        p.setPageBreak(true);
    }

    /**
     * 插入空行
     *
     * @param document XWPFDocument
     */
    public static void insertBr(XWPFDocument document) {
        XWPFParagraph p = document.createParagraph();
        setParagraphSpacingInfo(p, true, "0", "0", "0", "0", true, "240", STLineSpacingRule.EXACT);
    }

    /**
     * 设置段落间距信息,一行=100 一磅=20
     *
     * @param p           XWPFParagraph
     * @param isSpace     空格
     * @param before      段前磅数
     * @param after       段后磅数
     * @param beforeLines 段前行数
     * @param afterLines  段后行数
     * @param isLine      间距
     * @param line
     * @param lineValue
     */
    public static void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,
                                               String before, String after, String beforeLines, String afterLines,
                                               boolean isLine, String line, STLineSpacingRule.Enum lineValue) {
        CTPPr pPPr = getParagraphCTPPr(p);
        CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
                : pPPr.addNewSpacing();
        if (isSpace) {
            // 段前磅数
            if (before != null) {
                pSpacing.setBefore(new BigInteger(before));
            }
            // 段后磅数
            if (after != null) {
                pSpacing.setAfter(new BigInteger(after));
            }
            // 段前行数
            if (beforeLines != null) {
                pSpacing.setBeforeLines(new BigInteger(beforeLines));
            }
            // 段后行数
            if (afterLines != null) {
                pSpacing.setAfterLines(new BigInteger(afterLines));
            }
        }
        // 间距
        if (isLine) {
            if (line != null) {
                pSpacing.setLine(new BigInteger(line));
            }
            if (lineValue != null) {
                pSpacing.setLineRule(lineValue);
            }
        }
    }

    /**
     * 得到段落CTPPr
     *
     * @param p XWPFParagraph
     * @return
     */
    public static CTPPr getParagraphCTPPr(XWPFParagraph p) {
        CTPPr pPPr = null;
        if (p.getCTP() != null) {
            if (p.getCTP().getPPr() != null) {
                pPPr = p.getCTP().getPPr();
            } else {
                pPPr = p.getCTP().addNewPPr();
            }
        }
        return pPPr;
    }


    /**
     * 检测文件类型
     *
     * @param is
     * @return
     */
    public static String getDtd(InputStream is) {
        String dtd = "";
        if (getFileType(is) == FileMagic.OLE2) {
            dtd = "doc";
        } else if (getFileType(is) == FileMagic.OOXML) {
            dtd = "docx";
        } else if (getFileType(is) == FileMagic.BIFF2) {
            dtd = "xls";
        } else if (getFileType(is) == FileMagic.BIFF3) {
            dtd = "xls";
        } else if (getFileType(is) == FileMagic.BIFF4) {
            dtd = "xls";
        } else if (getFileType(is) == FileMagic.GIF) {
            dtd = "gif";
        } else if (getFileType(is) == FileMagic.HTML) {
            dtd = "html";
        } else if (getFileType(is) == FileMagic.JPEG) {
            dtd = "jpeg";
        } else if (getFileType(is) == FileMagic.PNG) {
            dtd = "png";
        } else if (getFileType(is) == FileMagic.XML) {
            dtd = "xml";
        } else if (getFileType(is) == FileMagic.TIFF) {
            dtd = "tiff";
        } else if (getFileType(is) == FileMagic.PDF) {
            dtd = "pdf";
        } else if (getFileType(is) == FileMagic.RTF) {
            dtd = "rtf";
        } else if (getFileType(is) == FileMagic.UNKNOWN) {
            dtd = "un know dtd";
        }
        return dtd;
    }

    /**
     * 获取文件类型
     *
     * @param is
     * @return
     */
    private static FileMagic getFileType(InputStream is) {
        try {
            return FileMagic.valueOf(FileMagic.prepareToCheckMagic(is));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

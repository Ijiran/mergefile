package top.pxyz.util;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * word文档功能类
 * @author Ijiran
 * @Package top.pxyz.util
 * @date 2019-11-07 22:28
 */
public class WordUtils {

    /**
     * word合并
     * @param is1
     * @param is2
     */
    public static void merge(InputStream is1, InputStream is2) throws IOException {
        String type1 = POIUtils.getDtd(is1);
        String type2 = POIUtils.getDtd(is2);
        if("doc".equals(type1)&&"doc".equals(type2)){
            HWPFDocument d1 = new HWPFDocument(is1);
            HWPFDocument d2 = new HWPFDocument(is2);
            docMergeDoc(d1,d2);
        }else if("docx".equals(type1)&&"docx".equals(type2)){
            XWPFDocument d1 = new XWPFDocument(is1);
            XWPFDocument d2 = new XWPFDocument(is2);
            docxMergeDocx(d1,d2);
        }else if("doc".equals(type1)&&"docx".equals(type2)){
            HWPFDocument d1 = new HWPFDocument(is1);
            XWPFDocument d2 = new XWPFDocument(is2);
            docMergeDocx(d1,d2);
        }else if("docx".equals(type1)&&"doc".equals(type2)){
            XWPFDocument d1 = new XWPFDocument(is1);
            HWPFDocument d2 = new HWPFDocument(is2);
            docxMergeDoc(d1,d2);
        }
    }

    /**
     * doc合并doc
     * @param doc1
     * @param doc2
     */
    public static void docMergeDoc(HWPFDocument doc1, HWPFDocument doc2){
//        List<HWPF>
    }

    /**
     * doc合并docx
     * @param d1
     * @param d2
     */
    public static void docMergeDocx(HWPFDocument d1, XWPFDocument d2){

    }

    /**
     * docx合并docx
     * @param docx1
     * @param docx2
     */
    public static void docxMergeDocx(XWPFDocument docx1, XWPFDocument docx2){
        List<XWPFParagraph> docx1Paragraphs = docx1.getParagraphs();
        List<XWPFParagraph> docx2Paragraphs = docx2.getParagraphs();
        for (int i = 0; i < docx1Paragraphs.size(); i++){
            XWPFParagraph docx1Paragraph = docx1Paragraphs.get(i);
//            XWPFParagraph docx2Paragraph = docx2Paragraphs.get(i);
            List<XWPFRun> xwpfRuns = docx1Paragraph.getRuns();

        }
    }

    /**
     * docx合并doc
     * @param d1
     * @param d2
     */
    public static void docxMergeDoc(XWPFDocument d1, HWPFDocument d2){

    }

}

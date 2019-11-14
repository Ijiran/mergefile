package top.pxyz.check.service.impl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import top.pxyz.check.service.ICheckService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文档效验Service实现类
 * @author Ijiran
 * @Package top.pxyz.check.service.impl
 * @date 2019-11-07 21:45
 */
@Service
public class CheckServiceImpl implements ICheckService {

    /**
     * doc文档测试
     * @param is
     */
    @Override
    public void checkDocTest(InputStream is){

    }

    /**
     * docx文档测试
     * @param is
     */
    @Override
    public void checkDocxTest(InputStream is) throws IOException {
        XWPFDocument xwpfDocument = new XWPFDocument(is);
        List<XWPFParagraph> docx1Paragraphs = xwpfDocument.getParagraphs();
        for (int i = 0; i < docx1Paragraphs.size(); i++){
            XWPFParagraph docx1Paragraph = docx1Paragraphs.get(i);
            List<XWPFRun> xwpfRuns = docx1Paragraph.getRuns();
            System.out.println(xwpfRuns.get(0).getStyle());
        }
    }

}

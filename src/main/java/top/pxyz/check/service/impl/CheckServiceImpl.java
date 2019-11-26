package top.pxyz.check.service.impl;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;
import org.springframework.stereotype.Service;
import top.pxyz.check.service.ICheckService;
import top.pxyz.util.ObjectUtils;

import java.io.*;
import java.math.BigInteger;
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
    public void checkDocxTest(InputStream is) throws Exception {
        //写入新的docx文档中
        XWPFDocument newDocument = new XWPFDocument();
        FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Ijiran\\Desktop\\7.docx"));
        XWPFDocument xwpfDocument = new XWPFDocument(is);

        List<XWPFParagraph> docx1Paragraphs = xwpfDocument.getParagraphs();
        for (int i = 0; i < docx1Paragraphs.size(); i++){
            XWPFParagraph docx1Paragraph = docx1Paragraphs.get(i);

            XWPFParagraph newParagraph = newDocument.createParagraph();
            List<XWPFRun> xwpfRuns = docx1Paragraph.getRuns();

            //因为序号没有真正存储到word的xml中，所以单独做处理。
            if(docx1Paragraph.getNumID()!=null){
                CTNumbering cTNumbering = CTNumbering.Factory.parse(getNumberingXMLString(16));
                CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);
                //Next we set the AbstractNumId. This requires care.
                //Since we are in a new document we can start numbering from 0.
                //But if we have an existing document, we must determine the next free number first.
                cTAbstractNum.setAbstractNumId(BigInteger.valueOf(0));
                /* Bullet list
                  CTLvl cTLvl = cTAbstractNum.addNewLvl();
                  cTLvl.addNewNumFmt().setVal(STNumberFormat.BULLET);
                  cTLvl.addNewLvlText().setVal("•");
                */
                ///* Decimal list
                CTLvl cTLvl = cTAbstractNum.addNewLvl();
                cTLvl.addNewNumFmt().setVal(STNumberFormat.Enum.forString(docx1Paragraph.getNumFmt()));
                cTLvl.addNewLvlText().setVal(docx1Paragraph.getNumLevelText());
                cTLvl.addNewStart().setVal(BigInteger.valueOf(docx1Paragraph.getNumID().bitCount()));
                //这里存在疑问，通过查看源码，得知可通过CTRPr类设置sz和szcs，可用作字号设置。
                //CTRPr是行级使用的，需要确认段落级是否存在。
//                cTLvl.getRPr().addNewSz().setVal(BigInteger.valueOf(18));
//                cTLvl.getRPr().addNewSzCs().setVal(BigInteger.valueOf(18));
//                newParagraph.getCTP().;

                XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
                XWPFNumbering numbering = newDocument.createNumbering();
                BigInteger abstractNumID = numbering.addAbstractNum(abstractNum);
                BigInteger numID = numbering.addNum(abstractNumID);
                //*/
                newParagraph.setNumID(numID);//序号id
            }

            /*//当段落无文本时，给段落设置原始样式
            if(xwpfRuns==null||xwpfRuns.size()==0){
                XWPFRun r1 = newParagraph.createRun();
                r1.setFontSize(16);
                CTRPr rpr = r1.getCTR().isSetRPr() ? r1.getCTR().getRPr() : r1.getCTR().addNewRPr();
            }*/

            newParagraph.setAlignment(docx1Paragraph.getAlignment());//段落对齐
            newParagraph.setFirstLineIndent(docx1Paragraph.getFirstLineIndent());//段落第一行缩进
            newParagraph.setFontAlignment(docx1Paragraph.getFontAlignment());//段落字体对齐
//            newParagraph.setIndentationFirstLine(docx1Paragraph.getIndentationFirstLine());
            newParagraph.setSpacingAfter(docx1Paragraph.getSpacingAfter());
//            newParagraph.setSpacingAfterLines(docx1Paragraph.getSpacingAfterLines());
            newParagraph.setSpacingBefore(docx1Paragraph.getSpacingBefore());
//            newParagraph.setSpacingBeforeLines(docx1Paragraph.getSpacingBeforeLines());
//            newParagraph.setIndentationHanging(docx1Paragraph.getIndentationHanging());
//            newParagraph.setIndentationLeft(docx1Paragraph.getIndentationLeft());
//            newParagraph.setIndentationRight(docx1Paragraph.getIndentationRight());
//            newParagraph.setIndentFromLeft(docx1Paragraph.getIndentFromLeft());
//            newParagraph.setIndentFromRight(docx1Paragraph.getIndentFromRight());
            newParagraph.setKeepNext(docx1Paragraph.isKeepNext());
            newParagraph.setSpacingBetween(docx1Paragraph.getSpacingBetween());//间距
            newParagraph.setPageBreak(docx1Paragraph.isPageBreak());//是否分页符
            newParagraph.setSpacingLineRule(docx1Paragraph.getSpacingLineRule());//间距线规则
            newParagraph.setVerticalAlignment(docx1Paragraph.getVerticalAlignment());//垂直对齐
//            newParagraph.setWordWrapped(docx1Paragraph.isWordWrapped());

            //设置标题
            newParagraph.setStyle("Heading2");

            /*System.out.println("段落XML-getCTP："+docx1Paragraph.getCTP());
            System.out.println("段落样式-getStyle："+docx1Paragraph.getStyle());
            System.out.println("脚注文本-getFootnoteText："+docx1Paragraph.getFootnoteText());
            System.out.println("序号格式-getNumFmt："+docx1Paragraph.getNumFmt());
            System.out.println("序号等级-getNumIlvl："+docx1Paragraph.getNumIlvl());
            System.out.println("序号数值-getNumID："+docx1Paragraph.getNumID());
            System.out.println("目前不清楚-getNumStartOverride："+docx1Paragraph.getNumStartOverride());
            System.out.println("序号等级文本-getNumLevelText："+docx1Paragraph.getNumLevelText());
            System.out.println("段落样式ID-getStyleID："+docx1Paragraph.getStyleID());
            System.out.println("段落对齐-getAlignment："+docx1Paragraph.getAlignment());
            System.out.println("段落右侧边框-getBorderRight："+docx1Paragraph.getBorderRight());
            System.out.println("段落第一行缩进-getFirstLineIndent："+docx1Paragraph.getFirstLineIndent());
            System.out.println("段落字体对齐-getFontAlignment："+docx1Paragraph.getFontAlignment());
            System.out.println("段落间距-getSpacingAfter："+docx1Paragraph.getSpacingAfter());
            System.out.println("缩进第一行-getIndentationFirstLine："+docx1Paragraph.getIndentationFirstLine());
            System.out.println("行后间距-getSpacingAfterLines："+docx1Paragraph.getSpacingAfterLines());
            System.out.println("行前间距-getSpacingBeforeLines："+docx1Paragraph.getSpacingBeforeLines());
            System.out.println("目前不清楚-isKeepNext："+docx1Paragraph.isKeepNext());
            System.out.println("分页符-isPageBreak："+docx1Paragraph.isPageBreak());*/

            for (XWPFRun run : xwpfRuns){

                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setColor(run.getColor());
                newXwpfrun.setBold(run.isBold());
                newXwpfrun.setCapitalized(run.isCapitalized());
                newXwpfrun.setFontFamily(run.getFontFamily());
                newXwpfrun.setFontSize(run.getFontSize());
                newXwpfrun.setText(run.getText(0));

                /*System.out.println("字体颜色-getColor："+run.getColor());
                System.out.println("XML格式-getCTR："+run.getCTR());
                System.out.println("字符间距-getCharacterSpacing："+run.getCharacterSpacing());
                System.out.println("字体类型-getFontFamily："+run.getFontFamily());
                System.out.println("字体名称-getFontName："+run.getFontName());
                System.out.println("字号-getFontSize："+run.getFontSize());
                System.out.println("图片文字-getPictureText："+run.getPictureText());
                System.out.println("下划线-getUnderline："+run.getUnderline());
                System.out.println("下划线颜色-getUnderlineColor："+run.getUnderlineColor());
                System.out.println("语音-getPhonetic："+run.getPhonetic());
                System.out.println("文字比例-getTextScale："+run.getTextScale());
                System.out.println("文字位置-getTextPosition："+run.getTextPosition());
                System.out.println("目前不清楚-getKerning："+run.getKerning());
                System.out.println("是否粗体-isBold："+run.isBold());
                System.out.println("是否大写-isCapitalized："+run.isCapitalized());
                System.out.println("文本-getText："+run.getText(0));*/
            }
        }
        newDocument.write(out);
        out.close();

        System.out.println("success");
    }

    private String getNumberingXMLString(int fontSize) {
        return  "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"0\">"
                + "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
                + "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.\"/><w:lvlJc w:val=\"left\"/>"
                + "<w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr><w:rPr><w:sz w:val=\"" + fontSize*2 +"\"/><w:szCs w:val=\""+fontSize*2 +"\"/></w:rPr></w:lvl>"
                + "</w:abstractNum>";
    }

}

package top.pxyz.merge.service.impl;

import ch.qos.logback.core.util.FileUtil;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.springframework.stereotype.Service;
import top.pxyz.merge.mapper.IMergeMapper;
import top.pxyz.merge.service.IMergeService;
import top.pxyz.util.POIUtils;
import top.pxyz.util.WordUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.service.impl
 * @date 2019-11-07 22:12
 */
@Service
public class MergeServiceImpl implements IMergeService {

    @Resource
    private IMergeMapper mergeMapper;

    public List<Map<String, String>> findFiles(){
        List<Map<String,String>> list = mergeMapper.findFiles();
        return list;
    }

    /**
     * 测试两个文档合并
     * @param source
     * @param target
     */
    public void mergeTest(InputStream source, InputStream target) throws Exception{
        //将source流合并到target流中
        XWPFDocument sourceD = new XWPFDocument(source);
        XWPFDocument targetD = new XWPFDocument(target);

        //先在合并前创建一个分页符，以便隔开
        POIUtils.insertPageBreak(targetD);

        //开始合并
        List<XWPFParagraph> sourceDParagraphs = sourceD.getParagraphs();
        for (int i = 0; i < sourceDParagraphs.size(); i++){
            XWPFParagraph sourceParagraph = sourceDParagraphs.get(i);
            XWPFParagraph newParagraph = targetD.createParagraph();
            List<XWPFRun> xwpfRuns = sourceParagraph.getRuns();
            //因为序号没有真正存储到word的xml中，所以单独做处理。
            if(sourceParagraph.getNumID()!=null){
                CTNumbering cTNumbering = CTNumbering.Factory.parse(WordUtils.getNumberingXMLString(16));
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
                cTLvl.addNewNumFmt().setVal(STNumberFormat.Enum.forString(sourceParagraph.getNumFmt()));
                cTLvl.addNewLvlText().setVal(sourceParagraph.getNumLevelText());
                cTLvl.addNewStart().setVal(BigInteger.valueOf(sourceParagraph.getNumID().bitCount()));
                //这里存在疑问，通过查看源码，得知可通过CTRPr类设置sz和szcs，可用作字号设置。
                //CTRPr是行级使用的，需要确认段落级是否存在。
//                cTLvl.getRPr().addNewSz().setVal(BigInteger.valueOf(18));
//                cTLvl.getRPr().addNewSzCs().setVal(BigInteger.valueOf(18));
//                newParagraph.getCTP().;
                XWPFAbstractNum abstractNum = new XWPFAbstractNum(cTAbstractNum);
                XWPFNumbering numbering = targetD.createNumbering();
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
            newParagraph.setAlignment(sourceParagraph.getAlignment());//段落对齐
            newParagraph.setFirstLineIndent(sourceParagraph.getFirstLineIndent());//段落第一行缩进
            newParagraph.setFontAlignment(sourceParagraph.getFontAlignment());//段落字体对齐
            newParagraph.setSpacingAfter(sourceParagraph.getSpacingAfter());
            newParagraph.setSpacingBefore(sourceParagraph.getSpacingBefore());
            newParagraph.setKeepNext(sourceParagraph.isKeepNext());
            newParagraph.setSpacingBetween(sourceParagraph.getSpacingBetween());//间距
            newParagraph.setPageBreak(sourceParagraph.isPageBreak());//是否分页符
            newParagraph.setSpacingLineRule(sourceParagraph.getSpacingLineRule());//间距线规则
            newParagraph.setVerticalAlignment(sourceParagraph.getVerticalAlignment());//垂直对齐

            String str = "";
            for (XWPFRun run : xwpfRuns){
                if(!StringUtils.isNullOrEmpty(run.getText(0))){
                    str = str + run.getText(0);
                }
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setColor(run.getColor());
                newXwpfrun.setBold(run.isBold());
                newXwpfrun.setCapitalized(run.isCapitalized());
                newXwpfrun.setFontFamily(run.getFontFamily());
                newXwpfrun.setFontSize(run.getFontSize());
                newXwpfrun.setText(run.getText(0));
            }
            System.out.println(str);
            //设置标题
            if("10千伏线损管理技术支撑".equals(str)){
                newParagraph.getCTP().addNewPPr().addNewOutlineLvl().setVal(BigInteger.ONE);
            }

        }

        FileOutputStream fos = null;
        try {
            File file = new File("C:\\Users\\mingliao\\Desktop\\3.docx");
            fos = new FileOutputStream(file);
            targetD.write(fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

    }

    public void mergeTestByJsfw(InputStream source, InputStream target) throws Exception{
        XWPFDocument sourceD = new XWPFDocument(source);
        XWPFDocument targetD = new XWPFDocument(target);

        //先在合并前创建一个分页符，以便隔开
        POIUtils.insertPageBreak(targetD);

        boolean flag = true;

        //开始合并
        List<XWPFParagraph> sourceDParagraphs = sourceD.getParagraphs();
        for (int i = 0; i < sourceDParagraphs.size(); i++){
            XWPFParagraph sourceParagraph = sourceDParagraphs.get(i);
            XWPFParagraph newParagraph = targetD.createParagraph();
            List<XWPFRun> xwpfRuns = sourceParagraph.getRuns();

            newParagraph.setAlignment(sourceParagraph.getAlignment());//段落对齐
            newParagraph.setFirstLineIndent(sourceParagraph.getFirstLineIndent());//段落第一行缩进
            newParagraph.setFontAlignment(sourceParagraph.getFontAlignment());//段落字体对齐
            newParagraph.setKeepNext(sourceParagraph.isKeepNext());
            newParagraph.setPageBreak(sourceParagraph.isPageBreak());//是否分页符
            newParagraph.setVerticalAlignment(sourceParagraph.getVerticalAlignment());//垂直对齐

            if(sourceParagraph.getSpacingBetween() < 0){
                newParagraph.setSpacingBetween(1.5);//间距
            }else{
                newParagraph.setSpacingBetween(sourceParagraph.getSpacingBetween());//间距
            }

            if(sourceParagraph.getNumID()!=null){
                System.out.println("有序号");
            }

            String str = "";
            for (XWPFRun run : xwpfRuns){
                if(!StringUtils.isNullOrEmpty(run.getText(0))){
                    str = str + run.getText(0);
                }
            }

            //设置标题
            if(!StringUtils.isNullOrEmpty(str)&&flag){
                flag = false;
                newParagraph.getCTP().addNewPPr().addNewOutlineLvl().setVal(BigInteger.ONE);
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setBold(true);
                newXwpfrun.setFontFamily("黑体");
                newXwpfrun.setFontSize(16);
                newXwpfrun.setText(xwpfRuns.get(0).getText(0));
            }else if(str.contains("计划目标")&&str.length()<=6) {
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setText("一、计划目标");
                newXwpfrun.setBold(true);
                newXwpfrun.setFontFamily("黑体");
                newXwpfrun.setFontSize(16);
            }else if(str.contains("工作开展情况")&&str.length()<=8) {
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setText("二、工作开展情况");
                newXwpfrun.setBold(true);
                newXwpfrun.setFontFamily("黑体");
                newXwpfrun.setFontSize(16);
            }else if(str.contains("项目成效")&&str.length()<=6) {
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setText("三、项目成效");
                newXwpfrun.setBold(true);
                newXwpfrun.setFontFamily("黑体");
                newXwpfrun.setFontSize(16);
            }else if(str.contains("工作建议")&&str.length()<=6) {
                XWPFRun newXwpfrun = newParagraph.createRun();
                newXwpfrun.setText("四、工作建议");
                newXwpfrun.setBold(true);
                newXwpfrun.setFontFamily("黑体");
                newXwpfrun.setFontSize(16);
            }else{//非固定格式
                for(XWPFRun run : xwpfRuns){
                    XWPFRun newXwpfrun = newParagraph.createRun();
                    POIUtils.copyStyleAndText(run,newXwpfrun);
                }
            }

        }

        FileOutputStream fos = null;
        try {
            File file = new File("C:\\Users\\mingliao\\Desktop\\zuizhong.docx");
            fos = new FileOutputStream(file);
            targetD.write(fos);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }

        System.out.println("success");
    }

}

package top.pxyz.merge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.pxyz.check.service.ICheckService;
import top.pxyz.common.PController;
import top.pxyz.common.Result;
import top.pxyz.merge.service.IMergeService;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.controller
 * @date 2019-11-07 22:10
 */
@Controller
public class MergeController extends PController {

    @Resource
    private IMergeService mergeService;

    @Resource
    private ICheckService checkService;

    /**
     * 跳转单个上传文件页面
     * @return
     */
    @RequestMapping("uploadBySingle")
    public ModelAndView toUploadBySingle(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload/single");
        return modelAndView;
    }

    /**
     * 跳转多个上传文件页面
     * @return
     */
    @RequestMapping("uploadByMulti")
    public ModelAndView toUploadByMulti(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload/multi");
        return modelAndView;
    }

    /**
     * 跳转文档合并页面
     * @return
     */
    @RequestMapping("merge")
    public ModelAndView toUpload(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload/merge");
        return modelAndView;
    }

    /**
     * 上传文件
     * @param file
     * transferTo
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Result upload(MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try {
//            checkService.checkDocTest(file.getInputStream());
//            checkService.checkDocxTest(file.getInputStream());
            File target = new File("C:\\Users\\Ijiran\\Desktop\\2.docx");
            InputStream in = new FileInputStream(target);
            mergeService.mergeTest(file.getInputStream(),in);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result("fileName",fileName);
    }

    /**
     * 查询文件信息列表
     */
    @RequestMapping("/file/findFiles")
    @ResponseBody
    public String findFiles(){
        List<Map<String,String>> list = mergeService.findFiles();
        return toPageJson(list);
    }

}

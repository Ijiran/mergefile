package top.pxyz.merge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.pxyz.merge.service.IMergeService;

import javax.annotation.Resource;

/**
 * @author Ijiran
 * @Package top.pxyz.merge.controller
 * @date 2019-11-07 22:10
 */
@Controller
public class MergeController {

    @Resource
    private IMergeService mergeService;

    @RequestMapping("uploadBySingle")
    public ModelAndView toUploadBySingle(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload/single");
        return modelAndView;
    }

    @RequestMapping("uploadByMulti")
    public ModelAndView toUploadByMulti(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("upload/multi");
        return modelAndView;
    }

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
    @PostMapping("/upload")
    public void upload(MultipartFile file){
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));



    }

}

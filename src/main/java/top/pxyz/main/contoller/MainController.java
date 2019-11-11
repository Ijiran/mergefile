package top.pxyz.main.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Ijiran
 * @Package top.pxyz.main.contoller
 * @date 2019-11-10 16:34
 */
@Controller
public class MainController {

    @RequestMapping({"/","index","index.html"})
    public String toIndex(){
        return "index";
    }

}

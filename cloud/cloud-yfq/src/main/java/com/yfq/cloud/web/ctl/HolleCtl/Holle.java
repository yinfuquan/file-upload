package com.yfq.cloud.web.ctl.HolleCtl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Holle {
    @RequestMapping(value = "/")
    public String sayHolle(Model model){

        model.addAttribute("test","holle");
;        return  "index.html";

    }

}

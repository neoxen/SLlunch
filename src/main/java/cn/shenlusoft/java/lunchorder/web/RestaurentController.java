package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Restaurent;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "restaurents", formBackingObject = Restaurent.class)
@RequestMapping("/restaurents")
@Controller
public class RestaurentController {
}

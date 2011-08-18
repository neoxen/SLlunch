package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "dishes", formBackingObject = Dish.class)
@RequestMapping("/dishes")
@Controller
public class DishController {
}

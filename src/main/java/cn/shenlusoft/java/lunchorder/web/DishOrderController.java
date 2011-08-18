package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RooWebScaffold(path = "dishorders", formBackingObject = DishOrder.class)
@RequestMapping("/dishorders")
@Controller
public class DishOrderController {
}

package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

@RooWebScaffold(path = "dishorders", formBackingObject = DishOrder.class)
@RequestMapping("/dishorders")
@Controller
public class DishOrderController {
    @RequestMapping(method = RequestMethod.POST)
    public String create(@Valid DishOrder dishOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("dishOrder", dishOrder);
            addDateTimeFormatPatterns(uiModel);
            return "dishorders/create";
        }
        uiModel.asMap().clear();

        //set the order time automatically
        dishOrder.setOrderDate(Calendar.getInstance().getTime());

        //calculate the total price from the Set of Dishes
        Set<Dish> orderedDishes = dishOrder.getDishes();
        Iterator<Dish> ita = orderedDishes.iterator();
        double totalPrice = 0.0;
        while (ita.hasNext() == true){
            Dish orderedDish = ita.next();
            totalPrice = totalPrice + orderedDish.getPrice();
        }
        dishOrder.setTotal(totalPrice);
        dishOrder.persist();
        return "redirect:/dishorders/" + encodeUrlPathSegment(dishOrder.getId().toString(), httpServletRequest);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@Valid DishOrder dishOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("dishOrder", dishOrder);
            addDateTimeFormatPatterns(uiModel);
            return "dishorders/update";
        }
        uiModel.asMap().clear();

        //set the order time automatically
        dishOrder.setOrderDate(Calendar.getInstance().getTime());

        //calculate the total price from the Set of Dishes
        Set<Dish> orderedDishes = dishOrder.getDishes();
        Iterator<Dish> ita = orderedDishes.iterator();
        double totalPrice = 0.0;
        while (ita.hasNext() == true){
            Dish orderedDish = ita.next();
            totalPrice = totalPrice + orderedDish.getPrice();
        }
        dishOrder.setTotal(totalPrice);
        dishOrder.merge();
        return "redirect:/dishorders/" + encodeUrlPathSegment(dishOrder.getId().toString(), httpServletRequest);
    }
}

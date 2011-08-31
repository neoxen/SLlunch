package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import cn.shenlusoft.java.lunchorder.domain.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

@RooWebScaffold(path = "dishorders", formBackingObject = DishOrder.class)
@RequestMapping("/dishorders")
@Controller
public class DishOrderController {

    private static final Log logger = LogFactory.getLog(DishOrderController.class);

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
        //check client ip address and choose the right person with the ip record
        String strIP = httpServletRequest.getRemoteAddr();

        DishOrder newDO = new DishOrder();

        if (strIP.startsWith("192")) {
            TypedQuery<Person> person = Person.findPeopleByIpaddressEquals(strIP);
            Person p = person.getSingleResult();
            newDO.setPerson(p);
        }

        uiModel.addAttribute("dishOrder", newDO);
        addDateTimeFormatPatterns(uiModel);
        return "dishorders/create";
    }

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

// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import cn.shenlusoft.java.lunchorder.domain.Person;
import java.io.UnsupportedEncodingException;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect DishOrderController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST)
    public String DishOrderController.create(@Valid DishOrder dishOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
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
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String DishOrderController.createForm(Model uiModel) {
        uiModel.addAttribute("dishOrder", new DishOrder());
        addDateTimeFormatPatterns(uiModel);
        return "dishorders/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String DishOrderController.show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("dishorder", DishOrder.findDishOrder(id));
        uiModel.addAttribute("itemId", id);
        return "dishorders/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String DishOrderController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            uiModel.addAttribute("dishorders", DishOrder.findDishOrderEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) DishOrder.countDishOrders() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("dishorders", DishOrder.findAllDishOrders());
        }
        addDateTimeFormatPatterns(uiModel);
        return "dishorders/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String DishOrderController.update(@Valid DishOrder dishOrder, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
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
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String DishOrderController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("dishOrder", DishOrder.findDishOrder(id));
        addDateTimeFormatPatterns(uiModel);
        return "dishorders/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String DishOrderController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        DishOrder.findDishOrder(id).remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "25" : size.toString());
        return "redirect:/dishorders";
    }
    
    @ModelAttribute("dishes")
    public Collection<Dish> DishOrderController.populateDishes() {
        return Dish.findAllDishes();
    }
    
    @ModelAttribute("dishorders")
    public Collection<DishOrder> DishOrderController.populateDishOrders() {
        return DishOrder.findAllDishOrders();
    }
    
    @ModelAttribute("people")
    public Collection<Person> DishOrderController.populatePeople() {
        return Person.findAllPeople();
    }
    
    void DishOrderController.addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("dishOrder_orderdate_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
    
    String DishOrderController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}

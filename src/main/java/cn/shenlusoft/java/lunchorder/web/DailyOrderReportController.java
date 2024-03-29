package cn.shenlusoft.java.lunchorder.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import cn.shenlusoft.java.lunchorder.domain.Restaurent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.Oracle10gDialect;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

//  RequestMapping("/dailyorderreport/**")
@RequestMapping("/")
@Controller
public class DailyOrderReportController {
    private static final Log logger = LogFactory.getLog(DailyOrderReportController.class);

    @RequestMapping
    public void get(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {

        DateTime dt = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        String strDt = dt.toString(fmt);

        dt =  DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(strDt + " 00:00:00");
        Date orderDate =  dt.toDate();

        List<DishOrder> dishOrderList = DishOrder.findDishOrdersByOrderDateGreaterThan(orderDate).getResultList();

        uiModel.addAttribute("dishorders", dishOrderList);

        ListIterator<DishOrder> dishOrderListIterator = dishOrderList.listIterator();

        ArrayList<Restaurent> ral = new ArrayList<Restaurent>();
        ArrayList<Dish> dishAL = new ArrayList<Dish>();

        while (dishOrderListIterator.hasNext() ){
            DishOrder dorder = dishOrderListIterator.next();
            Set<Dish> dishSet = dorder.getDishes();

            dishAL.addAll(dishSet);

            //TODO

        }
        uiModel.addAttribute("dishlist", dishAL);

        addDateTimeFormatPatterns(uiModel);
        //return "dishorders/list";
        return "dailyorderreport/index";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("dishOrder_orderdate_date_format", org.joda.time.format.DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }
}

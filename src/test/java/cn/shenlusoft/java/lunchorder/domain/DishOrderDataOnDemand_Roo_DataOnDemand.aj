// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.shenlusoft.java.lunchorder.domain;

import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import cn.shenlusoft.java.lunchorder.domain.Person;
import cn.shenlusoft.java.lunchorder.domain.PersonDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect DishOrderDataOnDemand_Roo_DataOnDemand {
    
    declare @type: DishOrderDataOnDemand: @Component;
    
    private Random DishOrderDataOnDemand.rnd = new SecureRandom();
    
    private List<DishOrder> DishOrderDataOnDemand.data;
    
    @Autowired
    private PersonDataOnDemand DishOrderDataOnDemand.personDataOnDemand;
    
    public DishOrder DishOrderDataOnDemand.getNewTransientDishOrder(int index) {
        DishOrder obj = new DishOrder();
        setOrderDate(obj, index);
        setPerson(obj, index);
        setTotal(obj, index);
        return obj;
    }
    
    public void DishOrderDataOnDemand.setOrderDate(DishOrder obj, int index) {
        Date orderDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setOrderDate(orderDate);
    }
    
    public void DishOrderDataOnDemand.setPerson(DishOrder obj, int index) {
        Person person = personDataOnDemand.getRandomPerson();
        obj.setPerson(person);
    }
    
    public void DishOrderDataOnDemand.setTotal(DishOrder obj, int index) {
        double total = new Integer(index).doubleValue();
        obj.setTotal(total);
    }
    
    public DishOrder DishOrderDataOnDemand.getSpecificDishOrder(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        DishOrder obj = data.get(index);
        return DishOrder.findDishOrder(obj.getId());
    }
    
    public DishOrder DishOrderDataOnDemand.getRandomDishOrder() {
        init();
        DishOrder obj = data.get(rnd.nextInt(data.size()));
        return DishOrder.findDishOrder(obj.getId());
    }
    
    public boolean DishOrderDataOnDemand.modifyDishOrder(DishOrder obj) {
        return false;
    }
    
    public void DishOrderDataOnDemand.init() {
        data = DishOrder.findDishOrderEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'DishOrder' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<cn.shenlusoft.java.lunchorder.domain.DishOrder>();
        for (int i = 0; i < 10; i++) {
            DishOrder obj = getNewTransientDishOrder(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator(); it.hasNext();) {
                    ConstraintViolation<?> cv = it.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}

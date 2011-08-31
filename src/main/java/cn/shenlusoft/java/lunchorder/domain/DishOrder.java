package cn.shenlusoft.java.lunchorder.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import cn.shenlusoft.java.lunchorder.domain.Person;
import javax.persistence.ManyToOne;
import java.util.Set;
import cn.shenlusoft.java.lunchorder.domain.Dish;
import java.util.HashSet;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findDishOrdersByOrderDateGreaterThan" })
public class DishOrder {

    @ManyToOne
    private Person person;

    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<Dish> dishes = new HashSet<Dish>();

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date orderDate;

    private double total;

    private String remark;
}

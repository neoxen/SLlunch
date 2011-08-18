package cn.shenlusoft.java.lunchorder.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.shenlusoft.java.lunchorder.domain.Restaurent;
import javax.persistence.ManyToOne;

@RooJavaBean
@RooToString
@RooEntity
public class Dish {

    @NotNull
    @Size(min = 2)
    private String name;

    @NotNull
    private double price;

    @ManyToOne
    private Restaurent restaurent;
}

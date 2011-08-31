package cn.shenlusoft.java.lunchorder.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findPeopleByIpaddressEquals" })
public class Person {

    @NotNull
    @Size(min = 2)
    private String name;

    private String ipaddress;
}

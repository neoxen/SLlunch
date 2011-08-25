// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package cn.shenlusoft.java.lunchorder.domain;

import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

privileged aspect DishOrder_Roo_Finder {
    
    public static TypedQuery<DishOrder> DishOrder.findDishOrdersByOrderDateEquals(Date orderDate) {
        if (orderDate == null) throw new IllegalArgumentException("The orderDate argument is required");
        EntityManager em = DishOrder.entityManager();
        TypedQuery<DishOrder> q = em.createQuery("SELECT o FROM DishOrder AS o WHERE o.orderDate = :orderDate", DishOrder.class);
        q.setParameter("orderDate", orderDate);
        return q;
    }
    
    public static TypedQuery<DishOrder> DishOrder.findDishOrdersByOrderDateGreaterThan(Date orderDate) {
        if (orderDate == null) throw new IllegalArgumentException("The orderDate argument is required");
        EntityManager em = DishOrder.entityManager();
        TypedQuery<DishOrder> q = em.createQuery("SELECT o FROM DishOrder AS o WHERE o.orderDate > :orderDate", DishOrder.class);
        q.setParameter("orderDate", orderDate);
        return q;
    }
    
}

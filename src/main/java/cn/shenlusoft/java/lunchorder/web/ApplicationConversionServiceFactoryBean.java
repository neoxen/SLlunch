package cn.shenlusoft.java.lunchorder.web;

import cn.shenlusoft.java.lunchorder.domain.Dish;
import cn.shenlusoft.java.lunchorder.domain.DishOrder;
import cn.shenlusoft.java.lunchorder.domain.Person;
import cn.shenlusoft.java.lunchorder.domain.Restaurent;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

    public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(new DishConverter());
        registry.addConverter(new DishOrderConverter());
        registry.addConverter(new PersonConverter());
        registry.addConverter(new RestaurentConverter());
    }

    static class DishConverter implements Converter<Dish, String> {
        public String convert(Dish
        dish) {
            return new StringBuilder().append(dish.getName()).toString();
        }

    }

    static class DishOrderConverter implements Converter<DishOrder, String> {
        public String convert(DishOrder
        dishOrder) {
            return new StringBuilder().append(dishOrder.getOrderDate()).append(" ").append(dishOrder.getTotal()).append(" ").append(dishOrder.getRemark()).toString();
        }

    }

    static class PersonConverter implements Converter<Person, String> {
        public String convert(Person person) {
            return new StringBuilder().append(person.getName()).toString();
        }

    }

    static class RestaurentConverter implements Converter<Restaurent, String> {
        public String convert(Restaurent restaurent) {
            return new StringBuilder().append(restaurent.getName()).toString();
        }

    }


}

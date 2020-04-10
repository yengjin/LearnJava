package design_pattern.builder.product;

import lombok.Getter;
import lombok.Setter;

/**
 * Product 产品角色
 * 一个具体的产品对象
 */
@Getter
@Setter
public class Meal {
    private String food;
    private String drink;
}

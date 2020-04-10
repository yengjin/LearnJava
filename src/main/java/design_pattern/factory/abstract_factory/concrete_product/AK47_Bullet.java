package design_pattern.factory.abstract_factory.concrete_product;

import design_pattern.factory.abstract_factory.abstract_product.Bullet;

/**
 * AK47子弹类
 */
public class AK47_Bullet implements Bullet {
    @Override
    public void load() {
        System.out.println("Load bullets with AK47");
    }
}

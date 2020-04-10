package design_pattern.factory.abstract_factory.concrete_product;

import design_pattern.factory.abstract_factory.abstract_product.Gun;

/**
 * M4A1类
 */
public class M4A1 implements Gun {
    @Override
    public void shooting() {
        System.out.println("shooting with M4A1");
    }
}

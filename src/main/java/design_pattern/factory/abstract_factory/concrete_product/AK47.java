package design_pattern.factory.abstract_factory.concrete_product;

import design_pattern.factory.abstract_factory.abstract_product.Gun;

/**
 * 具体实现类 - AK47
 */
public class AK47 implements Gun {

    @Override
    public void shooting() {
        System.out.println("shooting with AK47");
    }
}

package design_pattern.factory.abstract_factory.concrete_factory;

import design_pattern.factory.abstract_factory.abstract_factory.Factory;
import design_pattern.factory.abstract_factory.abstract_product.Bullet;
import design_pattern.factory.abstract_factory.abstract_product.Gun;
import design_pattern.factory.abstract_factory.concrete_product.AK47;
import design_pattern.factory.abstract_factory.concrete_product.AK47_Bullet;

/**
 * 具体工厂, 负责具体生产AK47**相关**产品
 * 实现 Factory接口
 */
public class AK47_Factory implements Factory {
    @Override
    public Gun produceGun() {
        return new AK47();
    }

    @Override
    public Bullet produceBullet() {
        return new AK47_Bullet();
    }
}

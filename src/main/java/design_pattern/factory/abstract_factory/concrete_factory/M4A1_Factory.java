package design_pattern.factory.abstract_factory.concrete_factory;

import design_pattern.factory.abstract_factory.abstract_factory.Factory;
import design_pattern.factory.abstract_factory.abstract_product.Bullet;
import design_pattern.factory.abstract_factory.abstract_product.Gun;
import design_pattern.factory.abstract_factory.concrete_product.M4A1;
import design_pattern.factory.abstract_factory.concrete_product.M4A1_Bullet;

/**
 * 具体工厂, 负责具体生产M4A1**相关**产品
 * 实现 Factory接口
 */
public class M4A1_Factory implements Factory {

    @Override
    public Gun produceGun() {
        return new M4A1();  // 生产
    }

    @Override
    public Bullet produceBullet() {
        return new M4A1_Bullet();   // 生产
    }
}

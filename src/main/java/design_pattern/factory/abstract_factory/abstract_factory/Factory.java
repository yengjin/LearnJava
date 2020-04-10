package design_pattern.factory.abstract_factory.abstract_factory;

import design_pattern.factory.abstract_factory.abstract_product.Bullet;
import design_pattern.factory.abstract_factory.abstract_product.Gun;

/**
 * 工厂接口
 */
public interface Factory {

    // 生产枪
    Gun produceGun();

    // 生产子弹
    Bullet produceBullet();
}

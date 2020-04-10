package design_pattern.factory.abstract_factory;

import design_pattern.factory.abstract_factory.abstract_factory.Factory;
import design_pattern.factory.abstract_factory.abstract_product.Bullet;
import design_pattern.factory.abstract_factory.abstract_product.Gun;
import design_pattern.factory.abstract_factory.concrete_factory.AK47_Factory;
import design_pattern.factory.abstract_factory.concrete_factory.M4A1_Factory;

/**
 * 抽象工厂模式角色分配
 * 抽象工厂角色: 工厂方法的核心, 与应用程序无关. 任何工厂类都必须实现这个接口
 * 具体工厂类角色: 实现抽象工厂接口的具体工厂类
 * 抽象产品类角色: 工厂方法模式创建的对象的超类型(产品对象的公共父类或接口)
 * 具体产品角色: 创建的产品实例 (**属于同一产品族**)
 */
public class AbstractFactoryTest {

    public static void main(String[] args) {
        // AK47工厂
        Factory ak47_factory = new AK47_Factory();
        Gun ak47 = ak47_factory.produceGun();  // 生产AK47
        Bullet ak47_bullet = ak47_factory.produceBullet();   // 生产AK47子弹

        ak47_bullet.load();
        ak47.shooting();

        // M4A1工厂
        Factory m4a1_factory = new M4A1_Factory();
        Gun m4a1 = m4a1_factory.produceGun();  // 生产M4A1
        Bullet m4a1_bullet = m4a1_factory.produceBullet();   // 生产M4A1子弹

        m4a1_bullet.load();
        m4a1.shooting();


    }
}

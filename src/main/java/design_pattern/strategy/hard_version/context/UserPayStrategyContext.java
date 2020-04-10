package design_pattern.strategy.hard_version.context;


import design_pattern.strategy.hard_version.concrete_strategy.SuperVipPayStrategy;
import design_pattern.strategy.hard_version.concrete_strategy.SuperVvipPayStrategy;
import design_pattern.strategy.hard_version.concrete_strategy.VipPayStrategy;
import design_pattern.strategy.hard_version.factory.UserPayStrategyFactory;
import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.math.BigDecimal;

public class UserPayStrategyContext {
    private BigDecimal orderPrice;
    private String vipType;

    public UserPayStrategyContext(String vipType, BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
        this.vipType = vipType;
    }

    // 按照策略进行支付
    public BigDecimal pay() {
        // 反例, 这样并不能消灭IF语句
        // 怎么优化: 使用工厂模式(HashMap注册), 直接提取相应的策略即可.
        /*if (vipType.equalsIgnoreCase("SVVIP")) {
            UserPayStrategy strategy = new SuperVvipPayStrategy();
            return strategy.quote(this.orderPrice);
        } else if (vipType.equalsIgnoreCase("SVIP")) {
            UserPayStrategy strategy = new SuperVipPayStrategy();
            return strategy.quote(this.orderPrice);
        } else if (vipType.equalsIgnoreCase("VIP")) {
            UserPayStrategy strategy = new VipPayStrategy();
        }
        return orderPrice;*/

        // 优化后, 使用工厂类获取注册好的对应策略
        // 设计模式的强大之处, 一行就完事了
        // 问题: 怎么初始化注册?
        // 具体的策略类, 再实现InitializingBean, 并贴上@Service标签,
        // 重写afterPropertiesSet() throws Exception, 就会在该类在Spring初始化后, 执行这个重写的代码. 此时注册即可.
        /*
        @Service
        public class ParticularlyVipPayStrategy implements UserPayStrategy,InitializingBean {

            @Override
            public BigDecimal quote(BigDecimal orderPrice) {
                 if (消费金额大于30元) {
                    return 7折价格;
                }
            }

            @Override
            public void afterPropertiesSet() throws Exception {
                UserPayStrategyFactory.register("ParticularlyVip",this);
            }
        }
        * */
        return UserPayStrategyFactory.getStrategyByVipType(vipType).quote(orderPrice);
    }
}

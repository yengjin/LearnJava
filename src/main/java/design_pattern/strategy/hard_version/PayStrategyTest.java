package design_pattern.strategy.hard_version;

import design_pattern.strategy.hard_version.concrete_strategy.SuperVipPayStrategy;
import design_pattern.strategy.hard_version.concrete_strategy.SuperVvipPayStrategy;
import design_pattern.strategy.hard_version.concrete_strategy.VipPayStrategy;
import design_pattern.strategy.hard_version.context.UserPayStrategyContext;
import design_pattern.strategy.hard_version.factory.UserPayStrategyFactory;
import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.math.BigDecimal;

/**
 * 测试类
 */
public class PayStrategyTest {

    public static void initRegisterBySpring() {
        UserPayStrategyFactory.register("SVVIP", new SuperVvipPayStrategy());
        UserPayStrategyFactory.register("SVIP", new SuperVipPayStrategy());
        UserPayStrategyFactory.register("VIP", new VipPayStrategy());
    }
    public static void main(String[] args) {
        String vipType = "SVVIP";
        BigDecimal orderPrice = new BigDecimal(40);
        // 上下文, 具体调用对客户端是屏蔽的
        UserPayStrategyContext context = new UserPayStrategyContext(vipType, orderPrice);

        // 模拟Spring的初始化过程(注册)
        initRegisterBySpring();

        BigDecimal realPay = context.pay(); // 真实支付金额
        System.out.println(realPay.toString());
    }
}

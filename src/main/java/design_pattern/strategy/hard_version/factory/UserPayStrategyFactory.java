package design_pattern.strategy.hard_version.factory;

import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.util.HashMap;

/**
 * 用户支付策略工厂类
 * 严格意义上来说, 不是工厂模式(因为没有负责对象的创建, 只是维护一个Map)
 */
public class UserPayStrategyFactory {

    private static HashMap<String, UserPayStrategy> strategyMap = new HashMap<>();

    // 进行策略的注册
    public static void register(String vipType, UserPayStrategy strategy) {
        strategyMap.put(vipType.toUpperCase(), strategy);
    }

    // 根据用户的等级获取对应策略类
    public static UserPayStrategy getStrategyByVipType(String vipType) {
        return strategyMap.get(vipType.toUpperCase());
    }
}

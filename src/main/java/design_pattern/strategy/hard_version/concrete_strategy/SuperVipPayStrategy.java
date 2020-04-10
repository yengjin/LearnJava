package design_pattern.strategy.hard_version.concrete_strategy;

import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.math.BigDecimal;

/**
 * 超级会员策略类
 */
public class SuperVipPayStrategy implements UserPayStrategy {

    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        // 超级会员, 8折
        return orderPrice.multiply(new BigDecimal("0.8"));
    }
}

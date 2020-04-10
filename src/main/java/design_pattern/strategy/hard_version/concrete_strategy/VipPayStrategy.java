package design_pattern.strategy.hard_version.concrete_strategy;

import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.math.BigDecimal;

/**
 * 普通会员策略类
 */
public class VipPayStrategy implements UserPayStrategy {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        return orderPrice.multiply(new BigDecimal("0.9"));
    }
}

package design_pattern.strategy.hard_version.concrete_strategy;

import design_pattern.strategy.hard_version.strategy.UserPayStrategy;

import java.math.BigDecimal;

/**
 * 至尊会员, 消费金额大于30打7折
 */
public class SuperVvipPayStrategy implements UserPayStrategy {
    @Override
    public BigDecimal quote(BigDecimal orderPrice) {
        if (orderPrice.intValue() > 30) {
            // 支付金额达30, 打7折
            return orderPrice.multiply(new BigDecimal("0.7"));
        }
        return orderPrice.multiply(new BigDecimal("0.8"));
    }
}

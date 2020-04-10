package design_pattern.strategy.hard_version.strategy;

import java.math.BigDecimal;

/**
 * 用户支付策略接口
 */
public interface UserPayStrategy {

    // 计算价格
    BigDecimal quote(BigDecimal orderPrice);
}

package com.gupaoedu.gpmall.user.userserviceprovider.event;

import com.gupaoedu.gpmall.user.userserviceprovider.dal.mapper.UmsUserMapper;
import com.gupaoedu.gpmall.user.userserviceprovider.dal.model.UmsUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 咕泡教育，ToBeBetterMan
 * Mic老师微信: mic4096
 * 微信公众号： 跟着Mic学架构
 * https://ke.gupaoedu.cn
 **/
@Component
@Slf4j
@RocketMQTransactionListener
public class UserRegisterTxmsgListener implements RocketMQLocalTransactionListener {

    @Autowired
    UmsUserMapper userMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        //TODO 执行本地事务
        log.info("begin start local transaction, message: {}",message);
        //INSERT Ums_user()
        return RocketMQLocalTransactionState.UNKNOWN; //表示本地事务执行状态未知
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        //本地事务的回调
        log.info("begin start broker check transaction, message:{}",message);
        int uid=Integer.parseInt(new String((byte[])message.getPayload()));
        UmsUser user=userMapper.selectById(uid);
        if(user!=null){
            return RocketMQLocalTransactionState.COMMIT; //表示本地事务执行状态未知
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}

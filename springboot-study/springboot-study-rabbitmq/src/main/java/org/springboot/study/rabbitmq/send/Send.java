package org.springboot.study.rabbitmq.send;

import java.util.UUID;

import org.springboot.study.rabbitmq.AmqpConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * ClassName: Send <br/>
 * Function: TODO <br/>
 * Reason: TODO <br/>
 * 
 * @date    2017年7月26日 下午2:45:12
 * @author  huangtao
 * @version 1.0.0
 * @since   JDK 1.8
 */
@Component
public class Send implements RabbitTemplate.ConfirmCallback {

	private RabbitTemplate rabbitTemplate;

	/**
	 * 构造方法注入
	 */
	@Autowired
	public Send(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		rabbitTemplate.setConfirmCallback(this); // rabbitTemplate如果为单例的话，那回调就是最后设置的内容
	}

	public void sendMsg(String content) {
		CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
		rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE, AmqpConfig.ROUTINGKEY, content, correlationId);
	}

	/**
	 * 回调
	 */
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		System.out.println(" 回调id:" + correlationData.getId());
		if (ack) {
			System.out.println("消息成功消费");
		} else {
			System.out.println("消息消费失败:" + cause);
		}
	}

}

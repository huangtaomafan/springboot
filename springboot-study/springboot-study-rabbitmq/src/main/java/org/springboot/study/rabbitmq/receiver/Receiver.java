/*
 * Project Name:springboot-study-rabbitmq
 * File Name:Receiver.java
 * Package Name:org.springboot.study.rabbitmq.receiver
 * Date:2017年7月26日
 * Copyright (c) 2017,  All Rights Reserved.
 *
 */

package org.springboot.study.rabbitmq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

import com.rabbitmq.client.Channel;

/**
 * 
 * ClassName: Receiver <br/>
 * Function: TODO <br/>
 * Reason: TODO <br/>
 * 
 * @date    2017年7月26日 下午3:20:17
 * @author  huangtao
 * @version 1.0.0
 * @since   JDK 1.8
 */
public class Receiver implements ChannelAwareMessageListener {
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	public void onMessage(Message message, Channel channel) throws Exception {
		String body = new String(message.getBody());
		if (Integer.parseInt(body) % 2 == 0) {
			System.out.println("receive msg2 : " + body);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 确认消息成功消费
		} else {
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
	}

}

/*
 * Project Name:springboot-study-rabbitmq
 * File Name:DemoSpringbootApplication.java
 * Package Name:org.springboot.study.rabbitmq
 * Date:2017年7月26日
 * Copyright (c) 2017,  All Rights Reserved.
 *
 */

package org.springboot.study.rabbitmq;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * ClassName: DemoSpringbootApplication <br/>
 * Function: TODO <br/>
 * Reason: TODO <br/>
 * Date: 2017年7月26日 下午2:30:54 <br/>
 *
 * @author   huangtao
 * @version  1.0.0
 * @since    JDK 1.8
 * @see      
 */

@SpringBootApplication
@ServletComponentScan
public class MainApplication {
	public static void main(String[] args) throws IOException {
		// pid 方便 用kill关闭
		String name = ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
		FileWriter writer = new FileWriter(new File("main.pid"));
		writer.write(name);
		writer.close();
		SpringApplication.run(MainApplication.class);
	}
}

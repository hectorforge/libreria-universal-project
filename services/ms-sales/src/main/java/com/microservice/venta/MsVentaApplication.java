package com.microservice.venta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@EnableFeignClients
@SpringBootApplication
public class MsVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsVentaApplication.class, args);
	}

}

package com.example.ordersystem.ordering.service;

import com.example.ordersystem.ordering.dto.ProductDto;
import com.example.ordersystem.ordering.dto.ProductUpdateStockDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//name은 eureka에 등록된 호출할 서비스의 이름
//url은 k8s에서 service명. 로컬(local)은 url 비워두고 eureka로 찾고, prod는 application-prod.yml의 feign.product-service.url 사용
@FeignClient(name = "product-service", url = "${feign.product-service.url:}")
public interface ProductFeign {

    @GetMapping("/product/{productId}")
    ProductDto getProductById(@PathVariable Long productId, @RequestHeader("X-User-Id") String userId);

    @PutMapping("/product/updatestock")
    void updateProductStock(@RequestBody ProductUpdateStockDto productUpdateStockDto);
}

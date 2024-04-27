package com.example.myselectshop.scheduler;

import com.example.myselectshop.dto.ItemDto;
import com.example.myselectshop.entity.Product;
import com.example.myselectshop.repository.ProductRepository;
import com.example.myselectshop.service.NaverApiService;
import com.example.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "Scheduler")
@Component
@RequiredArgsConstructor
public class Scheduler {
    private final NaverApiService naverApiService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Scheduled(cron = "0 0 1 * * *")
    public void updatePrice() throws InterruptedException {
        log.info("가격 업데이트 실행");

        List<Product> productList = productRepository.findAll();

        for (Product product : productList) {
            TimeUnit.SECONDS.sleep(1);

            String title = product.getTitle();
            List<ItemDto> itemDtoList = naverApiService.searchItems(title);

            if (itemDtoList.size() > 0) {
                ItemDto itemDto = itemDtoList.get(0);

                Long id = product.getId();

                try {
                    productService.updateBySearch(id, itemDto);
                } catch (Exception e) {
                    log.error(id + " : " + e.getMessage());
                }
            }
        }
    }
}

package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.StockEntity;
import com.cjss.ecommerce.model.Stock;
import com.cjss.ecommerce.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;

	public ResponseEntity getSKUStockDetailsById(Integer id){
			if(stockRepository.existsById(id)) {
				StockEntity stockEntity = stockRepository.getById(id);    //findById is available by default from JPARepository
				Stock stock = new Stock();
				stock.setSkuCode(stockEntity.getSkuCode());
				stock.setQuantityAvailable(stockEntity.getQuantityAvailable());

				return ResponseEntity.status(HttpStatus.OK).body(stock);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No stock available for product SKU with id:"+id+" (CODE 400)\n");
	}

	public ResponseEntity addSKUStockDetailsById(Integer skuId,Integer stock){
		StockEntity stockEntity = new StockEntity();
		if(stockRepository.existsById(skuId)) {
			stockEntity = stockRepository.getById(skuId);    //findById is available by default from JPARepository
			stockEntity.setQuantityAvailable(stockEntity.getQuantityAvailable()+stock);
		}
		else {
			stockEntity.setSkuCode(skuId);
			stockEntity.setQuantityAvailable(stock);
		}
		return ResponseEntity.status(HttpStatus.OK).body(stockRepository.save(stockEntity));
	}
}

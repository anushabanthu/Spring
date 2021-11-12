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
//		TODO: check if sku exists in db
			if(stockRepository.existsById(id)) {
				Optional<StockEntity> stockEntity = stockRepository.findById(id);    //findById is available by default from JPARepository
				Stock stock = new Stock();
				stock.setSkuCode(stockEntity.get().getSkuCode());
				stock.setQuantityAvailable(stockEntity.get().getQuantityAvailable());

				return ResponseEntity.status(HttpStatus.ACCEPTED).body(stock);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No stock available for product SKU with id:"+id+" (CODE 400)\n");
	}

	public ResponseEntity addSKUStockDetailsById(Integer id){
		StockEntity stockEntity = new StockEntity();
		if(stockRepository.existsById(id)) {
			stockEntity = stockRepository.getById(id);    //findById is available by default from JPARepository
			stockEntity.setQuantityAvailable(stockEntity.getQuantityAvailable()+1);
		}
		else {
			stockEntity.setSkuCode(id);
			stockEntity.setQuantityAvailable(1);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(stockRepository.save(stockEntity));
	}
}

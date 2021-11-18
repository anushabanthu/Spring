package com.cjss.ecommerce.service;

import com.cjss.ecommerce.entity.*;
import com.cjss.ecommerce.model.*;
import com.cjss.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductSKURepository productSKURepository;
	@Autowired
	private PriceRepository priceRepository;

	public ResponseEntity getProductDetailsById(Integer id){
			if(productRepository.existsById(id)) {
				Optional<ProductEntity> productEntity = productRepository.findById(id);    //findById is available by default from JPARepository
				Product product = new Product();

				List<ProductSku> skus = new ArrayList<>();
				product.setProductCode(productEntity.get().getProductCode());
				product.setProductName(productEntity.get().getProductName());
				product.setDescription(productEntity.get().getDescription());
				productEntity.get().getProductSKUEntities().forEach(sku -> {
					ProductSku productSku = new ProductSku();
					productSku.setProductCode(sku.getProductCode());
					productSku.setSkuCode(sku.getSkuCode());
					productSku.setSize(sku.getSize());
					Price price = new Price();
					if (sku.getPriceEntity()!=null) {
						price.setPrice(sku.getPriceEntity().getPrice());
						price.setCurrency(sku.getPriceEntity().getCurrency());
						price.setSkuCode(Integer.valueOf(sku.getPriceEntity().getSkuCode()));
						productSku.setPrice(price);
					}
					skus.add(productSku);
				});
				product.setProductSkus(skus);
				return ResponseEntity.status(HttpStatus.OK).body(product);
			}
			else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product with given id:"+id+" doesnt exists (CODE 400)\n");
	}

	public ResponseEntity getProductSkuDetailsById(Integer id){
		if(productSKURepository.existsById(id)) {
			Optional<ProductSKUEntity> productSKUEntity = productSKURepository.findById(id);    //findById is available by default from JPARepository
			ProductSku productSku = new ProductSku();
			Price price = new Price();
			productSku.setProductCode(productSKUEntity.get().getProductCode());
			productSku.setSkuCode(productSKUEntity.get().getSkuCode());
			productSku.setSize(productSKUEntity.get().getSize());
			price.setSkuCode(Integer.valueOf(productSKUEntity.get().getPriceEntity().getSkuCode()));
			price.setPrice(productSKUEntity.get().getPriceEntity().getPrice());
			price.setCurrency(productSKUEntity.get().getPriceEntity().getCurrency());
			productSku.setPrice(price);
			return ResponseEntity.status(HttpStatus.OK).body(productSku);
			}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product sku with given id:"+id+" doesnt exists (CODE 400)\n");
	}

	public ResponseEntity getPriceDetailsById(Integer id){
		if(priceRepository.existsById(id)) {
			Optional<PriceEntity> priceEntity = priceRepository.findById(id);    //findById is available by default from JPARepository
			Price price = new Price();
			price.setPrice(priceEntity.get().getPrice());
			price.setSkuCode(Integer.valueOf(priceEntity.get().getSkuCode()));
			price.setCurrency(priceEntity.get().getCurrency());
			return ResponseEntity.status(HttpStatus.OK).body(price);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price details doesnt exists for product sku with id:"+id+" (CODE 400)\n");
	}

	public ResponseEntity addProduct(Product product){

		if(!productRepository.existsById(product.getProductCode())) {
			List<ProductSKUEntity> skus = new ArrayList<>();
			ProductEntity productEntity = new ProductEntity();

			productEntity.setProductCode(product.getProductCode());
			productEntity.setProductName(product.getProductName());
			productEntity.setDescription(product.getDescription());

			if(product.getProductSkus()!=null) {
				product.getProductSkus().stream().filter(sku->sku.getProductCode().equals(product.getProductCode())).forEach(sku -> {
					ProductSKUEntity productSKUEntity = new ProductSKUEntity();
					PriceEntity priceEntity = new PriceEntity();
					productSKUEntity.setProductCode(sku.getProductCode());
					productSKUEntity.setSkuCode(sku.getSkuCode());
					productSKUEntity.setSize(sku.getSize());
					productSKUEntity.setProductEntity(productEntity);

					if(sku.getPrice()!=null && sku.getPrice().getSkuCode()==sku.getSkuCode()){
						priceEntity.setSkuCode(sku.getPrice().getSkuCode());
						priceEntity.setPrice(sku.getPrice().getPrice());
						priceEntity.setCurrency(sku.getPrice().getCurrency());
						productSKUEntity.setPriceEntity(priceEntity);
					}
						skus.add(productSKUEntity);
				});
			}
			productEntity.setProductSKUEntities(skus);
			ProductEntity productFromDB = productRepository.save(productEntity);
			return ResponseEntity.status(HttpStatus.OK).body(productFromDB);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product with given id:"+product.getProductCode()+" already exists (CODE 400)\n");
	}

	public ResponseEntity addProductSku(ProductSku productSku){
		boolean skuExists = false;
		if(productSku.getPrice()!=null && productSku.getPrice().getSkuCode()!=productSku.getSkuCode())	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product sku id mismatch between sku and price inputs (CODE 400)\n");
		if(productSKURepository.existsById(productSku.getSkuCode())) {
			skuExists = productSKURepository.findById(productSku.getSkuCode()).get().getProductCode().equals(productSku.getProductCode());
		}
		if(skuExists)	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product sku with given id:"+productSku.getSkuCode()+ "already exists (CODE 400)\n");
			if (!skuExists && productRepository.existsById(productSku.getProductCode())) {
				ProductEntity product = productRepository.getById(productSku.getProductCode());
				List<ProductSKUEntity> skus = product.getProductSKUEntities();
				ProductSKUEntity productSKUEntity = new ProductSKUEntity();
				PriceEntity priceEntity = new PriceEntity();
				productSKUEntity.setProductEntity(product);
				productSKUEntity.setSkuCode(productSku.getSkuCode());
				productSKUEntity.setProductCode(productSku.getProductCode());
				productSKUEntity.setSize(productSku.getSize());

				if(productSku.getPrice() != null) {
					priceEntity.setPrice(productSku.getPrice().getPrice());
					priceEntity.setSkuCode(productSku.getPrice().getSkuCode());
					priceEntity.setCurrency(productSku.getPrice().getCurrency());
					productSKUEntity.setPriceEntity(priceEntity);
				}
				skus.add(productSKUEntity);
				product.setProductSKUEntities(skus);
				return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));
			}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product with given id "+productSku.getProductCode()+" doesnt exist.Hence cant add productSKU details (CODE 400)\n");
	}

	public ResponseEntity addPrice(Price price){
		boolean priceExists = false;
		if(priceRepository.existsById(price.getSkuCode())) {
			priceExists = priceRepository.findById(price.getSkuCode()).get().getSkuCode().equals(price.getSkuCode());
		}
		if(priceExists)		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product price details with given id:"+price.getSkuCode()+" already exists (CODE 400)\n");
		if (!priceExists && productSKURepository.existsById(price.getSkuCode())) {
			ProductSKUEntity productSku = productSKURepository.getById(price.getSkuCode());
				ProductEntity product = productRepository.getById(productSku.getProductCode());
				List<ProductSKUEntity> skus = product.getProductSKUEntities();
				ProductSKUEntity productSKUEntity = new ProductSKUEntity();
				PriceEntity priceEntity = new PriceEntity();
				productSKUEntity.setProductEntity(product);
				productSKUEntity.setSkuCode(productSku.getSkuCode());
				productSKUEntity.setProductCode(productSku.getProductCode());
				productSKUEntity.setSize(productSku.getSize());

				priceEntity.setPrice(price.getPrice());
				priceEntity.setSkuCode(price.getSkuCode());
				priceEntity.setCurrency(price.getCurrency());

				productSKUEntity.setPriceEntity(priceEntity);
				skus.add(productSKUEntity);
				product.setProductSKUEntities(skus);
				return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(product));
		}
		else	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product sku with given id:"+price.getSkuCode()+" doesnt exist.Hence cant add price details (CODE 400)\n");
	}
}

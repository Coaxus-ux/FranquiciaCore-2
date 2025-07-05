package com.accenture.franquiciaCore.application.product.imp;

import com.accenture.franquiciaCore.application.product.AddProductCommand;
import com.accenture.franquiciaCore.application.product.AddProductUseCase;
import com.accenture.franquiciaCore.application.product.DeleteProductCommand;
import com.accenture.franquiciaCore.application.product.DeleteProductUseCase;
import com.accenture.franquiciaCore.application.product.FindMaxStockCommand;
import com.accenture.franquiciaCore.application.product.FindMaxStockUseCase;
import com.accenture.franquiciaCore.application.product.UpdateProductNameCommand;
import com.accenture.franquiciaCore.application.product.UpdateProductNameUseCase;
import com.accenture.franquiciaCore.application.product.UpdateProductStockCommand;
import com.accenture.franquiciaCore.application.product.UpdateProductStockUseCase;
import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.shared.util.IdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductService implements
    AddProductUseCase,
    DeleteProductUseCase,
    UpdateProductStockUseCase,
    UpdateProductNameUseCase,
    FindMaxStockUseCase {

  private final ProductRepository productRepository;
  private final SubsidiaryRepository subsidiaryRepository;
  private final StockRepository stockRepository;

  @Override
  public Mono<Product> addProduct(AddProductCommand cmd) {
    cmd.validate();
    
    return subsidiaryRepository.findById(cmd.getSubsidiaryId())
        .switchIfEmpty(Mono.error(
            new NoSuchElementException("Subsidiary not found: " + cmd.getSubsidiaryId())))
        .flatMap(sub -> {
          String newProdId = IdGenerator.generate();
          String newStockId = IdGenerator.generate();

          Product product = new Product(
              new ProductId(newProdId),
              cmd.getName().trim(),
              CategoryProduct.valueOf(cmd.getCategory().toUpperCase()),
              null,
              sub.getId()
          );

          return productRepository.save(product)
              .flatMap(savedProduct -> {
                Stock stock = Stock.builder()
                    .id(new StockId(newStockId))
                    .quantity(cmd.getQuantity())
                    .build();
                return stockRepository
                    .save(stock.withProductId(savedProduct.getId()))
                    .thenReturn(savedProduct.withStock(stock));
              });
        });
  }

  @Override
  public Mono<Void> deleteProduct(DeleteProductCommand cmd) {
    return productRepository.delete(cmd.getProductId())
        .then(stockRepository.deleteByProductId(
            new ProductId(cmd.getProductId())));
  }

  @Override
  public Mono<Product> updateStock(UpdateProductStockCommand cmd) {
    ProductId pid = new ProductId(cmd.getProductId());

    return stockRepository.findByProductId(pid)
        .switchIfEmpty(Mono.error(
            new NoSuchElementException("Stock not found for product " + pid.getValue())))
        .flatMap(stock -> {
          Stock updatedStock = Stock.builder()
              .id(stock.getId())
              .quantity(cmd.getQuantity())
              .build();
          return stockRepository.update(updatedStock)
              .then(productRepository.findById(pid.getValue()))
              .map(product -> product.withStock(updatedStock));
        });
  }

  @Override
  public Mono<Product> updateName(UpdateProductNameCommand cmd) {
    cmd.validate();
    
    return productRepository.findById(cmd.getProductId())
        .switchIfEmpty(Mono.error(
            new NoSuchElementException("Product not found")))
        .flatMap(p -> {
          Product updated = Product.builder()
              .id(p.getId())
              .name(cmd.getName().trim())
              .category(p.getCategory())
              .stock(p.getStock())
              .subsidiaryId(p.getSubsidiaryId())
              .build();
          return productRepository.update(updated);
        })
        .flatMap(prod -> stockRepository.findByProductId(
            new ProductId(prod.getId().getValue()))
            .map(stock -> prod.withStock(stock)));
  }

  @Override
  public Flux<Product> findMaxStockByFranchise(FindMaxStockCommand cmd) {
    return subsidiaryRepository.findByFranchiseId(cmd.getFranchiseId())
        .flatMap(sub -> productRepository.findBySubsidiaryId(sub.getId().getValue())
            .flatMap(product -> stockRepository.findByProductId(product.getId())
                .map(stock -> product.withStock(stock)))
            .sort((a, b) -> Integer.compare(b.getStock().getQuantity(), a.getStock().getQuantity()))
            .next());
  }
  
}

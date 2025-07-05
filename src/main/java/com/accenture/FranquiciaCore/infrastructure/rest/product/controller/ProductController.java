package com.accenture.franquiciaCore.infrastructure.rest.product.controller;

import java.net.URI;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
import com.accenture.franquiciaCore.infrastructure.rest.product.assembler.ProductModelAssembler;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.CreateProductRequest;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.ProductResponse;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.UpdateProductNameRequest;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.UpdateStockRequest;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

  private final AddProductUseCase addProductUseCase;
  private final DeleteProductUseCase deleteProductUseCase;
  private final UpdateProductStockUseCase updateProductStockUseCase;
  private final UpdateProductNameUseCase updateProductNameUseCase;
  private final FindMaxStockUseCase findMaxStockUseCase;
  private final ProductModelAssembler assembler;

  @PostMapping("/subsidiaries/{subsidiaryId}/products")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> create(
      @PathVariable String subsidiaryId,
      @Validated @RequestBody CreateProductRequest req) {
    AddProductCommand cmd = new AddProductCommand(
        req.getName(),
        subsidiaryId,
        req.getCategory(),
        req.getQuantity());

    return addProductUseCase.addProduct(cmd)
        .map(assembler::toModel)
        .map(model -> {
          String selfHref = model.getRequiredLink("self").getHref();
          return ResponseEntity
              .created(URI.create(selfHref))
              .body(model);
        });
  }

  @GetMapping("/subsidiaries/{subsidiaryId}/products/{productId}")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> findById(
      @PathVariable String subsidiaryId,
      @PathVariable String productId) {
    return updateProductNameUseCase
        .updateName(new UpdateProductNameCommand(subsidiaryId, productId, ""))
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
  }

  @PutMapping("/subsidiaries/{subsidiaryId}/products/{productId}/stock")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> updateStock(
      @PathVariable String subsidiaryId,
      @PathVariable String productId,
      @RequestBody UpdateStockRequest req) {
    UpdateProductStockCommand cmd = new UpdateProductStockCommand(
        subsidiaryId,
        productId,
        req.getQuantity());

    return updateProductStockUseCase.updateStock(cmd)
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
  }

  @PutMapping("/subsidiaries/{subsidiaryId}/products/{productId}")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> updateName(
      @PathVariable String subsidiaryId,
      @PathVariable String productId,
      @RequestBody UpdateProductNameRequest req) {
    UpdateProductNameCommand cmd = new UpdateProductNameCommand(
        subsidiaryId,
        productId,
        req.getName());

    return updateProductNameUseCase.updateName(cmd)
        .map(assembler::toModel)
        .map(ResponseEntity::ok);
  }

  @DeleteMapping("/subsidiaries/{subsidiaryId}/products/{productId}")
  public Mono<ResponseEntity<Void>> delete(
      @PathVariable String subsidiaryId,
      @PathVariable String productId) {
    return deleteProductUseCase
        .deleteProduct(new DeleteProductCommand(subsidiaryId, productId))
        .thenReturn(ResponseEntity.noContent().build());
  }

  @GetMapping("/franchises/{franchiseId}/products/max-stock")
  public Flux<EntityModel<ProductResponse>> maxStock(
      @PathVariable String franchiseId) {
    return findMaxStockUseCase
        .findMaxStockByFranchise(new FindMaxStockCommand(franchiseId))
        .map(assembler::toModel);
  }

}

package com.accenture.franquiciaCore.infrastructure.rest.product.controller;

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
import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.infrastructure.rest.product.assembler.ProductModelAssembler;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.CreateProductRequest;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.ProductResponse;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.UpdateProductNameRequest;
import com.accenture.franquiciaCore.infrastructure.rest.product.dto.UpdateStockRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.net.URI;

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
  private final SubsidiaryRepository subsidiaryRepository;

  @PostMapping("/subsidiaries/{subsidiaryId}/products")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> create(
      @PathVariable String subsidiaryId,
      @RequestBody CreateProductRequest req) {

    AddProductCommand cmd = new AddProductCommand(
        req.getName(),
        subsidiaryId,
        req.getCategory(),
        req.getQuantity()
    );

    return addProductUseCase.addProduct(cmd)
      .zipWith(subsidiaryRepository.findById(subsidiaryId))
      .map(this::toCreatedResponse);
  }

  @PutMapping("/subsidiaries/{subsidiaryId}/products/{productId}/stock")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> updateStock(
      @PathVariable String subsidiaryId,
      @PathVariable String productId,
      @RequestBody UpdateStockRequest req) {

    UpdateProductStockCommand cmd =
        new UpdateProductStockCommand(subsidiaryId, productId, req.getQuantity());

    return updateProductStockUseCase.updateStock(cmd)
      .zipWith(subsidiaryRepository.findById(subsidiaryId))
      .map(this::toOkResponse);
  }

  @PutMapping("/subsidiaries/{subsidiaryId}/products/{productId}")
  public Mono<ResponseEntity<EntityModel<ProductResponse>>> updateName(
      @PathVariable String subsidiaryId,
      @PathVariable String productId,
      @RequestBody UpdateProductNameRequest req) {

    UpdateProductNameCommand cmd =
        new UpdateProductNameCommand(subsidiaryId, productId, req.getName());

    return updateProductNameUseCase.updateName(cmd)
      .zipWith(subsidiaryRepository.findById(subsidiaryId))
      .map(this::toOkResponse);
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
      .flatMap(prod ->
        subsidiaryRepository.findById(prod.getSubsidiaryId().getValue())
          .map(sub -> {
            EntityModel<ProductResponse> model = assembler.toModel(prod);
            model.getContent().setSubsidiaryName(sub.getName());
            return model;
          })
      );
  }

  private ResponseEntity<EntityModel<ProductResponse>> toCreatedResponse(
      Tuple2<Product, Subsidiary> tup) {
    Product prod = tup.getT1();
    String   name = tup.getT2().getName();

    EntityModel<ProductResponse> model = assembler.toModel(prod);
    model.getContent().setSubsidiaryName(name);
    URI location = URI.create(model.getRequiredLink("self").getHref());
    return ResponseEntity.created(location).body(model);
  }

  private ResponseEntity<EntityModel<ProductResponse>> toOkResponse(
      Tuple2<Product, Subsidiary> tup) {
    Product prod = tup.getT1();
    String   name = tup.getT2().getName();

    EntityModel<ProductResponse> model = assembler.toModel(prod);
    model.getContent().setSubsidiaryName(name);
    return ResponseEntity.ok(model);
  }
}

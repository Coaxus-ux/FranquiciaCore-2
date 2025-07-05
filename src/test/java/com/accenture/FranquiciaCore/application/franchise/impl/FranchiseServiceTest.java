package com.accenture.franquiciaCore.application.franchise.impl;

import com.accenture.franquiciaCore.application.franchise.CreateFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.FindFranchiseCommand;
import com.accenture.franquiciaCore.application.franchise.UpdateFranchiseCommand;
import com.accenture.franquiciaCore.domain.franchise.model.Franchise;
import com.accenture.franquiciaCore.domain.franchise.model.Product;
import com.accenture.franquiciaCore.domain.franchise.model.Stock;
import com.accenture.franquiciaCore.domain.franchise.model.Subsidiary;
import com.accenture.franquiciaCore.domain.franchise.model.enums.CategoryProduct;
import com.accenture.franquiciaCore.domain.franchise.repository.FranchiseRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.ProductRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.StockRepository;
import com.accenture.franquiciaCore.domain.franchise.repository.SubsidiaryRepository;
import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.ProductId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.StockId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import com.accenture.franquiciaCore.infrastructure.rest.franchise.dto.FranchiseTopProductsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("FranchiseService Tests")
class FranchiseServiceTest {

    @Mock
    private FranchiseRepository franchiseRepository;
    
    @Mock
    private SubsidiaryRepository subsidiaryRepository;
    
    @Mock
    private ProductRepository productRepository;
    
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private FranchiseService franchiseService;

    private Franchise testFranchise;
    private FranchiseId franchiseId;

    @BeforeEach
    void setUp() {
        franchiseId = new FranchiseId("franchise-123");
        testFranchise = Franchise.builder()
            .id(franchiseId)
            .name("Test Franchise")
            .build();
    }

    @Test
    @DisplayName("Should create franchise successfully")
    void shouldCreateFranchiseSuccessfully() {

        CreateFranchiseCommand command = new CreateFranchiseCommand("McDonald's");
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(testFranchise));


        Mono<Franchise> result = franchiseService.createFranchise(command);


        StepVerifier.create(result)
            .expectNext(testFranchise)
            .verifyComplete();

        verify(franchiseRepository).save(any(Franchise.class));
    }

    @Test
    @DisplayName("Should find franchise by id successfully")
    void shouldFindFranchiseByIdSuccessfully() {

        FindFranchiseCommand command = new FindFranchiseCommand(franchiseId.getValue());
        when(franchiseRepository.findById(franchiseId.getValue())).thenReturn(Mono.just(testFranchise));


        Mono<Franchise> result = franchiseService.findFranchise(command);


        StepVerifier.create(result)
            .expectNext(testFranchise)
            .verifyComplete();

        verify(franchiseRepository).findById(franchiseId.getValue());
    }

    @Test
    @DisplayName("Should throw exception when franchise not found")
    void shouldThrowExceptionWhenFranchiseNotFound() {

        FindFranchiseCommand command = new FindFranchiseCommand(franchiseId.getValue());
        when(franchiseRepository.findById(franchiseId.getValue())).thenReturn(Mono.empty());

        Mono<Franchise> result = franchiseService.findFranchise(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof NoSuchElementException &&
                throwable.getMessage().contains("Franchise not found"))
            .verify();

        verify(franchiseRepository).findById(franchiseId.getValue());
    }

    @Test
    @DisplayName("Should update franchise successfully")
    void shouldUpdateFranchiseSuccessfully() {

        UpdateFranchiseCommand command = new UpdateFranchiseCommand(franchiseId.getValue(), "Updated Franchise");
        Franchise updatedFranchise = Franchise.builder()
            .id(franchiseId)
            .name("Updated Franchise")
            .build();
        
        when(franchiseRepository.findById(franchiseId.getValue())).thenReturn(Mono.just(testFranchise));
        when(franchiseRepository.save(any(Franchise.class))).thenReturn(Mono.just(updatedFranchise));


        Mono<Franchise> result = franchiseService.updateFranchise(command);


        StepVerifier.create(result)
            .expectNext(updatedFranchise)
            .verifyComplete();

        verify(franchiseRepository).findById(franchiseId.getValue());
        verify(franchiseRepository).save(any(Franchise.class));
    }

    @Test
    @DisplayName("Should find all franchises successfully")
    void shouldFindAllFranchisesSuccessfully() {

        Franchise franchise2 = Franchise.builder()
            .id(new FranchiseId("franchise-456"))
            .name("Another Franchise")
            .build();
        
        when(franchiseRepository.findAll()).thenReturn(Flux.just(testFranchise, franchise2));


        Flux<Franchise> result = franchiseService.findAll();


        StepVerifier.create(result)
            .expectNext(testFranchise)
            .expectNext(franchise2)
            .verifyComplete();

        verify(franchiseRepository).findAll();
    }

    @Test
    @DisplayName("Should find all top products successfully")
    void shouldFindAllTopProductsSuccessfully() {

        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name("Test Subsidiary")
            .franchiseId(franchiseId)
            .build();

        ProductId productId1 = new ProductId("product-123");
        ProductId productId2 = new ProductId("product-456");
        
        Product product1 = Product.builder()
            .id(productId1)
            .name("Product 1")
            .category(CategoryProduct.FOOD)
            .subsidiaryId(subsidiaryId)
            .build();
        
        Product product2 = Product.builder()
            .id(productId2)
            .name("Product 2")
            .category(CategoryProduct.BEVERAGE)
            .subsidiaryId(subsidiaryId)
            .build();

        Stock stock1 = Stock.builder()
            .id(new StockId("stock-123"))
            .quantity(100)
            .build();
        
        Stock stock2 = Stock.builder()
            .id(new StockId("stock-456"))
            .quantity(50)
            .build();

        when(franchiseRepository.findAll()).thenReturn(Flux.just(testFranchise));
        when(subsidiaryRepository.findByFranchiseId(franchiseId.getValue())).thenReturn(Flux.just(subsidiary));
        when(productRepository.findBySubsidiaryId(subsidiaryId.getValue())).thenReturn(Flux.just(product1, product2));
        when(stockRepository.findByProductId(productId1)).thenReturn(Mono.just(stock1));
        when(stockRepository.findByProductId(productId2)).thenReturn(Mono.just(stock2));


        Flux<FranchiseTopProductsResponse> result = franchiseService.findAllTopProducts();


        StepVerifier.create(result)
            .assertNext(response -> {
                assertEquals(franchiseId.getValue(), response.getFranchiseId());
                assertEquals("Test Franchise", response.getFranchiseName());
                assertEquals(1, response.getSubsidiaries().size());
                assertEquals("Product 1", response.getSubsidiaries().get(0).getTopProduct().getProductName());
                assertEquals(100, response.getSubsidiaries().get(0).getTopProduct().getStock());
            })
            .verifyComplete();

        verify(franchiseRepository).findAll();
        verify(subsidiaryRepository).findByFranchiseId(franchiseId.getValue());
        verify(productRepository).findBySubsidiaryId(subsidiaryId.getValue());
        verify(stockRepository).findByProductId(productId1);
        verify(stockRepository).findByProductId(productId2);
    }

    @Test
    @DisplayName("Should handle empty subsidiaries when finding top products")
    void shouldHandleEmptySubsidiariesWhenFindingTopProducts() {

        when(franchiseRepository.findAll()).thenReturn(Flux.just(testFranchise));
        when(subsidiaryRepository.findByFranchiseId(franchiseId.getValue())).thenReturn(Flux.empty());


        Flux<FranchiseTopProductsResponse> result = franchiseService.findAllTopProducts();


        StepVerifier.create(result)
            .assertNext(response -> {
                assertEquals(franchiseId.getValue(), response.getFranchiseId());
                assertEquals("Test Franchise", response.getFranchiseName());
                assertTrue(response.getSubsidiaries().isEmpty());
            })
            .verifyComplete();

        verify(franchiseRepository).findAll();
        verify(subsidiaryRepository).findByFranchiseId(franchiseId.getValue());
        verify(productRepository, never()).findBySubsidiaryId(anyString());
        verify(stockRepository, never()).findByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should handle empty products when finding top products")
    void shouldHandleEmptyProductsWhenFindingTopProducts() {

        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name("Test Subsidiary")
            .franchiseId(franchiseId)
            .build();

        when(franchiseRepository.findAll()).thenReturn(Flux.just(testFranchise));
        when(subsidiaryRepository.findByFranchiseId(franchiseId.getValue())).thenReturn(Flux.just(subsidiary));
        when(productRepository.findBySubsidiaryId(subsidiaryId.getValue())).thenReturn(Flux.empty());


        Flux<FranchiseTopProductsResponse> result = franchiseService.findAllTopProducts();


        StepVerifier.create(result)
            .assertNext(response -> {
                assertEquals(franchiseId.getValue(), response.getFranchiseId());
                assertEquals("Test Franchise", response.getFranchiseName());
                assertTrue(response.getSubsidiaries().isEmpty());
            })
            .verifyComplete();

        verify(franchiseRepository).findAll();
        verify(subsidiaryRepository).findByFranchiseId(franchiseId.getValue());
        verify(productRepository).findBySubsidiaryId(subsidiaryId.getValue());
        verify(stockRepository, never()).findByProductId(any(ProductId.class));
    }

    @Test
    @DisplayName("Should handle repository errors gracefully")
    void shouldHandleRepositoryErrorsGracefully() {

        CreateFranchiseCommand command = new CreateFranchiseCommand("Test Franchise");
        when(franchiseRepository.save(any(Franchise.class)))
            .thenReturn(Mono.error(new RuntimeException("Database error")));


        Mono<Franchise> result = franchiseService.createFranchise(command);


        StepVerifier.create(result)
            .expectErrorMatches(throwable -> 
                throwable instanceof RuntimeException &&
                throwable.getMessage().equals("Database error"))
            .verify();

        verify(franchiseRepository).save(any(Franchise.class));
    }
}


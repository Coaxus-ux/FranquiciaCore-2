package com.accenture.franquiciaCore.domain.franchise.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ProductId Tests")
class ProductIdTest {

    @Test
    @DisplayName("Should create ProductId with valid value")
    void shouldCreateProductIdWithValidValue() {
        String validValue = "product-123";
        
        ProductId productId = new ProductId(validValue);
        
        assertEquals(validValue, productId.getValue());
        assertEquals(validValue, productId.toString());
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        String nullValue = null;
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductId(nullValue)
        );
        assertEquals("ProductId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is empty")
    void shouldThrowExceptionWhenValueIsEmpty() {
        String emptyValue = "";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductId(emptyValue)
        );
        assertEquals("ProductId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is blank")
    void shouldThrowExceptionWhenValueIsBlank() {
        String blankValue = "   ";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductId(blankValue)
        );
        assertEquals("ProductId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are the same")
    void shouldBeEqualWhenValuesAreTheSame() {
        String value = "product-123";
        ProductId productId1 = new ProductId(value);
        ProductId productId2 = new ProductId(value);
        
        assertEquals(productId1, productId2);
        assertEquals(productId1.hashCode(), productId2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when values are different")
    void shouldNotBeEqualWhenValuesAreDifferent() {
        ProductId productId1 = new ProductId("product-123");
        ProductId productId2 = new ProductId("product-456");
        
        assertNotEquals(productId1, productId2);
        assertNotEquals(productId1.hashCode(), productId2.hashCode());
    }
}


package com.accenture.franquiciaCore.domain.franchise.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("StockId Tests")
class StockIdTest {

    @Test
    @DisplayName("Should create StockId with valid value")
    void shouldCreateStockIdWithValidValue() {
        
        String validValue = "stock-123";
        
        StockId stockId = new StockId(validValue);
        
        assertEquals(validValue, stockId.getValue());
        assertEquals(validValue, stockId.toString());
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        
        String nullValue = null;
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new StockId(nullValue)
        );
        assertEquals("StockId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is empty")
    void shouldThrowExceptionWhenValueIsEmpty() {
        
        String emptyValue = "";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new StockId(emptyValue)
        );
        assertEquals("StockId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is blank")
    void shouldThrowExceptionWhenValueIsBlank() {
        
        String blankValue = "   ";
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new StockId(blankValue)
        );
        assertEquals("StockId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are the same")
    void shouldBeEqualWhenValuesAreTheSame() {
        
        String value = "stock-123";
        StockId stockId1 = new StockId(value);
        StockId stockId2 = new StockId(value);
        
        assertEquals(stockId1, stockId2);
        assertEquals(stockId1.hashCode(), stockId2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when values are different")
    void shouldNotBeEqualWhenValuesAreDifferent() {

        StockId stockId1 = new StockId("stock-123");
        StockId stockId2 = new StockId("stock-456");
        
        assertNotEquals(stockId1, stockId2);
        assertNotEquals(stockId1.hashCode(), stockId2.hashCode());
    }
}


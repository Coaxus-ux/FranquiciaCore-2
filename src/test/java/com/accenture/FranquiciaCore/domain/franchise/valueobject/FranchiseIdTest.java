package com.accenture.franquiciaCore.domain.franchise.valueobject;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("FranchiseId Tests")
class FranchiseIdTest {

    @Test
    @DisplayName("Should create FranchiseId with valid value")
    void shouldCreateFranchiseIdWithValidValue() {
        
        String validValue = "franchise-123";
        
        
        FranchiseId franchiseId = new FranchiseId(validValue);
        
        
        assertEquals(validValue, franchiseId.getValue());
        assertEquals(validValue, franchiseId.toString());
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        
        String nullValue = null;
        
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new FranchiseId(nullValue)
        );
        assertEquals("FranchiseId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is empty")
    void shouldThrowExceptionWhenValueIsEmpty() {
        
        String emptyValue = "";
        
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new FranchiseId(emptyValue)
        );
        assertEquals("FranchiseId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is blank")
    void shouldThrowExceptionWhenValueIsBlank() {
        
        String blankValue = "   ";
        
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new FranchiseId(blankValue)
        );
        assertEquals("FranchiseId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are the same")
    void shouldBeEqualWhenValuesAreTheSame() {
        
        String value = "franchise-123";
        FranchiseId franchiseId1 = new FranchiseId(value);
        FranchiseId franchiseId2 = new FranchiseId(value);
        
        
        assertEquals(franchiseId1, franchiseId2);
        assertEquals(franchiseId1.hashCode(), franchiseId2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when values are different")
    void shouldNotBeEqualWhenValuesAreDifferent() {
        
        FranchiseId franchiseId1 = new FranchiseId("franchise-123");
        FranchiseId franchiseId2 = new FranchiseId("franchise-456");
        
        
        assertNotEquals(franchiseId1, franchiseId2);
        assertNotEquals(franchiseId1.hashCode(), franchiseId2.hashCode());
    }
}


package com.accenture.franquiciaCore.domain.franchise.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SubsidiaryId Tests")
class SubsidiaryIdTest {

    @Test
    @DisplayName("Should create SubsidiaryId with valid value")
    void shouldCreateSubsidiaryIdWithValidValue() {
        
        String validValue = "subsidiary-123";
        
        SubsidiaryId subsidiaryId = new SubsidiaryId(validValue);
        
        assertEquals(validValue, subsidiaryId.getValue());
        assertEquals(validValue, subsidiaryId.toString());
    }

    @Test
    @DisplayName("Should throw exception when value is null")
    void shouldThrowExceptionWhenValueIsNull() {
        
        String nullValue = null;
        

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new SubsidiaryId(nullValue)
        );
        assertEquals("SubsidiaryId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is empty")
    void shouldThrowExceptionWhenValueIsEmpty() {
        
        String emptyValue = "";
        

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new SubsidiaryId(emptyValue)
        );
        assertEquals("SubsidiaryId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when value is blank")
    void shouldThrowExceptionWhenValueIsBlank() {
        String blankValue = "   ";

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new SubsidiaryId(blankValue)
        );
        assertEquals("SubsidiaryId cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should be equal when values are the same")
    void shouldBeEqualWhenValuesAreTheSame() {
        
        String value = "subsidiary-123";
        SubsidiaryId subsidiaryId1 = new SubsidiaryId(value);
        SubsidiaryId subsidiaryId2 = new SubsidiaryId(value);
        
        assertEquals(subsidiaryId1, subsidiaryId2);
        assertEquals(subsidiaryId1.hashCode(), subsidiaryId2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when values are different")
    void shouldNotBeEqualWhenValuesAreDifferent() {
        
        SubsidiaryId subsidiaryId1 = new SubsidiaryId("subsidiary-123");
        SubsidiaryId subsidiaryId2 = new SubsidiaryId("subsidiary-456");
        

        assertNotEquals(subsidiaryId1, subsidiaryId2);
        assertNotEquals(subsidiaryId1.hashCode(), subsidiaryId2.hashCode());
    }
}


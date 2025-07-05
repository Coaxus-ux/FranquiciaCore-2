package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Subsidiary Tests")
class SubsidiaryTest {

    @Test
    @DisplayName("Should create Subsidiary with valid data")
    void shouldCreateSubsidiaryWithValidData() {
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        String name = "Downtown Branch";
        FranchiseId franchiseId = new FranchiseId("franchise-456");
        
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name(name)
            .franchiseId(franchiseId)
            .build();
        
        assertEquals(subsidiaryId, subsidiary.getId());
        assertEquals(name, subsidiary.getName());
        assertEquals(franchiseId, subsidiary.getFranchiseId());
    }

    @Test
    @DisplayName("Should be equal when ids are the same")
    void shouldBeEqualWhenIdsAreTheSame() {
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        FranchiseId franchiseId1 = new FranchiseId("franchise-456");
        FranchiseId franchiseId2 = new FranchiseId("franchise-789");
        
        Subsidiary subsidiary1 = Subsidiary.builder()
            .id(subsidiaryId)
            .name("Branch 1")
            .franchiseId(franchiseId1)
            .build();
        Subsidiary subsidiary2 = Subsidiary.builder()
            .id(subsidiaryId)
            .name("Branch 2")
            .franchiseId(franchiseId2)
            .build();
        
        assertEquals(subsidiary1, subsidiary2);
        assertEquals(subsidiary1.hashCode(), subsidiary2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when ids are different")
    void shouldNotBeEqualWhenIdsAreDifferent() {
        FranchiseId franchiseId = new FranchiseId("franchise-456");
        
        Subsidiary subsidiary1 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Downtown Branch")
            .franchiseId(franchiseId)
            .build();
        Subsidiary subsidiary2 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-456"))
            .name("Downtown Branch")
            .franchiseId(franchiseId)
            .build();
        
        assertNotEquals(subsidiary1, subsidiary2);
        assertNotEquals(subsidiary1.hashCode(), subsidiary2.hashCode());
    }

    @Test
    @DisplayName("Should have proper toString representation")
    void shouldHaveProperToStringRepresentation() {
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        String name = "Downtown Branch";
        FranchiseId franchiseId = new FranchiseId("franchise-456");
        
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name(name)
            .franchiseId(franchiseId)
            .build();
        
        String toStringResult = subsidiary.toString();
        
        assertNotNull(toStringResult);
        assertTrue(toStringResult.contains("subsidiary-123"));
        assertTrue(toStringResult.contains("Downtown Branch"));
        assertTrue(toStringResult.contains("franchise-456"));
    }

    @Test
    @DisplayName("Should handle null values in builder")
    void shouldHandleNullValuesInBuilder() {
        Subsidiary subsidiary = Subsidiary.builder()
            .id(null)
            .name(null)
            .franchiseId(null)
            .build();
        
        assertNull(subsidiary.getId());
        assertNull(subsidiary.getName());
        assertNull(subsidiary.getFranchiseId());
    }

    @Test
    @DisplayName("Should create subsidiary with empty name")
    void shouldCreateSubsidiaryWithEmptyName() {
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        String emptyName = "";
        FranchiseId franchiseId = new FranchiseId("franchise-456");
        
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name(emptyName)
            .franchiseId(franchiseId)
            .build();
        
        assertEquals(subsidiaryId, subsidiary.getId());
        assertEquals(emptyName, subsidiary.getName());
        assertEquals(franchiseId, subsidiary.getFranchiseId());
    }

    @Test
    @DisplayName("Should create subsidiary with long name")
    void shouldCreateSubsidiaryWithLongName() {
        SubsidiaryId subsidiaryId = new SubsidiaryId("subsidiary-123");
        String longName = "This is a very long subsidiary name that might be used in some business scenarios where detailed descriptions are needed";
        FranchiseId franchiseId = new FranchiseId("franchise-456");
        
        Subsidiary subsidiary = Subsidiary.builder()
            .id(subsidiaryId)
            .name(longName)
            .franchiseId(franchiseId)
            .build();
        
        assertEquals(subsidiaryId, subsidiary.getId());
        assertEquals(longName, subsidiary.getName());
        assertEquals(franchiseId, subsidiary.getFranchiseId());
    }
}


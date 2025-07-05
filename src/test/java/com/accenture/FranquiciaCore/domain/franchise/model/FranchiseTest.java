package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import com.accenture.franquiciaCore.domain.franchise.valueobject.SubsidiaryId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Franchise Tests")
class FranchiseTest {

    private FranchiseId franchiseId;
    private Franchise franchise;

    @BeforeEach
    void setUp() {
        franchiseId = new FranchiseId("franchise-123");
        franchise = Franchise.builder()
            .id(franchiseId)
            .name("Test Franchise")
            .build();
    }

    @Test
    @DisplayName("Should create Franchise with valid data")
    void shouldCreateFranchiseWithValidData() {
        String name = "McDonald's";
        Franchise franchise = Franchise.builder()
            .id(franchiseId)
            .name(name)
            .build();
        assertEquals(franchiseId, franchise.getId());
        assertEquals(name, franchise.getName());
        assertTrue(franchise.getSubsidiaries().isEmpty());
    }

    @Test
    @DisplayName("Should add subsidiary to franchise")
    void shouldAddSubsidiaryToFranchise() {
        Subsidiary subsidiary = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Test Subsidiary")
            .franchiseId(franchiseId)
            .build();
        
        franchise.addSubsidiary(subsidiary);
        assertEquals(1, franchise.getSubsidiaries().size());
        assertTrue(franchise.getSubsidiaries().contains(subsidiary));
    }

    @Test
    @DisplayName("Should throw exception when adding subsidiary with different franchise id")
    void shouldThrowExceptionWhenAddingSubsidiaryWithDifferentFranchiseId() {
        FranchiseId differentFranchiseId = new FranchiseId("different-franchise-456");
        Subsidiary subsidiary = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Test Subsidiary")
            .franchiseId(differentFranchiseId)
            .build();
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> franchise.addSubsidiary(subsidiary)
        );
        assertEquals("Subsidiary does not belong to this franchise", exception.getMessage());
        assertTrue(franchise.getSubsidiaries().isEmpty());
    }

    @Test
    @DisplayName("Should remove subsidiary from franchise")
    void shouldRemoveSubsidiaryFromFranchise() {
        Subsidiary subsidiary1 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Test Subsidiary 1")
            .franchiseId(franchiseId)
            .build();
        Subsidiary subsidiary2 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-456"))
            .name("Test Subsidiary 2")
            .franchiseId(franchiseId)
            .build();
        
        franchise.addSubsidiary(subsidiary1);
        franchise.addSubsidiary(subsidiary2);
        
        franchise.removeSubsidiary(subsidiary1);
        assertEquals(1, franchise.getSubsidiaries().size());
        assertFalse(franchise.getSubsidiaries().contains(subsidiary1));
        assertTrue(franchise.getSubsidiaries().contains(subsidiary2));
    }

    @Test
    @DisplayName("Should handle removing non-existent subsidiary")
    void shouldHandleRemovingNonExistentSubsidiary() {
        Subsidiary existingSubsidiary = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Existing Subsidiary")
            .franchiseId(franchiseId)
            .build();
        Subsidiary nonExistentSubsidiary = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-456"))
            .name("Non-existent Subsidiary")
            .franchiseId(franchiseId)
            .build();
        
        franchise.addSubsidiary(existingSubsidiary);
        
        franchise.removeSubsidiary(nonExistentSubsidiary);
        assertEquals(1, franchise.getSubsidiaries().size());
        assertTrue(franchise.getSubsidiaries().contains(existingSubsidiary));
    }

    @Test
    @DisplayName("Should return unmodifiable list of subsidiaries")
    void shouldReturnUnmodifiableListOfSubsidiaries() {
        Subsidiary subsidiary = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Test Subsidiary")
            .franchiseId(franchiseId)
            .build();
        franchise.addSubsidiary(subsidiary);
        
        var subsidiaries = franchise.getSubsidiaries();
        
        assertThrows(UnsupportedOperationException.class, () -> {
            subsidiaries.add(subsidiary);
        });
        assertThrows(UnsupportedOperationException.class, () -> {
            subsidiaries.remove(subsidiary);
        });
    }

    @Test
    @DisplayName("Should be equal when ids are the same")
    void shouldBeEqualWhenIdsAreTheSame() {
        Franchise franchise1 = Franchise.builder()
            .id(franchiseId)
            .name("Franchise 1")
            .build();
        Franchise franchise2 = Franchise.builder()
            .id(franchiseId)
            .name("Franchise 2")
            .build();
        
        assertEquals(franchise1, franchise2);
        assertEquals(franchise1.hashCode(), franchise2.hashCode());
    }

    @Test
    @DisplayName("Should not be equal when ids are different")
    void shouldNotBeEqualWhenIdsAreDifferent() {
        Franchise franchise1 = Franchise.builder()
            .id(new FranchiseId("franchise-123"))
            .name("Test Franchise")
            .build();
        Franchise franchise2 = Franchise.builder()
            .id(new FranchiseId("franchise-456"))
            .name("Test Franchise")
            .build();
        
        assertNotEquals(franchise1, franchise2);
        assertNotEquals(franchise1.hashCode(), franchise2.hashCode());
    }

    @Test
    @DisplayName("Should add multiple subsidiaries")
    void shouldAddMultipleSubsidiaries() {
        Subsidiary subsidiary1 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-123"))
            .name("Subsidiary 1")
            .franchiseId(franchiseId)
            .build();
        Subsidiary subsidiary2 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-456"))
            .name("Subsidiary 2")
            .franchiseId(franchiseId)
            .build();
        Subsidiary subsidiary3 = Subsidiary.builder()
            .id(new SubsidiaryId("subsidiary-789"))
            .name("Subsidiary 3")
            .franchiseId(franchiseId)
            .build();
        
        franchise.addSubsidiary(subsidiary1);
        franchise.addSubsidiary(subsidiary2);
        franchise.addSubsidiary(subsidiary3);
        
        assertEquals(3, franchise.getSubsidiaries().size());
        assertTrue(franchise.getSubsidiaries().contains(subsidiary1));
        assertTrue(franchise.getSubsidiaries().contains(subsidiary2));
        assertTrue(franchise.getSubsidiaries().contains(subsidiary3));
    }
}


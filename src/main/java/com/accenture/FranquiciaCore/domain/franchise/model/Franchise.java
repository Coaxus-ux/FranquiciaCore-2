package com.accenture.franquiciaCore.domain.franchise.model;

import com.accenture.franquiciaCore.domain.franchise.valueobject.FranchiseId;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
@Builder
public class Franchise {
  private FranchiseId id;
  private String name;
  private final List<Subsidiary> subsidiaries = new ArrayList<>();

  public void addSubsidiary(Subsidiary sub) {
    if (!sub.getFranchiseId().equals(this.id)) {
      throw new IllegalArgumentException("Subsidiary does not belong to this franchise");
    }
    this.subsidiaries.add(sub);
  }

  public void removeSubsidiary(Subsidiary sub) {
    subsidiaries.removeIf(s -> s.getId().equals(sub.getId()));
  }

  public List<Subsidiary> getSubsidiaries() {
    return Collections.unmodifiableList(subsidiaries);
  }
}

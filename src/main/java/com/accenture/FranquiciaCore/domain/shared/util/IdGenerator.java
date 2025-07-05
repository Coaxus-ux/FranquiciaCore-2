package com.accenture.franquiciaCore.domain.shared.util;

import java.util.UUID;

public final class IdGenerator {
  public static String generate() {
    return UUID.randomUUID().toString();
  }
}

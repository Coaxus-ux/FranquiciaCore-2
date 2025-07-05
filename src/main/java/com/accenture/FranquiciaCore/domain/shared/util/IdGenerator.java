package com.accenture.franquiciaCore.domain.shared.util;

import org.bson.types.ObjectId;

public final class IdGenerator {
  public static String generate() {
    return new ObjectId().toString();
  }
}

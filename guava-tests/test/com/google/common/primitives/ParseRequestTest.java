package com.google.common.primitives;

import junit.framework.TestCase;

/**
 * Created by Junxian Chen on 2020-02-23.
 */
public class ParseRequestTest extends TestCase {

  public void testEmptyString() {
    try {
      ParseRequest.fromString("");
    } catch (NumberFormatException expected) {
      assertEquals("empty string", expected.getMessage());
    }
  }
}

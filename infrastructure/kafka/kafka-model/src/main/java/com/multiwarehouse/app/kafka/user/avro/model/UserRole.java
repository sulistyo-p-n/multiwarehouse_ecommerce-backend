/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.multiwarehouse.app.kafka.user.avro.model;
@org.apache.avro.specific.AvroGenerated
public enum UserRole implements org.apache.avro.generic.GenericEnumSymbol<UserRole> {
  CUSTOMER, SUPER_ADMIN, WAREHOUSE_ADMIN  ;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"enum\",\"name\":\"UserRole\",\"namespace\":\"com.multiwarehouse.app.kafka.user.avro.model\",\"symbols\":[\"CUSTOMER\",\"SUPER_ADMIN\",\"WAREHOUSE_ADMIN\"]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
}

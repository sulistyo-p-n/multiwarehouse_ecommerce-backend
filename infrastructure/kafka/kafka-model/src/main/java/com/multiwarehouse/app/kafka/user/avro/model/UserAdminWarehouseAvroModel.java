/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.multiwarehouse.app.kafka.user.avro.model;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class UserAdminWarehouseAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 4742425181449664663L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UserAdminWarehouseAvroModel\",\"namespace\":\"com.multiwarehouse.app.kafka.user.avro.model\",\"fields\":[{\"name\":\"userAdminWarehouseId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"warehouseId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
  }

  private static final BinaryMessageEncoder<UserAdminWarehouseAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UserAdminWarehouseAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<UserAdminWarehouseAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<UserAdminWarehouseAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<UserAdminWarehouseAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this UserAdminWarehouseAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a UserAdminWarehouseAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a UserAdminWarehouseAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static UserAdminWarehouseAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID userAdminWarehouseId;
  private java.util.UUID warehouseId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UserAdminWarehouseAvroModel() {}

  /**
   * All-args constructor.
   * @param userAdminWarehouseId The new value for userAdminWarehouseId
   * @param warehouseId The new value for warehouseId
   */
  public UserAdminWarehouseAvroModel(java.util.UUID userAdminWarehouseId, java.util.UUID warehouseId) {
    this.userAdminWarehouseId = userAdminWarehouseId;
    this.warehouseId = warehouseId;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return userAdminWarehouseId;
    case 1: return warehouseId;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      null
  };

  @Override
  public org.apache.avro.Conversion<?> getConversion(int field) {
    return conversions[field];
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: userAdminWarehouseId = (java.util.UUID)value$; break;
    case 1: warehouseId = (java.util.UUID)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'userAdminWarehouseId' field.
   * @return The value of the 'userAdminWarehouseId' field.
   */
  public java.util.UUID getUserAdminWarehouseId() {
    return userAdminWarehouseId;
  }


  /**
   * Sets the value of the 'userAdminWarehouseId' field.
   * @param value the value to set.
   */
  public void setUserAdminWarehouseId(java.util.UUID value) {
    this.userAdminWarehouseId = value;
  }

  /**
   * Gets the value of the 'warehouseId' field.
   * @return The value of the 'warehouseId' field.
   */
  public java.util.UUID getWarehouseId() {
    return warehouseId;
  }


  /**
   * Sets the value of the 'warehouseId' field.
   * @param value the value to set.
   */
  public void setWarehouseId(java.util.UUID value) {
    this.warehouseId = value;
  }

  /**
   * Creates a new UserAdminWarehouseAvroModel RecordBuilder.
   * @return A new UserAdminWarehouseAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder newBuilder() {
    return new com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder();
  }

  /**
   * Creates a new UserAdminWarehouseAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UserAdminWarehouseAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder newBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder other) {
    if (other == null) {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder();
    } else {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new UserAdminWarehouseAvroModel RecordBuilder by copying an existing UserAdminWarehouseAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new UserAdminWarehouseAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder newBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel other) {
    if (other == null) {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder();
    } else {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for UserAdminWarehouseAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UserAdminWarehouseAvroModel>
    implements org.apache.avro.data.RecordBuilder<UserAdminWarehouseAvroModel> {

    private java.util.UUID userAdminWarehouseId;
    private java.util.UUID warehouseId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.userAdminWarehouseId)) {
        this.userAdminWarehouseId = data().deepCopy(fields()[0].schema(), other.userAdminWarehouseId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.warehouseId)) {
        this.warehouseId = data().deepCopy(fields()[1].schema(), other.warehouseId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
    }

    /**
     * Creates a Builder by copying an existing UserAdminWarehouseAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.userAdminWarehouseId)) {
        this.userAdminWarehouseId = data().deepCopy(fields()[0].schema(), other.userAdminWarehouseId);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.warehouseId)) {
        this.warehouseId = data().deepCopy(fields()[1].schema(), other.warehouseId);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'userAdminWarehouseId' field.
      * @return The value.
      */
    public java.util.UUID getUserAdminWarehouseId() {
      return userAdminWarehouseId;
    }


    /**
      * Sets the value of the 'userAdminWarehouseId' field.
      * @param value The value of 'userAdminWarehouseId'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder setUserAdminWarehouseId(java.util.UUID value) {
      validate(fields()[0], value);
      this.userAdminWarehouseId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'userAdminWarehouseId' field has been set.
      * @return True if the 'userAdminWarehouseId' field has been set, false otherwise.
      */
    public boolean hasUserAdminWarehouseId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'userAdminWarehouseId' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder clearUserAdminWarehouseId() {
      userAdminWarehouseId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'warehouseId' field.
      * @return The value.
      */
    public java.util.UUID getWarehouseId() {
      return warehouseId;
    }


    /**
      * Sets the value of the 'warehouseId' field.
      * @param value The value of 'warehouseId'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder setWarehouseId(java.util.UUID value) {
      validate(fields()[1], value);
      this.warehouseId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'warehouseId' field has been set.
      * @return True if the 'warehouseId' field has been set, false otherwise.
      */
    public boolean hasWarehouseId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'warehouseId' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder clearWarehouseId() {
      warehouseId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserAdminWarehouseAvroModel build() {
      try {
        UserAdminWarehouseAvroModel record = new UserAdminWarehouseAvroModel();
        record.userAdminWarehouseId = fieldSetFlags()[0] ? this.userAdminWarehouseId : (java.util.UUID) defaultValue(fields()[0]);
        record.warehouseId = fieldSetFlags()[1] ? this.warehouseId : (java.util.UUID) defaultValue(fields()[1]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UserAdminWarehouseAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<UserAdminWarehouseAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UserAdminWarehouseAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<UserAdminWarehouseAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}











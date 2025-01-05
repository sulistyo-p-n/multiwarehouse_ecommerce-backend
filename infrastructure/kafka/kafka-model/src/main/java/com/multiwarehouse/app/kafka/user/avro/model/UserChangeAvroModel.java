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
public class UserChangeAvroModel extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -3652848813442156699L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UserChangeAvroModel\",\"namespace\":\"com.multiwarehouse.app.kafka.user.avro.model\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"userId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"username\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"email\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"password\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"role\",\"type\":{\"type\":\"enum\",\"name\":\"UserRole\",\"symbols\":[\"CUSTOMER\",\"SUPER_ADMIN\",\"WAREHOUSE_ADMIN\"]}},{\"name\":\"active\",\"type\":\"boolean\"},{\"name\":\"userAdminWarehouse\",\"type\":[\"null\",{\"type\":\"record\",\"name\":\"UserAdminWarehouseAvroModel\",\"fields\":[{\"name\":\"userAdminWarehouseId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}},{\"name\":\"warehouseId\",\"type\":{\"type\":\"string\",\"logicalType\":\"uuid\"}}]}]},{\"name\":\"actionType\",\"type\":{\"type\":\"enum\",\"name\":\"ActionType\",\"symbols\":[\"DEFAULT\",\"CREATED\",\"UPDATED\",\"SOFT_DELETED\",\"HARD_DELETED\"]}},{\"name\":\"createdAt\",\"type\":{\"type\":\"long\",\"logicalType\":\"timestamp-millis\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();
  static {
    MODEL$.addLogicalTypeConversion(new org.apache.avro.Conversions.UUIDConversion());
    MODEL$.addLogicalTypeConversion(new org.apache.avro.data.TimeConversions.TimestampMillisConversion());
  }

  private static final BinaryMessageEncoder<UserChangeAvroModel> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UserChangeAvroModel> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<UserChangeAvroModel> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<UserChangeAvroModel> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<UserChangeAvroModel> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this UserChangeAvroModel to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a UserChangeAvroModel from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a UserChangeAvroModel instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static UserChangeAvroModel fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.util.UUID id;
  private java.util.UUID userId;
  private java.lang.String username;
  private java.lang.String email;
  private java.lang.String password;
  private com.multiwarehouse.app.kafka.user.avro.model.UserRole role;
  private boolean active;
  private com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel userAdminWarehouse;
  private com.multiwarehouse.app.kafka.user.avro.model.ActionType actionType;
  private java.time.Instant createdAt;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UserChangeAvroModel() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param userId The new value for userId
   * @param username The new value for username
   * @param email The new value for email
   * @param password The new value for password
   * @param role The new value for role
   * @param active The new value for active
   * @param userAdminWarehouse The new value for userAdminWarehouse
   * @param actionType The new value for actionType
   * @param createdAt The new value for createdAt
   */
  public UserChangeAvroModel(java.util.UUID id, java.util.UUID userId, java.lang.String username, java.lang.String email, java.lang.String password, com.multiwarehouse.app.kafka.user.avro.model.UserRole role, java.lang.Boolean active, com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel userAdminWarehouse, com.multiwarehouse.app.kafka.user.avro.model.ActionType actionType, java.time.Instant createdAt) {
    this.id = id;
    this.userId = userId;
    this.username = username;
    this.email = email;
    this.password = password;
    this.role = role;
    this.active = active;
    this.userAdminWarehouse = userAdminWarehouse;
    this.actionType = actionType;
    this.createdAt = createdAt.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return userId;
    case 2: return username;
    case 3: return email;
    case 4: return password;
    case 5: return role;
    case 6: return active;
    case 7: return userAdminWarehouse;
    case 8: return actionType;
    case 9: return createdAt;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  private static final org.apache.avro.Conversion<?>[] conversions =
      new org.apache.avro.Conversion<?>[] {
      new org.apache.avro.Conversions.UUIDConversion(),
      new org.apache.avro.Conversions.UUIDConversion(),
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      new org.apache.avro.data.TimeConversions.TimestampMillisConversion(),
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
    case 0: id = (java.util.UUID)value$; break;
    case 1: userId = (java.util.UUID)value$; break;
    case 2: username = value$ != null ? value$.toString() : null; break;
    case 3: email = value$ != null ? value$.toString() : null; break;
    case 4: password = value$ != null ? value$.toString() : null; break;
    case 5: role = (com.multiwarehouse.app.kafka.user.avro.model.UserRole)value$; break;
    case 6: active = (java.lang.Boolean)value$; break;
    case 7: userAdminWarehouse = (com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel)value$; break;
    case 8: actionType = (com.multiwarehouse.app.kafka.user.avro.model.ActionType)value$; break;
    case 9: createdAt = (java.time.Instant)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public java.util.UUID getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.util.UUID value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'userId' field.
   * @return The value of the 'userId' field.
   */
  public java.util.UUID getUserId() {
    return userId;
  }


  /**
   * Sets the value of the 'userId' field.
   * @param value the value to set.
   */
  public void setUserId(java.util.UUID value) {
    this.userId = value;
  }

  /**
   * Gets the value of the 'username' field.
   * @return The value of the 'username' field.
   */
  public java.lang.String getUsername() {
    return username;
  }


  /**
   * Sets the value of the 'username' field.
   * @param value the value to set.
   */
  public void setUsername(java.lang.String value) {
    this.username = value;
  }

  /**
   * Gets the value of the 'email' field.
   * @return The value of the 'email' field.
   */
  public java.lang.String getEmail() {
    return email;
  }


  /**
   * Sets the value of the 'email' field.
   * @param value the value to set.
   */
  public void setEmail(java.lang.String value) {
    this.email = value;
  }

  /**
   * Gets the value of the 'password' field.
   * @return The value of the 'password' field.
   */
  public java.lang.String getPassword() {
    return password;
  }


  /**
   * Sets the value of the 'password' field.
   * @param value the value to set.
   */
  public void setPassword(java.lang.String value) {
    this.password = value;
  }

  /**
   * Gets the value of the 'role' field.
   * @return The value of the 'role' field.
   */
  public com.multiwarehouse.app.kafka.user.avro.model.UserRole getRole() {
    return role;
  }


  /**
   * Sets the value of the 'role' field.
   * @param value the value to set.
   */
  public void setRole(com.multiwarehouse.app.kafka.user.avro.model.UserRole value) {
    this.role = value;
  }

  /**
   * Gets the value of the 'active' field.
   * @return The value of the 'active' field.
   */
  public boolean getActive() {
    return active;
  }


  /**
   * Sets the value of the 'active' field.
   * @param value the value to set.
   */
  public void setActive(boolean value) {
    this.active = value;
  }

  /**
   * Gets the value of the 'userAdminWarehouse' field.
   * @return The value of the 'userAdminWarehouse' field.
   */
  public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel getUserAdminWarehouse() {
    return userAdminWarehouse;
  }


  /**
   * Sets the value of the 'userAdminWarehouse' field.
   * @param value the value to set.
   */
  public void setUserAdminWarehouse(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel value) {
    this.userAdminWarehouse = value;
  }

  /**
   * Gets the value of the 'actionType' field.
   * @return The value of the 'actionType' field.
   */
  public com.multiwarehouse.app.kafka.user.avro.model.ActionType getActionType() {
    return actionType;
  }


  /**
   * Sets the value of the 'actionType' field.
   * @param value the value to set.
   */
  public void setActionType(com.multiwarehouse.app.kafka.user.avro.model.ActionType value) {
    this.actionType = value;
  }

  /**
   * Gets the value of the 'createdAt' field.
   * @return The value of the 'createdAt' field.
   */
  public java.time.Instant getCreatedAt() {
    return createdAt;
  }


  /**
   * Sets the value of the 'createdAt' field.
   * @param value the value to set.
   */
  public void setCreatedAt(java.time.Instant value) {
    this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
  }

  /**
   * Creates a new UserChangeAvroModel RecordBuilder.
   * @return A new UserChangeAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder newBuilder() {
    return new com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder();
  }

  /**
   * Creates a new UserChangeAvroModel RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UserChangeAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder newBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder other) {
    if (other == null) {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder();
    } else {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder(other);
    }
  }

  /**
   * Creates a new UserChangeAvroModel RecordBuilder by copying an existing UserChangeAvroModel instance.
   * @param other The existing instance to copy.
   * @return A new UserChangeAvroModel RecordBuilder
   */
  public static com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder newBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel other) {
    if (other == null) {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder();
    } else {
      return new com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder(other);
    }
  }

  /**
   * RecordBuilder for UserChangeAvroModel instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UserChangeAvroModel>
    implements org.apache.avro.data.RecordBuilder<UserChangeAvroModel> {

    private java.util.UUID id;
    private java.util.UUID userId;
    private java.lang.String username;
    private java.lang.String email;
    private java.lang.String password;
    private com.multiwarehouse.app.kafka.user.avro.model.UserRole role;
    private boolean active;
    private com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel userAdminWarehouse;
    private com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder userAdminWarehouseBuilder;
    private com.multiwarehouse.app.kafka.user.avro.model.ActionType actionType;
    private java.time.Instant createdAt;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.userId)) {
        this.userId = data().deepCopy(fields()[1].schema(), other.userId);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.username)) {
        this.username = data().deepCopy(fields()[2].schema(), other.username);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.email)) {
        this.email = data().deepCopy(fields()[3].schema(), other.email);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.password)) {
        this.password = data().deepCopy(fields()[4].schema(), other.password);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.role)) {
        this.role = data().deepCopy(fields()[5].schema(), other.role);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.active)) {
        this.active = data().deepCopy(fields()[6].schema(), other.active);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
      if (isValidValue(fields()[7], other.userAdminWarehouse)) {
        this.userAdminWarehouse = data().deepCopy(fields()[7].schema(), other.userAdminWarehouse);
        fieldSetFlags()[7] = other.fieldSetFlags()[7];
      }
      if (other.hasUserAdminWarehouseBuilder()) {
        this.userAdminWarehouseBuilder = com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.newBuilder(other.getUserAdminWarehouseBuilder());
      }
      if (isValidValue(fields()[8], other.actionType)) {
        this.actionType = data().deepCopy(fields()[8].schema(), other.actionType);
        fieldSetFlags()[8] = other.fieldSetFlags()[8];
      }
      if (isValidValue(fields()[9], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[9].schema(), other.createdAt);
        fieldSetFlags()[9] = other.fieldSetFlags()[9];
      }
    }

    /**
     * Creates a Builder by copying an existing UserChangeAvroModel instance
     * @param other The existing instance to copy.
     */
    private Builder(com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.userId)) {
        this.userId = data().deepCopy(fields()[1].schema(), other.userId);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.username)) {
        this.username = data().deepCopy(fields()[2].schema(), other.username);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.email)) {
        this.email = data().deepCopy(fields()[3].schema(), other.email);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.password)) {
        this.password = data().deepCopy(fields()[4].schema(), other.password);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.role)) {
        this.role = data().deepCopy(fields()[5].schema(), other.role);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.active)) {
        this.active = data().deepCopy(fields()[6].schema(), other.active);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.userAdminWarehouse)) {
        this.userAdminWarehouse = data().deepCopy(fields()[7].schema(), other.userAdminWarehouse);
        fieldSetFlags()[7] = true;
      }
      this.userAdminWarehouseBuilder = null;
      if (isValidValue(fields()[8], other.actionType)) {
        this.actionType = data().deepCopy(fields()[8].schema(), other.actionType);
        fieldSetFlags()[8] = true;
      }
      if (isValidValue(fields()[9], other.createdAt)) {
        this.createdAt = data().deepCopy(fields()[9].schema(), other.createdAt);
        fieldSetFlags()[9] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public java.util.UUID getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setId(java.util.UUID value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'userId' field.
      * @return The value.
      */
    public java.util.UUID getUserId() {
      return userId;
    }


    /**
      * Sets the value of the 'userId' field.
      * @param value The value of 'userId'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setUserId(java.util.UUID value) {
      validate(fields()[1], value);
      this.userId = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'userId' field has been set.
      * @return True if the 'userId' field has been set, false otherwise.
      */
    public boolean hasUserId() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'userId' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearUserId() {
      userId = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'username' field.
      * @return The value.
      */
    public java.lang.String getUsername() {
      return username;
    }


    /**
      * Sets the value of the 'username' field.
      * @param value The value of 'username'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setUsername(java.lang.String value) {
      validate(fields()[2], value);
      this.username = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'username' field has been set.
      * @return True if the 'username' field has been set, false otherwise.
      */
    public boolean hasUsername() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'username' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearUsername() {
      username = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'email' field.
      * @return The value.
      */
    public java.lang.String getEmail() {
      return email;
    }


    /**
      * Sets the value of the 'email' field.
      * @param value The value of 'email'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setEmail(java.lang.String value) {
      validate(fields()[3], value);
      this.email = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'email' field has been set.
      * @return True if the 'email' field has been set, false otherwise.
      */
    public boolean hasEmail() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'email' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearEmail() {
      email = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'password' field.
      * @return The value.
      */
    public java.lang.String getPassword() {
      return password;
    }


    /**
      * Sets the value of the 'password' field.
      * @param value The value of 'password'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setPassword(java.lang.String value) {
      validate(fields()[4], value);
      this.password = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'password' field has been set.
      * @return True if the 'password' field has been set, false otherwise.
      */
    public boolean hasPassword() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'password' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearPassword() {
      password = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'role' field.
      * @return The value.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserRole getRole() {
      return role;
    }


    /**
      * Sets the value of the 'role' field.
      * @param value The value of 'role'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setRole(com.multiwarehouse.app.kafka.user.avro.model.UserRole value) {
      validate(fields()[5], value);
      this.role = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'role' field has been set.
      * @return True if the 'role' field has been set, false otherwise.
      */
    public boolean hasRole() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'role' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearRole() {
      role = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'active' field.
      * @return The value.
      */
    public boolean getActive() {
      return active;
    }


    /**
      * Sets the value of the 'active' field.
      * @param value The value of 'active'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setActive(boolean value) {
      validate(fields()[6], value);
      this.active = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'active' field has been set.
      * @return True if the 'active' field has been set, false otherwise.
      */
    public boolean hasActive() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'active' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearActive() {
      fieldSetFlags()[6] = false;
      return this;
    }

    /**
      * Gets the value of the 'userAdminWarehouse' field.
      * @return The value.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel getUserAdminWarehouse() {
      return userAdminWarehouse;
    }


    /**
      * Sets the value of the 'userAdminWarehouse' field.
      * @param value The value of 'userAdminWarehouse'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setUserAdminWarehouse(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel value) {
      validate(fields()[7], value);
      this.userAdminWarehouseBuilder = null;
      this.userAdminWarehouse = value;
      fieldSetFlags()[7] = true;
      return this;
    }

    /**
      * Checks whether the 'userAdminWarehouse' field has been set.
      * @return True if the 'userAdminWarehouse' field has been set, false otherwise.
      */
    public boolean hasUserAdminWarehouse() {
      return fieldSetFlags()[7];
    }

    /**
     * Gets the Builder instance for the 'userAdminWarehouse' field and creates one if it doesn't exist yet.
     * @return This builder.
     */
    public com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder getUserAdminWarehouseBuilder() {
      if (userAdminWarehouseBuilder == null) {
        if (hasUserAdminWarehouse()) {
          setUserAdminWarehouseBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.newBuilder(userAdminWarehouse));
        } else {
          setUserAdminWarehouseBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.newBuilder());
        }
      }
      return userAdminWarehouseBuilder;
    }

    /**
     * Sets the Builder instance for the 'userAdminWarehouse' field
     * @param value The builder instance that must be set.
     * @return This builder.
     */

    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setUserAdminWarehouseBuilder(com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel.Builder value) {
      clearUserAdminWarehouse();
      userAdminWarehouseBuilder = value;
      return this;
    }

    /**
     * Checks whether the 'userAdminWarehouse' field has an active Builder instance
     * @return True if the 'userAdminWarehouse' field has an active Builder instance
     */
    public boolean hasUserAdminWarehouseBuilder() {
      return userAdminWarehouseBuilder != null;
    }

    /**
      * Clears the value of the 'userAdminWarehouse' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearUserAdminWarehouse() {
      userAdminWarehouse = null;
      userAdminWarehouseBuilder = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /**
      * Gets the value of the 'actionType' field.
      * @return The value.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.ActionType getActionType() {
      return actionType;
    }


    /**
      * Sets the value of the 'actionType' field.
      * @param value The value of 'actionType'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setActionType(com.multiwarehouse.app.kafka.user.avro.model.ActionType value) {
      validate(fields()[8], value);
      this.actionType = value;
      fieldSetFlags()[8] = true;
      return this;
    }

    /**
      * Checks whether the 'actionType' field has been set.
      * @return True if the 'actionType' field has been set, false otherwise.
      */
    public boolean hasActionType() {
      return fieldSetFlags()[8];
    }


    /**
      * Clears the value of the 'actionType' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearActionType() {
      actionType = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    /**
      * Gets the value of the 'createdAt' field.
      * @return The value.
      */
    public java.time.Instant getCreatedAt() {
      return createdAt;
    }


    /**
      * Sets the value of the 'createdAt' field.
      * @param value The value of 'createdAt'.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder setCreatedAt(java.time.Instant value) {
      validate(fields()[9], value);
      this.createdAt = value.truncatedTo(java.time.temporal.ChronoUnit.MILLIS);
      fieldSetFlags()[9] = true;
      return this;
    }

    /**
      * Checks whether the 'createdAt' field has been set.
      * @return True if the 'createdAt' field has been set, false otherwise.
      */
    public boolean hasCreatedAt() {
      return fieldSetFlags()[9];
    }


    /**
      * Clears the value of the 'createdAt' field.
      * @return This builder.
      */
    public com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel.Builder clearCreatedAt() {
      fieldSetFlags()[9] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UserChangeAvroModel build() {
      try {
        UserChangeAvroModel record = new UserChangeAvroModel();
        record.id = fieldSetFlags()[0] ? this.id : (java.util.UUID) defaultValue(fields()[0]);
        record.userId = fieldSetFlags()[1] ? this.userId : (java.util.UUID) defaultValue(fields()[1]);
        record.username = fieldSetFlags()[2] ? this.username : (java.lang.String) defaultValue(fields()[2]);
        record.email = fieldSetFlags()[3] ? this.email : (java.lang.String) defaultValue(fields()[3]);
        record.password = fieldSetFlags()[4] ? this.password : (java.lang.String) defaultValue(fields()[4]);
        record.role = fieldSetFlags()[5] ? this.role : (com.multiwarehouse.app.kafka.user.avro.model.UserRole) defaultValue(fields()[5]);
        record.active = fieldSetFlags()[6] ? this.active : (java.lang.Boolean) defaultValue(fields()[6]);
        if (userAdminWarehouseBuilder != null) {
          try {
            record.userAdminWarehouse = this.userAdminWarehouseBuilder.build();
          } catch (org.apache.avro.AvroMissingFieldException e) {
            e.addParentField(record.getSchema().getField("userAdminWarehouse"));
            throw e;
          }
        } else {
          record.userAdminWarehouse = fieldSetFlags()[7] ? this.userAdminWarehouse : (com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel) defaultValue(fields()[7]);
        }
        record.actionType = fieldSetFlags()[8] ? this.actionType : (com.multiwarehouse.app.kafka.user.avro.model.ActionType) defaultValue(fields()[8]);
        record.createdAt = fieldSetFlags()[9] ? this.createdAt : (java.time.Instant) defaultValue(fields()[9]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UserChangeAvroModel>
    WRITER$ = (org.apache.avro.io.DatumWriter<UserChangeAvroModel>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UserChangeAvroModel>
    READER$ = (org.apache.avro.io.DatumReader<UserChangeAvroModel>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}











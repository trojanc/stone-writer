package coza.trojanc.receipt.loader

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder

class ReceiptTypeResolverBuilder() : StdTypeResolverBuilder(JsonTypeInfo.Id.NAME, JsonTypeInfo.As.PROPERTY, "type")

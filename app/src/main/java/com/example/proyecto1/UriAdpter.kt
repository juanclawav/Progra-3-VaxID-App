package com.example.proyecto1

import android.net.Uri
import com.google.gson.*
import java.lang.reflect.Type

internal class UriAdpter : JsonSerializer<Uri>, JsonDeserializer<Uri> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Uri {
        return Uri.parse(json.asString)
    }
    override fun serialize(
        src: Uri,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
         return JsonPrimitive(src.toString())
    }

}

@file:Suppress("PackageDirectoryMismatch")

package cad97.spawnercraftkt.extensions.JsonObject

import com.google.gson.JsonElement
import com.google.gson.JsonObject

operator fun JsonObject.set(key: String, value: JsonElement) = this.add(key, value)
operator fun JsonObject.set(key: String, value: Char) = this.addProperty(key, value)
operator fun JsonObject.set(key: String, value: Number) = this.addProperty(key, value)
operator fun JsonObject.set(key: String, value: String) = this.addProperty(key, value)
operator fun JsonObject.set(key: String, value: Boolean) = this.addProperty(key, value)

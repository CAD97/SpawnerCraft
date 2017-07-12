package cad97.spawnercraftkt.recipe

import cad97.spawnercraftkt.Config
import cad97.spawnercraftkt.extensions.JsonObject.set
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSyntaxException
import net.minecraft.item.crafting.IRecipe
import net.minecraftforge.common.crafting.CraftingHelper
import net.minecraftforge.common.crafting.IRecipeFactory
import net.minecraftforge.common.crafting.JsonContext
import kotlin.reflect.full.memberProperties

@Suppress("unused")
class ConfigDependentShapedRecipeFactory : IRecipeFactory {
    override fun parse(context: JsonContext, json: JsonObject): IRecipe {
        val enabledPropertyName = json["config"].asString
        val enabledProperty = Config::class.memberProperties
                .firstOrNull { it.name == enabledPropertyName }
                ?: throw JsonSyntaxException("config_dependent recipe config must be a valid config name")
        val enabled = enabledProperty.get(Config) as? Boolean
                ?: throw JsonSyntaxException("config_dependent recipe config must be a boolean element")
        return if (enabled) {
            json["type"] = JsonPrimitive("minecraft:crafting_shaped")
            CraftingHelper.getRecipe(json, context)
        } else {
            NullRecipe()
        }
    }

}

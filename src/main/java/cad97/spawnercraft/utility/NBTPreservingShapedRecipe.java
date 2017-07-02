package cad97.spawnercraft.utility;

import cad97.spawnercraft.SpawnerCraft;
import com.google.common.base.Objects;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.oredict.RecipeSorter;

import javax.annotation.Nonnull;

/**
 * A ShapedRecipe that requires all crafting ItemStacks to have the same TagCompound.
 * This TagCompound is then given to the output ItemStack.
 */
public class NBTPreservingShapedRecipe extends ShapedRecipes {

    static {
        RecipeSorter.register(SpawnerCraft.MOD_ID + ":nbtshaped", NBTPreservingShapedRecipe.class,
                RecipeSorter.Category.SHAPED, "after:minecraft:shaped");
    }

    public NBTPreservingShapedRecipe(String group, int width, int height, NonNullList<Ingredient> items, ItemStack output) {
        super(group, width, height, items, output);
    }

    private NBTTagCompound matchingCompound = null;

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        ItemStack output = super.getRecipeOutput();
        output.setTagCompound(matchingCompound);
        return output;
    }

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, World worldIn) {
        matchingCompound = null;

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemStack = inv.getStackInSlot(i);
            if (!itemStack.isEmpty()) {
                matchingCompound = itemStack.getTagCompound();
                break;
            }
        }

        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack itemStack = inv.getStackInSlot(i);
            if (!itemStack.isEmpty() && !Objects.equal(itemStack.getTagCompound(), matchingCompound)) {
                return false;
            }
        }

        return super.matches(inv, worldIn);
    }
}

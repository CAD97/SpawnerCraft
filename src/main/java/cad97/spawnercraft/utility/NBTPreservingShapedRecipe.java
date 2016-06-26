package cad97.spawnercraft.utility;

import com.google.common.base.Objects;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * A ShapedRecipe that requires all crafting ItemStacks to have the same TagCompound.
 * This TagCompound is then given to the output ItemStack.
 */
public class NBTPreservingShapedRecipe extends ShapedRecipes {

    public NBTPreservingShapedRecipe(int width, int height, ItemStack[] items, ItemStack output) {
        super(width, height, items, output);
    }

    private NBTTagCompound matchingCompound = null;

    @Override
    public ItemStack getRecipeOutput() {
        ItemStack output = super.getRecipeOutput();
        assert output != null;
        output.setTagCompound(matchingCompound);
        return output;
    }

    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, World worldIn) {
        matchingCompound = null;

        for (ItemStack recipeItem : recipeItems) {
            if (recipeItem != null) {
                matchingCompound = recipeItem.getTagCompound();
                break;
            }
        }

        for (ItemStack recipeItem : recipeItems) {
            if (recipeItem != null) {
                if (Objects.equal(recipeItem.getTagCompound(), matchingCompound)) {
                    return false;
                }
            }
        }

        return super.matches(inv, worldIn);
    }
}

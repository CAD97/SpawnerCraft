package cad97.spawnercraft.init;

import cad97.spawnercraft.handler.ConfigHandler;
import cad97.spawnercraft.utility.LogHelper;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;
import java.util.Map;

public class SpawnerCraftMobAlias {

    public static final Map<String, NBTTagCompound> customNBT = new HashMap<String, NBTTagCompound>();
    public static final Map<String, String> aliasedIDs = new HashMap<String, String>();
    public static final Map<String, EntityList.EntityEggInfo> customEggs = new HashMap<String, EntityList.EntityEggInfo>();

    public static void registerMobAliases() {

        if (ConfigHandler.witherSkeletonSoul && !EntityList.ENTITY_EGGS.containsKey("WitherSkeleton")) {
            NBTTagCompound wither = new NBTTagCompound();      // {
            wither.setString("id", "WitherSkeleton");          //   id:"WitherSkeleton",
            wither.setByte("SkeletonType", (byte) 1);          //   SkeletonType:1b,
            NBTTagList handItems = new NBTTagList();           //   HandItems:[
            NBTTagCompound mainHand = new NBTTagCompound();    //     0:{
            mainHand.setString("id", "minecraft:stone_sword"); //       id:"minecraft:stone_sword"
            handItems.appendTag(mainHand);                     //     }
            wither.setTag("HandItems", handItems);             //   ]
            customNBT.put("WitherSkeleton", wither);           // }
            aliasedIDs.put("WitherSkeleton", "Skeleton");
        }

        if (ConfigHandler.elderGuardianSoul && !EntityList.ENTITY_EGGS.containsKey("ElderGuardian")) {
            NBTTagCompound elder = new NBTTagCompound();       // {
            elder.setString("id", "ElderGuardian");            //   id:"ElderGuardian",
            elder.setByte("Elder", (byte) 1);                  //   Elder:1b
            customNBT.put("ElderGuardian", elder);             // }
            aliasedIDs.put("ElderGuardian", "Guardian");
        }

        if (ConfigHandler.donkeySoul && !EntityList.ENTITY_EGGS.containsKey("Donkey")) {
            NBTTagCompound donkey = new NBTTagCompound();      // {
            donkey.setString("id", "Donkey");                  //   id:"Donkey",
            donkey.setByte("Type", (byte) 1);                  //   Type:1b
            customNBT.put("Donkey", donkey);                   // }
            aliasedIDs.put("Donkey", "EntityHorse");
        }

        if (ConfigHandler.muleSoul && !EntityList.ENTITY_EGGS.containsKey("Mule")) {
            NBTTagCompound mule = new NBTTagCompound();        // {
            mule.setString("id", "Mule");                      //   id:"Mule",
            mule.setByte("Type", (byte) 2);                    //   Type:2b
            customNBT.put("Mule", mule);                       // }
            aliasedIDs.put("Mule", "EntityHorse");
        }

        registerEggInfo();

        LogHelper.logInfo("Mob Aliases registered.");
        LogHelper.logDebug("These are only usable internally.");
    }

    @SuppressWarnings("ConstantConditions")
    private static void registerEggInfo() {
        if (ConfigHandler.witherSkeletonSoul) {
            customEggs.put("WitherSkeleton", new EntityList.EntityEggInfo(null, 0x131313, 0x3D3D3D));
        }
        if (ConfigHandler.elderGuardianSoul) {
            customEggs.put("ElderGuardian", new EntityList.EntityEggInfo(null, 0xC0BEAE, 0x5C5C75));
        }
        if (ConfigHandler.donkeySoul) {
            customEggs.put("Donkey", new EntityList.EntityEggInfo(null, 0x4D4035, 0x6A5C50));
        }
        if (ConfigHandler.muleSoul) {
            customEggs.put("Mule", new EntityList.EntityEggInfo(null, 0x190200, 0x402817));
        }
    }
}

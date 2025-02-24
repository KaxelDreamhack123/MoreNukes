//package com.dreamhack.morenukes.data;
//
//import com.dreamhack.morenukes.MoreNukes;
//import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
//import com.dreamhack.morenukes.registration.MNRegistrator;
//import net.minecraft.data.loot.packs.VanillaBlockLoot;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.entity.BlockEntityType;
//import net.minecraft.world.level.storage.loot.LootPool;
//import net.minecraft.world.level.storage.loot.LootTable;
//import net.minecraft.world.level.storage.loot.entries.DynamicLoot;
//import net.minecraft.world.level.storage.loot.entries.LootItem;
//import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
//import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
//import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
//import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
//import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
//import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.Map;
//import java.util.stream.Collectors;
//
//public class MNLootTablesGen extends VanillaBlockLoot {
//
//    @Override
//    protected void generate() {
//        createStandardTable(MNRegistrator.RBMK_FUEL_CORE.get(), MNRegistrator.RBMK_FUEL_CORE_ENTITY.get(), RBMKFuelCoreEntity.ITEMS_INPUT_TAG);
//    }
//
//    @Override
//    protected Iterable<Block> getKnownBlocks() {
//        return ForgeRegistries.BLOCKS.getEntries().stream()
//                .filter(e -> e.getKey().location().getNamespace().equals(MoreNukes.MODID))
//                .map(Map.Entry::getValue)
//                .collect(Collectors.toList());
//    }
//
//    private void createStandardTable(Block block, BlockEntityType<?> type, String... tags) {
//        LootPoolSingletonContainer.Builder<?> lti = LootItem.lootTableItem(block);
//        lti.apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY));
//        for (String tag : tags) {
//            lti.apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy(tag, "BlockEntityTag." + tag, CopyNbtFunction.MergeStrategy.REPLACE));
//        }
//        lti.apply(SetContainerContents.setContents(type).withEntry(DynamicLoot.dynamicEntry(new ResourceLocation("minecraft", "contents"))));
//
//        LootPool.Builder builder = LootPool.lootPool()
//                .setRolls(ConstantValue.exactly(1))
//                .add(lti);
//        add(block, LootTable.lootTable().withPool(builder));
//    }
//
//}

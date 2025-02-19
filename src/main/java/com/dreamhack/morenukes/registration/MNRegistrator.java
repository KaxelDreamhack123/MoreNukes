package com.dreamhack.morenukes.registration;

import com.dreamhack.morenukes.MoreNukes;
import com.dreamhack.morenukes.blocks.RBMKFuelColumnPart;
import com.dreamhack.morenukes.blocks.RBMKFuelCore;
import com.dreamhack.morenukes.blocks.entities.RBMKFuelCoreEntity;
import com.dreamhack.morenukes.containers.RBMKFuelCoreContainer;
import com.dreamhack.morenukes.items.GasWelderItem;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MNRegistrator {

    /************ REGISTRATORS ***********/
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MoreNukes.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreNukes.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreNukes.MODID);
    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MoreNukes.MODID);

    /************ BLOCKS ***********/
    //RBMK FUEL CORE
    public static final RegistryObject<RBMKFuelCore> RBMK_FUEL_CORE = BLOCKS.register("rbmk_fuel_core", RBMKFuelCore::new);

    //RBMK FUEL COLUMN PART
    public static final RegistryObject<RBMKFuelColumnPart> RBMK_FUEL_COLUMN_PART = BLOCKS.register("rbmk_fuel_column_part", RBMKFuelColumnPart::new);

    /************ ITEMS ***********/
    //RBMK FUEL CORE ITEM
    public static final RegistryObject<Item> RBMK_FUEL_CORE_ITEM = ITEMS.register("rbmk_fuel_core", () -> new BlockItem(RBMK_FUEL_CORE.get(), new Item.Properties()));

    //RBMK FUEL COLUMN PART ITEM
    public static final RegistryObject<Item> RBMK_FUEL_COLUMN_PART_ITEM = ITEMS.register("rbmk_fuel_column_part", () -> new BlockItem(RBMK_FUEL_COLUMN_PART.get(), new Item.Properties()));

    //GAS WELDER ITEM
    public static final RegistryObject<GasWelderItem> GAS_WELDER = ITEMS.register("gas_welder",
            () -> new GasWelderItem(new Item.Properties().stacksTo(1).durability(100)));

    /************ ENTITIES ***********/
    //RBMK FUEL CORE ENTITY
    public static final RegistryObject<BlockEntityType<RBMKFuelCoreEntity>> RBMK_FUEL_CORE_ENTITY =
            BLOCK_ENTITIES.register("rbmk_fuel_core",
                    () -> BlockEntityType.Builder.of(RBMKFuelCoreEntity::new, MNRegistrator.RBMK_FUEL_CORE.get()).build(null));

    /************ CONTAINERS ***********/
    //RBMK FUEL COLUMN PART CONTAINER
    public static final RegistryObject<MenuType<RBMKFuelCoreContainer>> RBMK_FUEL_CORE_CONTAINER = MENU_TYPES.register("rbmk_fuel_core",
            () -> IForgeMenuType.create((windowId, inv, data) -> new RBMKFuelCoreContainer(windowId, inv, data.readBlockPos())));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
        BLOCK_ENTITIES.register(eventBus);
        MENU_TYPES.register(eventBus);
    }

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(RBMK_FUEL_CORE_ITEM);
            event.accept(RBMK_FUEL_COLUMN_PART_ITEM);
            event.accept(GAS_WELDER);
        }
    }

}

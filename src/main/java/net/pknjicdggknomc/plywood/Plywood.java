package net.pknjicdggknomc.plywood;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Plywood - a wood-themed pane block that uses vanilla glass pane
 * placement/connection behaviour, wood sounds, and the vanilla
 * striped oak log texture.
 */
@Mod(Plywood.MOD_ID)
public class Plywood {
    public static final String MOD_ID = "plywood";

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MOD_ID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MOD_ID);

    /**
     * Behaviour and physical properties copied from the vanilla glass pane:
     * thin pane shape via IronBarsBlock placement/connect logic, 0.3 strength,
     * no tool required, wood sounds instead of glass.
     */
    public static final DeferredBlock<IronBarsBlock> PLYWOOD_PANE = BLOCKS.registerBlock(
            "plywood_pane",
            IronBarsBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .strength(0.3F)
                    .sound(SoundType.WOOD)
                    .noOcclusion()
                    .forceSolidOn()
    );

    public Plywood(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);

        ITEMS.registerSimpleBlockItem("plywood_pane", PLYWOOD_PANE, new Item.Properties());

        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.COLORED_BLOCKS
                || event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(PLYWOOD_PANE);
        }
    }
}

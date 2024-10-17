package net.nick.testmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nick.testmod.TestMod;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> TEST_MOD_TAB = CREATIVE_MODE_TABS.register("Test Mod",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIGHTNING_ESSENCE.get()))
                    .title(Component.translatable("Test Mod"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LIGHTNING_ESSENCE.get());

                        pOutput.accept(ModItems.GLOCK.get());



                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}

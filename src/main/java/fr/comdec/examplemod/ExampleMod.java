package fr.comdec.examplemod;

import fr.comdec.examplemod.registry.BiomeModifierRegistry;
import fr.comdec.examplemod.registry.BlockRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ExampleMod.MODID)
public class ExampleMod {

    public static final String MODID = "examplemod";

    public ExampleMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockRegistry.BLOCKS.register(bus);
        BiomeModifierRegistry.MODIFIERS.register(bus);
    }
}

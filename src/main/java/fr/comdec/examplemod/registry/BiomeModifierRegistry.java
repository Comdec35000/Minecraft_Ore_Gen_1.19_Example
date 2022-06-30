package fr.comdec.examplemod.registry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.comdec.examplemod.ExampleMod;
import fr.comdec.examplemod.world.gen.ExampleOreGenerator;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeModifierRegistry {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> MODIFIERS = DeferredRegister
            .create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ExampleMod.MODID);

    public static final RegistryObject<Codec<ExampleOreGenerator>> EXAMPLE_ORE = MODIFIERS.register(
            "example_ore_gen", () -> ExampleOreGenerator.CODEC);

}

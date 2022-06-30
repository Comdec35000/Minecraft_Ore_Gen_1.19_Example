package fr.comdec.examplemod.world.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import fr.comdec.examplemod.registry.BiomeModifierRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record ExampleOreGenerator(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) implements BiomeModifier {

    public static final Codec<ExampleOreGenerator> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(ExampleOreGenerator::biomes),
            PlacedFeature.CODEC.fieldOf("feature").forGetter(ExampleOreGenerator::feature)
    ).apply(builder, ExampleOreGenerator::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {

        System.out.println("modify");
        System.out.println(biome);

        if (phase == Phase.ADD && biomes.contains(biome)) {
            builder.getGenerationSettings().addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return BiomeModifierRegistry.EXAMPLE_ORE.get();
    }
}
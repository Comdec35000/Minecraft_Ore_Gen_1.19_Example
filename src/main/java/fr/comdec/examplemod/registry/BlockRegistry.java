package fr.comdec.examplemod.registry;

import fr.comdec.examplemod.ExampleMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.Keys.BLOCKS, ExampleMod.MODID);

    public static final RegistryObject<Block> EXAMPLE_ORE =
            BLOCKS.register("example_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
}

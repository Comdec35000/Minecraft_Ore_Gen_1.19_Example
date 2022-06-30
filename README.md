# Generating Ores

Minecraft 1.19 has a very different approach on how to implement ore generation in the game. 
This is meant to ease the customization of the world using datapacks.<br/>

Here's a quick tutorial on how to implement ore generation in minecraft 1.19 (using Forge 41.0.62) :

### Step 1 : Registering an Ore

First, register a block using a `DeferredRegister` like in [`BlockRegistry`](https://github.com/Comdec35000/Minecraft_Ore_Gen_1.19_Example/blob/master/src/main/java/fr/comdec/examplemod/registry/BlockRegistry.java)<br/>
You can then add basics graphics and localized name in the assets of the mod.<br/>

Also don't forget to register it to the Event Bus in your mod main class.<br/>

### Step 2 : Creating a Biome modifier

There's plenty of types of biome modifiers, but we will just use the `forge:add_features` one here, witch is already implemented.<br/>
To implement your custom BiomeModifier check [this](https://forge.gemwire.uk/wiki/Biome_Modifiers) out.<br/>

To create our biome modifier, we just have to create a json file at `data/<modid>/forge/biome_modifier/` :<br/>
````json
{
  "type" : "forge:add_features",
  "biomes" : "#minecraft:is_overworld",
  "features": "examplemod:example_ore_gen",
  "step": "underground_ores"
}
````

`type` is the biome modifier identifier of the biome modifier we want to use. <br/>
`biomes` is the biome identifier, the biome tag or even the array of biome identifier representing the Biome(s) we want to affect. <br/>
`features` is the feature identifier, the feature tag or the array of features representing the PlacedFeature(s) we want to add in these biome.<br/>
`step` is used to specify at witch step of the generation we want to add our features.<br/>


### Step 3 : Creating a Feature

To implement custom Features, create a class extending `PlacedFeature` and register it to  `ForgeRegistries.Keys.FEATURES`.<br/>
It's not needed here, we will just use the already implemented `minecraft:ore` one.<br/>

First, we will create a json file at `data/<modid>/worldgen/configured_feature/`.<br/>
It just need basically to have a `type` and a `config` key, to represent a feature to place (ores, trees, flowers...).<br/>

here, the content will be : <br/>

````json
{
  "type": "minecraft:ore",
  "config": {
    "size": 7,
    "discard_chance_on_air_exposure": 0.0,
    "targets": [
      {
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:stone_ore_replaceables"
        },
        "state": {
          "Name": "examplemod:example_ore"
        }
      },
      {
        "target": {
          "predicate_type": "minecraft:tag_match",
          "tag": "minecraft:deepslate_ore_replaceables"
        },
        "state": {
          "Name": "examplemod:example_ore"
        }
      }
    ]
  }
}
````

`type` : the type of PlacedFeature we want (here it's `minecraft:ore`, because we want to generate ores)<br/>
`config.size` : approximately the average count of blocks in each depot (int values only)<br/>
`config.discard_chance_on_air_exposure` : a float value between 0.0 and 1.0 witch represents the probability of the block not being placed if there's air next to it<br/>
`config.targets` : an array of objects with a target (witch select blocks to replace) and a state (a blockstate to set)<br/>

You can generate this json using [this tool](https://misode.github.io/worldgen/feature/?version=1.19)<br/>
More on the Configured feature Json [here](https://minecraft.fandom.com/wiki/Configured_feature)<br/>


Next, we will create a placed feature json at `data/<modid>/worldgen/placed_feature/`, to place our configured feature :<br/>
```json
{
  "feature": "examplemod:example_ore_gen",
  "placement": [
    {
      "type": "minecraft:count",
      "count": 14
    },
    {
      "type": "minecraft:height_range",
      "height": {
        "type": "minecraft:trapezoid",
        "max_inclusive": {
          "absolute": 64
        },
        "min_inclusive": {
          "absolute": -64
        }
      }
    }
  ]
}
```

`feature` : the identifier of the configured feature you just created<br/>
`placement` : the placement rules of you feature<br/>

Example of rules : <br/>
 Count : the amount of the feature generated (by chunk ?)<br/>
 Height range : the two height between witch your feature is generated<br/>

You can generate this json using [this tool](https://misode.github.io/worldgen/placed-feature/?version=1.19)<br/>
More on the Placed feature Json [here](https://minecraft.fandom.com/wiki/Placed_feature)<br/>

## Credits :

Thanks to TurtyWurty for the help with the Biome Modifiers<br/>
Thanks to Stoupy51 and Moimrvince for the help with placement features<br/>
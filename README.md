# Affogato Core

This is a core mod used in my modpacks that provides features, tweaks, tools and utilities to modify game behavior which cannot be modified by regular means. It provides a config powered by Fzzy Config that allows toggling features and tweaks on and off and using the tools and utilities provided in the mod for your own needs.

Features:
- Villager Nuke: Disables villager trading and makes all villages spawn as their abandoned variants. Wandering traders can be excluded from this.
- Raid Rework: Raids start in outposts instead of villages and winning the raid will prevent the outpost from spawning any more pillagers. Pairs well with Villager Nuke. Can support modded outposts by adding them to the `affogatotweaks:outposts` tag.
- Night Vision Nuke: Makes night vision unobtainable in survival by removing the recipe and preventing suspicious stew from getting the effect.
- Stone Tool Nuke: Makes stone tools unobtainable in survival, allows mining copper with wood pickaxes and replaces the stone swords on wither skeletons with golden swords.
- Animals Look For Food: Allows animals to move towards and eat their preferred food item from the ground when it's dropped nearby.
- Hard Difficulty Default: Difficulty on create world screen defaults to Hard and freshly created servers default to Hard in server.properties.
- Holding Anvil Applies Slowness: Constantly applies a heavy slowness effect while holding an anvil.
- No Riptide In Rain: Prevents Riptide from working in rain.

Tools and utilities:
- Adjust exhaustion values for all vanilla actions and a few additional actions that don't cause any exhaustion in vanilla.
- Adjust durability values for armor and unique items.
- Adjust attack damage, attack speed, durability and mining speed values for all tools in the game.
- Adjust burn time values for existing fuels, and add new fuels and specifying burn times for them.
- Remove recipes from the game based on ID.
- Hide items from creative tabs and recipe viewers based on ID.
- Specify loot replacements, which replace one item with another item in every loot table.
- Adjust the chance for dungeons and mineshafts to generate.
- Apply a global multiplier to ore size, and modify generation rules for ancient debris.
- Remove all XP rewards from smelting and blasting recipes, while allowing and specifying the XP reward for recipes which match the specified input or output item.
- Adjust the amount of XP dropped from mining ore.
- Prevent XP drops from all mobs, fishing, and Ender Dragon kills after the first.
- Adjust follow range for a bunch of mobs.
- Disable rapid healing. Works with AppleSkin.
- Adjust the time required to eat food items.
- Cancel eating on taking damage, excluding damage sources specified in the `affogatotweaks:no_eat_cancel` tag.
- Adjust the light level of torches.
- Adjust the friction of ice blocks.
- ... and a bunch of other miscellaneous tweaks too specific to list here.

Affogato-specific features:
- Affogato Block Loot Table Edits: Prevents gravel from dropping flint (obtained by shoveling gravel on the Farmer's Delight cutting table instead), increases the drop chance of jungle leaves, and increases the amount of quartz dropped from quartz ore.
- Affogato Entity Loot Table Edits: Increases mob loot drop chances and quantities across the board to account for the removal of Looting.
- Affogato Recipe Edits: Reduces the amount of iron and gold required in recipes using them to account for the removal of Fortune. Adds recipes for name tags, glowstone dust, and crying obsidian. Changes the empty map recipe to be much cheaper. If Farmer's Delight is present, reworks vanilla cake recipe to use Farmer's Delight ingredients, and makes shoveling gravel on the cutting table always drop flint.
- Affogato Wandering Trader Trades: Replaces wandering trader trades with custom trades.
- Affogato ItemSwapper Addon: Reworks some lists and palettes and adds support for items from several mods.
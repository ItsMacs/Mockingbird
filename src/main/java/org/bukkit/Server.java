//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package org.bukkit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityFactory;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemCraftResult;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.packs.DataPackManager;
import org.bukkit.packs.ResourcePack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Experimental;

public interface Server extends PluginMessageRecipient {
	String BROADCAST_CHANNEL_ADMINISTRATIVE = "bukkit.broadcast.admin";
	String BROADCAST_CHANNEL_USERS = "bukkit.broadcast.user";

	@NotNull
	String getName();

	@NotNull
	String getVersion();

	@NotNull
	String getBukkitVersion();

	@NotNull
	Collection<? extends Player> getOnlinePlayers();

	int getMaxPlayers();

	void setMaxPlayers(int var1);

	int getPort();

	int getViewDistance();

	int getSimulationDistance();

	@NotNull
	String getIp();

	@NotNull
	String getWorldType();

	boolean getGenerateStructures();

	int getMaxWorldSize();

	boolean getAllowEnd();

	boolean getAllowNether();

	boolean isLoggingIPs();

	@NotNull
	List<String> getInitialEnabledPacks();

	@NotNull
	List<String> getInitialDisabledPacks();

	@NotNull
	DataPackManager getDataPackManager();

	@NotNull
	ServerTickManager getServerTickManager();

	@Nullable
	ResourcePack getServerResourcePack();

	@NotNull
	String getResourcePack();

	@NotNull
	String getResourcePackHash();

	@NotNull
	String getResourcePackPrompt();

	boolean isResourcePackRequired();

	boolean hasWhitelist();

	void setWhitelist(boolean var1);

	boolean isWhitelistEnforced();

	void setWhitelistEnforced(boolean var1);

	@NotNull
	Set<OfflinePlayer> getWhitelistedPlayers();

	void reloadWhitelist();

	int broadcastMessage(@NotNull String var1);

	@NotNull
	String getUpdateFolder();

	@NotNull
	File getUpdateFolderFile();

	long getConnectionThrottle();

	/** @deprecated */
	@Deprecated
	int getTicksPerAnimalSpawns();

	/** @deprecated */
	@Deprecated
	int getTicksPerMonsterSpawns();

	/** @deprecated */
	@Deprecated
	int getTicksPerWaterSpawns();

	/** @deprecated */
	@Deprecated
	int getTicksPerWaterAmbientSpawns();

	/** @deprecated */
	@Deprecated
	int getTicksPerWaterUndergroundCreatureSpawns();

	/** @deprecated */
	@Deprecated
	int getTicksPerAmbientSpawns();

	int getTicksPerSpawns(@NotNull SpawnCategory var1);

	@Nullable
	Player getPlayer(@NotNull String var1);

	@Nullable
	Player getPlayerExact(@NotNull String var1);

	@NotNull
	List<Player> matchPlayer(@NotNull String var1);

	@Nullable
	Player getPlayer(@NotNull UUID var1);

	@NotNull
	PluginManager getPluginManager();

	@NotNull
	BukkitScheduler getScheduler();

	@NotNull
	ServicesManager getServicesManager();

	@NotNull
	List<World> getWorlds();

	@Nullable
	World createWorld(@NotNull WorldCreator var1);

	boolean unloadWorld(@NotNull String var1, boolean var2);

	boolean unloadWorld(@NotNull World var1, boolean var2);

	@Nullable
	World getWorld(@NotNull String var1);

	@Nullable
	World getWorld(@NotNull UUID var1);

	@NotNull
	WorldBorder createWorldBorder();

	/** @deprecated */
	@Deprecated
	@Nullable
	MapView getMap(int var1);

	@NotNull
	MapView createMap(@NotNull World var1);

	@NotNull
	ItemStack createExplorerMap(@NotNull World var1, @NotNull Location var2, @NotNull StructureType var3);

	@NotNull
	ItemStack createExplorerMap(@NotNull World var1, @NotNull Location var2, @NotNull StructureType var3, int var4, boolean var5);

	void reload();

	void reloadData();

	@NotNull
	Logger getLogger();

	@Nullable
	PluginCommand getPluginCommand(@NotNull String var1);

	void savePlayers();

	boolean dispatchCommand(@NotNull CommandSender var1, @NotNull String var2) throws CommandException;

	@Contract("null -> false")
	boolean addRecipe(@Nullable Recipe var1);

	@NotNull
	List<Recipe> getRecipesFor(@NotNull ItemStack var1);

	@Nullable
	Recipe getRecipe(@NotNull NamespacedKey var1);

	@Nullable
	Recipe getCraftingRecipe(@NotNull ItemStack[] var1, @NotNull World var2);

	@NotNull
	ItemStack craftItem(@NotNull ItemStack[] var1, @NotNull World var2, @NotNull Player var3);

	@NotNull
	ItemStack craftItem(@NotNull ItemStack[] var1, @NotNull World var2);

	@NotNull
	ItemCraftResult craftItemResult(@NotNull ItemStack[] var1, @NotNull World var2, @NotNull Player var3);

	@NotNull
	ItemCraftResult craftItemResult(@NotNull ItemStack[] var1, @NotNull World var2);

	@NotNull
	Iterator<Recipe> recipeIterator();

	void clearRecipes();

	void resetRecipes();

	boolean removeRecipe(@NotNull NamespacedKey var1);

	@NotNull
	Map<String, String[]> getCommandAliases();

	int getSpawnRadius();

	void setSpawnRadius(int var1);

	/** @deprecated */
	@Deprecated
	boolean shouldSendChatPreviews();

	boolean isEnforcingSecureProfiles();

	boolean isAcceptingTransfers();

	boolean getHideOnlinePlayers();

	boolean getOnlineMode();

	boolean getAllowFlight();

	boolean isHardcore();

	void shutdown();

	int broadcast(@NotNull String var1, @NotNull String var2);

	/** @deprecated */
	@Deprecated
	@NotNull
	OfflinePlayer getOfflinePlayer(@NotNull String var1);

	@NotNull
	OfflinePlayer getOfflinePlayer(@NotNull UUID var1);

	@NotNull
	PlayerProfile createPlayerProfile(@Nullable UUID var1, @Nullable String var2);

	@NotNull
	PlayerProfile createPlayerProfile(@NotNull UUID var1);

	@NotNull
	PlayerProfile createPlayerProfile(@NotNull String var1);

	@NotNull
	Set<String> getIPBans();

	/** @deprecated */
	@Deprecated
	void banIP(@NotNull String var1);

	/** @deprecated */
	@Deprecated
	void unbanIP(@NotNull String var1);

	void banIP(@NotNull InetAddress var1);

	void unbanIP(@NotNull InetAddress var1);

	@NotNull
	Set<OfflinePlayer> getBannedPlayers();

	@NotNull
	<T extends BanList<?>> T getBanList(@NotNull BanList.Type var1);

	@NotNull
	Set<OfflinePlayer> getOperators();

	@NotNull
	GameMode getDefaultGameMode();

	void setDefaultGameMode(@NotNull GameMode var1);

	@NotNull
	ConsoleCommandSender getConsoleSender();

	@NotNull
	File getWorldContainer();

	@NotNull
	OfflinePlayer[] getOfflinePlayers();

	@NotNull
	Messenger getMessenger();

	@NotNull
	HelpMap getHelpMap();

	@NotNull
	Inventory createInventory(@Nullable InventoryHolder var1, @NotNull InventoryType var2);

	@NotNull
	Inventory createInventory(@Nullable InventoryHolder var1, @NotNull InventoryType var2, @NotNull String var3);

	@NotNull
	Inventory createInventory(@Nullable InventoryHolder var1, int var2) throws IllegalArgumentException;

	@NotNull
	Inventory createInventory(@Nullable InventoryHolder var1, int var2, @NotNull String var3) throws IllegalArgumentException;

	@NotNull
	Merchant createMerchant(@Nullable String var1);

	int getMaxChainedNeighborUpdates();

	/** @deprecated */
	@Deprecated
	int getMonsterSpawnLimit();

	/** @deprecated */
	@Deprecated
	int getAnimalSpawnLimit();

	/** @deprecated */
	@Deprecated
	int getWaterAnimalSpawnLimit();

	/** @deprecated */
	@Deprecated
	int getWaterAmbientSpawnLimit();

	/** @deprecated */
	@Deprecated
	int getWaterUndergroundCreatureSpawnLimit();

	/** @deprecated */
	@Deprecated
	int getAmbientSpawnLimit();

	int getSpawnLimit(@NotNull SpawnCategory var1);

	boolean isPrimaryThread();

	@NotNull
	String getMotd();

	void setMotd(@NotNull String var1);

	@NotNull
	@Experimental
	ServerLinks getServerLinks();

	@Nullable
	String getShutdownMessage();

	@NotNull
	Warning.WarningState getWarningState();

	@NotNull
	ItemFactory getItemFactory();

	@NotNull
	EntityFactory getEntityFactory();

	@Nullable
	ScoreboardManager getScoreboardManager();

	@NotNull
	Criteria getScoreboardCriteria(@NotNull String var1);

	@Nullable
	CachedServerIcon getServerIcon();

	@NotNull
	CachedServerIcon loadServerIcon(@NotNull File var1) throws IllegalArgumentException, Exception;

	@NotNull
	CachedServerIcon loadServerIcon(@NotNull BufferedImage var1) throws IllegalArgumentException, Exception;

	void setIdleTimeout(int var1);

	int getIdleTimeout();

	@NotNull
	ChunkGenerator.ChunkData createChunkData(@NotNull World var1);

	@NotNull
	BossBar createBossBar(@Nullable String var1, @NotNull BarColor var2, @NotNull BarStyle var3, @NotNull BarFlag... var4);

	@NotNull
	KeyedBossBar createBossBar(@NotNull NamespacedKey var1, @Nullable String var2, @NotNull BarColor var3, @NotNull BarStyle var4, @NotNull BarFlag... var5);

	@NotNull
	Iterator<KeyedBossBar> getBossBars();

	@Nullable
	KeyedBossBar getBossBar(@NotNull NamespacedKey var1);

	boolean removeBossBar(@NotNull NamespacedKey var1);

	@Nullable
	Entity getEntity(@NotNull UUID var1);

	@Nullable
	Advancement getAdvancement(@NotNull NamespacedKey var1);

	@NotNull
	Iterator<Advancement> advancementIterator();

	@NotNull
	BlockData createBlockData(@NotNull Material var1);

	@NotNull
	BlockData createBlockData(@NotNull Material var1, @Nullable Consumer<? super BlockData> var2);

	@NotNull
	BlockData createBlockData(@NotNull String var1) throws IllegalArgumentException;

	@NotNull
	@Contract("null, null -> fail")
	BlockData createBlockData(@Nullable Material var1, @Nullable String var2) throws IllegalArgumentException;

	@Nullable
	<T extends Keyed> Tag<T> getTag(@NotNull String var1, @NotNull NamespacedKey var2, @NotNull Class<T> var3);

	@NotNull
	<T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String var1, @NotNull Class<T> var2);

	@Nullable
	LootTable getLootTable(@NotNull NamespacedKey var1);

	@NotNull
	List<Entity> selectEntities(@NotNull CommandSender var1, @NotNull String var2) throws IllegalArgumentException;

	@NotNull
	StructureManager getStructureManager();

	@Nullable
	<T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> var1);

	/** @deprecated */
	@Deprecated
	@NotNull
	UnsafeValues getUnsafe();

	@NotNull
	Spigot spigot();

	public static class Spigot {
		public Spigot() {
		}

		@NotNull
		public YamlConfiguration getConfig() {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public void broadcast(@NotNull BaseComponent component) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public void broadcast(@NotNull BaseComponent... components) {
			throw new UnsupportedOperationException("Not supported yet.");
		}

		public void restart() {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
}

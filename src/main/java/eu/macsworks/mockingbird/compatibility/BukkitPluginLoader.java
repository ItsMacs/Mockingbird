package eu.macsworks.mockingbird.compatibility;

import eu.macsworks.mockingbird.MockingbirdServer;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.bukkit.plugin.java.LibraryLoader;
import org.bukkit.plugin.java.PluginClassLoader;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;

public class BukkitPluginLoader {

	private final Map<String, PluginHolder> plugins = new HashMap<>();
	private final LibraryLoader libraryLoader;

	public BukkitPluginLoader(){
		LibraryLoader libraryLoader = null;
		try {
			libraryLoader = new LibraryLoader(MockingbirdServer.getLogger());
		} catch (NoClassDefFoundError ex) {
			// Provided depends were not added back
			MockingbirdServer.getLogger().warning("Could not initialize LibraryLoader (missing dependencies?)");
		}
		this.libraryLoader = libraryLoader;
	}

	public void loadAllPlugins(){
		File pluginsFolder = new File("plugins/");
		File[] files = pluginsFolder.listFiles();

		if(files == null) return;

		for(File file : files){
			if(file.isDirectory()) continue;
			if(!file.getName().endsWith(".jar")) continue;

			try{
				Plugin plugin = (Plugin) loadPlugin(file);
				plugins.put(plugin.getName(), new PluginHolder(plugin.getName(), plugin));
			}catch (InvalidPluginException ex){
				ex.printStackTrace();
			}
		}
	}

	@NotNull
	public JavaPlugin loadPlugin(@NotNull final File file) throws InvalidPluginException {
		final PluginDescriptionFile description;
		try {
			description = getPluginDescription(file);
		} catch (InvalidDescriptionException ex) {
			throw new InvalidPluginException(ex);
		}

		final File dataFolder = new File("plugins/", description.getName());

		if (dataFolder.exists() && !dataFolder.isDirectory()) {
			throw new InvalidPluginException(String.format(
					"Projected datafolder: `%s' for %s (%s) exists and is not a directory",
					dataFolder,
					description.getFullName(),
					file
			));
		}

		for (final String pluginName : description.getDepend()) {
			Optional<Plugin> current = getPlugin(pluginName);

			if (current.isEmpty()) {
				throw new UnknownDependencyException("Unknown dependency " + pluginName + ". Please download and install " + pluginName + " to run this plugin.");
			}
		}

		final PluginClassLoader loader;
		try {
			loader = new PluginClassLoader(null, getClass().getClassLoader(), description, dataFolder, file, (libraryLoader != null) ? libraryLoader.createLoader(description) : null);
		} catch (InvalidPluginException ex) {
			throw ex;
		} catch (Throwable ex) {
			throw new InvalidPluginException(ex);
		}

		return loader.plugin;
	}

	@NotNull
	public PluginDescriptionFile getPluginDescription(@NotNull File file) throws InvalidDescriptionException {
		JarFile jar = null;
		InputStream stream = null;

		try {
			jar = new JarFile(file);
			JarEntry entry = jar.getJarEntry("plugin.yml");

			if (entry == null) {
				throw new IllegalArgumentException(new FileNotFoundException("Jar does not contain plugin.yml"));
			}

			stream = jar.getInputStream(entry);

			return new PluginDescriptionFile(stream);

		} catch (IOException ex) {
			throw new InvalidDescriptionException(ex);
		} catch (YAMLException ex) {
			throw new InvalidDescriptionException(ex);
		} finally {
			if (jar != null) {
				try {
					jar.close();
				} catch (IOException e) {
				}
			}
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public Optional<Plugin> getPlugin(String name){
		PluginHolder holder = plugins.get(name);
		if(holder == null) return Optional.empty();

		return Optional.of(holder.getPlugin());
	}

	public int pluginCount(){
		return plugins.size();
	}

}

@RequiredArgsConstructor @Data
class PluginHolder {
	private final String name;
	private final Plugin plugin;
	private PluginState state = PluginState.ENABLED;

	public void enablePlugin(){
		state = PluginState.ENABLED;

		plugin.onLoad();
		plugin.onEnable();
	}

	public void disablePlugin(){
		state = PluginState.DISABLED;

		plugin.onDisable();
	}


	enum PluginState {
		ENABLED,
		DISABLED;
	}
}

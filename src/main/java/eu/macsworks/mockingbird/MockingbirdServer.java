package eu.macsworks.mockingbird;

import eu.macsworks.mockingbird.compatibility.BukkitPluginLoader;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import org.bukkit.Server;

import java.util.logging.Logger;

@Getter
public class MockingbirdServer {

	@Getter
	private final static Logger logger = Logger.getLogger("MockingbirdServer");

	@Getter
	private static MockingbirdServer instance = null;

	private final BukkitPluginLoader pluginLoader = new BukkitPluginLoader();

	private Server fakeBukkitServer;


	public MockingbirdServer(){
		instance = this;
	}

	private void init(){
		MinecraftServer server = MinecraftServer.init();
		server.start("0.0.0.0", 25565);

		MinecraftServer.setBrandName("Mockingbird");

		getLogger().info("Loading Bukkit plugins...");
		pluginLoader.loadAllPlugins();
	}


	public static void main(String[] args) {
		MockingbirdServer mockingbirdServer = new MockingbirdServer();

		mockingbirdServer.init();

		MockingbirdServer.getLogger().info("Mockingbird started successfully on 25565!");
		MockingbirdServer.getLogger().info("Bukkit Plugins: " + mockingbirdServer.getPluginLoader().pluginCount());
	}

}
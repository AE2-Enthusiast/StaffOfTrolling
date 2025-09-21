package stone.sot;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import zone.rong.mixinbooter.IEarlyMixinLoader;

@Mod(modid = StaffOfTrolling.MODID, name = StaffOfTrolling.NAME, version = StaffOfTrolling.VERSION)
public class StaffOfTrolling implements IEarlyMixinLoader {
  public static final String MODID = "staffoftrolling";
  public static final String NAME = "Staff Of Trolling";
  public static final String VERSION = "1.1";

  @EventHandler
  public void preInit(FMLPreInitializationEvent event) {
    if (event.getSide() == Side.CLIENT) {
      KeybindHandler.init();
      CommandTeleport.init();
      CommandTarget.init();
    }

  }

  @EventHandler
  public void postInit(FMLPostInitializationEvent event) {
    KeybindHandler.postInit();
  }

  @Override
  public List<String> getMixinConfigs() {
    List<String> configs = new ArrayList<>();
    configs.add("mixins.staff_of_trolling.json");
    return configs;
  }
}

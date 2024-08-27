package stone.SOTTrolling;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = SOTTrolling.MODID, name = SOTTrolling.NAME, version = SOTTrolling.VERSION)
public class SOTTrolling
{
    public static final String MODID = "staffoftrolling";
    public static final String NAME = "Staff Of Trolling";
    public static final String VERSION = "1.0";

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	if (event.getSide() == Side.CLIENT) {
            KeybindHandler.init();
            CommandTeleport.init();
        }
                        
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        KeybindHandler.postInit();
    }
}

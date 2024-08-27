package stone.SOTTrolling;

import org.lwjgl.input.Keyboard;

import crazypants.enderio.api.teleport.TravelSource;
import crazypants.enderio.api.upgrades.IDarkSteelItem;
import crazypants.enderio.base.item.darksteel.upgrade.energy.EnergyUpgrade;
import crazypants.enderio.base.item.darksteel.upgrade.energy.EnergyUpgradeManager;
import static crazypants.enderio.base.init.ModObject.itemTravelStaff;
import crazypants.enderio.base.network.PacketHandler;
import crazypants.enderio.base.teleport.TravelController;
import crazypants.enderio.base.teleport.packet.PacketTravelEvent;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class KeybindHandler {
    public static KeyBinding keybindStaffTeleport;

    private static ItemStack staff;

    public static void init() {

        keybindStaffTeleport = new KeyBinding("Staff of Travel Teleport", Keyboard.KEY_G, "Staff Of Traveling Keybind");
        ClientRegistry.registerKeyBinding(keybindStaffTeleport);
    }

    public static void postInit() {
        IDarkSteelItem staffItem = (IDarkSteelItem) itemTravelStaff.getItemNN();
        staff = new ItemStack((Item) staffItem);
        EnergyUpgrade.UPGRADES.get(3).addToItem(staff, staffItem);
        EnergyUpgradeManager.setPowerFull(staff, staffItem);
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void clientPlayerTick(TickEvent.PlayerTickEvent event) {

        if (KeybindHandler.keybindStaffTeleport.isPressed()) {
            TravelController.doBlink(staff.copy(), EnumHand.MAIN_HAND, event.player);
            //PacketTravelEvent p = new PacketTravelEvent(new BlockPos(30000000, 128, 30000000), 0, false, TravelSource.TELEPAD, EnumHand.MAIN_HAND);
            //PacketHandler.INSTANCE.sendToServer(p);
        }

    }
}

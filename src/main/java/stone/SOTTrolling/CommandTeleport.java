package stone.SOTTrolling;

import crazypants.enderio.api.teleport.TravelSource;
import crazypants.enderio.base.network.PacketHandler;
import crazypants.enderio.base.teleport.packet.PacketTravelEvent;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.IClientCommand;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class CommandTeleport implements IClientCommand {

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        Entity entitySender = sender.getCommandSenderEntity();
        
        if (args.length != 3 || !(entitySender instanceof EntityPlayer)) {
            return;
        }
        BlockPos dest = CommandBase.parseBlockPos(sender, args, 0, false);
        PacketTravelEvent p = new PacketTravelEvent(dest, 0, false, TravelSource.TELEPAD, EnumHand.MAIN_HAND);
        PacketHandler.INSTANCE.sendToServer(p);
    }
    
    public static void init() {
        ClientCommandHandler.instance.registerCommand(new CommandTeleport());
    }

    @Override
    public String getName() {
        return "setTeleport";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "Teleport to the specified <x, y ,z> coords for free with no restrictions";
    }

    @Override
    public List<String> getAliases() {
        return Collections.<String>emptyList();
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.<String>emptyList();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }

    public int compareTo(ICommand command)
    {
        return this.getName().compareTo(command.getName());
    }
}

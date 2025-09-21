package stone.sot;

import crazypants.enderio.base.item.coordselector.TelepadTarget;
import crazypants.enderio.base.network.PacketHandler;
import crazypants.enderio.machines.machine.teleport.telepad.TileDialingDevice;
import crazypants.enderio.machines.machine.teleport.telepad.packet.PacketTargetList;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.IClientCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nullable;

public class CommandTarget implements IClientCommand {

    Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
        throws CommandException {
        Entity entitySender = sender.getCommandSenderEntity();

        if (args.length != 8 || !(entitySender instanceof EntityPlayer))
        {
            return;
        }
        BlockPos telepadPos = CommandBase.parseBlockPos(sender, args, 0, false);
        TileEntity te = sender.getEntityWorld().getTileEntity(telepadPos);
        if (te != null && te instanceof TileDialingDevice dialingDevice)
        {
            TelepadTarget target = new TelepadTarget(
                new BlockPos(Integer.valueOf(args[3]), Integer.valueOf(args[4]),
                    Integer.valueOf(args[5])),
                CommandBase.parseInt(args[6]), args[7], new ItemStack(Items.NETHER_STAR));
            PacketTargetList list = new PacketTargetList(dialingDevice, target, true);
            PacketHandler.INSTANCE.sendToServer(list);
        }

    }

    public static void init() {
        ClientCommandHandler.instance.registerCommand(new CommandTarget());
    }

    @Override
    public String getName() {
        return "setTarget";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "<dialing (x, y, z)> <target (x, y, z)> <dimId> <label> where dialing and target are BlockPoses";
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
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender,
        String[] args, @Nullable BlockPos targetPos) {
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

    @Override
    public int compareTo(ICommand command) {
        return this.getName().compareTo(command.getName());
    }
}

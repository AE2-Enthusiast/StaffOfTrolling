package stone.sot.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.network.NettyPacketDecoder;

@Mixin(NettyPacketDecoder.class)
public abstract class NettyPacketDecoderMixin {
  @Redirect(method = "decode", at = @At(value = "INVOKE", target = "Lorg/apache/logging/log4j/Logger;isDebugEnabled()Z", remap = false))
  public boolean onDebugCheck() {
    return true;
  }
}

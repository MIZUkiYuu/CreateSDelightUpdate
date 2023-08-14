package phoupraw.mcmod.createsdelight.mixin;

import com.simibubi.create.content.fluids.spout.SpoutBlockEntity;
import com.simibubi.create.content.fluids.spout.SpoutRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import phoupraw.mcmod.createsdelight.registry.MySpoutingBehaviours;
@Environment(EnvType.CLIENT)
@Mixin(value = SpoutRenderer.class)
public class MixinSpoutRenderer {
    private final ThreadLocal<SpoutBlockEntity> spout = new ThreadLocal<>();

    @Inject(method = "renderSafe(Lcom/simibubi/create/content/fluids/spout/SpoutBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At("HEAD"))
    private void capturePara(SpoutBlockEntity te, float partialTicks, MatrixStack ms, VertexConsumerProvider buffer, int light, int overlay, CallbackInfo ci) {
        spout.set(te);
    }

    @ModifyArg(method = "renderSafe(Lcom/simibubi/create/content/fluids/spout/SpoutBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Box;<init>(DDDDDD)V"), index = 4)
    private double modifyBottomY(double yMin) {
        var spout = this.spout.get();
        return yMin - 13 / 16.0 + ((MySpoutingBehaviours.SpoutExtra) spout).getBottomY();
    }
}

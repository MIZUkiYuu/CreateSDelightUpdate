package phoupraw.mcmod.createsdelight.block.entity.renderer;

import com.simibubi.create.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import phoupraw.mcmod.createsdelight.block.entity.MultifuncBasinBlockEntity;@Environment(EnvType.CLIENT)
public class MultifuncBasinRenderer extends SmartBlockEntityRenderer<MultifuncBasinBlockEntity> {
    public MultifuncBasinRenderer(BlockEntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(MultifuncBasinBlockEntity tileEntityIn, float partialTicks, MatrixStack ms, VertexConsumerProvider buffer, int light, int overlay) {
        super.renderSafe(tileEntityIn, partialTicks, ms, buffer, light, overlay);
    }
}

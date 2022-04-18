package kogasastudio.ashihara.client.render.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import kogasastudio.ashihara.block.tileentities.CuttingBoardTE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;

import static kogasastudio.ashihara.block.BlockCuttingBoard.AXIS;
import static kogasastudio.ashihara.helper.RenderHelper.XTP;

public class CuttingBoardTER extends TileEntityRenderer<CuttingBoardTE>
{
    public CuttingBoardTER(TileEntityRendererDispatcher rendererDispatcherIn) {super(rendererDispatcherIn);}

    @Override
    public void render(CuttingBoardTE tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn)
    {
        ItemStack stack = tileEntityIn.getContent();
        if (!stack.isEmpty())
        {
            matrixStackIn.push();
            boolean isXAxis = tileEntityIn.getBlockState().get(AXIS).equals(Direction.Axis.X);
            boolean isBlock = stack.getItem() instanceof BlockItem;

            float tHeight = isBlock ? 5.0f : 1.0f;
            matrixStackIn.translate(XTP(8.0f), XTP(tHeight), XTP(8.0f));
            matrixStackIn.scale(0.5f, 0.5f, 0.5f);
            matrixStackIn.rotate(Vector3f.XP.rotationDegrees(90.0f));
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(isXAxis ? 270 : 0));

            ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
            renderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, combinedOverlayIn, matrixStackIn, bufferIn);
            matrixStackIn.pop();
        }
    }
}
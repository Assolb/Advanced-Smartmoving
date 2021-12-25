package com.sm.client.renderers;

import org.lwjgl.opengl.GL11;

import com.sm.client.models.SteveModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class SteveHeldItemLayer extends GeoLayerRenderer {

	public SteveHeldItemLayer(IGeoRenderer entityRendererIn)
	{
		super(entityRendererIn);
	}

	@Override
	public boolean shouldCombineTextures()
	{
		return false;
	}

	@Override
	public void render(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
	{
		if(entitylivingbaseIn.getHeldItemMainhand() != null)
		{
			GlStateManager.pushMatrix();
			((SteveModel)getEntityModel()).postRenderRightArmDown(entitylivingbaseIn, partialTicks, false);
			GL11.glRotated(5, 1, 0, 0);
			GL11.glTranslated(0, 0.27, 0.3);
			Minecraft.getMinecraft().getItemRenderer().renderItemSide(entitylivingbaseIn, entitylivingbaseIn.getHeldItemMainhand(), TransformType.THIRD_PERSON_RIGHT_HAND, false);
			GlStateManager.popMatrix();
		}
	}
}

package com.sm.client.renderers;

import com.sm.client.models.SteveModel;
import com.sm.entities.SteveEntity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

public class SteveRenderer extends GeoReplacedEntityRenderer<SteveEntity> {
	
	public SteveRenderer(RenderManager renderManager)
	{
		super(renderManager, new SteveModel(), new SteveEntity());
		addLayer(new SteveHeldItemLayer(this));
		addLayer(new SteveArmorLayer(this));
	}
	
	@Override
	public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLivingBase entity)
	{
		return ((AbstractClientPlayer)entity).getLocationSkin();
	}
}

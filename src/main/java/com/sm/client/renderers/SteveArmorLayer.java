package com.sm.client.renderers;

import org.lwjgl.opengl.GL11;

import com.sm.client.models.SteveModel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

public class SteveArmorLayer extends GeoLayerRenderer {

	private ModelBiped modelLeggings = new ModelBiped(0.5F);
	private ModelBiped modelArmor = new ModelBiped(1.0F);
	
	public SteveArmorLayer(IGeoRenderer entityRendererIn)
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
		GL11.glPushMatrix();
		GlStateManager.disableCull();
		GlStateManager.enableBlend();
		for(ItemStack item : entitylivingbaseIn.getArmorInventoryList())
		{
			if(item.getItem() == Items.AIR) continue;
			ItemArmor armor = (ItemArmor) item.getItem();
			if(armor.armorType == EntityEquipmentSlot.HEAD)
			{
				((SteveModel)getEntityModel()).postRenderHead(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0, -0.62, 0);
				GL11.glScaled(0.9F, 0.9F, 0.9F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedHead.render(0.0625f);
			}
			else if(armor.armorType == EntityEquipmentSlot.CHEST)
			{
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderBody(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0, -0.95, 0);
				GL11.glScaled(0.9F, 0.9F, 0.9F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedBody.render(0.0625f);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderRightArm(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0.2, -0.65, 0);
				GL11.glScaled(0.9F, 0.9F, 0.9F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedRightArm.render(0.0625f);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderLeftArm(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(-0.2, -0.65, 0);
				GL11.glScaled(0.9F, 0.9F, 0.9F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedLeftArm.render(0.0625f);
				GL11.glPopMatrix();
			}
			else if(armor.armorType == EntityEquipmentSlot.LEGS)
			{
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderRightLeg(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0.1, -1.28, 0);
				GL11.glScaled(0.81F, 0.85F, 0.81F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedRightLeg.render(0.0625f);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderLeftLeg(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(-0.1, -1.28, 0);
				GL11.glScaled(0.81F, 0.85F, 0.81F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedLeftLeg.render(0.0625f);
				GL11.glPopMatrix();
			}
			else if(armor.armorType == EntityEquipmentSlot.FEET)
			{
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderRightLeg(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(0.1, -1.04, 0);
				GL11.glScaled(0.8F, 0.8F, 0.8F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedRightLeg.render(0.0625f);
				GL11.glPopMatrix();
				
				GL11.glPushMatrix();
				((SteveModel)getEntityModel()).postRenderLeftLeg(entitylivingbaseIn, partialTicks, false);
				GL11.glRotated(-100, 1, 0, 0);
				GL11.glRotated(180, 0, 1, 0);
				GL11.glTranslated(-0.1, -1.04, 0);
				GL11.glScaled(0.8F, 0.8F, 0.8F);
				Minecraft.getMinecraft().renderEngine.bindTexture(getArmorResource(entitylivingbaseIn, item, armor.armorType, null));
				modelArmor = net.minecraftforge.client.ForgeHooksClient.getArmorModel(entitylivingbaseIn, item, armor.armorType, modelArmor);
				modelArmor.bipedLeftLeg.render(0.0625f);
				GL11.glPopMatrix();
			}
		}
		
		GlStateManager.disableBlend();
		GlStateManager.enableCull();
		GL11.glPopMatrix();
	}
	
	public ResourceLocation getArmorResource(net.minecraft.entity.Entity entity, ItemStack stack, EntityEquipmentSlot slot, String type)
    {
		try
		{
	        ItemArmor item = (ItemArmor)stack.getItem();
	        String texture = item.getArmorMaterial().getName();
	        String domain = "minecraft";
	        int idx = texture.indexOf(':');
	        if (idx != -1)
	        {
	            domain = texture.substring(0, idx);
	            texture = texture.substring(idx + 1);
	        }
	        String s1 = String.format("%s:textures/models/armor/%s_layer_%d%s.png", domain, texture, (slot == EntityEquipmentSlot.LEGS ? 2 : 1), type == null ? "" : String.format("_%s", type));
	
	        s1 = net.minecraftforge.client.ForgeHooksClient.getArmorTexture(entity, stack, s1, slot, type);
	        ResourceLocation resourcelocation = (ResourceLocation)LayerArmorBase.ARMOR_TEXTURE_RES_MAP.get(s1);
	
	        if (resourcelocation == null)
	        {
	            resourcelocation = new ResourceLocation(s1);
	            LayerArmorBase.ARMOR_TEXTURE_RES_MAP.put(s1, resourcelocation);
	        }
	        return resourcelocation;
		}
		catch(Exception e) {e.printStackTrace();}
		return null;
    }
}

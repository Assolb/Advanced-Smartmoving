package gloomyfolken.hooklib.example;

import java.util.ArrayList;
import java.util.List;

import com.sm.proxy.ClientProxy;

import gloomyfolken.hooklib.asm.At;
import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.InjectionPoint;
import gloomyfolken.hooklib.asm.ReturnCondition;
import gloomyfolken.hooklib.asm.Shift;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.renderers.geo.GeoReplacedEntityRenderer;

public class TestHooks {
	
    @SideOnly(Side.CLIENT)
    @Hook(at = @At(point = InjectionPoint.HEAD), returnCondition = ReturnCondition.ALWAYS)
    public static void renderArmFirstPerson(ItemRenderer render, float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_) 
    {
    	try
    	{
	    	boolean flag = p_187456_3_ != EnumHandSide.LEFT;
	        float f = flag ? 1.0F : -1.0F;
	        float f1 = MathHelper.sqrt(p_187456_2_);
	        float f2 = -0.3F * MathHelper.sin(f1 * (float)Math.PI);
	        float f3 = 0.4F * MathHelper.sin(f1 * ((float)Math.PI * 2F));
	        float f4 = -0.4F * MathHelper.sin(p_187456_2_ * (float)Math.PI);
	        GlStateManager.translate(f * (f2 + 0.64000005F), f3 + -0.6F + p_187456_1_ * -0.6F, f4 + -0.71999997F);
	        GlStateManager.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
	        float f5 = MathHelper.sin(p_187456_2_ * p_187456_2_ * (float)Math.PI);
	        float f6 = MathHelper.sin(f1 * (float)Math.PI);
	        GlStateManager.rotate(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
	        AbstractClientPlayer abstractclientplayer = Minecraft.getMinecraft().player;
	        Minecraft.getMinecraft().getTextureManager().bindTexture(abstractclientplayer.getLocationSkin());
	        GlStateManager.translate(f * -1.0F, 3.6F, 3.5F);
	        GlStateManager.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
	        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
	        GlStateManager.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.translate(f * 5.6F, 0.0F, 0.0F);
	        String s = ((AbstractClientPlayer)Minecraft.getMinecraft().player).getSkinType();
	        RenderPlayer renderplayer = Minecraft.getMinecraft().getRenderManager().skinMap.get(s);
	        if(renderplayer == null)
	        {
	        	renderplayer = Minecraft.getMinecraft().getRenderManager().playerRenderer;
	        }
	        ModelPlayer model = renderplayer.getMainModel();
	        GlStateManager.disableCull();
	        if (flag)
	        {
	            GlStateManager.color(1.0F, 1.0F, 1.0F);
	            GlStateManager.enableBlend();
	            model.swingProgress = 0.0F;
	            model.isSneak = false;
	            model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, Minecraft.getMinecraft().player);
	            model.bipedRightArm.rotateAngleX = 0.0F;
	            model.bipedRightArm.render(0.0625F);
	            GlStateManager.disableBlend();
	        }
	        else
	        {
	        	GlStateManager.color(1.0F, 1.0F, 1.0F);
	            GlStateManager.enableBlend();
	            model.swingProgress = 0.0F;
	            model.isSneak = false;
	            model.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, Minecraft.getMinecraft().player);
	            model.bipedLeftArm.rotateAngleX = 0.0F;
	            model.bipedLeftArm.render(0.0625F);
	            GlStateManager.disableBlend();
	        }
	
	        GlStateManager.enableCull();
    	}
    	catch(Exception e) { e.printStackTrace(); }
    }
    
    @SideOnly(Side.CLIENT)
    @Hook(at = @At(point = InjectionPoint.HEAD), returnCondition = ReturnCondition.ALWAYS)
    public static void renderArm(ItemRenderer render, EnumHandSide p_187455_1_) 
    {
    	return;
    }
    
    @SideOnly(Side.CLIENT)
    @Hook(at = @At(point = InjectionPoint.HEAD), returnCondition = ReturnCondition.ALWAYS)
    public static Render getEntityRenderObject(RenderManager manager, Entity entityIn)
    {
    	 if (entityIn instanceof AbstractClientPlayer)
         {
             return ClientProxy.steveRenderer;
         }
         else
         {
             return manager.getEntityClassRenderObject(entityIn.getClass());
         }
    }
    
    @SideOnly(Side.CLIENT)
    @Hook(at = @At(point = InjectionPoint.METHOD_CALL, target = "setLivingAnimations", shift = Shift.INSTEAD))
    public static void doRender(GeoReplacedEntityRenderer<IAnimatable> e, EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks, @Hook.LocalVariable(19) AnimationEvent<IAnimatable> event)
    {
		List<Object> list = new ArrayList<Object>();
		list.addAll(event.getExtraData());
		list.add(entity);
    	event = new AnimationEvent<IAnimatable>(event.getAnimatable(), event.getLimbSwing(), event.getLimbSwingAmount(), event.getPartialTick(), event.isMoving(), list);
    	e.getGeoModelProvider().setLivingAnimations(event.getAnimatable(), e.getUniqueID(entity), event);
    }
}
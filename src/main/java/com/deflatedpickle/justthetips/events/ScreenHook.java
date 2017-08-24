package com.deflatedpickle.justthetips.events;

import com.deflatedpickle.justthetips.utils.TipUtil;
import net.minecraft.client.LoadingScreenRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.lang.reflect.Field;

public final class ScreenHook {
    private static final class Holder {
        private static final ScreenHook INSTANCE = new ScreenHook();
    }

    private TipRenderer renderer = new TipRenderer();

    private int width, height;

    private void onTick() {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.displayWidth != width || mc.displayHeight != height) {
            width = mc.displayWidth;
            height = mc.displayHeight;
            try {
                addLoadingScreenHook(mc);
            } catch (Exception e) {
                throw new RuntimeException("Unable to hook loading screen renderer", e);
            }
        }
    }

    private void addLoadingScreenHook(Minecraft mc) throws Exception {
        Field framebufferField = ReflectionHelper.findField(
                LoadingScreenRenderer.class, "field_146588_g", "framebuffer"
        );
        Framebuffer delegate = (Framebuffer) framebufferField.get(mc.loadingScreen);
        framebufferField.set(mc.loadingScreen, new ForwardingFramebuffer(delegate) {
            private final ScaledResolution resolution = new ScaledResolution(mc);

            @Override
            public void unbindFramebuffer() {
                renderer.render(mc, resolution.getScaledWidth(), resolution.getScaledHeight());
                super.unbindFramebuffer();
            }
        });
    }

    @SubscribeEvent
    public static void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            instance().onTick();
        }
    }

    @Mod.InstanceFactory
    public static ScreenHook instance() {
        return Holder.INSTANCE;
    }

    private static final class TipRenderer {
        private void render(Minecraft mc, int width, int height) {
            TipUtil.drawTips(mc.fontRendererObj);
        }
    }

    private static abstract class ForwardingFramebuffer extends Framebuffer {
        private final Framebuffer delegate;

        private boolean isConstructed;

        private ForwardingFramebuffer(Framebuffer delegate) {
            super(delegate.framebufferWidth, delegate.framebufferHeight, delegate.useDepth);
            this.delegate = delegate;
        }

        @Override
        public void createBindFramebuffer(int width, int height) {
            // skip createBindFramebuffer from our construction
            if (isConstructed) {
                delegate.createBindFramebuffer(width, height);
            } else {
                isConstructed = true;
            }
        }

        @Override
        public void deleteFramebuffer() {
            delegate.deleteFramebuffer();
        }

        @Override
        public void createFramebuffer(int width, int height) {
            delegate.createFramebuffer(width, height);
        }

        @Override
        public void setFramebufferFilter(int filter) {
            delegate.setFramebufferFilter(filter);
        }

        @Override
        public void checkFramebufferComplete() {
            delegate.checkFramebufferComplete();
        }

        @Override
        public void bindFramebufferTexture() {
            delegate.bindFramebufferTexture();
        }

        @Override
        public void unbindFramebufferTexture() {
            delegate.unbindFramebufferTexture();
        }

        @Override
        public void bindFramebuffer(boolean setViewport) {
            delegate.bindFramebuffer(setViewport);
        }

        @Override
        public void unbindFramebuffer() {
            delegate.unbindFramebuffer();
        }

        @Override
        public void setFramebufferColor(float red, float green, float blue, float alpha) {
            delegate.setFramebufferColor(red, green, blue, alpha);
        }

        @Override
        public void framebufferRender(int width, int height) {
            delegate.framebufferRender(width, height);
        }

        @Override
        public void framebufferRenderExt(int width, int height, boolean disableBlend) {
            delegate.framebufferRenderExt(width, height, disableBlend);
        }

        @Override
        public void framebufferClear() {
            delegate.framebufferClear();
        }

        @Override
        public boolean enableStencil() {
            return delegate.enableStencil();
        }

        @Override
        public boolean isStencilEnabled() {
            return delegate.isStencilEnabled();
        }
    }
}
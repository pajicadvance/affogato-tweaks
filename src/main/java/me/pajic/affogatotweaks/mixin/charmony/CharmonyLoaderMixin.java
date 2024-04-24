package me.pajic.affogatotweaks.mixin.charmony;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import svenhjol.charmony.base.CharmonyLoader;
import svenhjol.charmony.iface.ILog;

@Mixin(value = CharmonyLoader.class, remap = false)
public class CharmonyLoaderMixin {

    @WrapWithCondition(method = "run", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;info(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs1(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "run", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;debug(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs2(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "classLoader", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;info(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs3(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "classLoader", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;warn(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs4(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "classLoader", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;error(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs5(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "check", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;info(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs6(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }

    @WrapWithCondition(method = "check", at = @At(value = "INVOKE", target = "Lsvenhjol/charmony/iface/ILog;warn(Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Object;)V"))
    private boolean killLogs7(ILog instance, Class<?> aClass, String s, Object[] objects) {
        return false;
    }
}

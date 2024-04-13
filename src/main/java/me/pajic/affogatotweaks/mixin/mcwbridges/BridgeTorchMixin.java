package me.pajic.affogatotweaks.mixin.mcwbridges;

import net.kikoz.mcwbridges.objects.items.Bridge_Torch;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Bridge_Torch.class)
public class BridgeTorchMixin {

    // Changes bridge torch light level to 10
    @ModifyConstant(method = "lambda$new$0", constant = @Constant(intValue = 15))
    private static int modifyBridgeTorchLightLevel(int constant) {
        return 10;
    }
}

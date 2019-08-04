package club.sk1er.bossbarcustomizer.asm;

import club.sk1er.bossbarcustomizer.tweaker.transform.ITransformer;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class GuiIngameTransformer implements ITransformer {

    @Override
    public String[] getClassName() {
        return new String[]{"net.minecraft.client.gui.GuiIngame"};
    }

    @Override
    public void transform(ClassNode classNode, String name) {
        for (MethodNode methodNode : classNode.methods) {
            String methodName = mapMethodName(classNode, methodNode);

            if (methodName.equalsIgnoreCase("renderBossHealth") || methodName.equalsIgnoreCase("func_73828_d")) {
                methodNode.instructions.clear();
                methodNode.localVariables.clear();
                methodNode.instructions.insert(replaceCode());
            }
        }
    }

    private InsnList replaceCode() {
        InsnList list = new InsnList();
        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "club/sk1er/bossbarcustomizer/hook/GuiIngameHook", "renderBossbarHealth", "()V", false));
        list.add(new InsnNode(Opcodes.RETURN));
        return list;
    }
}

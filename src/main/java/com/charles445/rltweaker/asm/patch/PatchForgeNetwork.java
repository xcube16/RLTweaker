package com.charles445.rltweaker.asm.patch;

import com.charles445.rltweaker.asm.Patch;
import com.charles445.rltweaker.asm.PatchResult;
import com.charles445.rltweaker.asm.Patcher;
import com.charles445.rltweaker.asm.RLTweakerASM;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import com.charles445.rltweaker.asm.util.TransformUtil;

import static com.charles445.rltweaker.asm.helper.PatchHelper.*;

@Patcher(name = "Patch Forge Network")
public class PatchForgeNetwork
{
	@Patch(target = "net.minecraftforge.fml.common.network.simpleimpl.SimpleChannelHandlerWrapper")
	public static PatchResult PatchForgeNetwork(RLTweakerASM tweaker, ClassNode clazzNode) {
		MethodNode m_channelRead0 = findMethod(clazzNode, "channelRead0");
		
		if(m_channelRead0 == null)
			throw new RuntimeException("Couldn't findMethod channelRead0");
		
		LocalVariableNode lvn_context = TransformUtil.findLocalVariableWithName(m_channelRead0, "context");
		
		if(lvn_context == null)
			throw new RuntimeException("Couldn't findLocalVariableWithName context");
		
		AbstractInsnNode anchor = first(m_channelRead0);
		
		while(anchor != null)
		{
			if(anchor.getType() == AbstractInsnNode.VAR_INSN)
			{
				VarInsnNode vAnchor = (VarInsnNode)anchor;
				if(vAnchor.getOpcode() == Opcodes.ASTORE && vAnchor.var == lvn_context.index)
				{
					break;
				}
			}
			
			anchor = anchor.getNext();
		}
		
		if(anchor == null)
			throw new RuntimeException("Couldn't find 'context' astore");
		
		//Anchor has our astore
		//We also need msg
		LocalVariableNode lvn_msg = TransformUtil.findLocalVariableWithName(m_channelRead0, "msg");
		
		if(lvn_msg == null)
			throw new RuntimeException("Couldn't findLocalVariableWithName msg");
		
		//Create hook...
		InsnList insert = new InsnList();
		insert.add(new VarInsnNode(Opcodes.ALOAD, lvn_msg.index));
		insert.add(new VarInsnNode(Opcodes.ALOAD, lvn_context.index));
		//stack has msg, context
		insert.add(new MethodInsnNode(
				Opcodes.INVOKESTATIC,
				"com/charles445/rltweaker/hook/HookForge",
				"receiveMessage",
				"(Lnet/minecraftforge/fml/common/network/simpleimpl/IMessage;Lnet/minecraftforge/fml/common/network/simpleimpl/MessageContext;)V",
				false));
		
		m_channelRead0.instructions.insert(anchor, insert);
		
		return PatchResult.MAXS;
	}
}

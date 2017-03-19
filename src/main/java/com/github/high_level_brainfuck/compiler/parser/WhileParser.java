package com.github.high_level_brainfuck.compiler.parser;

import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;
import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;
import com.github.high_level_brainfuck.compiler.instructions.WhileInstruction;

public class WhileParser {
	public boolean isWhile(BfGenLine bfGenLine) {
		return bfGenLine.getCode().startsWith(BfGenKeywords.WHILE);
	}
	
	public Instruction parse(BfGenLine bfGenLine, Instruction parent, InstructionRoot instructionRoot) 
			throws CompileException {
		
		String code = bfGenLine.getCode();
		String[] codeSplit = code.split(" ");
		VarInstruction var = null;
		
		if (codeSplit.length == 2) {
			String varName = codeSplit[1];
			var = instructionRoot.getVarByName(varName);
			
			if (var == null) {
				throw new CompileException("[While] Unknown var", bfGenLine.getLineNum());
			}
		} else {
			throw CompileException.newSyntaxError(bfGenLine.getLineNum());
		}
		
		return new WhileInstruction(parent, var);
	}
}

package me.dartcat25.mods.sodiumss.mixin;

import java.util.function.IntFunction;

import org.lwjgl.opengl.GL20C;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.jellysquid.mods.sodium.client.gl.GlObject;
import me.jellysquid.mods.sodium.client.gl.shader.uniform.GlUniform;

@Mixin(me.jellysquid.mods.sodium.client.gl.shader.GlProgram.class)
public class MixinGLProgram extends GlObject {
    
    /**
     * @author DartCat25
     * @reason Remake shader loader
     * 
     */
    @Overwrite(remap = false)
    public <U extends GlUniform<?>> U bindUniform(String name, IntFunction<U> factory) {
        int index = GL20C.glGetUniformLocation(this.handle(), name);

        if (index == GL20C.GL_INVALID_OPERATION)
            throw new NullPointerException("No uniform exists with name (invalid operation): " + name);
        else if (index == GL20C.GL_INVALID_VALUE)
            throw new NullPointerException("No uniform exists with name (invalid value): " + name);

        return factory.apply(index);
    }
}

package me.dartcat25.mods.sodiumss.mixin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import me.dartcat25.mods.sodiumss.ResourceLoader;
import me.jellysquid.mods.sodium.client.gl.shader.GlShader;
import me.jellysquid.mods.sodium.client.gl.shader.ShaderConstants;
import me.jellysquid.mods.sodium.client.gl.shader.ShaderParser;
import me.jellysquid.mods.sodium.client.gl.shader.ShaderType;
import net.minecraft.util.Identifier;

@Mixin(me.jellysquid.mods.sodium.client.gl.shader.ShaderLoader.class)
public class MixinShaderLoader {
    private static final Logger LOGGER = LogManager.getLogger(MixinShaderLoader.class);

    /**
     * @author DartCat25
     * @reason Remake shader loader
     * 
     */
    @Overwrite
    public static GlShader loadShader(ShaderType type, Identifier name, ShaderConstants constants) {
        try
        {
            return new GlShader(type, name, ShaderParser.parseShader(getShaderSource(name), constants));
        }
        catch (IllegalArgumentException e)
        {
            LOGGER.warn("Failed to load shader, loading built-in pack.");
            String path = String.format("/assets/%s/shaders/%s", name.getNamespace(), name.getPath());

            try (InputStream in = MixinShaderLoader.class.getResourceAsStream(path)) {
                if (in == null) {
                    throw new RuntimeException("Shader not found: " + path);
                }
                return new GlShader(type, name, ShaderParser.parseShader(IOUtils.toString(in, StandardCharsets.UTF_8), constants));
            } catch (IOException e2) {
                throw new RuntimeException("Failed to read shader source for " + path, e2);
            }

        }
    }

    /**
     * @author DartCat25
     * @reason Remake shader loader
     * 
     */
    @Overwrite
    public static String getShaderSource(Identifier name) {
        String path = String.format("shaders/%s", name.getPath());
        return ResourceLoader.getShader(new Identifier("sodium", path));
    }
}

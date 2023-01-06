package me.dartcat25.mods.sodiumss;

import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import org.apache.commons.io.IOUtils;

import net.fabricmc.fabric.api.resource.SimpleResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;

public class ResourceLoader implements SimpleResourceReloadListener {
    private static Map<Identifier, List<Resource>> shaders;

    @Override
    public Identifier getFabricId() {
        return new Identifier("sodium", "shaders");
    }

    @Override
    public CompletableFuture<Void> load(ResourceManager manager, Profiler profiler, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            shaders = null;
            shaders = manager.findAllResources("shaders", path -> true);
            return null;
          }, executor);
    }

    @Override
    public CompletableFuture<Void> apply(Object data, ResourceManager manager, Profiler profiler, Executor executor) {
        return CompletableFuture.runAsync(() -> {
        }, executor);
    }

    public static String getShader(Identifier id)
    {
        List<Resource> resources = shaders.get(id);
        
        if (resources == null)
            throw new RuntimeException("Unable to find shader: " + id.getPath());
        
        try (InputStream in = resources.get(resources.size() - 1).getInputStream())
        {
            return IOUtils.toString(in, StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            throw new RuntimeException("Unable to load shader: " + id.getPath(), e);
        }
    }
}

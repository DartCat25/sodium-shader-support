## Sodium Shader Support (1.19.4)
Sodium, as good render optimization mod, changes vanilla block shaders to its own but it didn't provide changing these shaders throught resource packs.

This mod provides changes of sodium shader by resource pack.
## Usage
Load this mod with [Sodium](https://github.com/CaffeineMC/sodium-fabric) and set new namespace `sodium`.

Sodium shaders are in `assets/sodium/shaders/blocks/` and named `block_layer_opaque`.

[Shader's code](https://github.com/CaffeineMC/sodium-fabric/tree/1.19.3/dev/src/main/resources/assets/sodium/shaders/blocks) is vanilla-like but with changed names of in/out/uniform values names.

## Features 
- [x] As first iteration of this mod it can only change these files by resource pack.
- [ ] Second iteration will setting vanilla values and adding new uniforms by json like in vanilla.

###### First modding experience. DartCat25.

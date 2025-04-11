[![Automatic version updates](https://github.com/zopencommunity/LuaJITport/actions/workflows/bump.yml/badge.svg)](https://github.com/zopencommunity/LuaJITport/actions/workflows/bump.yml)

# LuaJIT

LuaJIT — a Just-In-Time Compiler for Lua.

## Current Dev State

- does not build yet. It gives a SIGKILL when trying to create a header
  file because it is trying to run the minilua, which did not build.
  You can get slightly farther if you set HOSTLUA in `src/Makefile` to
  a fully-qualified installed lua, but then you will fail because there
  is no `bitop` installed.
  Probably both paths should be explored, but generating a proper minilua
  seems a good start.

## References courtesy of Ilya Leoshkevich

- [Linux on Z ABI](https://github.com/IBM/s390x-abi)

To learn a bit about the Lua JIT:

- Mike Pall explains why the dynamic assembler [dynasm](https://luajit.org/dynasm.html) is 
[very efficient](https://web.archive.org/web/20141001203737/https://article.gmane.org/gmane.comp.lang.lua.general/75426)
  making it viable to not use the full JIT capability, but just use the interpreter mode.
- YouTube video on [specific interesting things :)](https://www.youtube.com/watch?v=EaLboOUG9VQ). 
  It focuses only on a handful of specific things, but lets one get a feel of the code base. 
  Among other things, it explains the recording mechanism and how it helps switching 
  from interpreter mode to JIT mode.
- YouTube video on [Lua JIT Internals](https://pwner.gg/blog/2022-08-22-lua-jit-intro).
  It's from a security minded person, but is there a better way to learn things 
  than to break them? :-)

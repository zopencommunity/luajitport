# Comments on patches

These patches are very much incomplete.

They are copied for the most part from: https://github.com/LuaJIT/LuaJIT/pull/631/files

## Work to be done

- Create an assembler that will process some form of _extended_ HLASM
  that is easier to work with across the Linux on Z and z/OS teams.
  Right now, this is just a simple script `bin/as` until we figure out
  how much we want to generate true HLASM and how much we want to
  generate 'gas' syntax assembler and just transform it to HLASM.
- Create a z/OS version of the `dasm_xxx` routines in `dasm_s390x.h`. 
  These routines may be able to be common with Linux on Z where it is
  strictly instruction-set logic, but for things like linkage conventions
  with OS C code, they will need to be different, including stack layout.
- I only did a quick scan of `dasm_s390x.lua` which appears to be 
  common between Linux on Z and z/OS because they are restricted to
  the instruction set and the actual printing of the assembler is done
  using `dasm_s390x.h`.
- The generation of the assembler has to be different for Linux on Z and
  z/OS because Linux uses `gas` assembler syntax whereas z/OS uses
  `HLASM` assembler syntax. This assembler generation is done in
  `src/host/buildvm_asm.c` which will require significant changes to build.
- `src/lj_ccall.h` needs to be updated based on the z/OS 64-bit C linkage convention.
- `src/lj_err.c` is likely going to need to change - my presumption is that CFA is the
  frame pointer and how much it needs to be adjusted, but that may be incorrect.
- `src/lj_frame.h` will need to change since the stack frame layout is different for
  Linux and z/OS
- `src/vm_s390x.dasc` will need to change, especially around calling conventions and
  stack assumptions.

- `src/lj_ccall.c` may be common but requires more investigation, in particular how
  `onstack` works.
- `src/lj_ccallback.c` may be common but requires analysis of how floating point
  calling convention differs between Linux and z/OS.
- `src/lj_target_s390x.h` may be common but requires more investigation, in particular
  if the calling convention register values can be used.

- `src/lj_mcode.c` removes `lj_mcode_sync` function, which isn't used by z/OS.
  If it needs to be added back in, then __clear_cache equivalent will be necessary.
- `src/Makefile` is a common change.
- `src/host/buildvm.c` is a common change.
- `src/jit/bcsave.lua` is a common change.
- `src/jit/dis_s390x.lua` is a common change (no support for the JIT on S390X).
- `src/lj_target.h` is a common change.
- `src/lib_jit.c` is a common change.
- `src/lj_arch.h` is a common change.
- `src/lj_asm.c` is a common change.

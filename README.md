# AspectJ and Immutables

This repo demonstrates a way of handling the combination of [AspectJ](https://www.mojohaus.org/aspectj-maven-plugin/index.html) and [Immutables](https://immutables.github.io) annotation processors.

It consists of four modules:

1. [aspectj](aspectj) -- an implementation of a cross-domain concern, @LogExecutionTime, using AspectJ
1. [immutables](immutables) -- an implementation of an immutable model, using Immutables
1. [mixed-service](mixed-service) -- an attempt to use @LogExecutionTime annotation when a module has an immutable model, using Immutables
1. [pure-service](pure-service) -- an attempt to use @LogExecutionTime annotation and importing another module that uses Immutables

The [pure-service](pure-service) module can be compiled, but the [mixed-service](mixed-service) needs extra configuration of aspectj-maven-plugin excluding immutables from AJC. See [this PR](https://github.com/temirov/aspectj-immutables/pull/1) for the changes.

# AspectJ and Immutables

This repo demonstrates an issue with the combination of AspectJ and Immutables annotation processors.

It contains of four modules:

1. [aspectj](aspectj) -- an implementation of a cross-domain concern, @LogExecutionTime, using AspectJ
1. [immutables](immutables) -- an implementation of an immutable model, using Immutables
1. [mixed-service](mixed-service) -- an attempt to use @LogExecutionTime annotation when a module has an immutable model, using Immutables
1. [pure-service](pure-service) -- an attempt to use @LogExecutionTime annotation and importing another module that uses Immutables

The [pure-service](pure-service) module can be compiled, but the [mixed-service](mixed-service) can not. Running aspectj-maven-plugin plugin in [mixed-service](mixed-service) is currently disabled through configuration `<skip>true</skip>`. Enabling it demonstrates the issue.
